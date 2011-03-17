package com.trsvax.tapestry.facebook.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * @author bfb
 * Facebook XFBML LoginButton component
 * @see <a href="http://developers.facebook.com/docs/reference/plugins/login/">Login Button</a>
 *
 */
@SupportsInformalParameters
public class LoginButton {
	
	@Inject
	private ComponentResources componentResources;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("fb:login-button");
		componentResources.renderInformalParameters(writer);
		writer.end();
	}


}
