package com.barantschik.trinkets.raytracer;

public interface Renderable
{
	public double giveIntersection(Ray r);
	public double[] getNormal(double[] point);
	public float[] getColor();
	public double getSpecularity();
	public void move(double x, double y, double z);
	public void transform(M4x4 transform);
}
