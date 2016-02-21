package com.trsvax.facebook.environment;

import java.util.ArrayList;
import java.util.List;

public class FacebookValues implements FacebookEnvironment {
	private boolean loadJS;
	private List<String> subscriptions = new ArrayList<String>();

	public boolean isLoadJS() {
		return loadJS;
	}

	public void setLoadJS(boolean value) {
		this.loadJS = value;
	}
	

	public void addInitializerCall(String script) {
		loadJS = true;
		subscriptions.add(script);		
	}
	
	public List<String> getInitializerCalls() {
		return subscriptions;
	}
	



}
