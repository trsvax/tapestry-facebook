package com.trsvax.tapestry.facebook.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import com.trsvax.tapestry.facebook.services.FBAsyncSupport;

/**
 * @author bfb
 * Facebook XFBML Like Box component
 * @see <a href="http://developers.facebook.com/docs/reference/plugins/like-box/">Like Box</a>
 *
 */
@SupportsInformalParameters
public class LikeBox {

	@Parameter(value = "literal:edge.create,edge.remove")
	private String events;
	
	@Environmental
	private FBAsyncSupport fbAsync;

	@Inject
	private ComponentResources resources;

	@BeginRender
	void beginRender(MarkupWriter writer) {
		fbAsync.render();
		writer.element("fb:like-box");
		resources.renderInformalParameters(writer);
		writer.end();
		
		if ( events == null || events.length() == 0 ) {
			return;
		}
		for ( String e : events.split(",")) {
			fbAsync.subscribe(e,resources);
		}

	}

}
