package com.trsvax.tapestry.facebook.services;

import org.apache.tapestry5.ComponentResources;

import com.trsvax.tapestry.facebook.FBInit;
import com.trsvax.tapestry.facebook.opengraph.Tags;

public interface FBAsyncSupport {
	
	public void init(FBInit fbinit);
	public void init(String init);
	public void meta(Tags tags);
	public void subscribe(String event, ComponentResources resources);
	public void render();

}
