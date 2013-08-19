package com.barantschik.trinkets.raytracer.rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Raytracer extends JPanel implements KeyListener
{
	public class RenderThread implements Runnable
	{
		private Raytracer rt;
		
		
		public RenderThread(Raytracer rt)
		{
			this.rt = rt;
		}

		public void run()
		{
			rt.setImage(RMath.drawScene(rt, rt.getScene(), rt.getImage()));
		}	
	}
	
	public class PaintListener implements ActionListener
	{
		private Raytracer rt;
		
		public PaintListener(Raytracer rt)
		{
			this.rt = rt;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			rt.repaint();
		}
		
	}
	
	private final int MILLISECOND_UPDATES = 20;
	private final int PIXEL_X = 1000, PIXEL_Y = 1000;
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	
	private boolean start = false;
	
	private Scene s;
	private BufferedImage curImage = new BufferedImage(PIXEL_X, PIXEL_Y, BufferedImage.TYPE_INT_RGB);
	
	private PaintListener paintListener = new PaintListener(this);
	private Timer timer = new Timer(MILLISECOND_UPDATES, paintListener);
	
	private String renderStatusMessage = "Not Started";
	private DecimalFormat decimalFormat = new DecimalFormat("##0.00");
	
	public Raytracer()
	{
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		setFocusable(true);

		addKeyListener(this);
		
		CenterSampler aa = new CenterSampler();
		s = new Scene(new ScenePreferences(PIXEL_X, PIXEL_Y, aa), true);
		
		timer.setRepeats(true);
		timer.start();
		
		start = true;
		render();
	}

	public void render()
	{
		Thread curThread = new Thread(new RenderThread(this));
		curThread.start();
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(curImage, 0, 0, SIZE_X, SIZE_Y, null);
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			s.getC().move(new double[]{0, 0, 0.1});
			start = true;
			render();
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			s.getC().move(new double[]{0, 0, -0.1});
			start = true;
			render();
		}
		else if(e.getKeyCode() == KeyEvent.VK_A)
		{
			s.getC().move(new double[]{0.1, 0, 0});
			start = true;
			render();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			s.getC().move(new double[]{-0.1, 0, 0});
			start = true;
			render();
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			s.getC().move(new double[]{0, 0.1, 0});
			start = true;
			render();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			s.getC().move(new double[]{0, -0.1, 0});
			start = true;
			render();
		}
		else
		{			
			start = true;
			render();
		}
		System.out.println(s.getC().pos[0] + ", " + s.getC().pos[1] + ", " + s.getC().pos[2]);
	}

	public BufferedImage getImage()
	{
		return curImage;
	}
	
	public void setImage(BufferedImage image)
	{
		curImage = image;
	}
	
	public Scene getScene()
	{
		return s;
	}
	
	public void setScene(Scene s)
	{
		this.s = s;
	}
	
	public void updateRenderStatus(int numColumns)
	{
		renderStatusMessage = "Columns: " + numColumns;
		renderStatusMessage += ", percent complete: " + decimalFormat.format(numColumns * 1.0 / PIXEL_X * 100) + "%";
	}
	
	public String getRenderStatus()
	{
		return renderStatusMessage;
	}
	
	//Unimplemented methods

	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}