
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
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.trsvax.facebook.ColorScheme;
import com.trsvax.facebook.Font;
import com.trsvax.facebook.LinkTarget;
import com.trsvax.facebook.environment.FacebookEnvironment;

/**
 * @author bfb
 * Facebook XFBML Activity component
 * @see <a href="https://developers.facebook.com/docs/reference/plugins/activity/">Activity</a>
 *
 */

public class Activity {
	
	/**
	 *  the domain for which to show activity. The XFBML version defaults to the current domain
	 */
	@Parameter
	private String site;
	
	/**
	 * a comma separated list of actions to show activities for.
	 */
	@Parameter
	private String action;
	
	/**
	 * will display all actions, custom and global, associated with this app_id.
	 */
	@Parameter
	private String appID;
	
	/**
	 * the width of the plugin in pixels. Default width: 300px.
	 */
	@Parameter
	private Integer width;
	
	/**
	 *  the height of the plugin in pixels. Default height: 300px.
	 */
	@Parameter
	private Integer height;
	
	/**
	 * specifies whether to show the Facebook header.
	 */
	@Parameter
	private boolean header;
	
	/**
	 * the color scheme for the plugin. Options: 'light', 'dark'
	 */
	@Parameter
	private ColorScheme colorScheme;
	
	/**
	 * the font to display in the plugin. Options: 'arial', 'lucida grande', 'segoe ui', 'tahoma', 'trebuchet ms', 'verdana'
	 */
	@Parameter
	private Font font;
	
	/**
	 * the border color of the plugin.
	 */
	@Parameter
	private String borderColor;
	
	/**
	 * specifies whether to always show recommendations in the plugin. 
	 * If recommendations is set to true, the plugin will display recommendations in the bottom half.
	 */
	@Parameter
	private boolean recommendations;
	
	/**
	 * allows you to filter which URLs are shown in the plugin. The plugin will only include URLs which contain 
	 * the filter string in the first two path parameters of the URL. If nothing in the first two path parameters 
	 * of the URL matches the filter, the URL will not be included. For example, if the 'site' parameter is set to 
	 * 'www.example.com' and the 'filter' parameter was set to '/section1/section2' then only pages which matched 
	 * 'http://www.example.com/section1/section2/*' would be included in the activity feed section of this plugin. 
	 * The filter parameter does not apply to any recommendations which may appear in this plugin (see above); 
	 * Recommendations are based only on 'site' parameter.
	 */
	@Parameter
	private String filter;
	
	/**
	 * This specifies the context in which content links are opened. By default all links within the plugin will 
	 * open a new window. If you want the content links to open in the same window, you can set this parameter to
	 *  _top or _parent. Links to Facebook URLs will always open in a new window.
	 */
	@Parameter
	private LinkTarget linkTarget;
	
	/**
	 * a label for tracking referrals; must be less than 50 characters and can contain alphanumeric characters 
	 * and some punctuation (currently +/=-.:_). Specifying a value for the ref attribute adds the 'fb_ref' parameter
	 *  to the any links back to your site which are clicked from within the plugin. Using different values for the 
	 *  ref parameter for different positions and configurations of this plugin within your pages allows you to 
	 *  track which instances are performing the best.
	 */
	@Parameter
	private String ref;
	
	/**
	 * a limit on recommendation and creation time of articles that are surfaced in the plugins, the default is 0 
	 * (we don’t take age into account). Otherwise the valid values are 1-180, which specifies the number of days.
	 */
	@Parameter
	private Integer maxAge;
	
	@Environmental
	private FacebookEnvironment environment;
	

	@BeginRender
	void beginRender(MarkupWriter writer) {
		environment.setLoadJS(true);
		Element element = writer.element("fb:activity");
		if ( site != null ) {
			element.attribute("site", site);
		}
		if ( action != null ) {
			element.attribute("action", action);
		}
		if ( appID != null ) {
			element.attribute("app_id", appID);
		}
		if ( width != null ) {
			element.attribute("width", width.toString());
		}
		if ( height != null ) {
			element.attribute("height", height.toString());
		}
		element.attribute("header", header ? "true" : "false");
		if ( colorScheme != null ) {
			element.attribute("colorscheme", colorScheme.toString());
		}
		if ( font != null ) {
			element.attribute("font", font.toString());
		}
		if ( borderColor != null ) {
			element.attribute("border_color", borderColor);
		}
		element.attribute("recommendations", recommendations ? "true" : "false");
		if ( filter != null ) {
			element.attribute("filter", filter);
		}
		if ( linkTarget != null ) {
			element.attribute("linktarget", linkTarget.toString());
		}
		if ( ref != null ) {
			element.attribute("ref", ref);
		}
		if ( maxAge != null ) {
			element.attribute("max_age", maxAge.toString());
		}
		
		writer.end();
	}
}
