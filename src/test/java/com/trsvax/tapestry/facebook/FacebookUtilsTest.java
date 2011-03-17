package com.trsvax.tapestry.facebook;

import com.trsvax.tapestry.facebook.FacebookUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FacebookUtilsTest {


	@Test
	public void testFb2tap() {

		Assert.assertEquals(FacebookUtils.fb2tap("edge.create"), "edgeCreate");
		Assert.assertEquals(FacebookUtils.fb2tap("edge.remove"), "edgeRemove");
		Assert.assertEquals(FacebookUtils.fb2tap("one.two.three.four"), "oneTwoThreeFour");

	}
}
