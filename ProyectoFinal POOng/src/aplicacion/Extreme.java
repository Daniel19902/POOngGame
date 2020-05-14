package aplicacion;

public class Extreme extends Bot{

    public Extreme(int x, int y){
        super(x,y);

    }

    @Override
    public void move(boolean right, Ball ball) {
        x = (int) ball.getX();

        /**
        if(ball.getDx() < 0) {
            if (this.x > ball.getX())
                x -= dy;
            else x += dy;
        }
        else if(ball.getDx() > 0) {
            if(x > ball.getX()) {
                x+=dy;
            }
            else {
                x -= dy;
            }
        }
         */
        hitBox.setLocation(this.x,this.y);

    }
}
