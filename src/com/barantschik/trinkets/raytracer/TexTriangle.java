package com.barantschik.trinkets.raytracer;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class TexTriangle extends Triangle
{
	private double[] uv1, uv2, uv3;
	private BufferedImage image;
	
	public TexTriangle(double[] v1, double[] v2, double[] v3, double[] uv1, double[] uv2, double[] uv3, BufferedImage image)
	{
		super(v1, v2, v3, new float[]{0, 0, 0});
		
		this.uv1 = uv1;
		this.uv2 = uv2;
		this.uv3 = uv3;
		
		this.image = image;
	}
	
	public float[] getDiffuse()
	{
		double[] pos = GMath.add(GMath.mult(uv1, s1), GMath.mult(uv2, s2), GMath.mult(uv3, s3));
		return new Color(image.getRGB((int) (pos[0] * image.getWidth()), (int) (pos[1] * image.getHeight())), false).getRGBColorComponents(null);
	}
}
