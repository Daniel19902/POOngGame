package aplicacion;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Freezer extends Power implements Serializable {
    private Timer timer;
    public int freeze = 0;

    @Override
    public void spell(Ball ball, Racket racket) {
        timer = new Timer();
        racket.freezer(freeze);
        TimerTask freezer = new TimerTask() {
            @Override
            public void run() {
                System.out.println("10 seg");
                racket.noFreezer();
                timer.cancel();
            }
        };
        timer.schedule(freezer, 2000);
    }
}
