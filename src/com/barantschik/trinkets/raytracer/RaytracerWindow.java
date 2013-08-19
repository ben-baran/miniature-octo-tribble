package com.barantschik.trinkets.raytracer;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.barantschik.trinkets.Container;
import com.barantschik.trinkets.raytracer.rendering.Raytracer;

public class RaytracerWindow extends Container
{
	private Raytracer raytracer = new Raytracer();
	
	public RaytracerWindow()
	{
		setTitle("Raytracer");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().add(raytracer);
		
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0)
	{
		
	}
	
}
