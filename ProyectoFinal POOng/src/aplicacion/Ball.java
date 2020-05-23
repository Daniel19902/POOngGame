package aplicacion;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Ball {

    public double x = 100;
    public double y = 658;
    public static int width = 35 , height = 35;
    public Rectangle hitBox;
    public double dx = 0, dy = 3;
    public boolean isPause = true;
    public boolean isPower = false;
    public boolean colder = false;

    public Ball(int x, int y){
        this.x = x;
        this.y = y;
        hitBox = new Rectangle(x,y,width,height);
    }

    /** give spell*/
    public void fastBall(){
        if (dy < 0) dy -=1;
        else dy +=1;
    }

    public void colder(Racket r){
        if(colder) {
            r.setDx(0);
            setColder(false);
            Timer timer = new Timer();
            TimerTask cold = new TimerTask() {
                @Override
                public void run() {
                    r.setDx(2);
                }
            };
            timer.schedule(cold, 3000);
        }
    }

    /** move things*/
    public abstract void move();

    /** set things*/
    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setIsPause(boolean pause){
        this.isPause = pause;
    }

    /** get things*/
    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setColder(boolean cold){
        this.colder = cold;
    }

    public abstract int getSpeed();

}
