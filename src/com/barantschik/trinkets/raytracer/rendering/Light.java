package com.barantschik.trinkets.raytracer.rendering;


public interface Light
{
	public float[] getIntensity(double[] point);
	public boolean blocked(double[] point, Renderable renderable, double floatAdjust);
	public double[] directionalVector(double[] point);
}