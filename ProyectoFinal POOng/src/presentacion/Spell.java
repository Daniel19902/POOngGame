package presentacion;



import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.LinkedList;

public class Spell {


    private Window spell;
    private HashMap<String, Image> imagePowers;

    private JCheckBox[] powersSelect ={new JCheckBox("Freezer"),new JCheckBox("Fast Ball"),new JCheckBox("Flash"),
                                new JCheckBox("Phantom Racket"),new JCheckBox("Cold Racket"),
                                new JCheckBox("Turtle"),new JCheckBox("Energy")};

    private LinkedList<Image> selectPowers = new LinkedList<Image>();
    private LinkedList<String> powerName = new LinkedList<String>();
    private Setting setting;

    private JButton back;
    private JLabel label;

    public Spell(){
        spell = new Window();
        addPowers();
        prepareElement();
        prepareAction();
    }

    public void button(){
        back = new JButton("Ok");
        back.setBounds(spell.getWidth()/3+240,spell.getHeight()/2+250,100,40);
        spell.add(back);
    }

    public void prepareElement(){
        JPanel panel = new JPanel();
        label = new JLabel();
        panel.setBounds(20,20,spell.getWidth()/3,spell.getHeight()-100);
        panel.setBackground(Color.green);
        panel.setLayout(new GridLayout(7,1));
        for (int i = 0; i< powersSelect.length; i++){
            powersSelect[i].setBackground(Color.green);
            powersSelect[i].setBorder(new LineBorder(Color.blue,3));
            powersSelect[i].setFont(new Font("Bauhaus 93",0,20));
            panel.add(powersSelect[i]);
        }
        label.setOpaque(true);
        label.setBackground(Color.RED);
        label.setBounds(spell.getWidth()/3+90,20,400,300);
        button();
        spell.add(label);
        spell.add(panel);
    }

    public void prepareAction(){
        for(int i = 0; i < powersSelect.length; i++){
            int finalI = i;
            powersSelect[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    label.setIcon(new ImageIcon(imagePowers.get(powersSelect[finalI].getText()).getScaledInstance(400,300,Image.SCALE_SMOOTH)));
                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JCheckBox j : powersSelect){
                    if(j.isSelected()){
                        selectPowers.add(imagePowers.get(j.getText()));
                        powerName.add(j.getText());
                    }
                }
                optionBack();
            }
        });
    }

    public void optionBack(){
        setting.setVisible();
        setting.setPowerImage(selectPowers);
        setting.setPowerName(powerName);
        selectPowers = new LinkedList<Image>();
        powerName = new LinkedList<String>();
        spell.setVisible(false);

    }

    public void addPowers(){
        imagePowers = new HashMap<String, Image>();
        imagePowers.put("Freezer",new ImageIcon("ice.png").getImage());
        imagePowers.put("Fast Ball",new ImageIcon("fast.jpg").getImage());
        imagePowers.put("Flash",new ImageIcon("flash.png").getImage());
        imagePowers.put("Turtle",new ImageIcon("slow.jpg").getImage());
        imagePowers.put("Cold Racket",new ImageIcon("cold.jpg").getImage());
        imagePowers.put("Phantom Racket",new ImageIcon("fantasma.png").getImage());
        imagePowers.put("Energy",new ImageIcon("energy.jpg").getImage());
    }

    public void setVisible(){
        spell.setVisible(true);
    }

    public void back(Setting setting){
        this.setting = setting;
    }





}
