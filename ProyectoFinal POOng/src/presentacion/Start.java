package presentacion;

import aplicacion.POOngGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {

    private Window start;
    private JButton onePlayer;
    private JButton twoPlayers;
    private JButton bot;
    private Difficulty difficulty;

    public Start(){
        start = new Window();
        prepareElements();
        prepareActions();
    }

    public void setTitle(){
        JPanel panelTitle = new JPanel();
        JLabel title = new JLabel("Pong Game");
        panelTitle.setBounds(0,0,start.getWidth(),300);
        panelTitle.setBackground(Color.cyan);
        title.setFont(new Font("Bauhaus 93",0,100));
        panelTitle.add(title);
        start.add(panelTitle);
    }


    public void prepareElements(){
        setTitle();
        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(Color.BLACK);
        panelButtons.setBounds(start.getWidth()/2-50,start.getHeight()/2,90,230);
        panelButtons.setLayout(new GridLayout(3,1,0,60));
        onePlayer = new JButton("1 PLAYER");
        twoPlayers = new JButton("2 PLAYER");
        bot = new JButton("BOTS");
        panelButtons.add(onePlayer);
        panelButtons.add(twoPlayers);
        panelButtons.add(bot);
        start.add(panelButtons);
    }

    public void difficulty(){
        difficulty = new Difficulty();
        start.setVisible(false);
        setStart();
        difficulty.setVisible();
    }

    public void twoCharacter(){
        TwoCharacter twoCharacter= new TwoCharacter();
        start.setVisible(false);
        twoCharacter.setVisible();
    }

    public void bots(){
        Machine machine = new Machine();
        start.setVisible(false);
        machine.setVisible();
    }


    public void prepareActions(){
        onePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty();
            }
        });

        twoPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                twoCharacter();
            }
        });

        bot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bots();
            }
        });
    }

    public void setVisible(){
        start.setVisible(true);
    }

    public void setStart(){
        difficulty.setStart(this);
    }

}
