package com.barantschik.trinkets.raytracer;

public class IntersectionData
{
	double t;
	Renderable renderable;
	
	int u, v;
	
	public IntersectionData(double t, Renderable renderable)
	{
		this(t, renderable, -1, -1);
	}
	
	public IntersectionData(double t, Renderable renderable, int u, int v)
	{
		this.t = t;
		this.renderable = renderable;
		
		this.u = u;
		this.v = v;
	}
}