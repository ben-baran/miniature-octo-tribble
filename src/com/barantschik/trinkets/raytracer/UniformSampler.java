package com.barantschik.trinkets.raytracer;

public class UniformSampler implements Sampler
{
	public final static double EPSILON_1 = 0.99;
	
	protected int numAA;
	protected double numAAInverse;
	protected int numAAMap;
	
	public UniformSampler()
	{
		this(1);
	}
	
	public UniformSampler(int numAA)
	{
		this.numAA = numAA;
		numAAInverse = 1.0 / this.numAA;
		numAAMap = this.numAA * this.numAA;
	}
	
	public int getNumAAMap()
	{
		return numAAMap;
	}
	
	public Ray[] generateRays(double x, double y, Camera c, ScenePreferences sp)
	{
		Ray[] rays = new Ray[numAAMap];
		int i = 0;
		for(double addX = 0; addX < EPSILON_1; addX += numAAInverse)
		{
			for(double addY = 0; addY < EPSILON_1; addY += numAAInverse)
			{
				rays[i] = c.makeRay(x + addX, y + addY, sp.getSizeX(), sp.getSizeY());
				i++;
			}
		}
		return rays;
	}
}
