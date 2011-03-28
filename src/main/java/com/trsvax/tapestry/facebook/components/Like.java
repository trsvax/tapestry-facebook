//Copyright [2011] [Barry Books]

//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at

//       http://www.apache.org/licenses/LICENSE-2.0

//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.trsvax.tapestry.facebook.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * @author bfb Facebook XFBML Like component
 * @see <a href="http://developers.facebook.com/docs/reference/plugins/like/">Like</a>
 * @see <a href="http://developers.facebook.com/docs/reference/plugins/like-box/">Like Box</a>
 */
@SupportsInformalParameters
public class Like
{
	@Parameter(value = "literal:false")
	private boolean box;
	
	// This could/should be events like edge.create edge.remove to receive callbacks from
	// Facebook when a user click the like button so an example would be:
	// (value = "edge.create,edge.remove")
	// I like literal prefix since I want this in tml other then code
	@Parameter(defaultPrefix = "literal")
	private String events;

	@Inject
	private ComponentResources resources;
	
	@Environmental
	private JavaScriptSupport javaScriptSupport;

	@BeginRender
	void beginRender(MarkupWriter writer)
	{
		if (box)
			writer.element("fb:like-box");
		else
			writer.element("fb:like");
	
		resources.renderInformalParameters(writer);
		
		writer.end();

		if (events == null || events.length() == 0)
		{
			return;
		}
		
		// This should be the same for Like and Like Box elements
		ComponentResources container = resources.getContainerResources();
		
		for (String event : events.split(","))
		{
			Link link = container.createEventLink(eventConv(event));
			javaScriptSupport.addScript(
					InitializationPriority.NORMAL,
					"FB.Event.subscribe('%s', "
						+ "function(response) {"
							+ "Tapestry.debug('Ajax request called from a callback-like facebook event...');"
							+ "Tapestry.ajaxRequest('%s', {"
								+ "method : 'get',"
								+ "parameters : "
								+ "{"
									+ "url : response"
								+ "}"
							+ "});"
						+ "});\n",
					event, link.toURI());
			
			javaScriptSupport.addScript(
					InitializationPriority.NORMAL,
					"Tapestry.debug('Event is %s and callback link created as %s');",
					event, link.toURI());
		}
		
	}
	
	/*
	 * Code taken from ascandroli@gituhub fork...
	 */
	private final String eventConv(String event)
	{
		int length = event.length();
		
		StringBuilder tapestryEvent = new StringBuilder(length);

		boolean capitalizeNext = false;

		for (int i = 0; i < length; i++) {
			char ch = event.charAt(i);

			if (ch == '.') {
				capitalizeNext = true;
			} else if (capitalizeNext) {
				tapestryEvent.append(Character.toTitleCase(ch));
				capitalizeNext = false;
			} else {
				tapestryEvent.append(ch);
			}
		}

		return tapestryEvent.toString();
	}
}
