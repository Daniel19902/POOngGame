package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.LineBorder;

public class Character {

    private Window character;
    private JButton[] buttonsCharacters = {new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
    private Image[] characterImage = {new ImageIcon("4.png").getImage(),new ImageIcon("12.png").getImage(),
            new ImageIcon("20.png").getImage(),new ImageIcon("img2.png").getImage(),
            new ImageIcon("it.jpg").getImage(),new ImageIcon("21.jpg").getImage(),
            new ImageIcon("22.jpg").getImage(),new ImageIcon("21.jpg").getImage()};

    private String nick = "";
    private JLabel label = new JLabel();
    private Image characterSelect = null;
    private Image imageSelected = null;
    private JButton next;
    private String nameEnemy = "";
    private JTextField textField;
    private String mode = "OnePlayer";


    public Character(){
        character = new Window();
    }

    public void runCharacter(Image imageSelected){
        this.imageSelected = imageSelected;
        prepareElement();
        prepareActions();
    }

    public void button(){
        next = new JButton("Next");
        next.setBounds(character.getWidth()/3+240,character.getHeight()/2+250,100,40);
        character.add(next);
    }


    public void prepareElement(){
        JPanel panel = new JPanel();
        panel.setBounds(20,20,character.getWidth()/3,character.getHeight()-100);
        panel.setLayout(new GridLayout(4,2,0,20));
        for(int i = 0; i<buttonsCharacters.length; i++){
            buttonsCharacters[i].setIcon(new ImageIcon(characterImage[i].getScaledInstance(50,50,Image.SCALE_SMOOTH)));
            panel.add(buttonsCharacters[i]);
        }
        label.setOpaque(true);
        label.setBackground(Color.RED);
        label.setBounds(character.getWidth()/3+90,20,400,300);
        textField();
        button();
        character.add(label);
        character.add(panel);
    }

    public void textField(){
        textField = new JTextField();
        textField.setBounds(character.getWidth()/3+190,400,200,50);
        textField.setHorizontalAlignment(textField.CENTER);
        textField.setBorder(new LineBorder(Color.BLACK,2));
        textField.setFont(new Font("Bauhaus 93",0,50));
        character.add(textField);
    }

    public void prepareActions(){
        final boolean[] select = {false};
        for(int i = 0; i< buttonsCharacters.length; i++){
            int finalI = i;
            buttonsCharacters[i].addMouseListener(new MouseListener() {
                Icon icon = new ImageIcon(characterImage[finalI].getScaledInstance(400,300,Image.SCALE_SMOOTH));
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if(characterImage[finalI] != imageSelected){
                        label.setIcon(icon);
                        characterSelect = characterImage[finalI];
                        select[0] = true;
                    }else exceptionImage();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if(!select[0]) {
                        label.setIcon(icon);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(characterSelect != null && !textField.getText().isEmpty()){
                    nick = textField.getText();
                    optionNext();
                }
            }
        });
    }

    public void optionNext(){
        Setting setting = new Setting();
        setting.setMode(mode);
        setting.setNamePlayerOne(nick);
        setting.setNamePlayerTwo(nameEnemy);
        setting.setPlayerOne(characterSelect);
        setting.setPlayerTwo(imageSelected);
        setting.runSetting(mode);
        setting.setVisible();
        character.setVisible(false);
    }



    public void exceptionImage(){
        JOptionPane.showConfirmDialog(character,"Image Selected", "AV", JOptionPane.YES_NO_OPTION);
    }


    public void setVisible(){
        character.setVisible(true);
    }

    public void setNameEnemy(String nameEnemy) {
        this.nameEnemy = nameEnemy;
    }
}
