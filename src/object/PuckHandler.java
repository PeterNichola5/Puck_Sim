package object;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class PuckHandler {

    double relXVel;
    double relYVel;
    boolean flip;
    Puck puck1;
    Puck puck2;
    ArrayList<Puck> pucks;
    Tether tether;

    public PuckHandler() {
        flip = false;
        pucks = new ArrayList<>();
    }

    public void update(boolean click, boolean release, int x, int y) {
        if(click && pucks.size() < 2) {
            pucks.add(new Puck(x, y));
        }
        if(release) {
            Puck launched = pucks.get(pucks.size() - 1);
            if(!launched.isLaunched()) {
                launched.initVel(x, y);
                pucks.set(pucks.size() - 1, launched);
            }
        }
    }

    public void setPucks(Puck p1, Puck p2){
        if(p1.x <= p2.x) {
            puck1 = p1;
            puck2 = p2;
        }
        else {
            puck1 = p2;
            puck2 = p1;
        }
    }

    public double calcTetherVelDistance(Puck p1, Puck p2) {
        double dSquare = Math.pow(p1.nextPointX() - (p2.x + 25), 2) + Math.pow(p1.nextPointY() - (p2.y + 25), 2);
        return Math.sqrt(dSquare);
    }

    public void collisionCalc() {
        puck1.x += puck1.velX;
        puck1.y += puck1.velY;
        puck2.x += puck2.velX;
        puck2.y += puck2.velY;
        tether = new Tether(puck1.x + 25, puck1.y + 25, puck2.x + 25, puck2.y + 25);
        if(tether.length() < 50) {
            double line = this.calcTetherVelDistance(puck1, puck2);
            double numerator = Math.pow(puck1.totalVel(), 2) + Math.pow(tether.length(), 2) - Math.pow(line, 2);
            double denominator = 2 * puck1.totalVel() * tether.length();
            double p1TAngle = Math.acos(numerator/denominator);
            if(p1TAngle > Math.PI/2) {
                p1TAngle = Math.PI - p1TAngle;
                flip = true;
            }
            double relVel1 = puck1.totalVel() * Math.cos(p1TAngle);
            relXVel = relVel1 * Math.cos(tether.angle());
            relYVel = relVel1 * Math.sin(tether.angle());
            this.relVelDirection(puck1);
            flip = false;
            line = this.calcTetherVelDistance(puck2, puck1);
            numerator = Math.pow(puck2.totalVel(), 2) + Math.pow(tether.length(), 2) - Math.pow(line, 2);
            denominator = 2 * puck2.totalVel() * tether.length();
            double p2TAngle = Math.acos(numerator/denominator);
            if(p2TAngle > Math.PI/2) {
                p2TAngle = Math.PI - p2TAngle;
                flip = true;
            }
            double relVel2 = puck2.totalVel() * Math.cos(p2TAngle);
            relXVel = relVel2 * Math.cos(tether.angle());
            relYVel = relVel2 * Math.sin(tether.angle());
            this.relVelDirection(puck2);
            flip = false;
    
            puck1.setVelX(puck1.getVelX() - puck1.getRelVelX() + puck2.getRelVelX());
            puck1.setVelY(puck1.getVelY() - puck1.getRelVelY() + puck2.getRelVelY());
            puck2.setVelX(puck2.getVelX() - puck2.getRelVelX() + puck1.getRelVelX());
            puck2.setVelY(puck2.getVelY() - puck2.getRelVelY() + puck2.getRelVelY());
        }
    }

    public void relVelDirection(Puck puck) {
        switch(tether.detremineRelativeTetherQuadrant(puck.x + 25)) {
        case 1:
            if(flip) {
                relXVel *= -1;
                relYVel *= -1;
                break;
            }
            break;
        case 2:
            if(flip) {
                relYVel *= -1;
                break;
            }
            relXVel *= -1;
            break;
        case 3:
            if(flip) {
                break;
            }
            relXVel *= -1;
            relYVel *= -1;
            break;
        case 4:
            if(flip) {
                relXVel *= -1;
                break;
            }
            relYVel *= -1;
            break;
        default:
            break;
        }
        puck.setRelVelX(this.relXVel);
        puck.setRelVelY(this.relYVel);
    }



    public void draw(Graphics2D g2) {

        if(pucks.size() > 1) {
            this.setPucks(pucks.get(0), pucks.get(1));
            this.collisionCalc(); 
            for(Puck puck : pucks) {
                if(puck.x <= 0) {
                    puck.x = 0;
                    puck.velX *= -1;
                }
                if(puck.x + 50 >= 800) {
                    puck.x = 750;
                    puck.velX *= -1;
                }
                if(puck.y + 50 >= 500) {
                    puck.y = 450;
                    puck.velY *= -1;
                }
                if(puck.y <= 0) {
                    puck.y = 0;
                    puck.velY *= -1;
                }
                g2.fill(puck);
            }  
        }
        else {
            try {
                Puck onlyPuck = pucks.get(0);
                onlyPuck.x += onlyPuck.velX;
                onlyPuck.y += onlyPuck.velY;
                if(onlyPuck.x <= 0) {
                    onlyPuck.x = 0;
                    onlyPuck.velX *= -1;
                }
                if(onlyPuck.x + 50 >= 800) {
                    onlyPuck.x = 750;
                    onlyPuck.velX *= -1;
                }
                if(onlyPuck.y + 50 >= 500) {
                    onlyPuck.y = 450;
                    onlyPuck.velY *= -1;
                }
                if(onlyPuck.y <= 0) {
                    onlyPuck.y = 0;
                    onlyPuck.velY *= -1;
                }
                g2.fill(onlyPuck);
            }
            catch(IndexOutOfBoundsException e) {
                return;
            }
        }
    }
}
