package com.barantschik.trinkets.raytracer;

public class SuperSamplingAA implements AAProvider
{
	private int numAA;
	private double numAAInverse;
	private int numAAMap;
	
	public SuperSamplingAA()
	{
		this(1);
	}
	
	public SuperSamplingAA(int numAA)
	{
		this.numAA = numAA;
		numAAInverse = 1.0 / this.numAA;
		numAAMap = this.numAA * this.numAA;
	}

	public Ray[] generateRays(double x, double y, Camera c, ScenePreferences sp)
	{
		Ray[] rays = new Ray[numAAMap];
		int i = 0;
		for(double addX = 0; addX < 1; addX += numAAInverse)
		{
			for(double addY = 0; addY < 1; addY += numAAInverse)
			{
				rays[i] = c.makeRay(x + addX, y + addY, sp.getSizeX(), sp.getSizeY());
				i++;
			}
		}
		return rays;
	}

	public int getNumAAMap()
	{
		return numAAMap;
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