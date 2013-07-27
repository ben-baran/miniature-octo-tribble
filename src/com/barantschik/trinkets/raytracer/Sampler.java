package com.barantschik.trinkets.raytracer;

public interface Sampler
{
	public Ray[] generateRays(double x, double y, Camera c, ScenePreferences sp);
}