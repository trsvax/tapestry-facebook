package com.trsvax.tapestry.facebook.components;

import org.apache.tapestry5.annotations.SupportsInformalParameters;


/**
 * @author bfb
 *         Facebook XFBML Like Box component
 * @see <a href="http://developers.facebook.com/docs/reference/plugins/like-box/">Like Box</a>
 */
@SupportsInformalParameters
public class LikeBox extends Like {

	@Override
	public String getElement() {
		return "fb:like-box";
	}

}
