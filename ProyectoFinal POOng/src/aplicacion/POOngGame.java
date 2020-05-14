package aplicacion;

import persistencia.Salve;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class POOngGame implements Serializable {

    private Table table;
    private Power[] powers = {new Freezer(),new FastBall()};
    private int[] times = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000,20000};
    private int[] timesSpell = {10000,20000};
    private Power powerSelect;
    private boolean isPower = false;
    private int spellRandom;
    private boolean pause = true;
    private Timer timerPower;

    public POOngGame(){
        table = new Table();
        timePower();
    }

    public void timePower(){
        Timer timer = new Timer();
        TimerTask timePower = new TimerTask() {
            @Override
        public void run() {
            if(!isPower){
                isPower = true;
                Random random = new Random();
                int randomNumber = random.nextInt(times.length);
                selectPower(times[randomNumber]);
                timerPower.purge();
            }
        }
    };
        timer.schedule(timePower,5000,1000);
}

    public void selectPower(int delay){
        timerPower = new Timer();
        TimerTask selectPower = new TimerTask() {
            int times = 0;
            @Override
            public void run() {
                if (times == 0) {
                    timeSpell();
                    Random randomSpell = new Random();
                    spellRandom = randomSpell.nextInt(powers.length);
                    powerSelect = powers[spellRandom];
                    times++;
                }
                table.spell(powerSelect);
                if(table.isPower()){
                    powerSelect = null;
                    table.setPower(false);
                    isPower = false;
                    times = 0;
                    timerPower.cancel();
                }
            }
        };
        timerPower.schedule(selectPower,delay,6);
    }

    public void timeSpell(){
        int time = 0;
        Random random = new Random();
        time = random.nextInt(timesSpell.length);
        table.timeLimit(timesSpell[time]);
    }













/** add things*/
    public Power getPower(){
        return this.powerSelect;
    }

    public void addPlayer(){
        table.addPlayer();
    }

    public void addBot(){
        table.addBot();
    }

/** move things*/

    public void moveRacked(boolean right, int racked){
        table.moveRacked(right, racked);
    }


/** get things*/

    public ArrayList<Racket> getRackets(){
        return table.getRackets();
    }

    public Ball getBall(){
        return table.getBall();
    }

    public int getRandom(){
        return this.spellRandom;
    }

    public boolean getPause(){
        return pause;
    }

    public Score getScore(int i){
        return table.getScore(i);
    }

    /** pause*/

    public void setPause(boolean pause){
        this.pause = pause;
        table.setPause(pause);
    }

    public void save(File file) throws IOException {
        Salve salve = new Salve();
        salve.optionSalve(this,file);
    }

    public POOngGame open(File file) throws IOException, ClassNotFoundException {
        Salve salve = new Salve();
        return salve.optionOpen(file);
    }

    /** run */
    public void run(){
        table.run();
    }

}