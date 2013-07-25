package com.barantschik.trinkets.raytracer;

public class NoAA implements AAProvider
{
	public Ray[] generateRays(double x, double y, Camera c, ScenePreferences sp)
	{
		return new Ray[]{c.makeRay(x + 0.5, y + 0.5, sp.getSizeX(), sp.getSizeY())};
	}

	public AAData createPixelData(float[][] colors)
	{
		return new AAData(true, colors[0]);
	}
}