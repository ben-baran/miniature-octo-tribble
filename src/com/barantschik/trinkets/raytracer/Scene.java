package com.barantschik.trinkets.raytracer;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Scene
{
	private Camera c;
	private Light[] lights = new Light[0];
	private Renderable[] renderable = new Renderable[0];

	private ScenePreferences sp;

	public Scene(ScenePreferences sp, boolean chooseDefault) //Makes empty scene if not default scene
	{
		this.setSP(sp);

		if(!chooseDefault)
		{
			setC(new Camera(new double[]{0, 0, 0}, new double[]{0, 0, 1}, new double[]{0, 1, 0}));
		}
		else
		{
//			c = new Camera(new double[]{0, 0, 0}, new double[]{0, 0, 1}, new double[]{0, 1, 0});
//			lights = new Light[1];
//			renderable = new Renderable[1];
//			
//			lights[0] = new PointLight(new double[]{0, 0, 2}, new float[]{0.7f, 0.7f, 0.7f});
//			
//			renderable[0] = new Sphere(new double[]{0, 0, 0}, 1, new float[]{0, 0, 1});
			
			/**********************************************************************************/
			
			c = new Camera(new double[]{-1.8, 0.1, 6.2}, new double[]{0, 0, 7}, new double[]{0, 0, -1});
			lights = new Light[2];
			renderable = new Renderable[7];
			
			lights[0] = new PointLight(new double[]{1, 2, 5}, new float[]{0.7f, 0.7f, 0.7f});
			lights[1] = new PointLight(new double[]{-1, 2, 5}, new float[]{0.7f, 0.7f, 0.7f});
			
			try
			{
				BufferedImage checker = ImageIO.read(new File("./res/Raytracer/images/checker.gif"));
				
				renderable[0] = new TexTriangle(new double[]{20, 20, 7}, new double[]{-20, 20, 7}, new double[]{-20, -20, 7}, 
												new double[]{1, 1}, new double[]{0, 1}, new double[]{0, 0}, checker);
				renderable[1] = new TexTriangle(new double[]{-20, -20, 7}, new double[]{20, -20, 7}, new double[]{20, 20, 7}, 
												new double[]{1, 1}, new double[]{1, 0}, new double[]{0, 0}, checker);
				
				renderable[2] = new TexTriangle(new double[]{-20, -20, 4}, new double[]{-20, 20, 4}, new double[]{20, 20, 4}, 
												new double[]{1, 1}, new double[]{0, 1}, new double[]{0, 0}, checker);
				renderable[3] = new TexTriangle(new double[]{20, 20, 4}, new double[]{20, -20, 4}, new double[]{-20, -20, 4}, 
												new double[]{1, 1}, new double[]{1, 0}, new double[]{0, 0}, checker);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			renderable[4] = new Sphere(new double[]{0, 0, 6}, 1, new float[]{0, 0, 0});
			renderable[5] = new Sphere(new double[]{0.1, 1.2, 5.5}, 0.1, new float[]{0, 0, 0});
			
			renderable[6] = new Sphere(new double[]{-1, 1, 6.75}, 0.25, new float[]{0, 0, 0});
			
			/**********************************************************************************/
			
//			c = new Camera(new double[]{0, 0, -0.3}, new double[]{0, 0, 1}, new double[]{0, 1, 0});
//			lights = new Light[1];
//			renderable = new Renderable[4];
//			
//			lights[0] = new PointLight(new double[]{0, 0, 0}, new float[]{0.7f, 0.7f, 0.7f});
//			
//			renderable[0] = new Sphere(new double[]{0, 0, 1.73205}, 0.8660254, new float[]{0, 0, 0}, new float[]{0, 0, 0}, 0, new float[]{0, 0.1f, 1});
//			renderable[1] = new Sphere(new double[]{1, 0, 0}, 0.8660254, new float[]{0, 0, 0}, new float[]{0, 0, 0}, 0, new float[]{0, 0.1f, 0});
//			renderable[2] = new Sphere(new double[]{-0.5, 0.8660254, 0}, 0.8660254, new float[]{0, 0, 0}, new float[]{0, 0, 0}, 0, new float[]{0.1f, 0, 0});
//			renderable[3] = new Sphere(new double[]{-0.5, -0.8660254, 0}, 0.8660254, new float[]{0, 0, 0}, new float[]{0, 0, 0}, 0, new float[]{0.05f, 0.05f, 0});
			
			/**********************************************************************************/
			
//			c = new Camera(new double[]{-2.9, -1.4, 5.9}, new double[]{0, 0, 4}, new double[]{0, 1, 0});
//
//			lights = new Light[2];
//			renderable = new Renderable[7];
//
//			lights[0] = new PointLight(new double[]{0, 0, 6}, new float[]{1, 1, 1});
//			lights[1] = new PointLight(new double[]{0, 5, 2}, new float[]{1, 1, 1});
//
//			renderable[0] = new Sphere(new double[]{0, 0, 4}, 1.5, new float[]{0, 0, 0});
//			renderable[1] = new Sphere(new double[]{3.5, 0, 4}, 1.5, new float[]{0, 0.7f, 0});
//			renderable[2] = new Sphere(new double[]{-3.5, 0, 4}, 1.5, new float[]{0, 0, 0.7f});
//			renderable[3] = new Sphere(new double[]{0, 3.5, 4}, 1.5, new float[]{0.7f, 0, 0.7f});
//			renderable[4] = new Sphere(new double[]{0, -3.5, 4}, 1.5, new float[]{0, 0.7f, 0.7f});
//			
//			renderable[5] = new Triangle(new double[]{20, 20, 7}, new double[]{-20, 20, 7}, new double[]{-20, -20, 7}, new float[]{0, 0.3f, 0.7f});
//			renderable[6] = new Triangle(new double[]{-20, -20, 7}, new double[]{20, -20, 7}, new double[]{20, 20, 7}, new float[]{0, 0.3f, 0.7f});
//			
//			try
//			{
//				renderable[5] = new TexTriangle(new double[]{20, 20, 7}, new double[]{-20, 20, 7}, new double[]{-20, -20, 7}, 
//												new double[]{0, 0}, new double[]{1, 0}, new double[]{0, 1}, ImageIO.read(new File("./res/Raytracer/images/checker.gif")));
//				renderable[6] = new TexTriangle(new double[]{-20, -20, 7}, new double[]{20, -20, 7}, new double[]{20, 20, 7},
//												new double[]{1, 1}, new double[]{1, 0}, new double[]{0, 1}, ImageIO.read(new File("./res/Raytracer/images/checker.gif")));
//			}
//			catch (IOException e)
//			{
//				e.printStackTrace();
//			}
//			
//			double[][] values = {{1, 0, 0, 0},
//								 {0, 5, 0, 0},
//								 {0, 0, 1, 0},
//								 {0, 0, 0, 1}};
//			
//			renderable[1].transform(new M4x4(values));
//			renderable[2].transform(new M4x4(values));
		}
	}

	public float[] getBackColor(Ray r)
	{
//		return new float[]{(float) Math.min(Math.abs(r.dir[0]), 1) / 3, (float) Math.min(Math.abs(r.dir[1]), 1) / 3, (float) Math.min(Math.abs(r.dir[2]), 1) / 3};
		return new float[]{0, 0, 0.01f};
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

	public ScenePreferences getSP()
	{
		return sp;
	}
	
	public void setSP(ScenePreferences sp)
	{
		this.sp = sp;
	}
}