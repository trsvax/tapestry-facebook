package com.trsvax.tapestry.facebook.services;

import com.trsvax.tapestry.facebook.opengraph.Tags;
import org.apache.tapestry5.ComponentResources;

public interface FBAsyncSupport {

	public void init(String init);

	public void meta(Tags tags);

	public void subscribe(String event, ComponentResources resources);

	public void render();

}
