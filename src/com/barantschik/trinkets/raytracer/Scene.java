package com.barantschik.trinkets.raytracer;

import java.util.Arrays;

public class Scene
{
	private Camera c;
	private Light[] lights = new Light[0];
	private Renderable[] renderable = new Renderable[0];
	
	public Scene(boolean chooseDefault) //Makes empty scene if not default scene
	{
		if(!chooseDefault)
		{
			setC(new Camera(new double[]{0, 0, 0}, new double[]{0, 0, 1}, new double[]{0, 1, 0}));
		}
		else
		{
			c = new Camera(new double[]{-2.9, 0, 7.2}, new double[]{0, 0, 10}, new double[]{0, 1, 0});
			
			lights = new Light[1];
			renderable = new Renderable[7];
			
			lights[0] = new Light(new double[]{0, 0, 0}, new double[]{1, 1, 1});
			
			renderable[0] = new Sphere(new double[]{2.5, 0, 15}, 1.5, new float[]{0.7f, 0, 0});
			renderable[1] = new Sphere(new double[]{0, 0, 10}, 1.5, new float[]{0, 0.7f, 0});
			renderable[2] = new Sphere(new double[]{-2.5, 0, 15}, 1.5, new float[]{0, 0, 0.7f});
			renderable[3] = new Sphere(new double[]{0, 2.5, 15}, 1.5, new float[]{.6f, 0.6f, 0});
			renderable[4] = new Sphere(new double[]{0, -2.5, 15}, 1.5, new float[]{0, 0.6f, 0.6f});
			
			renderable[5] = new Triangle(new double[]{-1, 5, -1}, new double[]{-1, 7, 1}, new double[]{1, 5, 1}, new float[]{0, 1, 0});
			renderable[6] = new Triangle(new double[]{1, 5, 1}, new double[]{1, 7, -1}, new double[]{-1, 5, -1}, new float[]{0, 1, 0});
		}
	}

	public Camera getC()
	{
		return c;
	}

	public void setC(Camera c)
	{
		this.c = c;
	}
	
	public Light[] getLights()
	{
		return lights;
	}
	
	public void setLights(Light[] lights)
	{
		this.lights = lights;
	}
	
	public void removeLights()
	{
		lights = new Light[0];
	}
	
	public void addLight(Light l)
	{
		lights = Arrays.copyOf(lights, lights.length + 1);
		lights[lights.length - 1] = l;
	}

	public Renderable[] getRenderable()
	{
		return renderable;
	}
	
	public void setRenderable(Renderable[] renderable)
	{
		this.renderable = renderable;
	}
	
	public void removeRenderable()
	{
		renderable = new Renderable[0];
	}
	
	public void addRenderable(Renderable r)
	{
		renderable = Arrays.copyOf(renderable, renderable.length + 1);
		renderable[renderable.length - 1] = r;
	}
}