package aplicacion;

import java.io.Serializable;

public class Player extends Racket implements Serializable {

    public Player(int x, int y){
        super(x, y);
    }

    public void move(boolean right, Ball ball){
        if(right){
            this.x = x+dx;
        }else this.x = x-dx;
        hitBox.setLocation(this.x,this.y);
    }


}
