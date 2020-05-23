package aplicacion;

import java.util.Timer;
import java.util.TimerTask;

public class Incremental extends Ball {

    private Timer time = new Timer();
    private int speed = 3;


    public Incremental(int x, int y) {
        super(x, y);
        moveR();setDy(3);
    }

    public void move(){
        if(y>768) dy = -dy;
        if(y<0) dy = -dy;
        y += dy;x += dx;
        hitBox.setLocation((int) x,(int)y);
    }

    public void moveR(){
        TimerTask fastTime = new TimerTask() {
            @Override
            public void run() {
                if(!isPause){
                    System.out.println(dy);
                    if(dy > 0)setDy(dy+1);
                    else if(dy < 0)setDy(dy-1);

                }
            }
        };
        time.schedule(fastTime,5000,5000);
    }

    public int getSpeed(){
        return this.speed;
    }

}
