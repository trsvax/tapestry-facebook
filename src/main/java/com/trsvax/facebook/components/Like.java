
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

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.dom.Element;

import com.trsvax.facebook.ColorScheme;
import com.trsvax.facebook.Font;
import com.trsvax.facebook.Verb;
import com.trsvax.facebook.Layout;
import com.trsvax.facebook.environment.FacebookEnvironment;





/**
 * @author bfb
 * Facebook XFBML Like component
 * @see <a href="http://developers.facebook.com/docs/reference/plugins/like/">Like</a>
 *
 */
public class Like {

	/**
	 *  the URL to like. The XFBML version defaults to the current page.
	 */
	@Parameter
	private String href;
	
	/**
	 * specifies whether to include a Send button with the Like button. This only works with the XFBML version.
	 */
	@Parameter
	private boolean send;
	
	/**
	 * there are three options.
	 */
	@Parameter
	private Layout layout;
	
	/**
	 * specifies whether to display profile photos below the button (standard layout only)
	 */
	@Parameter
	private boolean showFaces;
	
	/**
	 * the width of the Like button.
	 */
	@Parameter
	private Integer width;
	
	/**
	 * the verb to display on the button. Options: 'like', 'recommend'
	 */
	@Parameter
	private Verb action;
	
	/**
	 * the font to display in the button. Options: 'arial', 'lucida grande', 'segoe ui', 'tahoma', 'trebuchet ms', 'verdana'
	 */
	@Parameter
	private Font font;
	
	/**
	 * the color scheme for the like button. Options: 'light', 'dark'
	 */
	@Parameter
	private ColorScheme colorScheme;
	
	/**
	 * a label for tracking referrals; must be less than 50 characters and can contain alphanumeric characters and some 
	 * punctuation (currently +/=-.:_). The ref attribute causes two parameters to be added to the referrer URL when a 
	 * user clicks a link from a stream story about a Like action:
	 */
	@Parameter
	private String ref;
	
	@Environmental
	private FacebookEnvironment environment;


	@BeginRender
	void beginRender(MarkupWriter writer) {
		environment.setLoadJS(true);
		Element element = writer.element("div");
		element.addClassName("fb-like");
		if ( href != null ) {
			element.attribute("data-href", href);
		}
		element.attribute("data-send", send ? "true" : "false");
		if ( layout != null ) {
			element.attribute("data-layout", layout.toString());
		}
		element.attribute("data-show-faces", showFaces ? "true" : "false");
		if ( width != null ) {
			element.attribute("data-width", width.toString());
		}
		if ( action != null ) {
			element.attribute("data-action", action.toString());
		}
		if ( font != null ) {
			element.attribute("data-font", font.toString());
		}
		if ( colorScheme != null ) {
			element.attribute("data-colorscheme", colorScheme.toString());
		}
		if ( ref != null ) {
			element.attribute("data-ref",ref);
		}
		writer.end();
		
		

	}
	


}
