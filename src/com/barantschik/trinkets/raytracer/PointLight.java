package com.barantschik.trinkets.raytracer;

public class PointLight implements Light
{
	private static final double[] DEFAULT_ATTENUATION = {1, 0, 0};
	private double[] attenuation;

	private double[] pos;
	private float[] color;

	public PointLight(double[] pos)
	{
		this(pos, new float[]{1, 1, 1});
	}

	public PointLight(double[] pos, float[] color)
	{
		this(pos, color, DEFAULT_ATTENUATION);
	}

	public PointLight(double[] pos, float[] color, double[] attenuation)
	{
		this.pos = pos;
		this.color = color;
		this.attenuation = attenuation;
	}

	public float[] getIntensity(double[] point)
	{
		double distance = GMath.getMag(GMath.subtract(point, pos));
		float intensityMagnitude = 1.0f / (float) (attenuation[0] + distance * attenuation[1] + distance * distance * attenuation[2]);
		return GMath.mult(color, intensityMagnitude);
	}

	public boolean blocked(double[] point, Renderable renderable, double floatAdjust)
	{
		double[] difference = GMath.subtract(point, pos);
		double distance = GMath.getMag(difference);
		Ray original = new Ray(point, difference);
		Ray r = new Ray(GMath.add(point, GMath.mult(original.dir, floatAdjust)), original.dir);
		double solution = renderable.giveIntersection(r);
		if(!Double.isNaN(solution) && solution > 0 && solution < distance)
		{
			return true;
		}
		return false;
	}

	public double[] directionalVector(double[] point)
	{
		return GMath.subtract(point, pos);
	}
}