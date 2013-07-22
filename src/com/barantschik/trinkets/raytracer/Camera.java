package com.barantschik.trinkets.raytracer;

public class Camera
{
	double fov = 110;
	double halfTan = Math.tan(Math.toRadians(fov / 2));
	double pos[];
	double look[];
	double up[];

	public Camera(double[] pos, double[] look, double[] up)
	{
		this.pos = pos;
		this.look = look;
		this.up = GMath.normalize(up);
	}

	public Ray makeRay(double x, double y, double width, double height)
	{
		double[] vecA = GMath.subtract(look, pos);
		double[] vecW = GMath.normalize(vecA);
		double[] vecU = GMath.normalize(GMath.cross(up, vecW));
		double[] vecV = GMath.cross(vecW, vecU);

		double alpha = halfTan * (x - width / 2) / (width / 2);
		double beta = halfTan * (height / 2 - y) / (height / 2);
		
		return new Ray(pos, GMath.normalize(GMath.add(GMath.mult(vecU, alpha), GMath.mult(vecV, beta), GMath.negative(vecW))));
	}

	public void move(double[] move)
	{
		pos[0] += move[0];
		pos[1] += move[1];
		pos[2] += move[2];
	}
}