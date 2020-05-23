package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Information {

    private Window information;
    private Image image = null;
    private String nameBot = "";
    private Difficulty difficulty;
    JButton next;
    JButton back;


    public Information(){
        information = new Window();
    }

    public void setTitle(){
        JPanel panelTitle = new JPanel();
        JLabel title = new JLabel(nameBot);
        panelTitle.setBounds(0,0,information.getWidth(),100);
        panelTitle.setBackground(Color.cyan);
        title.setFont(new Font("Bauhaus 93",0,50));
        panelTitle.add(title);
        information.add(panelTitle);
    }

    public void buttons(){
        JPanel panel = new JPanel();
        next =new JButton("Next");
        back = new JButton("Back");
        panel.setBounds(information.getWidth()/2-100,information.getHeight()/2+250,200,40);
        panel.setLayout(new GridLayout(1,2,50,0));
        panel.add(next); panel.add(back);
        information.add(panel);
    }

    public void prepareElement(){
        setTitle();
        JPanel panel = new JPanel();
        JLabel image = new JLabel(new ImageIcon(this.image.getScaledInstance(250,250,Image.SCALE_SMOOTH)));
        panel.setBounds(information.getWidth()/2-125,100,250,250);
        panel.add(image);
        description();
        buttons();
        information.add(panel);
    }

    public void prepareActions(){
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionNext();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionBack();
            }
        });
    }

    public void optionBack(){
        difficulty.setVisible();
        information.setVisible(false);
    }

    public void optionNext(){
        Character character = new Character();
        character.runCharacter(image);
        character.setNameEnemy(nameBot);
        character.setVisible();
        information.setVisible(false);
    }


    public void description(){
        JLabel label= new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.RED);label.setText(nameBot);
        label.setBounds(information.getWidth()/2-200,information.getHeight()/2+70,400,100);
        information.add(label);
    }



    public void setImage(Image image) {
        this.image = image;
    }

    public void setNameBot(String nameBot) {
        this.nameBot = nameBot;
    }

    public void setVisible(){
        information.setVisible(true);
    }

    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }


}
