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

package com.trsvax.tapestry.facebook;


public class FBInit {
	
	private String appID;	
	private Boolean cookie;	
	private Boolean logging;	
	private Object session;	
	private Boolean status;	
	private Boolean xfbml = true;	
	private String channelURL;

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public Boolean getCookie() {
		return cookie;
	}

	public void setCookie(Boolean cookie) {
		this.cookie = cookie;
	}

	public Boolean getLogging() {
		return logging;
	}

	public void setLogging(Boolean logging) {
		this.logging = logging;
	}

	public Object getSession() {
		return session;
	}

	public void setSession(Object session) {
		this.session = session;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getXfbml() {
		return xfbml;
	}

	public void setXfbml(Boolean xfbml) {
		this.xfbml = xfbml;
	}

	public String getChannelURL() {
		return channelURL;
	}

	public void setChannelURL(String channelURL) {
		this.channelURL = channelURL;
	}
	

	

}
