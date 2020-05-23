package aplicacion;

import java.io.Serializable;

public class ThreadBall extends Thread implements Serializable {

    private Table table;

    public ThreadBall(Table table){
        this.table = table;
    }

    @Override
    public void run() {
        while (true) {
            if (!table.isPause()) {
                table.getBall().move();
                table.intersect();
                table.plusScore();
                table.objective();
                if (table.getNumberBots() == 1) {
                    table.getRackets().get(1).move(true, table.getBall());
                }
                if (table.getBlock() != null) table.moveBlock();

                if (table.getNumberBots() == 2) {
                    table.getRackets().get(1).move(true, table.getBall());
                    table.getRackets().get(0).move(true, table.getBall());
                }
            }
            try {
                sleep(6);
            }catch (InterruptedException ignored){

            }
        }
    }
}
