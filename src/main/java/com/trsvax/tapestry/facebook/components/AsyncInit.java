//Copyright [2011] [Barry Books]

//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at

//       http://www.apache.org/licenses/LICENSE-2.0

//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.trsvax.tapestry.facebook.components;

import java.util.Locale;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.javascript.InitializationPriority;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.slf4j.Logger;

/**
 * 
 *
 */
public class AsyncInit
{
	@Inject
	private Logger logger;
	@Inject
	private RequestGlobals requestGlobals;
	
	@Environmental
	private JavaScriptSupport javaScriptSupport;
	
	@Parameter(required = true)
	private String appId;
	@Parameter(value = "literal:true")
	private boolean status;
	@Parameter(value = "literal:true")
	private boolean cookie;
	@Parameter(value = "literal:true")
	private boolean xfbml;
	@Parameter(value = "literal:false")
	private boolean logging;
	
	void setupRender(MarkupWriter writer)
	{
		// Facebook app registration and initialization
		writer.element("div", "id", "fb-root");
		writer.end();
	}

	@BeginRender
	void beginRender(MarkupWriter writer)
	{
		Locale locale = requestGlobals.getRequest().getLocale();
		String lang = locale.getLanguage();
		String country = locale.getCountry();
		
		if (country == null || country.trim().length() == 0)
			country = lang.toUpperCase();
		
		logger.debug("Intializing FB object. Locale defined as {} {}", lang, country);
		
		javaScriptSupport.importJavaScriptLibrary(String.format("http://connect.facebook.net/%s_%s/all.js", lang, country));
		
		String fbInit = "FB.init({appId: '%s', status: %s, cookie: %s, xfbml: %s, logging: %s});";
		
		javaScriptSupport.addScript(
				InitializationPriority.IMMEDIATE,
				fbInit,
				appId, status, cookie, xfbml, logging);
	}
}