package aplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Table implements Serializable{

    private static int sideRight = 650;
    private static int sideLeft = 214;
    private static int sideUp = 61;
    private static int sideDown = 690;
    private ArrayList<Racket> rackets;
    private boolean isPower;
    private Ball ball;
    private ThreadBall threadBall = new ThreadBall(this);;
    private boolean pause;
    private Objective objective;
    private Block block = null;
    private Timer timer;
    private int numberBots = 0;


    public Table(){
        rackets = new ArrayList<Racket>();
        isPower = false;
        pause = true;
        addObjective();
    }

    public void addBall(String tBall){
        if(tBall.equals("Ball(Default)") || tBall.equals("Incremental")) ball = new Incremental(441-35,384-35);
        if(tBall.equals("Slow")) ball = new Slow(441-35,384-35);
        if(tBall.equals("Fast")) ball = new Fast(441-35,384-35);
    }

    public void addOnePlayer(){
        numberBots++;
        rackets.add(new Player(432-50,sideUp-40));
        rackets.add(new Greedy(432-50,sideDown-80));
    }

    public void addTwoPlayers(){
        rackets.add(new Player(432-50,sideUp-40));
        rackets.add(new Player(432-50,sideDown-80));
    }

    public  void addBots(){
        numberBots = 2;
        rackets.add(new Greedy(432-50,sideUp-40));
        rackets.add(new Greedy(432-50,sideDown-80));
    }

    public boolean intersect(){
        boolean intersect = false;
        for(Racket r : rackets){
            if(r.getHitBox().intersects(ball.getHitBox())){
                r.intersect(ball);
                ball.colder(r);
                intersect = true;
            }
        }
        return intersect;
    }

    public void setPause(boolean pause) {
        ball.setIsPause(pause);
        this.pause = pause;
    }

    public void run(){
        threadBall.start();
    }

    public void addScore(String nameOne, String nameTwo){
        rackets.get(0).addScore(sideUp,nameOne);
        rackets.get(1).addScore(sideDown-50,nameTwo);
    }

    public Score getScore(int i){
        return rackets.get(i).getScore();
    }

    public void plusScore(){
        if (ball.getY() < 0){
            restart();
            setPause(true);ball.setIsPause(true);
            rackets.get(1).setScore(1);
        }else if (ball.getY()> sideDown){
            restart();
            setPause(true);ball.setIsPause(true);
            rackets.get(0).setScore(1);
        }
    }

    public void restart(){
        block = null;
        isPower = true;
        ball.setColder(false);
        if(timer != null)timer.cancel();
        rackets.get(0).setX(432-50);rackets.get(0).setY(sideUp-40);rackets.get(0).setDx(2);
        rackets.get(1).setX(432-50);rackets.get(1).setY(sideDown-80);rackets.get(1).setDx(2);
        for(Racket r : rackets)r.setHitBox();
        ball.setX(441-35); ball.setY(384-35);
        ball.setDy(ball.getSpeed());ball.setDx(0);
    }

    public void moveRacked(boolean right, int racked){
        rackets.get(racked).move(right,ball);
    }

    public void spell(Power powerSelect){
        if(ball.getHitBox().intersects(powerSelect.getHitBox())){
            if(ball.getDy()<0){
                powerSelect.spell(this,0);
                addBlock(1);
            }else if(ball.getDy()>0){
                powerSelect.spell(this,1);
                addBlock(-1);
            }
            this.isPower = true;
            timer.cancel();timer.purge();
        }
    }

    public void addBlock(int dy){
        if(block == null || !block.isBlock()){
            block = new Block((int) (Math.random()*(600-214)+214),384-35,dy);
        }
    }

    public void moveBlock() {
        block.move(ball,rackets);
    }

    public void addObjective(){
        Random random = new Random();int[] y = {61,690-50};
        objective = new Objective((int) (Math.random()*(600-214)+214), y[random.nextInt(y.length)]);
    }

    public void objective(){
        objective.objective(rackets,ball);
    }

    public void timeLimit(int delay){
        timer = new Timer();
        TimerTask timeSpell = new TimerTask() {
            @Override
            public void run() {
                isPower = true;
            }
        };
        timer.schedule(timeSpell, delay);
    }

    public void setPower(Power power) {
        if(power == null) isPower = false;
        else isPower = true;
        rackets.get(0).setIsPower(power);
        rackets.get(1).setIsPower(power);
    }

    public boolean getPause(){
        return this.pause;
    }

    public Block getBlock(){
        return block;
    }

    public boolean getIsObjective(){
        return objective.isObjective();
    }

    public Ball getBall(){
        return ball;
    }

    public ArrayList<Racket> getRackets() {
        return rackets;
    }

    public boolean isPause(){
        return pause;
    }

    public boolean isPower() {
        return isPower;
    }

    public int getNumberBots() {
        return numberBots;
    }

    public Objective getObjective() {
        return objective;
    }

    public void runObjective(){
        objective.time();
    }
}
