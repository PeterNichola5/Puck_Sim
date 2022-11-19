package main;

import java.awt.*;
import javax.swing.*;

import object.*;

public class Panel extends JPanel implements Runnable {

    Thread thread;
    Input input = App.input;
    PuckHandler puckHandler = new PuckHandler();

    public Panel() {
        this.setPreferredSize(new Dimension(800, 500));
        this.setBackground(Color.gray);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        final double DRAW_INTERVAL = 1000000000.00 / 60.00;
        //Forces program to only update at 60 FPS
        while(thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / DRAW_INTERVAL;
            lastTime = currentTime;
            if(delta > 1) {
                update();
                repaint();
                delta--;
            }
        }
        
    }

    public void update() {
        puckHandler.update(input.isMousePressed(), input.isMouseReleased(), input.getMouseX(), input.getMouseY());
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2.5f));
        g2.setBackground(Color.LIGHT_GRAY);
        puckHandler.draw(g2);
        input.setMousePressed(false);
        input.setMouseReleased(false);
        g2.dispose();
    }
    
}
