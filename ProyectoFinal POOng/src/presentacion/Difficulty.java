package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Difficulty {

    private Window difficulty;
    private Information information;
    private JButton[] buttons = {new JButton(),new JButton(), new JButton(), new JButton()};
    private HashMap<Integer,String> bots;
    private Image[] name = {new ImageIcon("4.png").getImage(),new ImageIcon("12.png").getImage(),
                            new ImageIcon("20.png").getImage(),new ImageIcon("img2.png").getImage()};
    private String botSelected = "";
    private Image imageBot = null;
    private JButton back;
    private Start start;


    public Difficulty(){
        difficulty = new Window();
        addBots();
        prepareElement();
        prepareAction();
    }

    public void setTitle(){
        JPanel panelTitle = new JPanel();
        JLabel title = new JLabel("Difficulty");
        panelTitle.setBounds(0,0,difficulty.getWidth(),200);
        panelTitle.setBackground(Color.cyan);
        title.setFont(new Font("Bauhaus 93",0,100));
        panelTitle.add(title);
        difficulty.add(panelTitle);
    }

    public void start(){
        back = new JButton("Back");
        back.setBounds((difficulty.getWidth()/2)-40,difficulty.getHeight()-150,80,50);
        difficulty.add(back);
    }

    public void prepareElement(){
        setTitle();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,4,15,0));
        panel.setBounds(0,difficulty.getHeight()/3,difficulty.getWidth(),difficulty.getHeight()/3);
        for (int i = 0; i < buttons.length; i++){
            buttons[i].setIcon(new ImageIcon(name[i].getScaledInstance(100,100,Image.SCALE_SMOOTH)));
            buttons[i].setBackground(Color.black);
            panel.add(buttons[i]);
        }
        start();
        difficulty.add(panel);
    }

    public void informationBot(){
        information = new Information();
        information.setImage(imageBot);
        information.setNameBot(botSelected);
        information.setDifficulty(this);
        information.prepareElement();
        information.prepareActions();
        information.setVisible();
        difficulty.setVisible(false);
    }

    public void prepareAction(){
        for(int i = 0; i < buttons.length; i++){
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    botSelected = bots.get(finalI);
                    imageBot = name[finalI];
                    informationBot();
                }
            });
        }
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionBack();
            }
        });
    }

    public void optionBack(){
        start.setVisible();
        difficulty.setVisible(false);
    }


    public void addBots(){
        bots = new HashMap<Integer, String>();
        bots.put(0 ,"Extreme");
        bots.put(1,"Sniper");
        bots.put(2,"Greedy");
        bots.put(3,"Lazy");
    }


    public void setVisible(){
        difficulty.setVisible(true);
    }

    public void setStart(Start start){
        this.start = start;
    }

}
