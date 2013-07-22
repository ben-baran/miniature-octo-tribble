package com.barantschik.trinkets.raytracer;

public class Light
{
	double[] pos;
	double[] color;
	
	public Light(double[] pos, double[] color)
	{
		this.pos = pos;
		this.color = color;
	}
}