package aplicacion;

import java.util.Timer;
import java.util.TimerTask;

public class Turtle extends Power{

    private int slow = 1;
    private Timer timer = new Timer();

    @Override
    public void spell(Table table, int index) {
        table.getRackets().get(index).turtle(slow);
        TimerTask freezer = new TimerTask() {
            @Override
            public void run() {
                table.getRackets().get(index).setDx(2);
            }
        };
        timer.schedule(freezer, 3000);
    }

    @Override
    public void deleteSpell() {

    }
}
