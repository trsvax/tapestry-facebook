
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

package com.trsvax.facebook.services;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.dom.Document;

import com.trsvax.facebook.FBInit;
import com.trsvax.facebook.environment.FacebookEnvironment;
import com.trsvax.facebook.opengraph.Tags;

public interface FBAsyncSupport {
	
	public void init(FBInit fbinit);
	public void init(String init);
	public void meta(Tags tags);
	public void subscribe(String event, ComponentResources resources);
	public void render();
	public void updateDocument(FacebookEnvironment facebookEnvironment, Document document);

}
