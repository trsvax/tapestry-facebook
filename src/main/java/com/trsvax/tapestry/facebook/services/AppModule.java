package com.trsvax.tapestry.facebook.services;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.internal.services.DocumentLinker;
import org.apache.tapestry5.internal.services.DocumentLinkerImpl;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.slf4j.Logger;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);
        
        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }
    
    
    public static void contributeApplicationDefaults(
            MappedConfiguration<String, String> configuration)
    {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");

        // The factory default is true but during the early stages of an application
        // overriding to false is a good idea. In addition, this is often overridden
        // on the command line as -Dtapestry.production-mode=false
        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");

        // The application version number is incorprated into URLs for some
        // assets. Web browsers will cache assets because of the far future expires
        // header. If existing assets are changed, the version number should also
        // change, to force the browser to download new versions.
        configuration.add(SymbolConstants.APPLICATION_VERSION, "0.0.1-SNAPSHOT");
    }
    
    public void contributeMarkupRenderer(OrderedConfiguration<MarkupRendererFilter> configuration, 
    		final Logger logger, final Environment environment) {
    	 MarkupRendererFilter documentLinker = new MarkupRendererFilter()
    	 	        {
    	 	            public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer)
    	 	            {
    	 	                FBAsyncSupportImpl linker = new FBAsyncSupportImpl(logger);
    	 	
    	 	                environment.push(FBAsyncSupport.class, linker);
    	 	
    	 	                renderer.renderMarkup(writer);
    	 	
    	 	                environment.pop(FBAsyncSupport.class);
    	 	
    	 	                linker.updateDocument(writer.getDocument());
    	 	            }
    	 	        };
    	 	       configuration.add("FBAsyncLinker", documentLinker);
    }
}
