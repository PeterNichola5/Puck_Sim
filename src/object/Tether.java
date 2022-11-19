package object;
import java.awt.geom.Line2D;

public class Tether extends Line2D.Double {
    
    public Tether(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    public double length() {
        double x = this.x2 - this.x1;
        double y = this.y2 - this.y1;
        x = Math.pow(x, 2);
        y = Math.pow(y, 2);
        double lengthSquared = x + y;
        return Math.sqrt(lengthSquared);
    }

    public double angle() {
        double x = Math.abs(this.x2 - this.x1);
        double y = Math.abs(this.y2 - this.y1);
        if(x == 0) {
            return Math.PI/2;
        }
        return Math.atan(y/x);
    }


    public int detremineRelativeTetherQuadrant(double x) {
        double startX;
        double endX;
        double startY;
        double endY;
        if(x == this.x1) {
            startX = this.x1;
            startY = this.y1;
            endX = this.x2;
            endY = this.y2;
        }
        else if(x == this.x2) {
            startX = this.x2;
            startY = this.y2;
            endX = this.x1;
            endY = this.y1;
        }
        else {return -1;}

        if(endX - startX > 0) {
            if(endY - startY > 0) {
                return 1;
            }
            return 4;
        }
        if(endY - startY > 0) {
            return 2;
        }
        return 3;
    }

    public double slope() {
        return (y2-y1)/(x2-x1);
    }
}
