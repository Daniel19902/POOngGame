package aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Ball  implements Serializable {

    private double x = 100;
    private double y = 658;
    private static int width = 35 , height = 35;
    private Rectangle hitBox;
    private double dx = 0, dy = 3;

    public Ball(int x, int y){
        this.x = x;
        this.y = y;
        hitBox = new Rectangle(x,y,width,height);
    }

    /** give spell*/
    public void fastBall(){
        if (dy < 0) dy--;
        else dy++;
    }

    /** move things*/
    public void move(){
        if(y>768) dy = -dy;
        if(y<0) dy = -dy;
        y += dy;x += dx;
        hitBox.setLocation((int) x,(int)y);
    }

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

}
