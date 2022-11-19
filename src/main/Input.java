package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Input extends MouseAdapter {
    
    private boolean mousePressed = false;
    private boolean mouseReleased = false;
    private boolean mouseClicked = false;
    private int mouseX = 0;
    private int mouseY = 0;

    public boolean isMousePressed() {
        return this.mousePressed;
    }
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }
    public void setMouseClicked(boolean mouseClicked) {
        this.mouseClicked = mouseClicked;
    }

    public boolean isMouseReleased() {
        return this.mouseReleased;
    }
    public void setMouseReleased(boolean mouseReleased) {
        this.mouseReleased = mouseReleased;
    }

    public int getMouseX() {
        return this.mouseX;
    }
    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return this.mouseY;
    }
    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
        mouseClicked = true;
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked = false;
        mouseReleased = true;
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
