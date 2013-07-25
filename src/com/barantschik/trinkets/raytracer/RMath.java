package com.barantschik.trinkets.raytracer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public abstract class RMath
{
	private static final double INTENSITY_MULTIPLIER = 0.5;

	private static final double FLOAT_ADJUST = 0.001;

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

	//	private static double[] getColorValue(Ray r, Renderable[] renderableList)
	//	{
	//		return null;
	//	}

	private static boolean inShadow(double[] p, Light light, Renderable[] renderableList)
	{
		double[] difference = GMath.subtract(p, light.pos);
		double distance = GMath.getMag(difference);
		Ray original = new Ray(p, difference);
		Ray r = new Ray(GMath.add(p, GMath.mult(original.dir, FLOAT_ADJUST)), original.dir);
		for(Renderable renderable : renderableList)
		{
			double solution = renderable.giveIntersection(r);
			if(!Double.isNaN(solution) && solution > 0 && solution < distance)
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

					IntersectionData interData = findIntersection(r, renderable);
					if(interData.renderable != null)
					{							
						float intensity = 0;
						double[] point = r.makeVector(interData.t);
						double[] normal = interData.renderable.getNormal(point);
						double[] viewVector = GMath.subtract(point, r.pos);

						for(int lightNum = 0; lightNum < lights.length; lightNum++)
						{
							if(!inShadow(point, lights[lightNum], renderable))
							{					
								float diffuseIntensity = (float) Math.max(GMath.dot(normal, GMath.normalize(GMath.subtract(point, lights[lightNum].pos))), 0);
								float specularIntensity = (float) Math.pow(Math.max(GMath.dot(normal, GMath.getHalfAngle(viewVector, GMath.subtract(point, lights[lightNum].pos), normal)), 0), interData.renderable.getShininess());
								intensity += INTENSITY_MULTIPLIER * (diffuseIntensity + specularIntensity);
							}
						}
						intensity += s.getSP().getAmbient();

						colors[rNum][0] = Math.min(interData.renderable.getDiffuse()[0] * intensity, 1.0f);
						colors[rNum][1] = Math.min(interData.renderable.getDiffuse()[1] * intensity, 1.0f);
						colors[rNum][2] = Math.min(interData.renderable.getDiffuse()[2] * intensity, 1.0f);
					}
				}
				float[] pixelcolor = s.getSP().getAAProvider().createPixelData(colors).color;
				g.setColor(new Color(pixelcolor[0], pixelcolor[1], pixelcolor[2]));
				g.fillRect(i, j, 1, 1);
			}
		}
		return image;
	}
}