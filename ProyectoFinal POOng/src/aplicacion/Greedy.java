package aplicacion;

public class Greedy extends Racket {

    private int finY = 0;

    public Greedy(int x, int y){
        super(x, y);
    }

    public void intersect(Ball ball){
        ball.setDy(-ball.getDy());
        if(ball.getDy()<0) {
            finY = 61;
            ball.setY((int) (ball.getY()-40));
        }
        else {
            ball.setY((int) (ball.getY()+40));
            finY = 762;
        }

        int ran = specialMove(ball);

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
        System.out.println(isPower);
        if(isPower == null) {
            if (ball.getDx()<0){
                ran = (int) (Math.random()*(ball.getX()-214)+214);
            }else if(ball.getDx()>0){
                ran = (int) (Math.random()*(ball.getX()-650)+650);
            }else
                ran = 650;
        }else {
            ran = 441-35;
            finY = 384-35;
        }
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
            score.plusDistance();
        }else if(ball.getDx() > 0){
            if(ball.getX()>this.x){
                x+=dx;
            }else if(ball.getX()<this.x){
                x-=dx;
            }
            x+=dx;
            score.plusDistance();
        }
        hitBox.setLocation(this.x,this.y);
    }
}
