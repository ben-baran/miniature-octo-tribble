package com.barantschik.trinkets.raytracer.rendering;


public enum MPreset
{
	AMBIENT_RED(GMath.empty(), GMath.empty(), 0, 0, 0, 0, new float[]{1, 0, 0}, GMath.empty()),
	AMBIENT_GREEN(GMath.empty(), GMath.empty(), 0, 0, 0, 0, new float[]{0, 1, 0}, GMath.empty()),
	AMBIENT_BLUE(GMath.empty(), GMath.empty(), 0, 0, 0, 0, new float[]{0, 0, 1}, GMath.empty()),
	AMBIENT_CYAN(GMath.empty(), GMath.empty(), 0, 0, 0, 0, new float[]{0, 1, 1}, GMath.empty()),
	AMBIENT_MAGENTA(GMath.empty(), GMath.empty(), 0, 0, 0, 0, new float[]{1, 0, 1}, GMath.empty()),
	AMBIENT_YELLOW(GMath.empty(), GMath.empty(), 0, 0, 0, 0, new float[]{1, 1, 0}, GMath.empty()),
	AMBIENT_GREY(GMath.empty(), GMath.empty(), 0, 0, 0, 0, new float[]{0.5f, 0.5f, 0.5f}, GMath.empty()),
	
	LAMBERTIAN_RED(new float[]{1, 0, 0}, GMath.empty(), 0, 0, 0, 0, GMath.empty(), GMath.empty()),
	LAMBERTIAN_GREEN(new float[]{0, 1, 0}, GMath.empty(), 0, 0, 0, 0, GMath.empty(), GMath.empty()),
	LAMBERTIAN_BLUE(new float[]{0, 0, 1}, GMath.empty(), 0, 0, 0, 0, GMath.empty(), GMath.empty()),
	LAMBERTIAN_CYAN(new float[]{0, 1, 1}, GMath.empty(), 0, 0, 0, 0, GMath.empty(), GMath.empty()),
	LAMBERTIAN_MAGENTA(new float[]{1, 0, 1}, GMath.empty(), 0, 0, 0, 0, GMath.empty(), GMath.empty()),
	LAMBERTIAN_YELLOW(new float[]{1, 1, 0}, GMath.empty(), 0, 0, 0, 0, GMath.empty(), GMath.empty()),
	LAMBERTIAN_GREY(new float[]{0.5f, 0.5f, 0.5f}, GMath.empty(), 0, 0, 0, 0, GMath.empty(), GMath.empty()),
	
	PHONG_RED(new float[]{1, 0, 0}, GMath.full(), 100, 0, 0, 0, GMath.empty(), GMath.empty()),
	PHONG_GREEN(new float[]{0, 1, 0}, GMath.full(), 100, 0, 0, 0, GMath.empty(), GMath.empty()),
	PHONG_BLUE(new float[]{0, 0, 1}, GMath.full(), 100, 0, 0, 0, GMath.empty(), GMath.empty()),
	PHONG_CYAN(new float[]{0, 1, 1}, GMath.full(), 100, 0, 0, 0, GMath.empty(), GMath.empty()),
	PHONG_MAGENTA(new float[]{1, 0, 1}, GMath.full(), 100, 0, 0, 0, GMath.empty(), GMath.empty()),
	PHONG_YELLOW(new float[]{1, 1, 0}, GMath.full(), 100, 0, 0, 0, GMath.empty(), GMath.empty()),
	PHONG_GREY(new float[]{0.5f, 0.5f, 0.5f}, GMath.full(), 100, 0, 0, 0, GMath.empty(), GMath.empty()),
	
	COMPLETE_REFLECTION(GMath.empty(), GMath.empty(), 0, 1, 0, 1, GMath.empty(), GMath.empty()),
	COMPLETE_REFRACTION(GMath.empty(), GMath.empty(), 0, 0, 1, 1.5f, GMath.empty(), GMath.empty()),
	
	REFLECTIVE_RED(new float[]{0.5f, 0, 0}, GMath.full(), 100, 0.5f, 0, 0, GMath.empty(), GMath.empty()),
	REFLECTIVE_GREEN(new float[]{0, 0.5f, 0}, GMath.full(), 100, 0.5f, 0, 0, GMath.empty(), GMath.empty()),
	REFLECTIVE_BLUE(new float[]{0, 0, 0.5f}, GMath.full(), 100, 0.5f, 0, 0, GMath.empty(), GMath.empty()),
	REFLECTIVE_CYAN(new float[]{0, 0.5f, 0.5f}, GMath.full(), 100, 0.5f, 0, 0, GMath.empty(), GMath.empty()),
	REFLECTIVE_MAGENTA(new float[]{0.5f, 0, 0.5f}, GMath.full(), 100, 0.5f, 0, 0, GMath.empty(), GMath.empty()),
	REFLECTIVE_YELLOW(new float[]{0.5f, 0.5f, 0}, GMath.full(), 100, 0.5f, 0, 0, GMath.empty(), GMath.empty()),
	REFLECTIVE_GREY(new float[]{0.25f, 0.25f, 0.25f}, GMath.full(), 100, 0.5f, 0, 0, GMath.empty(), GMath.empty());
	
	private Material m = new Material();
	
	MPreset(float[] diffuse,
			float[] specular,
	
			double shininess,
	
			float reflectivity,
			float transmission,
			float indexOfRefraction,

			float[] ambient,
			float[] emmissive)
	{
		m.diffuse = diffuse;
		m.specular = specular;
		
		m.shininess = shininess;
		
		m.reflectivity = reflectivity;
		m.transmission = transmission;
		m.indexOfRefraction = indexOfRefraction;
		
		m.ambient = ambient;
		m.emmissive = emmissive;
	}
	
	public Material getMaterial()
	{
		return m;
	}
}