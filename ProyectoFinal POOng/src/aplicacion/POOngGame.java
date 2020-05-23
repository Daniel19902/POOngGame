package aplicacion;

import persistencia.Salve;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class POOngGame implements Serializable {

    private Table table;
    private LinkedList<String> powerName = new LinkedList<String>();
    private LinkedList<Power> powers = new LinkedList<Power>();
    private int[] times = {5000,7000,8000,9000,10000,20000};
    private int[] timesSpell = {10000,20000};
    private Power powerSelect;
    private boolean isPower = false;
    private int spellRandom;
    private Timer timerPower;
    private Timer addPower = new Timer();
    private boolean pause = false;

    public POOngGame(){
        table = new Table();
    }

    public void  addBall(String tBall){
        table.addBall(tBall);
    }

    public void addOnePlayer(){
        table.addOnePlayer();
    }

    public void run(){
        table.run();
    }

    public void setPause(boolean pause){
        this.pause = pause;
        table.setPause(pause);
    }

    public void addScore(String nameOne, String nameTwo){
        table.addScore(nameOne, nameTwo);
    }

    public void timePower(){
        TimerTask timePower = new TimerTask() {
            @Override
        public void run() {
            if(!isPower && !pause){
                isPower = true;
                Random random = new Random();
                int randomNumber = random.nextInt(times.length);
                selectPower(times[0]);
                timerPower.purge();
            }
        }};
        addPower.schedule(timePower,5000,1000);
    }

    public void addPowers(){
        for(String s : powerName){
            if(s.equals("Freezer"))powers.add(new Freezer());
            if(s.equals("Fast Ball"))powers.add(new FastBall());
            if(s.equals("Flash"))powers.add(new Flash());
            if(s.equals("Turtle"))powers.add(new Turtle());
            if(s.equals("Energy"))powers.add(new Energy());
            if(s.equals("Cold Racket"))powers.add(new ColdRacket());
            /**
            if(s.equals("Cold Racket"))powers.add(new Freezer());
            if(s.equals("Phantom Racket"))powers.add(new Freezer());
            */
        }
    }

    public void moveRacked(boolean right, int racked){
        table.moveRacked(right, racked);
    }

    public void setPowerName(LinkedList<String> powerName) {
        this.powerName = powerName;
        addPowers();
    }

    public void selectPower(int delay){
        timerPower = new Timer();
        TimerTask selectPower = new TimerTask() {
            int times = 0;
            @Override
            public void run() {
                if (times == 0) {
                    Random randomSpell = new Random();
                    spellRandom = randomSpell.nextInt(powers.size());
                    powerSelect = powers.get(spellRandom);
                    timeSpell();
                    times++;
                }
                table.spell(powerSelect);
                if(table.isPower()){
                    powerSelect = null;table.setPower(null);
                    isPower = false;times = 0;
                    timerPower.cancel();
                }
            }
        };
        timerPower.schedule(selectPower,delay,6);
    }

    public void timeSpell(){
        int time ;
        Random random = new Random();
        time = random.nextInt(timesSpell.length);
        table.getRackets().get(0).setIsPower(powerSelect);
        table.getRackets().get(1).setIsPower(powerSelect);
        table.timeLimit(timesSpell[time]);
    }

    public Block getBlock(){
        return table.getBlock();
    }

    public void addObjective(){
        table.addObjective();
    }



   public void addTwoPlayers(){
       table.addTwoPlayers();
   }

   public void addBots(){
       table.addBots();
   }

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
        this.pause = table.getPause();
        return table.getPause();
    }

    public Score getScore(int i){
        return table.getScore(i);
    }

    public Objective getObjective(){
        return table.getObjective();
    }

    public boolean isObjective(){
        return table.getIsObjective();
    }

    public Power getPower(){
        return this.powerSelect;
    }

    public void save(File file) throws IOException {
        Salve salve = new Salve();
        salve.optionSalve(this,file);
    }

    public POOngGame open(File file) throws IOException, ClassNotFoundException {
        Salve salve = new Salve();
        return salve.optionOpen(file);
    }

    public void runObjective(){
        table.runObjective();
    }

}