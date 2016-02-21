package com.trsvax.facebook.services;

import com.restfb.FacebookClient;
import com.restfb.Parameter;

public class FBClientImpl implements FBClient {
	private final FacebookClient client;
	
	public FBClientImpl(FacebookClient client) {
		this.client = client;
	}
	
	public <T> T fetchObject(Class<T> clazz, String key, Parameter ... parameter) {
		return client.fetchObject(key, clazz, parameter);
	}

}
