
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

import com.trsvax.facebook.environment.FacebookEnvironment;


/**
 * @author bfb
 * Facebook XFBML LoginButton component
 * @see <a href="http://developers.facebook.com/docs/reference/plugins/login/">Login Button</a>
 *
 */
public class LoginButton {
	
	/**
	 * 
	 */
	@Parameter
	private boolean showFaces;
	
	@Environmental
	private FacebookEnvironment environment;

	@BeginRender
	void beginRender(MarkupWriter writer) {
		environment.setLoadJS(true);
		Element element = writer.element("div");
		element.addClassName("fb-login-button");
		element.attribute("data-show-faces", showFaces ? "true" : "false");
		writer.end();
	}


}
