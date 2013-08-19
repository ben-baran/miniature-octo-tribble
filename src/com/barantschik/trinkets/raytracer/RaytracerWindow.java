package com.barantschik.trinkets.raytracer;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.barantschik.trinkets.Container;
import com.barantschik.trinkets.pathfinder.PathWindow;
import com.barantschik.trinkets.raytracer.rendering.Raytracer;

public class RaytracerWindow extends Container
{
	private RenderAndInfo renderAndInfo = new RenderAndInfo();
	private Raytracer raytracer = renderAndInfo.getRaytracer();
	private JFileChooser fc;
	
	public RaytracerWindow()
	{
		setTitle("Raytracer");
		
		addMenu("File", new String[]{"New Window", "Save Image"});
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().add(renderAndInfo);
		
		pack();
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("New Window"))
		{
			new RaytracerWindow();
		}
		else if(e.getActionCommand().equals("Save Image"))
		{
			fc = new JFileChooser("./res/Raytracer/images/usr");
			int accepted = fc.showSaveDialog(RaytracerWindow.this);
			if(accepted == JFileChooser.APPROVE_OPTION)
			{
				File f = fc.getSelectedFile();
				try
				{
					if(!f.exists()) f.createNewFile();
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
				}

				BufferedImage image = raytracer.getImage();;
				try
				{
					ImageIO.write(image, "PNG", f);
				}
				catch (IOException exception)
				{
					exception.printStackTrace();
				}
			}
		}
	}
	
}
