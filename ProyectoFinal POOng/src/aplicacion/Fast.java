package aplicacion;

public class Fast extends Ball {

    private int speed = 6;

    public Fast(int x, int y) {
        super(x, y);
        setDy(6);
    }

    public void move(){
        y += dy; x += dx;
        hitBox.setLocation((int) x, (int) y);
    }

    public int getSpeed(){
        return this.speed;
    }


}
