package com.barantschik.trinkets.raytracer;

public class IntersectionData
{
	public double t;
	Renderable renderable;
	
	public IntersectionData(double t, Renderable renderable)
	{
		this.t = t;
		this.renderable = renderable;
	}
}
