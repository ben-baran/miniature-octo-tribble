package com.barantschik.trinkets.raytracer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.barantschik.trinkets.raytracer.rendering.Raytracer;

public class RenderInfo extends JPanel implements ActionListener
{
	private int MILLISECOND_UPDATES = 50;
	
	private JLabel label = new JLabel("");
	private RenderAndInfo parent;
	
	private Timer timer = new Timer(MILLISECOND_UPDATES, this);
	
	public RenderInfo(RenderAndInfo parent)
	{
		timer.setRepeats(true);
		timer.start();
		
		this.parent = parent;
		
		setPreferredSize(new Dimension(1000, 25));
		
		setLayout(new FlowLayout());
		add(label);
	}
	
	public String getRenderStatus()
	{
		Raytracer curRayTrace = parent.getRaytracer();
		if(curRayTrace != null)
		{
			return curRayTrace.getRenderStatus();
		}
		else
		{
			return "";
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		label.setText(getRenderStatus());
	}
}
