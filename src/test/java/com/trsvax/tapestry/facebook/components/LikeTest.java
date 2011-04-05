package com.trsvax.tapestry.facebook.components;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LikeTest
{


	@Test
	public void eventConvTest()
	{

		Assert.assertEquals(Like.eventConv("edge.create"), "edgeCreate");
		Assert.assertEquals(Like.eventConv("edge.remove"), "edgeRemove");
		Assert.assertEquals(Like.eventConv("one.two.three.four"), "oneTwoThreeFour");

	}
}
