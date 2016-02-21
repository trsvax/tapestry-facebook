package com.trsvax.facebook.services;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.plastic.MethodAdvice;
import org.apache.tapestry5.plastic.MethodInvocation;
import org.apache.tapestry5.plastic.PlasticMethod;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import com.trsvax.facebook.FBSubscribe;
import com.trsvax.facebook.environment.FacebookEnvironment;
import com.trsvax.jacquard.annotations.Subscribe;
import com.trsvax.jacquard.services.AbstractSubscribeWorker;

public class FBSubscribeWorker extends AbstractSubscribeWorker implements FBSubscribe {
	private final Environment environment;
	
	public FBSubscribeWorker(JavaScriptSupport javaScriptSupport, Environment environment) {
		super(javaScriptSupport);
		this.environment = environment;
	}
	
	@Override
	public void subscribe(final Map<String, Subscribe> events, final PlasticMethod method) {
		method.addAdvice( new MethodAdvice() {
			
			public void advise(MethodInvocation invocation) {
				ComponentResources resources = invocation.getInstanceContext().get(ComponentResources.class);
				FacebookEnvironment facebookEnvironment = environment.peekRequired(FacebookEnvironment.class);
				for (  Entry<String, Subscribe> entry: events.entrySet() ) {
					String name = entry.getKey();
					Subscribe subscribe = entry.getValue();
					String url = resources.createEventLink(name).toURI();
					facebookEnvironment.addInitializerCall(script(name,url,subscribe));
					//javaScriptSupport.addScript(script(name, url, subscribe));
				}
				invocation.proceed();
				
			}
		});		
	}

	@Override
	public String script(String name, String url, Subscribe subscribe) {
		return String.format("FB.Event.subscribe('%s',function(response) {" +
				"$.ajax({url: '%s'}); " +
				"return %s;\n});",name, url,subscribe.returnValue());
	}	
	
}
