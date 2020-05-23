package presentacion;

import aplicacion.POOngGame;
import aplicacion.Score;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class GameBoard extends JPanel {

    /** images*/
    private LinkedList<Image> powers = new LinkedList<Image>();
    private LinkedList<Image> players = new LinkedList<Image>();
    private Image field = null;
    private Image ball = new ImageIcon("00.png").getImage();
    private Image objective = new ImageIcon("tiro.jpg").getImage();;
    private Image block = new ImageIcon("bl.jpg").getImage();

    /** images*/

    private POOngGame poOngGame;
    private int width;
    private int height;

    public GameBoard(int width, int height){
        setLayout(null);
        setBounds(0,0,width,height);
        setBackground(Color.green);
        this.height = height;
        this.width = width;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(field,0,0,width,730,this);

        g.drawImage(ball,(int) Math.ceil(poOngGame.getBall().getX()),(int) Math.ceil(poOngGame.getBall().getY()),poOngGame.getBall().getWidth(),poOngGame.getBall().getHeight(),this);

        if(poOngGame.getPower() != null && !powers.isEmpty()) {
            g.drawImage(powers.get(poOngGame.getRandom()), 441-35,384-35, 35, 35, this);
        }

        if(poOngGame.getBlock() != null){
            if(poOngGame.getBlock().isBlock()){
                g.drawImage(block,poOngGame.getBlock().getXPos(),poOngGame.getBlock().getYPos(),poOngGame.getBlock().getWeight(),poOngGame.getBlock().getHeight(),this);
            }
        }

        if(poOngGame.isObjective()){
            g.drawImage(objective,poOngGame.getObjective().getXPos(),poOngGame.getObjective().getYPos(),50,50,this);
        }

        for(int i = 0; i < 2; i++){
            g.drawImage(players.get(i),poOngGame.getRackets().get(i).getX(),poOngGame.getRackets().get(i).getY(),poOngGame.getRackets().get(i).getWidth(),poOngGame.getRackets().get(i).getHeight(),this);
        }

        drawScore(g);
    }

    private void drawScore(Graphics g) {
        Font score = new Font("Bauhaus 93", Font.BOLD, 30);
        Score data;
        g.setFont(score);g.setColor(Color.black);
        for(int i = 0; i< 2; i ++){
            data = poOngGame.getScore(i);
            g.drawString(data.getName(),10,data.getY());
            g.drawString(Integer.toString(data.getScore()),150,data.getY());
        }
        drawFortaleza(g,g);
    }

    private void drawFortaleza(Graphics g, Graphics g2){
        Score data;
        g.setColor(Color.GREEN);
        if((175*75)/100 >= poOngGame.getScore(0).getFortaleza()){
            g.setColor(Color.ORANGE);
        }if((175*50)/100 >= poOngGame.getScore(0).getFortaleza()){
            g.setColor(Color.RED);
        }
        g.fillRect(width - 200, poOngGame.getScore(0).getY(), poOngGame.getScore(0).getFortaleza(), 30);

        g2.setColor(Color.GREEN);

        if((175*75)/100 >= poOngGame.getScore(1).getFortaleza()){
            g2.setColor(Color.ORANGE);
        }if((175*50)/100 >= poOngGame.getScore(1).getFortaleza()){
            g2.setColor(Color.RED);
        }
        g2.fillRect(width - 200, poOngGame.getScore(1).getY(), poOngGame.getScore(1).getFortaleza(), 30);
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

