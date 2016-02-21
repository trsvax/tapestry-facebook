package com.trsvax.facebook;

public enum Layout {
	STANDARD,BUTTON_COUNT,BOX_COUNT;
	
	public String toString() {
		return this.name().toLowerCase();
	}
}
