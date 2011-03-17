package com.trsvax.tapestry.facebook.components;

import com.trsvax.tapestry.facebook.FacebookUtils;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * @author bfb
 *         Facebook XFBML Like component
 * @see <a href="http://developers.facebook.com/docs/reference/plugins/like/">Like</a>
 */
@SupportsInformalParameters
public class Like {

	private static String subscribeScript = "FB.Event.subscribe('%s', " +
			"function(response){" +
//					"alert(response);" +
				"Tapestry.ajaxRequest('%s', {" +
					"method : 'get'," +
					"parameters : {" +
					"url : response }" +
				"});" +
			"});\n";


	@Parameter(value = "literal:edge.create,edge.remove")
	private String events;

	@Inject
	private ComponentResources resources;


	/**
	 * Used to include scripting code in the rendered page.
	 */
	@Environmental
	private JavaScriptSupport javascriptSupport;

	@BeginRender
	void beginRender(MarkupWriter writer) {

		writer.element("fb:like");
		resources.renderInformalParameters(writer);
		writer.end();

		if (events == null || events.length() == 0) {
			return;
		}

		ComponentResources containerResources = resources.getContainerResources();

		for (String event : events.split(",")) {
			Link link = containerResources.createEventLink(FacebookUtils.fb2tap(event));
			javascriptSupport.addScript(subscribeScript, event, link.toURI());
		}

	}
}
