package com.barantschik.trinkets.raytracer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public abstract class RMath
{
	private static final double FLOAT_ADJUST = 0.001;
	private static final int NUM_RECURSIVE = 5;
	
	private static IntersectionData findIntersection(Ray r, Renderable[] renderableList)
	{
		double shortestPath = Double.POSITIVE_INFINITY;
		Renderable curShortest = null;

		for(int i = 0; i < renderableList.length; i++)
		{
			double intersection = renderableList[i].giveIntersection(r);
			if(!Double.isNaN(intersection) && intersection < shortestPath)
			{
				shortestPath = intersection;
				curShortest = renderableList[i];
			}
		}
		return new IntersectionData(shortestPath, curShortest);
	}

	private static boolean inShadow(double[] p, Light light, Renderable[] renderableList)
	{
		for(Renderable renderable : renderableList)
		{
			if(light.blocked(p, renderable, FLOAT_ADJUST))
			{
				return true;
			}
		}
		return false;
	}

	public static Image drawScene(Scene s, Image image)
	{
		Light[] lights = s.getLights();
		Renderable[] renderable = s.getRenderable();

		int width = image.getWidth(null), height = image.getHeight(null);
		Graphics g = image.getGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				float averageR = 0, averageG = 0, averageB = 0;
				Ray[] rays = s.getSP().getAAProvider().generateRays(i, j, s.getC(), s.getSP());
				float[][] colors = new float[rays.length][3];
				for(int rNum = 0; rNum < rays.length; rNum++)
				{
					Ray r = rays[rNum];

					float[] curColor = getRecursiveColorValue(1, s, r);

					colors[rNum] = curColor;
				}
				float[] pixelcolor = s.getSP().getAAProvider().createPixelData(colors).color;
				g.setColor(new Color(pixelcolor[0], pixelcolor[1], pixelcolor[2]));
				g.fillRect(i, j, 1, 1);
			}
		}
		return image;
	}

	public static float[] getColorValue(IntersectionData interData, Ray r, Scene s)
	{
		Light[] lights = s.getLights();
		Renderable[] renderable = s.getRenderable();

		if(interData.renderable == null)
		{
			return new float[]{0, 0, 0};
		}

		float[] colorVal = new float[3];
		colorVal = interData.renderable.getAmbient();
		colorVal = GMath.add(colorVal, interData.renderable.getEmissive());

		double[] point = r.makeVector(interData.t);
		double[] normal = interData.renderable.getNormal(point);
		double[] viewVector = GMath.subtract(point, r.pos);

		for(int lightNum = 0; lightNum < lights.length; lightNum++)
		{
			if(!inShadow(point, lights[lightNum], renderable))
			{
				double[] directionalVector = lights[lightNum].directionalVector(point);
				
				float[] diffuse = GMath.mult(interData.renderable.getDiffuse(), (float) Math.max(GMath.dot(normal, GMath.normalize(directionalVector)), 0));
				float specDot = (float)  Math.max(GMath.dot(normal, GMath.getHalfAngle(viewVector, directionalVector, normal)), 0);
				float[] specular = GMath.mult(interData.renderable.getDiffuse(), (float) Math.pow(specDot, interData.renderable.getShininess()));

				
				float[] lightIntensity = lights[lightNum].getIntensity(point);
				float[] curVal = GMath.mult(GMath.add(diffuse, specular), lightIntensity);
				
				colorVal = GMath.add(colorVal, curVal);
			}
		}

		colorVal[0] = Math.min(colorVal[0], 1);
		colorVal[1] = Math.min(colorVal[1], 1);
		colorVal[2] = Math.min(colorVal[2], 1);

		return colorVal;
	}

	public static float[] getRecursiveColorValue(int n, Scene s, Ray r)
	{
		if(n == NUM_RECURSIVE)
		{
			return getColorValue(findIntersection(r, s.getRenderable()), r, s);
		}
		else
		{
			IntersectionData interData = findIntersection(r, s.getRenderable());
			
			if(interData.renderable != null)
			{
				double[] interPoint = r.makeVector(interData.t);
				double[] difference = GMath.subtract(r.pos, interPoint);
				Ray original = new Ray(interPoint, GMath.reflectRay(difference, interData.renderable.getNormal(interPoint)));
				Ray reflected = new Ray(GMath.add(interPoint, GMath.mult(original.dir, FLOAT_ADJUST)), original.dir);
				
				return GMath.capColor(GMath.add(getColorValue(interData, r, s), GMath.mult(getRecursiveColorValue(n + 1, s, reflected), interData.renderable.getReflectivity())));
			}
			else
			{
				return new float[]{0, 0, 0};
			}
		}
	}
}