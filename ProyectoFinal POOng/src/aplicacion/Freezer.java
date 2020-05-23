package aplicacion;


import java.util.Timer;
import java.util.TimerTask;

public class Freezer extends Power {
    private Timer timer = new Timer();
    public int freeze = 0;

    @Override
    public void spell(Table table,int index) {
        table.getRackets().get(index).freezer(freeze);
        TimerTask freezer = new TimerTask() {
            @Override
            public void run() {
                table.getRackets().get(index).noFreezer();
            }
        };
        timer.schedule(freezer, 2000);
}

    @Override
    public void deleteSpell() {
        
    }

}
