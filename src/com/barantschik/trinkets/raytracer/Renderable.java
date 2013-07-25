package com.barantschik.trinkets.raytracer;

public interface Renderable
{
	public double giveIntersection(Ray r);
	public double[] getNormal(double[] point);
	public double getShininess();
	public float[] getDiffuse();
	public float[] getSpecular();
	public float[] getEmissive();
	public void move(double x, double y, double z);
	public void transform(M4x4 transform);
}
