package com.trsvax.tapestry.facebook;

public class FacebookUtils {

	public static String fb2tap(String event) {

		int length = event.length();
		StringBuilder tapestryEvent = new StringBuilder(length);

		boolean capitalizeNext = false;

		for (int i = 0; i < length; i++) {
			char ch = event.charAt(i);

			if (ch == '.') {
				capitalizeNext = true;
			} else if (capitalizeNext) {
				tapestryEvent.append(Character.toTitleCase(ch));
				capitalizeNext = false;
			} else {
				tapestryEvent.append(ch);
			}
		}

		return tapestryEvent.toString();
	}

}
