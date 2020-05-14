package presentacion;

import aplicacion.POOngGame;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GameBoard extends JPanel {

    /** images*/
    private LinkedList<Image> powers = new LinkedList<Image>();
    private LinkedList<Image> players = new LinkedList<Image>();
    private Image field = null;
    private Image ball = new ImageIcon("00.png").getImage();

    /** images*/

    private POOngGame poOngGame;
    private int width;
    private int height;

    public GameBoard(int width, int height, POOngGame poOngGame){
        setLayout(null);
        setBounds(0,0,width,height);
        setBackground(Color.green);
        this.height = height;
        this.width = width;
        this.poOngGame = poOngGame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(field,0,0,width,730,this);
        g.drawImage(ball,(int) Math.ceil(poOngGame.getBall().getX()),(int) Math.ceil(poOngGame.getBall().getY()),poOngGame.getBall().getWidth(),poOngGame.getBall().getHeight(),this);
        if(poOngGame.getPower() != null && !powers.isEmpty()) {
            g.drawImage(powers.get(poOngGame.getRandom()), 441-35,384-35, 35, 35, this);
        }

        for(int i = 0; i < 2; i++){
            g.drawImage(players.get(i),poOngGame.getRackets().get(i).getX(),poOngGame.getRackets().get(i).getY(),poOngGame.getRackets().get(i).getWidth(),poOngGame.getRackets().get(i).getHeight(),this);
        }
        drawScore(g);

    }

    private void drawScore(Graphics g) {
        Font score = new Font("Bauhaus 93", Font.BOLD, 20);
        g.setFont(score);
        g.setColor(Color.CYAN);
        for(int i = 0; i< 2; i ++){
            g.drawString("Score",10,poOngGame.getScore(i).getYPos());
            g.drawString(Integer.toString(poOngGame.getScore(i).getScore()), poOngGame.getScore(i).getXPos(),poOngGame.getScore(i).getYPos());
        }
    }


















    public void paint(){
        this.repaint();
    }

    public void setPoOngGame(POOngGame poOngGame) {
        this.poOngGame = poOngGame;
    }

    public void setPlayers(LinkedList<Image> players){
        this.players = players;
    }

    public  void setPowers(LinkedList<Image> powers){
        this.powers = powers;
    }

    public void setFiled(Image field){
        this.field = field;
    }
}

