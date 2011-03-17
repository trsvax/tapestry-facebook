package com.trsvax.tapestry.facebook.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.services.LibraryMapping;

public class FacebookModule {

	public static void bind(ServiceBinder binder) {
	}

	public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
		configuration.add(new LibraryMapping("fb", "com.trsvax.tapestry.facebook"));
	}

}
