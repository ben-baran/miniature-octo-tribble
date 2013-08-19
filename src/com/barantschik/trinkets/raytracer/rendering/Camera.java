package com.barantschik.trinkets.raytracer.rendering;


public class Camera
{
	public static double DEFAULT_FOV = 60;
	
	double fov;
	double halfTan;
	double pos[];
	double look[];
	double up[];

	double[] vecW, vecU, vecV;
	
	public Camera(double[] pos, double[] look, double[] up)
	{
		this(pos, look, up, DEFAULT_FOV);
	}
	
	public Camera(double[] pos, double[] look, double[] up, double fov)
	{
		this.pos = pos;
		this.look = look;
		this.up = GMath.normalize(up);
		this.fov = fov;
		
		halfTan = Math.tan(Math.toRadians(fov / 2));
		
		calcWUV();
	}
	
	public Camera(double[] pos, double[] look, double[] up, double focalLength, double width)
	{
		this(pos, look, up, 2 * Math.toDegrees(Math.atan(width / (2 * focalLength))));
	}

	public Ray makeRay(double x, double y, double width, double height)
	{
		double alpha = halfTan * (x - width / 2) / (width / 2);
		double beta = halfTan * (height / 2 - y) / (height / 2);
		
		return new Ray(pos, GMath.normalize(GMath.add(GMath.mult(vecU, alpha), GMath.mult(vecV, beta), GMath.negative(vecW))));
	}

	private void calcWUV()
	{
		vecW = GMath.normalize(GMath.subtract(look, pos));
		vecU = GMath.normalize(GMath.cross(up, vecW));
		vecV = GMath.cross(vecW, vecU);
	}
	
	public void move(double[] move)
	{
		pos[0] += move[0];
		pos[1] += move[1];
		pos[2] += move[2];
		
		calcWUV();
	}
}