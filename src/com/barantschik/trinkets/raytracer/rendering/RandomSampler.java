package com.barantschik.trinkets.raytracer.rendering;

import java.util.Random;


public class RandomSampler implements Sampler
{
	protected Random r = new Random();
	protected int numRays;
	
	
	public RandomSampler()
	{
		this(1);
	}
	
	public RandomSampler(int numRays)
	{
		this.numRays = numRays;
	}
	
	public Ray[] generateRays(double x, double y, Camera c, ScenePreferences sp)
	{
		Ray[] rays = new Ray[numRays];
		
		for(int i = 0; i < numRays; i++)
		{
			rays[i] = c.makeRay(x + Math.abs(r.nextDouble()), y + Math.abs(r.nextDouble()), sp.getSizeX(), sp.getSizeY());
		}
		
		return rays;
	}
}
