package com.barantschik.trinkets.raytracer;

public class Ray
{
	double pos[];
	double dir[];

	public Ray(double[] pos, double[] dir)
	{
		this.pos = pos;
		this.dir = GMath.normalize(dir);
	}
	
	public double[] makeVector(double t)
	{
		return new double[]{pos[0] + t * dir[0], pos[1] + t * dir[1], pos[2] + t * dir[2]};
	}
}