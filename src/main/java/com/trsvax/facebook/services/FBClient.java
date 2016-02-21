package com.trsvax.facebook.services;

import com.restfb.Parameter;

public interface FBClient {
	public <T> T fetchObject(Class<T> clazz, String key, Parameter ... parameter );
}