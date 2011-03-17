package com.trsvax.tapestry.facebook.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import java.io.StringWriter;

public class NewsFeed {

	@Parameter
	private String name;

	@Parameter
	private String link;

	@Parameter
	private String picture;

	@Parameter
	private String caption;

	@Parameter
	private String description;

	@Parameter
	private String message;

	@Environmental
	private JavaScriptSupport javaScriptSupport;

	@Inject
	private ComponentResources resources;

	@BeginRender
	void beginRender(MarkupWriter writer) {
		String id = javaScriptSupport.allocateClientId(resources);
		writer.element("a", "id", id, "href", "#");
		writer.end();

		javaScriptSupport.addScript(init(id));
	}

	String init(String id) {

		StringWriter init = new StringWriter();

		init.append(String.format(
				"Event.observe( document.getElementById('%s'), 'click',", id));
		init.append("function(event){");
		init.append("FB.ui(");
		init.append("{");
		init.append("method: 'feed',");
		if (name != null) {
			init.append(String.format("name: '%s',", name));
		}
		if (link != null) {
			init.append(String.format("link: '%s',", link));
		}
		if (picture != null) {
			init.append(String.format("picture: '%s',", picture));
		}
		if (caption != null) {
			init.append(String.format("caption: '%s',", caption));
		}
		if (description != null) {
			init.append(String.format("description: '%s',", description));
		}
		if (message != null) {
			init.append(String.format("message: '%s'", message));
		}
		init.append("},");
		init.append("function(response) {");

		init.append(" }");

		init.append(");");
		init.append("}");
		init.append(");\n");
		return init.toString();
	}
}
