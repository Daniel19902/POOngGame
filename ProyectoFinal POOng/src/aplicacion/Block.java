package aplicacion;


import java.awt.*;
import java.util.ArrayList;

public class Block {

    private int xPos;
    private int yPos;
    private int weight = 40;
    private int height = 40;
    private Rectangle hitBox;
    private boolean isBlock = true;
    private int dy = 1;

    public Block(int xPos, int yPos, int dy){
        this.xPos = xPos;
        this.yPos = yPos;
        this.dy = dy;
        hitBox = new Rectangle(weight,height);

    }

    public void move(Ball ball, ArrayList<Racket> rackets){
        if(isBlock){
            intersect(rackets);yPos+=dy;
            if(yPos>700 || yPos<0)isBlock = false;
            hitBox.setLocation(xPos, yPos);
        }
    }

    public void intersect(ArrayList<Racket> rackets){
        for(Racket r : rackets){
            if(r.getHitBox().intersects(getHitBox())){
                r.getScore().setFortaleza(-r.getScore().getFortaleza()*50/100);
                isBlock = false;
            }
        }
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public int getYPos() {
        return yPos;
    }

    public int getXPos() {
        return xPos;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isBlock(){
        return this.isBlock;
    }

}
