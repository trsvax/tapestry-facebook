package com.trsvax.tapestry.facebook.services;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.slf4j.Logger;

public class FacebookModule {

    public static void bind(ServiceBinder binder) {
    }


    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
        configuration.add(new LibraryMapping("fb", "com.trsvax.tapestry.facebook"));
    }

    public void contributeMarkupRenderer(OrderedConfiguration<MarkupRendererFilter> configuration,
                                         final Logger logger, final Environment environment) {
        MarkupRendererFilter documentLinker = new MarkupRendererFilter() {
            public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer) {
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
