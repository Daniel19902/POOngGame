package aplicacion;

public class Extreme extends Racket{

    public Extreme(int x, int y){
        super(x, y);
    }

    public void intersect(Ball ball){
        int ran = specialMove(ball);
        ball.setDy(-ball.getDy());
        int finX = ran;
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

        double dx = finX-valX;
        double dy = finY-valY;

        double relation = dx/dy;

        relation = Math.abs(relation);
        if (!(finX > valX)) {
            relation = relation * (-1);
        }
        ball.setDx(relation*Math.abs(ball.getDy()));
        if(isPower != null)isPower.deleteSpell();
    }

    public int specialMove(Ball ball){
        int ran = 0;
        if (ball.getDx()<0){
            ran = (int) (Math.random()*(ball.getX()-214)+214);
        }else if(ball.getDx()>0){
            ran = (int) (Math.random()*(ball.getX()-650)+650);
        }else
            ran = 650;
        return ran;
    }

    @Override
    public void move(boolean right, Ball ball) {
        if(ball.getDx() < 0){
            if(ball.getX()>this.x){
                x+=dx;
            }else if(ball.getX()<this.x){
                x-=dx;
            }
            x-=dx;
        }else if(ball.getDx() > 0){
            if(ball.getX()>this.x){
                x+=dx;
            }else if(ball.getX()<this.x){
                x-=dx;
            }
            x+=dx;
        }
        hitBox.setLocation(this.x,this.y);
    }
}
