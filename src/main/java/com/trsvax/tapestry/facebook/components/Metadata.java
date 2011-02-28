package com.trsvax.tapestry.facebook.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;

import com.trsvax.tapestry.facebook.opengraph.Tags;

public class Metadata {
	
	@Parameter(autoconnect=true)
	private Tags tags;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {
		if ( tags == null ) {
			return;
		}
		if ( tags.getTitle() != null ) {
			writer.element("meta", "property","og:title","content",tags.getTitle());
			writer.end();
		}
		if ( tags.getType() != null ) {
			writer.element("meta", "property","og:type","content",tags.getType());
			writer.end();
		}
		if ( tags.getImageURL() != null ) {
			writer.element("meta", "property","og:image","content",tags.getImageURL());
			writer.end();
		}
		if ( tags.getUrl() != null ) {
			writer.element("meta", "property","og:url","content",tags.getUrl());
			writer.end();
		}
		if ( tags.getSiteName() != null ) {
			writer.element("meta", "property","og:site_name","content",tags.getSiteName());
			writer.end();
		}
		if ( tags.getApplicationID() != null ) {
			writer.element("meta", "property","fb:app_id","content",tags.getApplicationID());
			writer.end();
		}
		if ( tags.getAdmins() != null ) {
			writer.element("meta", "property","fb:admins","content",tags.getAdmins());
			writer.end();
		}
	}

}
