package com.barantschik.trinkets.raytracer;

public class CenterSampler implements Sampler
{
	public Ray[] generateRays(double x, double y, Camera c, ScenePreferences sp)
	{
		return new Ray[]{c.makeRay(x + 0.5, y + 0.5, sp.getSizeX(), sp.getSizeY())};
	}

}