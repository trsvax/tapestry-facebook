package com.trsvax.facebook.test.services;

import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.SubModule;

import com.trsvax.facebook.services.FBModule;

@SubModule(FBModule.class)
public class AppModule {
	
	public static void bind(ServiceBinder binder) {
	}

}
