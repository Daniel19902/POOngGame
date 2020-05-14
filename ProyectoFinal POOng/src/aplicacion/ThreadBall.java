package aplicacion;

import java.io.Serializable;

public class ThreadBall extends Thread implements Serializable {

    private Table table;

    public ThreadBall(Table table){
        this.table = table;
    }

    @Override
    public void run() {
        while (true){
            if(!table.isPause()) {
                table.moveThreadBall();
            }
            try {
                sleep(6);
            }catch (InterruptedException ignored){

            }
        }
    }
}
