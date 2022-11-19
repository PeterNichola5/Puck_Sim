package object;
import java.awt.geom.Ellipse2D;

public class Puck extends Ellipse2D.Double {

    double velX = 0;
    double velY = 0;
    double relVelX;
    double relVelY;
    boolean launched = false;

    public boolean isLaunched() {
        return this.launched;
    }

    public double getRelVelX() {
        return this.relVelX;
    }
    public void setRelVelX(double relVelX) {
        this.relVelX = relVelX;
    }

    public double getRelVelY() {
        return this.relVelY;
    }
    public void setRelVelY(double relVelY) {
        this.relVelY = relVelY;
    }

    public double totalVel() {
        double length = Math.pow(velX, 2) + Math.pow(velY, 2);
        return Math.sqrt(length);
    }

    public double totalVelAngle() {
        if(velX == 0) {
            return Math.PI/2;
        }
        return Math.atan(Math.abs(velY)/Math.abs(velX));
    }

    public double nextPointY() {
        return this.y + 25 + velY;
    }
    public double nextPointX() {
        return this.x + 25 + velX;
    }

    public int determineVelQuadrant() {
        if(velX > 0) {
            if(velY > 0) {
                return 1;
            }
            return 4;
        }
        else {
            if(velY > 0) {
                return 2;
            }
            return 3;
        }
    }


    public double getVelX() {
        return this.velX;
    }
    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return this.velY;
    }
    public void setVelY(double velY) {
        this.velY = velY;
    }

    public Puck(double x, double y) {
        super(x - 25, y - 25, 50, 50);
    }

    public void initVel(int x, int y) {
        velX = (x - 25 - this.x) / 30;
        velY = (y - 25 - this.y) / 30; 
        launched = true;
    }

    
}
