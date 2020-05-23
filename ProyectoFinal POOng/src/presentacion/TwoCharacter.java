package presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoCharacter {

    private Window twoCharacter;
    private Image[] characterImage = {new ImageIcon("4.png").getImage(),new ImageIcon("12.png").getImage(),
            new ImageIcon("20.png").getImage(),new ImageIcon("img2.png").getImage(),
            new ImageIcon("it.jpg").getImage(),new ImageIcon("21.jpg").getImage(),
            new ImageIcon("22.jpg").getImage(),new ImageIcon("21.jpg").getImage()};

    private JLabel label = new JLabel();
    private JButton next;
    private JTextField textField;
    private JTextField textField2;
    private JButton[] playerOne = {new JButton(), new JButton(), new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
    private JButton[] playerTwo = {new JButton(), new JButton(), new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
    private JButton playerOneButton;
    private JButton playerTwoButton;
    private Image playerOneImage;
    private Image playerTwoImage;




    public TwoCharacter(){
        twoCharacter = new Window();
        prepareElement();
        prepareAction();
    }

    public void prepareElement(){
        JPanel panel = new JPanel();
        panel.setBounds(20,20,twoCharacter.getWidth()/3,twoCharacter.getHeight()/2+20);
        panel.setLayout(new GridLayout(4,2,0,20));
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.green);
        panel2.setBounds(twoCharacter.getWidth()/2+115,20,twoCharacter.getWidth()/3,twoCharacter.getHeight()/2+20);
        panel2.setLayout(new GridLayout(4,2,0,20));
        for(int i = 0; i<playerOne.length; i++){
            playerOne[i].setIcon(new ImageIcon(characterImage[i].getScaledInstance(50,50,Image.SCALE_SMOOTH)));
            playerOne[i].setBackground(Color.WHITE);
            panel.add(playerOne[i]);
            playerTwo[i].setIcon(new ImageIcon(characterImage[i].getScaledInstance(50,50,Image.SCALE_SMOOTH)));
            playerTwo[i].setBackground(Color.WHITE);
            panel2.add(playerTwo[i]);
        }
        button();
        textField();
        twoCharacter.add(label);
        twoCharacter.add(panel2);
        twoCharacter.add(panel);

    }

    public void prepareAction(){
        for(int i = 0; i < playerOne.length; i++){
            int finalI = i;
            playerOne[i].addActionListener(e -> {
                if(playerOneButton != null) {
                    playerOneButton.setBackground(Color.WHITE);
                    playerOne[finalI].setBackground(Color.RED);
                    playerOneButton = playerOne[finalI];playerOneImage = characterImage[finalI];
                }
                if(playerOneButton == null){
                    playerOne[finalI].setBackground(Color.RED);
                    playerOneButton = playerOne[finalI];playerOneImage = characterImage[finalI];
                }
            });
            playerTwo[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(playerTwoButton != null){
                        playerTwoButton.setBackground(Color.WHITE);
                        playerTwo[finalI].setBackground(Color.RED);
                        playerTwoButton = playerTwo[finalI];playerTwoImage = characterImage[finalI];
                    }
                    if(playerTwoButton == null){
                        playerTwo[finalI].setBackground(Color.RED);
                        playerTwoButton = playerTwo[finalI];playerTwoImage = characterImage[finalI];
                    }
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


    public void optionNext(){
        Setting setting = new Setting();
        setting.setVisible();
        setting.setPlayerOne(playerOneImage);setting.setPlayerTwo(playerTwoImage);
        setting.setNamePlayerOne(textField.getText());setting.setNamePlayerTwo(textField2.getText());
        setting.runSetting("TwoPlayers");
    }



    public void textField(){
        textField = new JTextField();
        textField.setBounds(60,twoCharacter.getHeight()/2+65,200,50);
        textField.setHorizontalAlignment(textField.CENTER);
        textField.setBorder(new LineBorder(Color.BLACK,2));
        textField.setFont(new Font("Bauhaus 93",0,50));
        textField2 = new JTextField();
        textField2.setBounds(twoCharacter.getWidth()/2+162,twoCharacter.getHeight()/2+60,200,50);
        textField2.setHorizontalAlignment(textField.CENTER);
        textField2.setBorder(new LineBorder(Color.BLACK,2));
        textField2.setFont(new Font("Bauhaus 93",0,50));
        twoCharacter.add(textField);
        twoCharacter.add(textField2);
    }

    public void button(){
        next = new JButton("Next");
        next.setBounds(twoCharacter.getWidth()/2-50,twoCharacter.getHeight()-200,100,40);
        twoCharacter.add(next);
    }


    public void setVisible(){
        twoCharacter.setVisible(true);
    }


}
