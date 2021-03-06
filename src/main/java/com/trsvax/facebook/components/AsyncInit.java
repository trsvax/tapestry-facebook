
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

import com.trsvax.facebook.FBInit;
import com.trsvax.facebook.services.FBAsyncSupport;

public class AsyncInit {
	
	@Parameter(autoconnect=true,required=true)
	private FBInit fbinit;
	
	@Environmental
	private FBAsyncSupport fbAsyncSupport;

	@BeginRender
	void beginRender(MarkupWriter writer) {				
		fbAsyncSupport.init(fbinit);		
	}

}

