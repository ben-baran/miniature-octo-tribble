package com.barantschik.trinkets.raytracer;

public class RandomAA extends RandomSampler implements AAProvider
{
	public RandomAA()
	{
		super();
	}
	
	public RandomAA(int numAA)
	{
		super(numAA);
	}
	
	public AAData createPixelData(float[][] colors)
	{
		float averageR = 0, averageG = 0, averageB = 0;
		for(int i = 0; i < colors.length; i++)
		{
			averageR += colors[i][0];
			averageG += colors[i][1];
			averageB += colors[i][2];
		}
		averageR /= colors.length;
		averageG /= colors.length;
		averageB /= colors.length;
		
		return new AAData(true, new float[]{averageR, averageG, averageB});
	}

}
