
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

package com.trsvax.tapestry.facebook.services;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.slf4j.Logger;

import com.trsvax.tapestry.facebook.FBInit;
import com.trsvax.tapestry.facebook.opengraph.Tags;

public class FBAsyncSupportImpl implements FBAsyncSupport {
	private final Logger logger;
	private FBInit fbinit;
	private Tags tags;
	private boolean render = false;
	private StringWriter initWriter = new StringWriter();

	private Map<String, Set<String>> events = new HashMap<String, Set<String>>();

	public FBAsyncSupportImpl(Logger logger) {
		this.logger = logger;
		
	}
	
	public void subscribe(String event,ComponentResources resources) {
		render();
		String containerID = resources.getContainerResources().getCompleteId() + ":" + fb2tap(event) ;
		Set<String> s = events.get(event);
		if ( s == null ) {
			s = new HashSet<String>();
			events.put(event,s);
		}
		s.add(containerID);
	}
	
	public void init(FBInit fbinit) {
		render();
		this.fbinit = fbinit;
	}
	
	public void init(String init) {
		render();
		initWriter.append(init);
		initWriter.append("\n");
	}

	public void meta(Tags tags) {
		render();
		this.tags = tags;
	}
	
	public void render() {
		render = true;
	}

	public void updateDocument(Document document) {
		if ( !render ) {
			return;
		}
		if (tags != null) {
			Element head = findOrCreateElement(document.getRootElement(),
					"head", false);
			renderMeta(head);
		}

		Element body = findOrCreateElement(document.getRootElement(), "body",
				false);
		body.elementAt(0, "div", "id", "fb-root");
		Element script = body.elementAt(1, "script");
		//script.raw(script());
		script(script);
		
	}



	private Element findOrCreateElement(Element root, String childElement,
			boolean atTop) {
		Element container = root.find(childElement);
		// Create the element is it is missing.
		if (container == null)
			container = atTop ? root.elementAt(0, childElement) : root
					.element(childElement);

		return container;
	}



	void script(Element script) {
		script.raw("window.fbAsyncInit = function() {\n");
		script.raw("FB.init({" + initString() + "});\n");
		script.raw(initWriter.toString());
				
		for ( Entry<String, Set<String>> e : events.entrySet()) {
			String event = e.getKey();
			for ( String url : e.getValue()) {
				script.raw(subscribe(event, url));
			}
			
			
		}				
		script.raw("};\n");
						
		script.raw(
		"(function() {\n"
		+ "var e = document.createElement('script');\n"
		+ "e.type = 'text/javascript';\n"
		+ "e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';\n"
		+ "e.async = true;\n"
		+ "document.getElementById('fb-root').appendChild(e);"
		+ "}());\n");
	}

	void renderMeta(Element head) {
		if (tags == null) {
			return;
		}
		if (tags.getTitle() != null) {
			head.element("meta", "property", "og:title", "content",
					tags.getTitle());
		}
		if (tags.getType() != null) {
			head.element("meta", "property", "og:type", "content",
					tags.getType());
		}
		if (tags.getImageURL() != null) {
			head.element("meta", "property", "og:image", "content",
					tags.getImageURL());
		}
		if (tags.getUrl() != null) {
			head.element("meta", "property", "og:url", "content", tags.getUrl());
		}
		if (tags.getSiteName() != null) {
			head.element("meta", "property", "og:site_name", "content",
					tags.getSiteName());
		}
		if (tags.getApplicationID() != null) {
			head.element("meta", "property", "fb:app_id", "content",
					tags.getApplicationID());
		}
		if (tags.getAdmins() != null) {
			head.element("meta", "property", "fb:admins", "content",
					tags.getAdmins());
		}
	}


	
	String initString() {		
		String sep = "";
		StringWriter s = new StringWriter();
		//s.append("({");
		if ( fbinit.getAppID() != null ) {
			s.append(sep);
			sep = ",";
			s.append("appId: '");
			s.append(fbinit.getAppID());
			s.append("'");
		}
		if ( fbinit.getCookie() != null ) {
			s.append(sep);
			sep = ",";
			s.append("cookie: " + fbinit.getCookie());
		}
		if ( fbinit.getLogging() != null ) {
			s.append(sep);
			sep = ",";
			s.append("logging: " + fbinit.getLogging());
		}
		if ( fbinit.getStatus() != null ) {
			s.append(sep);
			sep = ",";
			s.append("status: " + fbinit.getStatus());
		}
		if ( fbinit.getXfbml() != null ) {
			s.append(sep);
			sep = ",";
			s.append("xfbml: " + fbinit.getXfbml());
		}
		
		return s.toString();
		
	}
	
	String fb2tap(String event) {
		String t = null;
		
		String[] n = event.split("\\.");
		boolean first = true;
		for ( String s : n) {
			if ( first ) {
				t = s;
				first = false;
				continue;
			} 
			t += s.substring(0, 1).toUpperCase();
			t += s.substring(1);
		}
		
		return t;
	}
	
	String subscribe(String event, String url) {
		return String.format("FB.Event.subscribe('%s', function(response){" +
				//"alert(response);" +
				"Tapestry.ajaxRequest('/%s', {" +
					"method : 'get'," +
					"parameters : {" +
					"url : response }" +
					"});" +
				"});\n", event,url);
	}
	
}
