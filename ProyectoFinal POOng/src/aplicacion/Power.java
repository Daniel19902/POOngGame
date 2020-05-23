package aplicacion;

import java.awt.*;


public abstract class  Power{

    public Rectangle hitBox;
    public boolean power =  false;
    public int x = 441-35;
    public int y = 384-35;
    public int w = 35;
    public int h = 35;

    public Power(){
        hitBox = new Rectangle(x,y,w,h);
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }

    public abstract void spell(Table table,int index);

    public abstract void deleteSpell();

    public boolean isPower() {
        return power;
    }
}
