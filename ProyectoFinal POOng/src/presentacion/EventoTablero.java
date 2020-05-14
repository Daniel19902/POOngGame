package presentacion;


import java.awt.event.*;


public class EventoTablero extends KeyAdapter {

    public static boolean a, d, up, down;

    @Override
    public void keyPressed(KeyEvent e) {

        int id = e.getKeyCode();
        if (id == KeyEvent.VK_A) {
            a = true;

        }
        if (id == KeyEvent.VK_D) {
            d = true;

        }
        if (id == KeyEvent.VK_UP) {
            up = true;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int id = e.getKeyCode();
        if (id == KeyEvent.VK_A) {
            a = false;
        }
        if (id == KeyEvent.VK_D) {
            d = false;
        }
        if (id == KeyEvent.VK_UP) {
            up = false;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = false;
        }
    }

}
