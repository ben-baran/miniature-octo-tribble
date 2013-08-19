package com.barantschik.trinkets.raytracer.rendering;


public interface Sampler
{
	public Ray[] generateRays(double x, double y, Camera c, ScenePreferences sp);
}