package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {


    public Window() {
        setTitle("POOngGame");
        setFocusable(true);
        setLayout(null);
        prepareElements();
        prepareActions();
    }


    public void prepareActions(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            public void windowClosing (WindowEvent e){
                close();
            }
        });
    }

    public void close(){
        int valor = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit POOng", "Abvertencia", JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }

    public void prepareElements(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((screenSize.width/2)+200,screenSize.height);
        System.out.println((screenSize.width/2)+200+" "+screenSize.height);
        setLocationRelativeTo(null);
    }
}

