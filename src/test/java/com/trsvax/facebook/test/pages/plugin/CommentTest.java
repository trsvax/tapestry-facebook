package com.trsvax.facebook.test.pages.plugin;

import com.trsvax.facebook.FBSubscribe;
import com.trsvax.jacquard.annotations.Subscribe;

public class CommentTest {
	
	@Subscribe(type=FBSubscribe.class,event="edge.comment")
	void comment() {
		
	}
}
