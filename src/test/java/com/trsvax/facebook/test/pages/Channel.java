package com.trsvax.facebook.test.pages;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.services.Response;

public class Channel {
	
	private final String response = "<script src=\"//connect.facebook.net/en_US/all.js\"></script>";
	private final Long maxAge = 60*60*24L;
	
	Object onActivate() {
		return new StreamResponse() {

			public String getContentType() {
				return "text/html";
			}

			public InputStream getStream() throws IOException {
				return new ByteArrayInputStream(response.getBytes());
			}

			public void prepareResponse(Response response) {
				Date date = new Date();
				response.setDateHeader("Expires", date.getTime() + (maxAge * 1000));
				response.setHeader("Cache-Control", "max-age="+maxAge);
				response.setHeader("Pragma", "public");
			}
			
		};
	}

}
