package presentacion;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;

public class Setting {


    private Window setting;
    private LinkedList<Image> powerImage = new LinkedList<Image>();
    private LinkedList<String> powerName = new LinkedList<String>();
    private String namePlayerOne;
    private String namePlayerTwo;
    private Image playerOne;
    private Image playerTwo;
    private JComboBox<String> score;
    private JComboBox<String> tBall;
    private String mode = " ";
    private String[] scoreSetting = {"Score(Default)","1","2","3","4","5","6","7","8","9","10"};
    private String[] tBallSetting = {"Ball(Default)","Slow","Fast","incremental"};
    private JButton powers;
    private JButton play;

    public Setting(){
        setting = new Window();
    }

    public void runSetting(String mode){
        this.mode = mode;
        prepareElement();
        prepareActions();
    }

    public void prepareElement(){
        JPanel vs = new JPanel();vs.setLayout(null);
        JLabel title = new JLabel("VS");title.setFont(new Font("Bauhaus 93", Font.PLAIN,50));
        vs.setBackground(Color.cyan); vs.setBounds(40,20,setting.getWidth()-100,200);
        JLabel image1 = new JLabel(new ImageIcon(playerOne.getScaledInstance(200,200,Image.SCALE_SMOOTH)));
        JLabel image2 = new JLabel(new ImageIcon(playerTwo.getScaledInstance(200,200,Image.SCALE_SMOOTH)));
        image2.setBounds(vs.getWidth()-260,0,200,200);image1.setBounds(60,0,200,200);
        title.setBounds(vs.getWidth()/2-25,vs.getHeight()/2-25,50,50);vs.add(title);
        vs.add(image1);vs.add(image2);
        addComboBox(vs);
        setting.add(vs);

    }

    public void addComboBox(JPanel vs){
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setBounds(setting.getWidth()/2-250,250,500,100);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,120,50));
        score = new JComboBox<String>();tBall = new JComboBox<String>();
        score.setFont(new Font("Bauhaus 93",0,15));tBall.setFont(new Font("Bauhaus 93",0,15));
        score.setBorder(new LineBorder(Color.BLACK,2));tBall.setBorder(new LineBorder(Color.BLACK,2));
        for(String s : scoreSetting) score.addItem(s);for(String s : tBallSetting) tBall.addItem(s);
        panel.add(score);panel.add(tBall);
        buttons();
        setting.add(panel);
    }

    public void buttons(){
        JPanel panel = new JPanel();
        powers = new JButton("Powers");
        play = new JButton("Play");
        panel.setBounds(setting.getWidth()/2-125,setting.getHeight()/2+250,250,40);
        panel.setLayout(new GridLayout(1,2,50,0));
        panel.add(play); panel.add(powers);
        setting.add(panel);
    }

    public void prepareActions(){
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionPlay();
            }
        });
        powers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionPower();
            }
        });
    }

    public void optionPlay(){
        String tBa = Objects.requireNonNull(tBall.getSelectedItem()).toString();
        Pong pong = new Pong();
        setting.setVisible(false);
        pong.setVisible();
        pong.setPowerImage(powerImage);
        pong.setPowerName(powerName);
        pong.setBall(tBa);
        pong.setPlayer(playerOne);pong.setPlayer(playerTwo);
        pong.setNamePlayerOne(namePlayerOne);pong.setNamePlayerTwo(namePlayerTwo);
        pong.setMode(mode);
        pong.runPong();
    }

    public void optionPower(){
        Spell spell = new Spell();
        spell.setVisible();
        spell.back(this);
        setting.setVisible(false);
    }

    public void setVisible(){
        setting.setVisible(true);
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setNamePlayerOne(String namePlayerOne) {
        this.namePlayerOne = namePlayerOne;
    }

    public void setNamePlayerTwo(String namePlayerTwo) {
        this.namePlayerTwo = namePlayerTwo;
    }

    public void setPlayerOne(Image playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Image playerTwo){
        this.playerTwo = playerTwo;
    }

    public void setPowerImage(LinkedList<Image> powerImage) {
        this.powerImage = powerImage;
    }

    public void setPowerName(LinkedList<String> powerName) {
        this.powerName = powerName;
    }
}
