package com.trsvax.tapestry.facebook.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * @author bfb
 *         Facebook XFBML ShareButton component
 *         replace kind'a by the like button
 */
@SupportsInformalParameters
public class ShareButton {
	@Inject
	private ComponentResources componentResources;

	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.element("fb:share-button");
		componentResources.renderInformalParameters(writer);
		writer.end();
	}
}
