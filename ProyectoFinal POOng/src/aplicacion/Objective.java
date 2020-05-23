package aplicacion;

import java.awt.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Objective {

    private int xPos = 214;
    private int yPos = 61;
    private int wight = 30;
    private int height = 30;
    private Rectangle hitBox;
    private boolean isObjective = false;
    private Timer time;


    public Objective(int xPos,int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        hitBox = new Rectangle(wight,height);
    }

    public void time(){
        time = new Timer();Random random = new Random();
        TimerTask givePoint = new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                int[] y = {61,690-50};
                if(i%2 == 0){
                    isObjective = true;
                }else {
                    xPos = (int) (Math.random()*(600-214)+214);yPos = y[random.nextInt(y.length)];
                    hitBox.setLocation(xPos,yPos);isObjective = false;
                }
                i++;
            }
        };
        time.schedule(givePoint,5000,2000);
    }

    public void objective(ArrayList<Racket> rackets, Ball ball){
        Random random = new Random();
        if(isObjective()) {
            int[] points = {2, (rackets.get(0).getScore().getScore()+rackets.get(1).getScore().getScore())/2};
            int plus = random.nextInt(points.length);
            if (ball.getHitBox().intersects(getHitBox())) {
                if(ball.getDy() < 0) rackets.get(1).setScore(points[plus]);
                if(ball.getDy() > 0) rackets.get(0).setScore(points[plus]);
                isObjective = false;
            }
        }
    }

    public void cancelTimer(){
        isObjective = false;
        time.cancel();
    }

    public boolean isObjective() {
        return isObjective;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getHeight() {
        return height;
    }

    public int getWight() {
        return wight;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}