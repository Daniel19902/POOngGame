package aplicacion;

public abstract class Bot extends Racket {

    public Bot(int x, int y) {
        super(x,y);
    }

    public abstract void move(boolean right, Ball ball);

}
