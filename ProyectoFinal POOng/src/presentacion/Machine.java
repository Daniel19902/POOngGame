package presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Machine {

    private Window machine;
    private JButton[] buttons = {new JButton(),new JButton(), new JButton(), new JButton()};
    private JButton[] buttons2 = {new JButton(),new JButton(), new JButton(), new JButton()};
    private HashMap<Integer,String> bots;
    private Image[] name = {new ImageIcon("4.png").getImage(),new ImageIcon("12.png").getImage(),
            new ImageIcon("20.png").getImage(),new ImageIcon("img2.png").getImage()};
    private JButton next = new JButton("Next");
    private JLabel label = new JLabel("bot name 1"); JLabel label1 = new JLabel("VS"); JLabel label2 = new JLabel("bot name 1");
    private Image botOne, botTwo;

    public Machine(){
        machine = new Window();
        addBots();
        prepareElement();
        prepareAction();
    }


    public void prepareElement(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,4,15,0));
        panel.setBounds(0,40,machine.getWidth(),machine.getHeight()/4);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,4,15,0));
        panel2.setBounds(0,machine.getHeight()/2,machine.getWidth(),machine.getHeight()/4);
        for (int i = 0; i < buttons.length; i++){
            buttons[i].setIcon(new ImageIcon(name[i].getScaledInstance(100,100,Image.SCALE_SMOOTH)));
            buttons[i].setBackground(Color.black);
            buttons2[i].setIcon(new ImageIcon(name[i].getScaledInstance(100,100,Image.SCALE_SMOOTH)));
            buttons2[i].setBackground(Color.black);
            panel2.add(buttons2[i]);
            panel.add(buttons[i]);
        }
        text();
        buttons();
        machine.add(panel2);
        machine.add(panel);
    }

    public void prepareAction(){
        for(int i = 0; i < buttons.length; i++){
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    label.setText(bots.get(finalI));
                    botOne = name[finalI];
                }
            });
            buttons2[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    label2.setText(bots.get(finalI));
                    botTwo = name[finalI];
                }
            });
        }
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionNext();
            }
        });
    }

    public void buttons(){
        next.setBounds(machine.getWidth()/2-50,machine.getHeight()-150,100,50);
        machine.add(next);
    }

    public void optionNext(){
        Setting setting = new Setting();
        machine.setVisible(false);
        setting.setVisible();
        setting.setNamePlayerOne(label.getText());setting.setNamePlayerTwo(label2.getText());
        setting.setPlayerOne(botOne);setting.setPlayerTwo(botTwo);
        setting.runSetting("bots");
    }


    public void text(){
        JPanel panel = new JPanel();
        label.setFont(new Font("Bauhaus 93",0,50));
        label2.setFont(new Font("Bauhaus 93",0,50));
        label1.setFont(new Font("Bauhaus 93",0,20));
        label.setBorder(new LineBorder(Color.red,2));
        label2.setBorder(new LineBorder(Color.red,2));
        panel.setBounds(machine.getWidth()/2-350,65+machine.getHeight()/4,700,100);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));
        panel.add(label);panel.add(label1);panel.add(label2);
        machine.add(panel);

    }

    public void addBots(){
        bots = new HashMap<>();
        bots.put(0 ,"Extreme");
        bots.put(1,"Sniper");
        bots.put(2,"Greedy");
        bots.put(3,"Lazy");
    }

    public void setVisible(){
        machine.setVisible(true);
    }




}
