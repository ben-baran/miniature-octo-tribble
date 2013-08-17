package com.barantschik.trinkets.raytracer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Raytracer extends JPanel implements KeyListener
{
	private final int PIXEL_X = 100, PIXEL_Y = 100;
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	
	private boolean start = false;
	
	private Scene s;
	private Image curImage = createImage(PIXEL_X, PIXEL_Y);
	
	public Raytracer()
	{
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		setFocusable(true);

		addKeyListener(this);

		CenterSampler aa = new CenterSampler();
		s = new Scene(new ScenePreferences(PIXEL_X, PIXEL_Y, aa), true);
	}

	public void paintComponent(Graphics g)
	{
		bufferPaint(g);
	}

	public void bufferPaint(Graphics h)
	{
		curImage = createImage(PIXEL_X, PIXEL_Y);
		Graphics g = curImage.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0,  0, PIXEL_X, PIXEL_Y);
		
		if(start)
		{
			curImage = RMath.drawScene(s, curImage);
		}
		
		h.drawImage(curImage, 0, 0, SIZE_X, SIZE_Y, null);
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			s.getC().move(new double[]{0, 0, 0.1});
			start = true;
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			s.getC().move(new double[]{0, 0, -0.1});
			start = true;
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			s.getC().move(new double[]{0.1, 0, 0});
			start = true;
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			s.getC().move(new double[]{-0.1, 0, 0});
			start = true;
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			s.getC().move(new double[]{0, 0.1, 0});
			start = true;
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			s.getC().move(new double[]{0, -0.1, 0});
			start = true;
			repaint();
		}
		else
		{			
			start = true;
			repaint();
		}
		System.out.println(s.getC().pos[0] + ", " + s.getC().pos[1] + ", " + s.getC().pos[2]);
	}

	//Unimplemented methods

	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}