package aplicacion;

public class Slow extends Ball {

    private int speed = 3;

    public Slow(int x, int y) {
        super(x, y);
        setDy(speed);
    }

    public void move(){
        y += dy;x += dx;
        hitBox.setLocation((int) x,(int)y);
    }


    public int getSpeed(){
        return this.speed;
    }

}
