package com.trsvax.tapestry.facebook.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;

import com.trsvax.tapestry.facebook.FBInit;
import com.trsvax.tapestry.facebook.services.FBAsyncSupport;

public class AsyncInit {
	
	@Parameter(autoconnect=true,required=true)
	private FBInit fbinit;
	
	@Environmental
	private FBAsyncSupport fbAsyncSupport;

	@BeginRender
	void beginRender(MarkupWriter writer) {				
		fbAsyncSupport.init(fbinit);		
	}

}

