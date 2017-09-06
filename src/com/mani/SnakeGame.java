package com.mani;
/*
 * Author: Manikanta Challa.
 * CreatedOn 28th Oct 2016
 * Key Listener based snake movements capturing....
 * Runnable Interface implementation for Snake Movement capturing..
 * All parameters depends on Snake Ground (Width,Height)
 * */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class SnakeGame {

	public static void main(String[] args) {
		SnakeScreen sc = new SnakeScreen(1000, 600);
		new Thread(sc).start();
	}

}

class SnakeScreen extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int x = 300;
	private int y = 50;
	private int radius = 10;
	private int snakeSize = 15;
	private int width = 1000;
	private int height = 600;
	private int incX = 10;
	private int incY = 0;

	private int[][] snakePosition ;
	private int[][] previousPosition;

	SnakeScreen(int width, int height) {

		this.width = width;
		this.height = height;
		
		//radius = width / 50;
		//snakeSize = width / 40;
		//this.incX = radius;
		//this.incY = 0;
		//x = this.width / 4;
		//y = this.height / 4;
		
		snakePosition = new int[2][snakeSize];
		previousPosition = new int[2][snakeSize];
		
		setTitle("Snake Game By Mani:)");
		setSize(this.width, this.height);
		setBackground(Color.WHITE);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					incY = -1 * radius;
					incX = 0;
					break;
				case KeyEvent.VK_DOWN:
					incY = radius;
					incX = 0;
					break;
				case KeyEvent.VK_LEFT:
					incX = -1 * radius;
					incY = 0;
					break;
				case KeyEvent.VK_RIGHT:
					incX = radius;
					incY = 0;
					break;
				}

			}
		});

		initSnakePosition();
	}

	private void initSnakePosition() {
		for (int i = 0; i < snakeSize; i++) {
			snakePosition[0][i] = x - (i * radius);
			snakePosition[1][i] = y;
		}
	}

	public void run() {

		while (true) {

			try {

				x += (x > (width - radius))  ?  (-1 * (width - 2*radius)) : (x < 2 * radius) ? width - 2*radius : incX;
				y += (y > (height - radius)) ?  (-1 * (height - 2*radius)) : (y < 2 * radius) ? height - 2*radius : incY;
				repaint();
				Thread.sleep(200);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void paint(Graphics g) {
		drawing(g, x, y, radius, radius);
	}

	public void drawing(Graphics g, int x, int y, int w, int h) {

		previousPosition[0][0] = snakePosition[0][0];
		previousPosition[1][0] = snakePosition[1][0];
		snakePosition[0][0] = x;
		snakePosition[1][0] = y;
		g.setColor(Color.RED);
		g.drawOval(snakePosition[0][0], snakePosition[1][0], w, h);

		for (int i = 1; i < snakeSize; i++) {

			previousPosition[0][i] = snakePosition[0][i];
			previousPosition[1][i] = snakePosition[1][i];

			snakePosition[0][i] = previousPosition[0][i - 1];
			snakePosition[1][i] = previousPosition[1][i - 1];
			g.setColor(Color.GREEN);
			g.drawOval(snakePosition[0][i], snakePosition[1][i], w, h);
		}

		g.setColor(Color.WHITE);
		g.drawOval(previousPosition[0][snakeSize - 1], previousPosition[1][snakeSize - 1], w, h);

	}
}
