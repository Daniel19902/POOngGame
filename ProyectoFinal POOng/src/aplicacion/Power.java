package aplicacion;

import java.awt.*;
import java.io.Serializable;

public abstract class  Power implements Serializable {

    private Rectangle hitBox;

    public Power(){
        hitBox = new Rectangle(441-35,384-35,35,35);
    }

    public Rectangle getHitBox() {
        return this.hitBox;
    }

    public abstract void spell(Ball ball, Racket racket);

}
