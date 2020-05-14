package aplicacion;

import java.awt.*;
import java.io.Serializable;


public abstract class Racket  implements Serializable {

    public int x;
    public int y;
    public Rectangle hitBox;
    public int dx = 4, dy = 2;
    public static int width = 100, height = 80;
    public Score score;

    public Racket(){
        x=0;
        y=0;
        score= null;
        hitBox = null;
    }

    public Racket(int x, int y){
        this();
        this.x = x;
        this.y = y;
        score = new Score(100,this.y);
        hitBox = new Rectangle(x,y,width,height);
    }

    /**add powers*/
    public void freezer(int freeze) {
        this.dx = freeze;
    }

    public void noFreezer(){
        this.dx = 5;
    }

    /** move things*/

    public abstract void move(boolean right, Ball ball);

    /** get things*/

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Score getScore(){
        return this.score;
    }


    /** set things*/

    public void setScore(int score) {
        this.score.setScore(score);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHitBox(){
        hitBox.setLocation(this.x,this.y);
    }


}
