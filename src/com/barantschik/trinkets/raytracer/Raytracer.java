package com.barantschik.trinkets.raytracer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Raytracer extends JFrame implements KeyListener
{
	private final int PIXEL_X = 500, PIXEL_Y = 500;
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	
	private boolean start = false;
	private Camera c;
	private Renderable renderable[] = new Renderable[6];
	private Light lights[] = new Light[1];

	public static void main(String[] args)
	{
		new Raytracer();
	}

	public Raytracer()
	{
		setSize(SIZE_X, SIZE_Y);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);

		addKeyListener(this);
		c = new Camera(new double[]{-2.9, 0, 7.2}, new double[]{0, 0, 10}, new double[]{0, 1, 0});
		
		renderable[0] = new Sphere(new double[]{2.5, 0, 15}, 1.5, new float[]{0.7f, 0, 0});
		renderable[1] = new Sphere(new double[]{0, 0, 10}, 1.5, new float[]{0, 0.7f, 0});
		renderable[2] = new Sphere(new double[]{-2.5, 0, 15}, 1.5, new float[]{0, 0, 0.7f});
		renderable[3] = new Sphere(new double[]{0, 2.5, 15}, 1.5, new float[]{.6f, 0.6f, 0});
		renderable[4] = new Sphere(new double[]{0, -2.5, 15}, 1.5, new float[]{0, 0.6f, 0.6f});
		renderable[5] = new Triangle(new double[]{0, -15, 0}, new double[]{0, -15, 1}, new double[]{1, -15, 0}, new float[]{1, 0, 0});
		
		lights[0] = new Light(new double[]{0, 0, 0}, new double[]{1, 1, 1});
	}

	public void paint(Graphics g)
	{
		bufferPaint(g);
	}

	public void bufferPaint(Graphics h)
	{
		Image buffer = createImage(PIXEL_X, PIXEL_Y);
		Graphics g = buffer.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0,  0, PIXEL_X, PIXEL_Y);
		
		if(start)
		{
			buffer = RMath.drawScene(c, lights, renderable, buffer);
		}
		
		h.drawImage(buffer, 0, 0, SIZE_X, SIZE_Y, null);
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
		else if(e.getKeyCode() == KeyEvent.VK_W)
		{
			c.move(new double[]{0, 0, 0.1});
			start = true;
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			c.move(new double[]{0, 0, -0.1});
			start = true;
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			c.move(new double[]{0.1, 0, 0});
			start = true;
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			c.move(new double[]{-0.1, 0, 0});
			start = true;
			repaint();
		}
		else
		{			
			start = true;
			repaint();
		}
	}

	//Unimplemented methods

	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}
