package com.barantschik.trinkets.raytracer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Raytracer extends JFrame implements KeyListener
{
	private final int PIXEL_X = 250, PIXEL_Y = 250;
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	
	private boolean start = false;
	
	private Scene s;
	
	public Raytracer()
	{
		setSize(SIZE_X, SIZE_Y);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);

		addKeyListener(this);

		s = new Scene(true);
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
			buffer = RMath.drawScene(s, buffer);
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
	}

	//Unimplemented methods

	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}
