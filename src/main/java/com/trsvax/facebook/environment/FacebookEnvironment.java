package com.trsvax.facebook.environment;

import java.util.List;

public interface FacebookEnvironment {

		public boolean isLoadJS();
		public void setLoadJS(boolean value);
		
		public void addInitializerCall(String script);
		public List<String> getInitializerCalls();
}
