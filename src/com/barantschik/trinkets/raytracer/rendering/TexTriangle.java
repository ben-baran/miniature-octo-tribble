package com.barantschik.trinkets.raytracer.rendering;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class TexTriangle extends Triangle
{
	private double[] uv1, uv2, uv3;
	private BufferedImage image;
	
	public TexTriangle(double[] v1, double[] v2, double[] v3, double[] uv1, double[] uv2, double[] uv3, BufferedImage image)
	{
		super(v1, v2, v3, GMath.empty());
		
		this.uv1 = uv1;
		this.uv2 = uv2;
		this.uv3 = uv3;
		
		this.image = image;
	}
	
	public IntersectionData giveIntersection(Ray r)
	{
		if(!transformed)
		{
			double dirDotN = GMath.dot(r.dir, normal);
			if(dirDotN != 0)
			{
				double t = GMath.dot(normal, GMath.subtract(r.pos, v1)) / dirDotN;
				if(t > 0)
				{
					M3x3 inverseMatrix = GMath.findInverseMatrix(GMath.constructMatrix(bMinA, cMinA, GMath.negative(r.dir)));
					double[] solution = GMath.mult(inverseMatrix, GMath.subtract(v1, r.pos));
					
					if(solution[0] >= 0 && solution[1] >= 0 && solution[0] + solution[1] <= 1)
					{
						double[] texPos = GMath.add(GMath.mult(uv1, 1 - solution[0] - solution[1]), GMath.mult(uv2, solution[0]), GMath.mult(uv3, solution[1]));
						
						return new IntersectionData(t, this, (int) (texPos[0] * image.getWidth()), (int) (texPos[1] * image.getHeight()));
					}
				}
			}
			return new IntersectionData(Double.NaN, null);
		}
		else
		{
			double[] transformedPos = GMath.mult(inverseTransformMatrix, GMath.createHomogenousPos(r.pos));
			double[] transformedDir = GMath.mult(inverseTransformMatrix, GMath.createHomogenousDir(r.dir));
			Ray rT = new Ray(transformedPos, transformedDir);
			
			double dirDotN = GMath.dot(rT.dir, normal);
			if(dirDotN != 0)
			{
				double t = GMath.dot(normal, GMath.subtract(rT.pos, v1)) / dirDotN;
				if(t > 0)
				{
					M3x3 inverseMatrix = GMath.findInverseMatrix(GMath.constructMatrix(bMinA, cMinA, GMath.negative(rT.dir)));
					double[] solution = GMath.mult(inverseMatrix, GMath.subtract(v1, rT.pos));
					
					if(solution[0] >= 0 && solution[1] >= 0 && solution[0] + solution[1] <= 1)
					{
						double[] texPos = GMath.add(GMath.mult(uv1, 1 - solution[0] - solution[1]), GMath.mult(uv2, solution[0]), GMath.mult(uv3, solution[1]));
						
						return new IntersectionData(t, this, (int) (texPos[0] * image.getWidth()), (int) (texPos[1] * image.getHeight()));
					}
				}
			}
			return new IntersectionData(Double.NaN, null);
		}
	}
	
	public float[] getDiffuse(IntersectionData interData)
	{
		if(image.getWidth() <= interData.u) interData.u = image.getWidth() - 1;
		else if(interData.u < 0) interData.u = 0;
		if(image.getHeight() <= interData.v) interData.v = image.getHeight() - 1;
		else if(interData.v < 0) interData.v = 0;
		return new Color(image.getRGB(interData.u, interData.v), false).getRGBColorComponents(null);
	}
}