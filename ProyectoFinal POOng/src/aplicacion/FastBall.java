package aplicacion;

import java.io.Serializable;

public class FastBall extends Power implements Serializable {

    @Override
    public void spell(Ball ball,Racket racket) {
        ball.fastBall();
    }
}
