package com.barantschik.trinkets.raytracer;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import com.barantschik.trinkets.raytracer.rendering.Raytracer;

public class RenderAndInfo extends JPanel
{
	private RenderInfo ri;
	private Raytracer rt;
	
	public RenderAndInfo()
	{
		setPreferredSize(new Dimension(1000, 1025));
		setFocusable(true);
		
		ri = new RenderInfo(this);
		rt = new Raytracer();
		
		setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
		
		add(rt);
		add(ri);
	}
	
	public RenderInfo getRenderInfo()
	{
		return ri;
	}
	
	public Raytracer getRaytracer()
	{
		return rt;
	}
}
