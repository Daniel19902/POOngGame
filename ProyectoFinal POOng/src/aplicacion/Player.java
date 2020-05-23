package aplicacion;

import java.io.Serializable;
import java.util.Random;

public class Player extends Racket implements Serializable {

    public Player(int x, int y){
        super(x, y);
    }

    public void move(boolean right, Ball ball){
        if(right){
            this.x = x+dx*2;
        }else this.x = x-dx*2;
        hitBox.setLocation(this.x,this.y);
        score.plusDistance();
    }

    @Override
    public void intersect(Ball ball) {
        int ran = specialMove(ball);
        ball.setDy(-ball.getDy());
        int finY = 0;
        if(ball.getDy()<0) {
            finY = 61;
            ball.setY((int) (ball.getY()-40));
        }
        else {
            ball.setY((int) (ball.getY()+40));
            finY = 762;
        }

        double valX = ball.getX();
        double valY = ball.getY();

        double dx = ran -valX;
        double dy = finY-valY;

        double relation = dx/dy;

        relation = Math.abs(relation);
        if (!(ran > valX)) {
            relation = relation * (-1);
        }
        ball.setDx(relation*Math.abs(ball.getDy()));
        if(isPower != null){
            if(isPower.isPower())isPower.deleteSpell();
        }
    }

    public int specialMove(Ball ball){
        int ran = 0;
        ran = ran + (int) (Math.random()*(650-214)+214);
        return ran;
    }
}
