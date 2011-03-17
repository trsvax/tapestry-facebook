package com.trsvax.tapestry.facebook.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * The most efficient way to load the SDK in your site is to load it asynchronously so it does not block loading other
 * elements of your page. This is particularly important to ensure fast page loads for users and SEO robots/spiders.
 * This is the component that does that
 * <p/>
 *
 * @see <a href="http://developers.facebook.com/docs/reference/javascript/">JavaScript SDK</a>
 */
public class AsyncInit {

	@Parameter(required = true)
	private String appId;

	@Parameter(value = "literal:true")
	private boolean cookie;

	@Parameter(value = "literal:false")
	private boolean logging;

	@Parameter(value = "literal:true")
	private boolean status;

	@Parameter(value = "literal:true")
	private boolean xfbml;

	/**
	 * Used to include scripting code in the rendered page.
	 */
	@Environmental
	private JavaScriptSupport javascriptSupport;

	@BeginRender
	void beginRender(MarkupWriter writer) {

		writer.element("div", "id", "fb-root");
		writer.end();
		javascriptSupport.addScript(InitializationPriority.IMMEDIATE,
				"window.fbAsyncInit = function() {" +
					"FB.init({appId: '%s', status: %s, cookie: %s, xfbml: %s, logging: %s});" +
				"};", appId, status, cookie, xfbml, logging);

		javascriptSupport.addScript(InitializationPriority.IMMEDIATE, "(function() {" +
				"var e = document.createElement('script');" +
				"e.async = true;" +
				"e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';" +
				"document.getElementById('fb-root').appendChild(e);" +
				"}());");

	}

	/**
	 * Prevent the body from rendering.
	 */
	boolean beforeRenderBody() {
		return false;
	}
}

