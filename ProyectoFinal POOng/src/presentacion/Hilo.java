package presentacion;


public class Hilo extends Thread {

    private final GameBoard canvas;

    public Hilo (GameBoard canvas){
        this.canvas = canvas;
    }

    @Override
    public void run(){
        while (true) {
            canvas.repaint();
            try {
                Thread.sleep(6);
            } catch (Exception e) {
                System.out.println("Error Graphics engine: " + e.getMessage());
            }
        }
    }
}

