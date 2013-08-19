package com.barantschik.trinkets.raytracer.rendering;


public class IntersectionData
{
	double t;
	Renderable renderable;
	
	int u, v;
	int id;
	
	public IntersectionData(double t, Renderable renderable)
	{
		this(t, renderable, -1, -1);
	}
	
	public IntersectionData(double t, Renderable renderable, int u, int v)
	{
		this(t, renderable, u, v, -1);
	}
	
	public IntersectionData(double t, Renderable renderable, int u, int v, int id)
	{
		this.t = t;
		this.renderable = renderable;
		
		this.u = u;
		this.v = v;
		this.id = id;
	}
}