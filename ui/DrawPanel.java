package ui;

import javax.swing.*;

import java.awt.Graphics;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel {
	
	private DrawerThread dt;
	
	private int originx = 0;
	private int originy = 0;
	private int zoom = 0;
	
	public DrawPanel() {
		setFocusable(true);
		requestFocusInWindow();
		dt = new DrawerThread(this);
	}
	
	public void addListener(LifeController listener){
		addComponentListener(listener);
		addMouseMotionListener(listener);
		addMouseWheelListener(listener);
		addMouseListener(listener);
		addKeyListener(listener);
	}

	public void start() {
		applyTransform();
		dt.setDim(getWidth(), getHeight());
		Thread t = new Thread(dt);
		t.start();		
	}
	
	private void applyTransform(){
		dt.setTransform(originx, originy, zoom);
		dt.requestAnimFrame();
	}
	
	public DrawerThread getDrawer(){
		return dt;
	}
	
	public void onResize() {
		dt.setDim(getWidth(), getHeight());
		dt.requestAnimFrame();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		synchronized(dt.imageLock){
			if (dt.getImage() != null) {
				g.drawImage(dt.getImage(), 0, 0, null);				
			}
		}
	}

	public void translate(int x, int y){
		originx += x;
		originy += y;
		applyTransform();
	}

	public void zoomIn(int i, int x, int y) {
		//TODO prevent from zooming too far
		zoom += i;
		originx = ((originx - x) << i) + x;
		originy = ((originy - y) << i) + y;
		applyTransform();
	}

	public void zoomOut(int i, int x, int y) {
		zoom -= i;
		originx = ((originx - x) >> i) + x;
		originy = ((originy - y) >> i) + y;
		applyTransform();
	}
	
}