package com.barantschik.trinkets.raytracer;

public interface AAProvider extends Sampler
{
	public AAData createPixelData(float[][] colors);
}