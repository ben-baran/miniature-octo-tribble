package com.barantschik.trinkets.raytracer;

public class TexMesh implements Renderable
{

	@Override
	public IntersectionData giveIntersection(Ray r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getNormal(double[] point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getShininess() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getReflectivity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float[] getDiffuse(IntersectionData interData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float[] getSpecular() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float[] getAmbient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float[] getEmissive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(double x, double y, double z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transform(M4x4 transform) {
		// TODO Auto-generated method stub
		
	}

}
