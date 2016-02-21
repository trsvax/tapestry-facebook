
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

package com.trsvax.facebook.services;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.dom.Document;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.slf4j.Logger;

import com.trsvax.facebook.FBInit;
import com.trsvax.facebook.environment.FacebookEnvironment;
import com.trsvax.facebook.opengraph.Tags;

public class FBAsyncSupportImpl implements FBAsyncSupport {
	private final Logger logger;
	private final RequestGlobals requestGlobals;
	private final String context;
	private final JavaScriptSupport javaScriptSupport;
	
	private FBInit fbinit;
	private Tags tags;
	private boolean render = false;
	private StringWriter initWriter = new StringWriter();

	private Map<String, Set<String>> events = new HashMap<String, Set<String>>();

	public FBAsyncSupportImpl(Logger logger, RequestGlobals requestGlobals,
			JavaScriptSupport javascriptSupport) {
		this.logger = logger;
		this.requestGlobals = requestGlobals;
		this.javaScriptSupport = javascriptSupport;
		String c = requestGlobals.getHTTPServletRequest().getContextPath();
		if ( c == null ) {
			c = "/";
		}
		if ( ! c.endsWith("/")) {
			c+="/";
		}
		context = c;
	}
	
	public void subscribe(String event,ComponentResources resources) {
		render();
		String containerID = resources.getContainerResources().getCompleteId() + ":" + fb2tap(event) ;
		//String url = resources.createEventLink(event).toAbsoluteURI();
		String url = resources.getContainerResources().createEventLink(fb2tap(event)).toAbsoluteURI();
		url = url.replace(":8080", "");
		Set<String> s = events.get(event);
		if ( s == null ) {
			s = new HashSet<String>();
			events.put(event,s);
		}
		//s.add(containerID);
		s.add(url);
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

	public void updateDocument(FacebookEnvironment facebookEnvironment, Document document) {
		if ( ! facebookEnvironment.isLoadJS() ) {
			return;
		}
		StringBuilder builder = new StringBuilder();
		builder.append("window.fbAsyncInit = function() {\n");
		//builder.append("alert('init');\n");
		String init = String.format("FB.init({\n" +
				"appId:'%s',\n" +
				"channelUrl:'%s',\n" +
				"status:true,\n" +
				"cookie:true,\n" +
				"xfbml:true\n" +
				"});\n" +
				"// Additional initialization code here\n", "155760164479254"
				,"//judypaul.com/studio/channel"
				);
		builder.append(init);
		for ( String script : facebookEnvironment.getInitializerCalls()) {
			builder.append(script);
		}
		builder.append("};\n");
		
		if ( requestGlobals.getRequest().isXHR()) {
			javaScriptSupport.addScript("window.fbAsyncInit();");
			return;
		}
		if (tags != null) {
			Element head = findOrCreateElement(document.getRootElement(),
					"head", false);
			renderMeta(head);
		}

		Element body = findOrCreateElement(document.getRootElement(), "body",false);
		if ( body != null ) {
			body.elementAt(0, "div", "id", "fb-root");
			Element script = body.elementAt(1, "script");
			builder.append(getHTML5());
			script.raw(builder.toString());
			//script(script);
		}
		
	}
	
	String getXFBML() {
		return " (function(d){ \n" +
				" var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];\n" +
				" if (d.getElementById(id)) {return;}\n" +
				" js = d.createElement('script'); js.id = id; js.async = true;\n" +
				//" js.src = '//connect.facebook.net/en_US/all.js';\n" +
				" js.src = '//connect.facebook.net/en_US/all.js#xfbml=1';\n" +
				" ref.parentNode.insertBefore(js, ref);\n" +
				" }(document));";
	}
	
	String getHTML5() {
		return "(function(d, s, id) {\n" +
			  "var js, fjs = d.getElementsByTagName(s)[0];\n" +
			  "if (d.getElementById(id)) return;\n" +
			  "js = d.createElement(s); js.id = id;\n" +
			  //"js.src = \"//connect.facebook.net/en_US/all.js#xfbml=1&appId=193102360713707\";\n" +
			  "js.src = \"//connect.facebook.net/en_US/all.js\";\n" +
			  "fjs.parentNode.insertBefore(js, fjs);\n" +
			"}(document, 'script', 'facebook-jssdk'));\n";
	}



	private Element findOrCreateElement(Element root, String childElement,boolean atTop) {
		if ( root == null ) {
			return null;
		}
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
				"Tapestry.ajaxRequest('%s', {" +
					"method : 'get'," +
					"parameters : {" +
					"url : response }" +
					"});" +
				"});\n", event,url);
	}
	
}
