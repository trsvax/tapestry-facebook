package com.trsvax.tapestry.facebook.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * @author bfb
 *         Facebook XFBML Activity component
 * @see <a href="http://developers.facebook.com/docs/reference/plugins/activity/">Activity</a>
 */
@SupportsInformalParameters
public class Activity {

	@Inject
	private ComponentResources componentResources;

	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("fb:activity");
		componentResources.renderInformalParameters(writer);
		writer.end();
	}
}
