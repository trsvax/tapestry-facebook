package com.trsvax.facebook.test.pages.plugin;


import java.util.Date;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.slf4j.Logger;

import com.trsvax.facebook.FBSubscribe;
import com.trsvax.jacquard.annotations.Subscribe;

public class LikeTest {
	
	@Inject
	private Logger logger;
	
	@Subscribe(type=FBSubscribe.class,event="edge.create")
	public void onLiked() {
		logger.info("liked");
	}
	
	public String getHref() {
		return source.createPageRenderLinkWithContext(LikeTest.class, new Date().getTime()).toAbsoluteURI();
	}
	
	@Inject
	PageRenderLinkSource source;

}
