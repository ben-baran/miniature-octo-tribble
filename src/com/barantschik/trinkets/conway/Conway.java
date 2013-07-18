package com.barantschik.trinkets.conway;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Conway extends JPanel implements MouseListener, MouseMotionListener, KeyListener, ActionListener
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	
	private int numX = 100, numY = 100;
	private int blockX = SIZE_X / numX, blockY = SIZE_Y / numY;
	private boolean running = false;
	private boolean[][] map = new boolean[numX][numY];
	private boolean[][] premap = new boolean[numX][numY];
	
	private boolean wrap = true;
	
	private Timer time = new Timer(0, this);
	
	public Conway()
	{
		setSize(SIZE_X, SIZE_Y);
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		setFocusable(true);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		
		time.setRepeats(true);
	}

	public Conway(File f)
	{
		this();
		
		Scanner s = null;
		try
		{
			s = new Scanner(f);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		numX = Integer.parseInt(s.nextLine());
		numY = Integer.parseInt(s.nextLine());
		blockX = SIZE_X / numX;
		blockY = SIZE_Y / numY;
		
		map = new boolean[numX][numY];
		premap = new boolean[numX][numY];
		
		for(int i = 0; i < numY; i++)
		{
			String curLine = s.nextLine();
			for(int j = 0; j < numX; j++)
			{
				if(curLine.charAt(j) == '1')
				{
					map[j][i] = true;
					premap[j][i] = true;
				}
				else
				{
					map[j][i] = false;
					premap[j][i] = false;
				}
			}
		}
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		update();
		repaint();
	}
	
	public void update()
	{
		if(wrap)
		{
			for(int i = 0; i < premap.length; i++)
			{
				for(int j = 0; j < premap[0].length; j++)
				{
					int numAlive = getWrapNumNeighbors(i, j);
					if(premap[i][j] == true)
					{
						if(!(numAlive == 2 || numAlive == 3)) premap[i][j] = false;
					}
					else if(numAlive == 3)
					{
						premap[i][j] = true;
					}
				}
			}
		}
		else
		{			
			for(int i = 0; i < premap.length; i++)
			{
				for(int j = 0; j < premap[0].length; j++)
				{
					int numAlive = getNumNeighbors(i, j);
					if(premap[i][j] == true)
					{
						if(!(numAlive == 2 || numAlive == 3)) premap[i][j] = false;
					}
					else if(numAlive == 3)
					{
						premap[i][j] = true;
					}
				}
			}
		}
		for(int i = 0; i < premap.length; i++)
		{
			for(int j = 0; j < premap[0].length; j++)
			{
				map[i][j] = premap[i][j];
			}
		}
	}
	
	public int getNumNeighbors(int x, int y)
	{
		int num = 0;
		
		for(int i = -1; i < 2; i++)
		{
			for(int j = -1; j < 2; j++)
			{
				if(!(i == 0 && j == 0))
				{
					if(alive(x + i, y + j)) num++;
				} 
			}
		}
		
		return num;
	}
	
	public int getWrapNumNeighbors(int x, int y)
	{
		int num = 0;
		
		for(int i = -1; i < 2; i++)
		{
			for(int j = -1; j < 2; j++)
			{
				if(!(i == 0 && j == 0))
				{
					if(aliveWrap(x + i, y + j)) num++;
				} 
			}
		}
		
		return num;
	}
	
	public boolean alive(int x, int y)
	{
		if(x != -1 && x != numX && y != -1 && y != numY)
		{
			return map[x][y];
		}
		return false;
	}
	
	public boolean aliveWrap(int x, int y)
	{
		if(x == -1) x = numX - 1;
		else if(x == numX) x = 0;
		if(y == -1) y = numY - 1;
		else if(y == numY) y = 0;
		return map[x][y];
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE_X, SIZE_Y);
		g.setColor(Color.BLACK);
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[0].length; j++)
			{
				g.drawRect(i * blockX, j * blockY, blockX, blockY);
				if(map[i][j] == true) g.fillRect(i * blockX, j * blockY, blockX, blockY);
			}
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			running ^= true;
			if(!running) time.stop();
			else time.start();
		}
	}
	public void mouseClicked(MouseEvent e)
	{
		if(!running)
		{			
			int selectedX = e.getX() / blockX, selectedY = e.getY() / blockY;
			map[selectedX][selectedY] ^= true;
			premap[selectedX][selectedY] = map[selectedX][selectedY];
			repaint();
		}
	}
	
	public void mouseDragged(MouseEvent e)
	{
		if(!running)
		{			
			int selectedX = e.getX() / blockX, selectedY = e.getY() / blockY;
			map[selectedX][selectedY] = true;
			premap[selectedX][selectedY] = map[selectedX][selectedY];
			repaint();
		}
	}
	
	public Timer getTimer()
	{
		return time;
	}
	
	//Unimplemented methods
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
}