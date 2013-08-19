package com.barantschik.trinkets.raytracer.rendering;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class TexMesh implements Renderable
{
	TexTriangle[] triangles;
	
	public TexMesh(TexTriangle[] triangles)
	{
		this.triangles = triangles;
	}
	
	public void setMaterial(Material m)
	{
		for(int i = 0; i < triangles.length; i++)
		{
			triangles[i].setMaterial(m);
		}
	}
	
	public IntersectionData giveIntersection(Ray r)
	{	
		return RMath.findIntersection(r, triangles);
	}
	
	public double[] getNormal(double[] point)
	{
		return triangles[0].getNormal(point);
	}

	public double getShininess()
	{
		return triangles[0].getShininess();
	}
	
	public float getReflectivity()
	{
		return triangles[0].getReflectivity();
	}

	public float[] getDiffuse(IntersectionData interData)
	{
		return triangles[0].getDiffuse(interData);
	}

	public float[] getSpecular()
	{
		return triangles[0].getSpecular();
	}

	public float[] getAmbient()
	{
		return triangles[0].getAmbient();
	}

	public float[] getEmissive()
	{
		return triangles[0].getEmissive();
	}

	public void move(double x, double y, double z)
	{
		for(int i = 0; i < triangles.length; i++)
		{
			triangles[i].move(x, y, z);
		}
	}

	public void transform(M4x4 transform)
	{
		for(int i = 0; i < triangles.length; i++)
		{
			triangles[i].transform(transform);
		}
	}

	public float getTransmission()
	{
		return triangles[0].getTransmission();
	}
	
	public float getIndexOfRefraction()
	{
		return triangles[0].getIndexOfRefraction();
	}
	
	public static TexMesh createFromFile(File f, String fType, BufferedImage image)
	{
		TexTriangle[] triangles = null;
		
		if(fType.toLowerCase().equals("obj"))
		{
			double[][] vertices, textureCoords;
			
			Scanner scan = null;
			try
			{
				scan = new Scanner(f);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			
			int numVertices = 0, numTexCoords = 0, numTris = 0;
			
			while(scan.hasNext())
			{
				StringTokenizer curLineTokenizer = new StringTokenizer(scan.nextLine());
				if(curLineTokenizer.hasMoreTokens())
				{					
					String curStart = curLineTokenizer.nextToken();
					if(curStart.equals("v")) numVertices++;
					else if(curStart.equals("vt")) numTexCoords++;
					else if(curStart.equals("f")) numTris++;
				}
			}
			
			triangles = new TexTriangle[numTris];
			vertices = new double[numVertices][3];
			textureCoords = new double[numTexCoords][2];
			
			try
			{
				scan = new Scanner(f);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			
			int curNumVertices = 0, curNumTexCoords = 0, curNumTris = 0;
			while(scan.hasNext())
			{
				StringTokenizer curLineTokenizer = new StringTokenizer(scan.nextLine());
				if(curLineTokenizer.hasMoreTokens())
				{
					String start = curLineTokenizer.nextToken();
					
					if(start.equals("v"))
					{
						vertices[curNumVertices][0] = Double.parseDouble(curLineTokenizer.nextToken());
						vertices[curNumVertices][1] = Double.parseDouble(curLineTokenizer.nextToken());
						vertices[curNumVertices][2] = Double.parseDouble(curLineTokenizer.nextToken());
						
						curNumVertices++;
					}
					else if(start.equals("vt"))
					{
						textureCoords[curNumTexCoords][0] = Double.parseDouble(curLineTokenizer.nextToken());
						textureCoords[curNumTexCoords][1] = Double.parseDouble(curLineTokenizer.nextToken());
						
						curNumTexCoords++;
					}
					else if(start.equals("f"))
					{
						int[] vID = new int[3], tID = new int[3];
						for(int i = 0; i < 3; i++)
						{
							String curToken = curLineTokenizer.nextToken();
							vID[i] = Integer.parseInt(curToken.substring(0, curToken.indexOf('/')));
							tID[i] = Integer.parseInt(curToken.substring(curToken.indexOf('/') + 1));
						}
						
						triangles[curNumTris] = new TexTriangle(vertices[vID[0] - 1], vertices[vID[1] - 1], vertices[vID[2] - 1], textureCoords[tID[0] - 1], textureCoords[tID[1] - 1], textureCoords[tID[2] - 1], image);
					
						curNumTris++;
					}
				}
			}
		}
		
		return new TexMesh(triangles);
	}
}
