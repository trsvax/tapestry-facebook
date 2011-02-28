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
