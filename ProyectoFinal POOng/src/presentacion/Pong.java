package presentacion;

import aplicacion.POOngGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Pong {

    private Window pong;
    private Image field = new ImageIcon("fielReal.png").getImage();
    private LinkedList<Image> powerImage = new LinkedList<Image>();
    private LinkedList<String> powerName = new LinkedList<String>();
    private String mode = " ";
    private LinkedList<Image> players = new LinkedList<Image>();
    private String namePlayerOne = "";
    private String namePlayerTwo = "";
    private POOngGame poOngGame;
    private GameBoard gameBoard;
    private String tBall = "";
    private boolean pause;
    private Timer paint;
    private int moveBall = 0;
    private int control = 0;
    private JButton restart = new JButton();JButton save = new JButton();JButton open = new JButton();JButton exit = new JButton();JButton start = new JButton();
    private JPanel panel = new JPanel();
    private JButton next = new JButton("Continue");


    public Pong(){
        pong = new Window();
        poOngGame = new POOngGame();
        pong.addKeyListener(new EventoTablero());
        pong.setFocusable(true);
        pause = true;
    }

    public void runPong(){
        prepareElements();
        prepareAction();
    }

    public void prepareElements(){
        if(mode.equals("OnePlayer")){
            poOngGame.addBall(tBall);
            poOngGame.addOnePlayer();
            poOngGame.addObjective();
            poOngGame.setPowerName(powerName);
            poOngGame.addScore(namePlayerOne,namePlayerTwo);
        }else if(mode.equals("TwoPlayers")){
            poOngGame.addBall(tBall);
            poOngGame.addTwoPlayers();
            poOngGame.addObjective();
            poOngGame.setPowerName(powerName);
            poOngGame.addScore(namePlayerOne,namePlayerTwo);
        }else {
            poOngGame.addBall(tBall);
            poOngGame.addBots();
            poOngGame.addObjective();
            poOngGame.setPowerName(powerName);
            poOngGame.addScore(namePlayerOne,namePlayerTwo);
        }
        gameBoard = new GameBoard(pong.getWidth(),pong.getHeight());
        gameBoard.setPoOngGame(this.poOngGame);gameBoard.setFiled(field);gameBoard.setPlayers(players);
        gameBoard.setPowers(powerImage);
        pong.add(gameBoard);

    }

    public void prepareAction(){
        pong.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() ==  KeyEvent.VK_ESCAPE){
                    pause = true;
                    poOngGame.setPause(true);
                    poOngGame.getObjective().cancelTimer();
                    pause();
                    gameOver();
                }
                if(e.getExtendedKeyCode() == KeyEvent.VK_A || e.getExtendedKeyCode() == KeyEvent.VK_D){
                    if(control == 0) {
                        pause = false;
                        poOngGame.setPause(false);
                        poOngGame.runObjective();
                        control++;
                    }
                    moveBall();
                    moveBall++;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionStart();
            }
        });
    }

    public void optionStart(){
        panel.setVisible(false);
        control = 0;
    }

    public void repaint(){
        paint = new Timer();
        TimerTask paint = new TimerTask() {
            @Override
            public void run() {
                if(!pause) {
                    gameBoard.paint();
                    moveRacket();
                    pause = poOngGame.getPause();
                    if(pause)control = 0;
                }
            }
        };
        this.paint.schedule(paint,0,6);
    }

    public void moveBall(){
        if(this.moveBall == 0) {
            repaint();
            poOngGame.run();
            if(!powerName.isEmpty())poOngGame.timePower();
        }
    }

    public void moveRacket() {

        if(EventoTablero.a) {
            poOngGame.moveRacked(false,0);
        }

        if(EventoTablero.d) {
            poOngGame.moveRacked(true,0);
        }

        if(EventoTablero.up){
            poOngGame.moveRacked(false,1);
        }

        if(EventoTablero.down){
            poOngGame.moveRacked(true,1);
        }
    }

    public void pause(){
        panel.setBounds(0,0,pong.getWidth()/3,pong.getHeight());
        panel.setLayout(null);
        panel.setBackground(Color.cyan);
        restart.setText("Restart");save.setText("Save");exit.setText("Exit");open.setText("Open");start.setText("Start");
        panel.add(start);panel.add(restart);panel.add(save);panel.add(open);panel.add(exit);
        start.setBounds(35,pong.getHeight()/2-70,pong.getWidth()/4,50);
        restart.setBounds(35,pong.getHeight()/2,pong.getWidth()/4,50);
        save.setBounds(35,pong.getHeight()/2+70,pong.getWidth()/4,50);
        open.setBounds(35,pong.getHeight()/2+140,pong.getWidth()/4,50);
        exit.setBounds(35,pong.getHeight()/2+280,pong.getWidth()/4,50);
        panel.setVisible(false);
        gameBoard.add(panel);
        gameBoard.repaint();
    }

    public void gameOver(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(pong.getWidth()/2-350,pong.getHeight()/2-150,500,300);
        JLabel title = new JLabel("Game Over");JLabel winner = new JLabel("Winner : ");JLabel photo = new JLabel();

        title.setBounds(panel.getWidth()/2,20,500,100);
        title.setFont(new Font("Bauhaus 93",0,50));

        winner.setBounds(panel.getWidth()/2,panel.getHeight()/2-25,panel.getWidth(),100);
        winner.setFont(new Font("Bauhaus 93",0,50));

        panel.add(title);panel.add(winner);panel.add(photo);
        panel.setBackground(Color.black);
        gameBoard.add(panel);
        gameBoard.repaint();
    }

    public void setNamePlayerTwo(String namePlayerTwo) {
        this.namePlayerTwo = namePlayerTwo;
    }

    public void setNamePlayerOne(String namePlayerOne) {
        this.namePlayerOne = namePlayerOne;
    }

    public void setPowerName(LinkedList<String> powerName) {
        this.powerName = powerName;
    }

    public void setPowerImage(LinkedList<Image> powerImage) {
        this.powerImage = powerImage;
    }

    public void setPlayer(Image player){
        this.players.add(player);
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setVisible(){
        pong.setVisible(true);
    }

    public void setBall(String tBall){
        this.tBall = tBall;
    }

}
