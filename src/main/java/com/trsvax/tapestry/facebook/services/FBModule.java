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

package com.trsvax.tapestry.facebook.services;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.PartialMarkupRenderer;
import org.apache.tapestry5.services.PartialMarkupRendererFilter;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.slf4j.Logger;

public class FBModule {
	
    public static void bind(ServiceBinder binder)
    {
    }
	
    public void contributeMarkupRenderer(OrderedConfiguration<MarkupRendererFilter> configuration, 
    		final Logger logger, final Environment environment, final RequestGlobals requestGlobals) {
    	 MarkupRendererFilter documentLinker = new MarkupRendererFilter()
    	 	        {
    	 	            public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer)
    	 	            {
    	 	            	JavaScriptSupport javascriptSupport = environment.peekRequired(JavaScriptSupport.class);
    	 	                FBAsyncSupportImpl linker = new FBAsyncSupportImpl(logger, requestGlobals, javascriptSupport);
    	 	
    	 	                environment.push(FBAsyncSupport.class, linker);
    	 	
    	 	                renderer.renderMarkup(writer);
    	 	
    	 	                environment.pop(FBAsyncSupport.class);
    	 	
    	 	                linker.updateDocument(writer.getDocument());
    	 	            }
    	 	        };
    	 	       configuration.add("FBAsyncLinker", documentLinker, "after:JavaScriptSupport");
    }
    
    public void contributePartialMarkupRenderer(OrderedConfiguration<PartialMarkupRendererFilter> configuration, 
    		final Logger logger, final Environment environment, final RequestGlobals requestGlobals) {
    	PartialMarkupRendererFilter documentLinker = new PartialMarkupRendererFilter()
    	 	        {

						public void renderMarkup(MarkupWriter writer, JSONObject arg1, PartialMarkupRenderer renderer) {
							JavaScriptSupport javascriptSupport = environment.peekRequired(JavaScriptSupport.class);
							FBAsyncSupportImpl linker = new FBAsyncSupportImpl(logger, requestGlobals,javascriptSupport);
				    	 	
    	 	                environment.push(FBAsyncSupport.class, linker);
    	 	
    	 	                renderer.renderMarkup(writer, arg1);
    	 	
    	 	                environment.pop(FBAsyncSupport.class);
    	 	               linker.updateDocument(writer.getDocument());
						}
    	 	        };
    	 	       configuration.add("FBAsyncLinker", documentLinker,"after:JavaScriptSupport");
    }
    
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration)
    {
        configuration.add(new LibraryMapping("fb", "com.trsvax.tapestry.facebook"));
    }

}
