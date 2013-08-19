package com.barantschik.trinkets.raytracer.rendering;


public interface AccelerationStructure
{
	public IntersectionData findIntersection(Ray r, Renderable[] renderableList);
}
