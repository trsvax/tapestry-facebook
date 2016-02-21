
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

package com.trsvax.facebook.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SupportsInformalParameters;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.trsvax.facebook.ColorScheme;
import com.trsvax.facebook.environment.FacebookEnvironment;
import com.trsvax.facebook.services.FBAsyncSupport;


/**
 * @author bfb
 * Facebook XFBML Comments component
 * @see <a href="http://developers.facebook.com/docs/reference/plugins/comments/">Comments</a>
 *
 */
public class Comments {
	
	/**
	 * the URL for this Comments plugin. News feed stories on Facebook will link to this URL
	 */
	@Parameter
	private String href;
	
	/**
	 * the width of the plugin in pixels. Minimum recommended width: 400px.
	 */
	@Parameter
	private Integer width;
	
	/**
	 *  the color scheme for the plugin. Options: 'light', 'dark'
	 */
	@Parameter
	private ColorScheme colorScheme;
	
	/**
	 * the number of comments to show by default. Default: 10. Minimum: 1
	 */
	@Parameter
	private Integer numPosts;
	
	/**
	 * whether to show the mobile-optimized version. Default: auto-detect.
	 */
	private String mobile;
	
	@Environmental
	private FacebookEnvironment environment;

	@BeginRender
	void beginRender(MarkupWriter writer) {
		environment.setLoadJS(true);
		Element  element = writer.element("div");
		element.addClassName("fb-comments");
		if ( href != null ) {
			element.attribute("href", href);			
		}
		writer.end();
	}


}
