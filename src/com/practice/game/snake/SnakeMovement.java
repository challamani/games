package com.practice.game.snake;
/*
 * Author: Manikanta Challa.
 * CreatedOn 28th Oct 2016
 * Key Listener based snake movements capturing....
 * Runnable Interface implementation for Snake Movement capturing.
 * All parameters depending on Snake Ground (Width,Height)
 * */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class SnakeMovement {
	public static void main(String[] args) {
		SnakeScreen sc = new SnakeScreen(900, 900, 20);
		new Thread(sc).start();
	}
}

class SnakeScreen extends JFrame implements Runnable {

	private int x = 300;
	private int y = 50;
	private int radius = 15;
	private int size;
	private int width;
	private int height;
	private int incX = 15;
	private int incY = 0;

	private int[][] snake;
	private int[][] previous;

	SnakeScreen(int width, int height, int s) {
		this.width = width;
		this.height = height;
		this.size = s;
		snake = new int[this.size][2];
		previous = new int[this.size][2];

		setTitle("Snake game implemented by @MyTechHub3");
		setSize(this.width, this.height);
		setBackground(Color.BLACK);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_DOWN:
						incY = radius;
						incX = 0;
						break;
					case KeyEvent.VK_UP:
						incY = -1 * radius;
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
		for (int i = 0; i < size; i++) {
			snake[i][0] = x - (i * radius);
			snake[i][1] = y;
		}
	}

	@Override
	public void run(){
		while (true){
			try{
				x += (x > (width-radius))?(-1 * (width-2*radius)):
						x<2*radius?(width-2*radius):incX;
				y += (y >(height-radius))?(-1 * (height - 2*radius)):
						y<2*radius?(height-2*radius):incY;
				repaint();//invokes paint method
				Thread.sleep(200);
			}catch (InterruptedException ie){
				System.out.println("failed at snake auto path delay" +
						""+ie.getMessage());
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		drawing(g, x, y, radius, radius);
	}

	//the actual logic
	public void drawing(Graphics g, int x, int y, int w, int h){
		//Here I'm following ant leader and followers algo
		//Hold the previous position of snake head
		previous[0][0] = snake[0][0];
		previous[0][1] = snake[0][1];

		snake[0][0] = x;
		snake[0][1] = y;

		g.setColor(Color.MAGENTA);
		g.drawOval(x,y,w,h);
		g.fillOval(x,y,w,h);

		for(int i=1; i<size;i++){
			previous[i][0] = snake[i][0];
			previous[i][1] = snake[i][1];

			snake[i][0] = previous[i-1][0];
			snake[i][1] = previous[i-1][1];
			g.setColor(Color.GREEN);
			g.drawOval(snake[i][0],snake[i][1],w,h);
			g.fillOval(snake[i][0],snake[i][1],w,h);
		}

		g.setColor(Color.BLACK);
		g.drawOval(snake[size-1][0],snake[size-1][1],w,h);
		g.fillOval(snake[size-1][0],snake[size-1][1],w,h);
	}

}
