package aplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Table implements Serializable {

    private static int sideRight = 650;
    private static int sideLeft = 214;
    private static int sideUp = 61;
    private static int sideDown = 690;
    private ArrayList<Racket> rackets;
    private boolean isPower = false;
    private Ball ball;
    private ThreadBall threadBall = new ThreadBall(this);;
    private boolean pause = true;
    private Timer timer;
    private int[] moves = {214,650,200,300,400,500,600};


    public Table(){
        rackets = new ArrayList<Racket>();
        ball = new Ball(441-35,384-35);
    }

    public void addPlayer(){
        rackets.add(new Player(432-50,sideUp-40));
    }


    public void addBot(){
        rackets.add(new Extreme(432-50,sideDown-80));
    }


    public void intersect(){
        for(Racket r : rackets){
            if(r.getHitBox().intersects(ball.getHitBox())){
                ball.setDy(-ball.getDy());
                Random random = new Random();
                int ran = random.nextInt(moves.length);
                int finX = moves[ran];
                int finy = 0;
                if(ball.getDy()<0) finy = 61;
                else finy = 762;

                double valX = ball.getX();
                double valY = ball.getY();


                double dx = finX-valX;

                double dy = finy-valY;

                double relacion = dx/dy;

                relacion = Math.abs(relacion);
                if (!(finX > valX)) {
                    relacion = relacion * (-1);
                }
                ball.setDx(relacion*Math.abs(ball.getDy()));
            }
        }
    }

    /** move things*/
    public void moveRacked(boolean right, int racked){
        rackets.get(0).move(right,ball);
    }

    public void moveThreadBall(){
        ball.move();
        intersect();
        plusScore();
        rackets.get(1).move(true, ball);
    }

    public void plusScore(){
        if (ball.getY()<(sideUp-50)){
            restart();
            rackets.get(1).setScore(1);
        }else if (ball.getY()> sideDown){
            restart();
            rackets.get(0).setScore(1);
        }
    }

    public void restart(){
        rackets.get(0).setX((sideRight-sideLeft)/2);rackets.get(0).setY(sideUp-30);
        rackets.get(1).setX((sideRight-sideLeft)/2);rackets.get(1).setY(sideDown-115);
        for(Racket r : rackets)r.setHitBox();
        ball.setX((sideRight-sideLeft)/2); ball.setY((sideDown-sideUp)/2);
        ball.setDy(3);ball.setDx(0);
    }

    /** show power*/

    public void spell(Power powerSelect){
        if(ball.getHitBox().intersects(powerSelect.getHitBox())){
            if(ball.getDy()<0){
                powerSelect.spell(ball,rackets.get(0));
            }else if(ball.getDy()>0){
                powerSelect.spell(ball,rackets.get(1));
            }
            this.isPower = true;
            timer.cancel();
            timer.purge();
        }
    }

    public void timeLimit(int delay){
        timer = new Timer();
        TimerTask timeSpell = new TimerTask() {
            @Override
            public void run() {
                System.out.println("ENTRE");
                isPower = true;
            }
        };
        timer.schedule(timeSpell, delay);
    }



    /**set Power*/

    public void isPause(boolean pause){
        this.pause = pause;
    }

    public void setPower(boolean power) {
        isPower = power;
    }

    /** get things*/


    public Ball getBall(){
        return ball;
    }

    public ArrayList<Racket> getRackets() {
        return rackets;
    }

    public boolean isPause(){
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isPower() {
        return isPower;
    }

    public Score getScore(int i){
        return rackets.get(i).getScore();
    }

    /** run thread*/
    public void run(){
        threadBall.start();
    }
}
