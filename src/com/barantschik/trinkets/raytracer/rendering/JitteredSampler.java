package com.barantschik.trinkets.raytracer.rendering;

import java.util.Random;


public class JitteredSampler implements Sampler
{
	Random r = new Random();
	
	private int numAA;
	private double numAAInverse;
	private int[] baseArray;
	
	public JitteredSampler(int numAA)
	{
		this.numAA = numAA;
		numAAInverse = 1.0 / numAA;
		
		baseArray = new int[numAA];
		for(int i = 0; i < numAA; i++) baseArray[i] = i;
	}
	
	public Ray[] generateRays(double x, double y, Camera c, ScenePreferences sp)
	{
		int[] shuffle = createShuffle();
		Ray[] rays = new Ray[numAA];
		
		for(int i = 0; i < numAA; i++)
		{
			double ycoord = y + (i + 0.5) * numAAInverse;
			double xcoord = x + (shuffle[i] + 0.5) * numAAInverse;
			
			rays[i] = c.makeRay(xcoord, ycoord, sp.getSizeX(), sp.getSizeY());
		}
		
		return rays;
	}
	
	private int[] createShuffle()
	{
		int[] shuffle = baseArray;
		for(int i = 0; i < numAA; i++)
		{
			int switchNum = r.nextInt(numAA);
			int temp = shuffle[i];
			shuffle[i] = shuffle[switchNum];
			shuffle[switchNum] = temp;
		}
		
		return shuffle;
	}
}
