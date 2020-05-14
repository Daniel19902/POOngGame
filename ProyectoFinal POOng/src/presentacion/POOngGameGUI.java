package presentacion;
// hola que hace nada okas
import aplicacion.POOngGame;
import aplicacion.Power;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;


public class POOngGameGUI {
    Window start;
    Window pong;
    Window characters;
    private JButton onePlayer;
    private JButton twoPlayers;
    private JButton bot;

    /**pongGame*/
    private LinkedList<Image> imagePlayers = new LinkedList<Image>();
    private boolean startGame = false;
    private boolean pauseGame = false;
    private GameBoard gameBoard;
    private POOngGame poOngGame;
    JPanel pausePanel = new JPanel();
    JButton restart = new JButton("Restart");
    JButton save = new JButton("Save");
    JButton open = new JButton("Open");
    JButton exit = new JButton("Exit");
    private boolean times = true;

    /**difficulty*/
    Window difficulty;
    JButton[] panelBots = {new JButton(),new JButton(), new JButton(), new JButton()};
    Image[] bots = {new ImageIcon("4.png").getImage(),new ImageIcon("12.png").getImage(), new ImageIcon("20.png").getImage(),new ImageIcon("img2.png").getImage()};
    JLabel[] names = {new JLabel("Extreme"),new JLabel("Sniper"),new JLabel("Lazy"),new JLabel("Greedy")};
    String botSelected = "";

    /**Information*/
    Window information;
    JButton next;
    JButton back;

    /** bot choose*/

    /**Characters*/
    JButton[] panelCharacters = {new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
    Image[] character = {new ImageIcon("4.png").getImage(),new ImageIcon("12.png").getImage(),
                        new ImageIcon("20.png").getImage(),new ImageIcon("img2.png").getImage(),
                        new ImageIcon("it.jpg").getImage(),new ImageIcon("21.jpg").getImage(),
                        new ImageIcon("22.jpg").getImage(),new ImageIcon("21.jpg").getImage()};

    JButton nextCharacter = new JButton();
    String nick = "";

    /**Setting*/

    Window setting;
    JPanel vs;
    JLabel player;
    JLabel playerTwo;
    JButton play;
    JButton powers;
    JComboBox<String> score;
    JComboBox<String> tBall;

    /**Setting powers*/

    HashMap<String,Image> imagePowers;

    JCheckBox[] powersSelect ={new JCheckBox("Freezer"),new JCheckBox("Fast Ball"),new JCheckBox("Flash"),
                                new JCheckBox("Phantom Racket"),new JCheckBox("Cold Racket"),
                                 new JCheckBox("Turtle"),new JCheckBox("Energy")};

    LinkedList<Image> selectPowers = new LinkedList<Image>();

    Window settingPowers;
    JPanel panelPowers;
    JPanel imagePower;
    JButton backSetting;
    JPanel powersInformation;

    public POOngGameGUI(){
        poOngGame = new POOngGame();
        pong = new Window();
        start = new Window();
        //prepareElementsPong();
        prepareElementsStart();
        prepareActions();
    }

    public void prepareElementsStart(){
        JPanel panelTitle =  new JPanel();
        panelTitle.setBounds(0,0,start.getWidth(),300);
        panelTitle.setLayout(null);
        JLabel title = new JLabel("PongGame");
        title.setFont(new Font("Bauhaus 93",0,100));
        title.setBounds(0,0,panelTitle.getWidth(),300);
        panelTitle.setBackground(Color.cyan);
        panelTitle.add(title);
        start.add(panelTitle);


        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(Color.RED);
        panelButtons.setBounds(start.getWidth()/2-50,start.getHeight()/2,90,230);
        panelButtons.setLayout(new GridLayout(3,1,0,60));
        onePlayer = new JButton("1 PLAYER");
        twoPlayers = new JButton("2 PLAYER");
        bot = new JButton("BOTS");
        panelButtons.add(onePlayer);
        panelButtons.add(twoPlayers);
        panelButtons.add(bot);
        start.add(panelButtons);
    }

    public void prepareActions(){
        onePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poOngGame.addPlayer();
                poOngGame.addBot();
                prepareElementDifficulty();
            }
        });
    }


    public void prepareElementDifficulty(){
        int i = 0;
        difficulty = new Window();
        difficulty.setVisible(true);
        start.setVisible(false);
        /** bots*/
        JPanel panelBot = new JPanel();
        panelBot.setLayout(new GridLayout(1,4,30,50));
        panelBot.setBounds(20,(difficulty.getHeight()/2)-100,difficulty.getWidth()-55,150);
        panelBot.setBackground(Color.black);
        JPanel panelTitle = new JPanel();
        panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER,100,50));
        panelTitle.setBounds(0,(difficulty.getHeight()/2)+100,difficulty.getWidth(),150);
        for(JButton j : panelBots){
            j.setBackground(Color.blue);
            j.setIcon(new ImageIcon(bots[i].getScaledInstance(150,150,Image.SCALE_SMOOTH)));
            panelBot.add(j);
            names[i].setFont(new Font("Bauhaus 93",0,20));
            panelTitle.add(names[i]);
            i++;
        }
        difficulty.add(panelTitle);
        difficulty.add(panelBot);
        /** title*/
        setTile(difficulty, new JLabel("Difficulty"));
        prepareActionDifficulty();
    }

    public void setTile(Window window, JLabel title){
        JPanel panelTitle = new JPanel();
        title.setFont(new Font("Bauhaus 93",0,100));
        panelTitle.add(title);
        panelTitle.setBackground(Color.orange);
        panelTitle.setBounds(0,0,window.getWidth(),200);
        window.add(panelTitle);
    }

    public void prepareActionDifficulty(){
        for (int i = 0; i < panelBots.length;i++){
            int finalI = i;
            panelBots[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    difficulty.setVisible(false);
                    botSelected = panelBots[finalI].getIcon().toString();
                    prepareElementInformation(finalI);
                }
            });
        }
    }

    public void prepareElementInformation(int i){
        information = new Window();
        information.setVisible(true);
        JPanel panelInformation = new JPanel();
        JLabel image = new JLabel(new ImageIcon(bots[i].getScaledInstance(400,300,Image.SCALE_SMOOTH)));
        JLabel pInformation = new JLabel();
        image.setBounds((information.getWidth()/2)-200,30,400,300);
        panelInformation.setBounds((information.getWidth()/2)-150,(information.getHeight()/2),300,200);
        panelInformation.setBackground(Color.cyan);

        pInformation.setSize(400,400);
        panelInformation.add(pInformation);
        buttonsPosition();
        prepareActionInformation(i);
        information.add(image);
        information.add(panelInformation);
    }

    public void buttonsPosition(){
        next = new JButton("Next");
        back = new JButton("Back");
        JPanel panelButtons = new JPanel();
        panelButtons.setBounds((information.getWidth()/2)-150,information.getHeight()/2+250,300,50);
        panelButtons.setLayout(new GridLayout(1,2,90,90));
        panelButtons.add(back);
        panelButtons.add(next);
        information.add(panelButtons);
    }

    public void prepareActionInformation(int i){
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePlayers.add(bots[i]);
                prepareElementCharacter();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionBack(information,difficulty);
            }
        });
    }

    public void optionBack(Window start, Window end){
        start.setVisible(false);
        end.setVisible(true);
    }



    public void prepareElementCharacter(){
        int i = 0;
        characters = new Window();
        information.setVisible(false);
        characters.setVisible(true);
        JPanel listCharacters = new JPanel();
        JPanel imageCharacter = new JPanel();
        imageCharacter.setLayout(null);
        JLabel imageC = new JLabel();
        imageC.setSize(450,400);imageC.setOpaque(true);imageC.setBackground(Color.cyan);
        imageCharacter.setBounds((characters.getWidth()/2)-50,20,450,400);
        imageCharacter.setBackground(Color.black);
        imageCharacter.add(imageC);
        listCharacters.setBounds(20,20,(characters.getWidth()/4)+100,characters.getHeight()-100);
        listCharacters.setBackground(Color.GREEN);
        listCharacters.setLayout(new GridLayout(4,2,20,40));
        for(JButton j : panelCharacters){
            listCharacters.add(j);
            j.setIcon(new ImageIcon(character[i].getScaledInstance(50,50,Image.SCALE_SMOOTH)));
            i++;
        }
        characters.add(imageCharacter);
        characters.add(listCharacters);
        buttonsCharacter();
        prepareActionsCharacter(imageC);
    }

    public void buttonsCharacter(){
        nextCharacter.setText("Next");
        JTextField nick = new JTextField();
        nick.setBounds((characters.getWidth()/2)+75,500,200,20);
        nextCharacter.setBounds((characters.getWidth()/2)+125,600,100,50);
        characters.add(nextCharacter);
        characters.add(nick);
        this.nick = nick.getText();
        System.out.println(this.nick);

    }

    public void prepareActionsCharacter(JLabel imageCharacter){
        final boolean[] select = {false};
        for(int i = 0; i< panelCharacters.length;i++){
            int finalI = i;
            panelCharacters[i].addMouseListener(new MouseListener() {
                Icon icon = new ImageIcon(character[finalI].getScaledInstance(450,400,Image.SCALE_SMOOTH));
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    imageCharacter.setIcon(icon);
                    imagePlayers.add(character[finalI]);
                    select[0] = true;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!select[0]){
                        imageCharacter.setIcon(icon);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
        nextCharacter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                prepareElementSetting();
            }
        });
    }

    public void prepareElementSetting(){
        JLabel title = new JLabel("VS");
        title.setFont(new Font("Bauhaus 93",0,50));
        setting = new Window();setting.setVisible(true);characters.setVisible(false);
        vs = new JPanel(); vs.setBackground(Color.cyan); vs.setBounds(40,20,setting.getWidth()-100,200);
        vs.setLayout(null);
        JLabel image1 = new JLabel(new ImageIcon(imagePlayers.get(1).getScaledInstance(200,200,Image.SCALE_SMOOTH)));
        JLabel image2 = new JLabel(new ImageIcon(imagePlayers.get(0).getScaledInstance(200,200,Image.SCALE_SMOOTH)));
        image2.setBounds(vs.getWidth()-260,0,200,200);
        image1.setBounds(60,0,200,200);
        vs.add(image1);vs.add(image2);
        addComboBox();
        addButtonsSetting();
        title.setBounds(vs.getWidth()/2-25,vs.getHeight()/2-25,50,50);
        vs.add(title);
        setting.add(score);setting.add(tBall);
        prepareActionSetting();
        setting.add(vs);

    }

    public void addComboBox(){
        String[] scoreSetting = {"Score(Default)","1","2","3","4","5","6","7","8","9","10"};
        String[] tBallSetting = {"Ball(Default)","Slow","Fast","incremental"};
        score = new JComboBox<String>();
        tBall = new JComboBox<String>();
        for(String s : scoreSetting) score.addItem(s);
        for(String s : tBallSetting) tBall.addItem(s);
        tBall.setBounds(vs.getWidth()-175,260,150,25);
        score.setBounds(125,260,150,25);
    }

    public void addButtonsSetting(){
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1,2,60,60));panelButtons.setBounds(vs.getWidth()/2+50,setting.getHeight()-200,300,50);
        panelButtons.setBackground(Color.RED);
        play = new JButton("Play");
        powers = new JButton("Powers");
        panelButtons.add(play); panelButtons.add(powers);
        setting.add(panelButtons);
    }

    public void prepareActionSetting(){
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareElementsPong();
            }
        });

        powers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareElementPower();
            }
        });

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

    public void prepareElementPower(){
        JLabel spell = new JLabel();
        settingPowers = new Window();settingPowers.setVisible(true);setting.setVisible(false);
        panelPowers = new JPanel();
        imagePower = new JPanel();
        panelPowers.setBackground(Color.RED);panelPowers.setBounds(20,20,settingPowers.getWidth()/4+100,characters.getHeight()-100);
        panelPowers.setLayout(new GridLayout(7,1,50,30));
        imagePower = new JPanel();imagePower.setBounds((settingPowers.getWidth()/2)-50,20,450,400);
        imagePower.setBackground(Color.black);
        imagePower.setLayout(null);
        spell.setBounds(0,0,450,400);
        imagePower.add(spell);
        for(JCheckBox c : powersSelect){
            c.setFont(new Font("Bauhaus 93",0,15));
            c.setBackground(Color.RED);
            panelPowers.add(c);
        }
        addPowers();
        addButtonsPower();
        prepareActionPowerSelect(spell);
        settingPowers.add(imagePower);
        settingPowers.add(panelPowers);
    }

    public void addButtonsPower(){
        backSetting = new JButton("Back");
        powersInformation = new JPanel();
        powersInformation.setBackground(Color.RED);
        powersInformation.setBounds((settingPowers.getWidth()/2)-50,440,450, 100);
        backSetting.setBounds((settingPowers.getWidth()/2)+125,570,100,50);
        settingPowers.add(backSetting);
        settingPowers.add(powersInformation);
    }

    public void prepareActionPowerSelect(JLabel spell){
        for(JCheckBox c : powersSelect){
            c.addMouseListener(new MouseListener() {
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
                    spell.setIcon(new ImageIcon(imagePowers.get(c.getText()).getScaledInstance(450,400,Image.SCALE_SMOOTH)));
                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }


        backSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JCheckBox c : powersSelect){
                    if(c.isSelected()){
                        selectPowers.add(imagePowers.get(c.getText()));
                    }
                }
                optionBack(settingPowers,setting);
            }
        });

    }




























    public void prepareElementsPong(){

        /** pong*/

        gameBoard = new GameBoard(pong.getWidth(),pong.getHeight(),poOngGame);
        Image field = new ImageIcon("fielReal.png").getImage();
        field = field.getScaledInstance(pong.getWidth(),pong.getHeight(),Image.SCALE_SMOOTH);
        gameBoard.setFiled(field);
        gameBoard.setPlayers(imagePlayers);
        gameBoard.setPowers(selectPowers);
        pong.add(gameBoard);

        /** teclado */
        pong.addKeyListener(new EventoTablero());
        pong.setVisible(true);
        optionPause();
    }

    public void repaintGame(GameBoard gameBoard){
        Timer timer = new Timer();
        TimerTask repaint = new TimerTask() {
            @Override
            public void run() {
                if(!pauseGame) {
                    gameBoard.paint();
                    moveRacket();
                }
            }
        };
        timer.schedule(repaint,0,6);
    }
    public void restart(){
        poOngGame.setPause(false);
        pauseGame = false;
        startGame = true;
    }

    public void optionPause(){
        pong.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() ==  KeyEvent.VK_ESCAPE) {
                    pauseGame = true;
                    poOngGame.setPause(true);
                    prepareElementsPanelPause();
                }
                if(e.getExtendedKeyCode() == KeyEvent.VK_A){
                    if(times) {
                        poOngGame.setPause(false);
                        repaintGame(gameBoard);
                        poOngGame.run();
                        startGame = true;
                        times = false;
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
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

    public void prepareElementsPanelPause(){
        pausePanel.setVisible(true);
        pausePanel.setBackground(new Color(95,131,90,200));
        pausePanel.setLayout(null);
        restart.setBounds(0,pong.getHeight()/2,pong.getWidth()/4,50);
        save.setBounds(0,pong.getHeight()/2+70,pong.getWidth()/4,50);
        open.setBounds(0,pong.getHeight()/2+140,pong.getWidth()/4,50);
        exit.setBounds(0,pong.getHeight()/2+280,pong.getWidth()/4,50);
        restart.setBackground(Color.WHITE);
        save.setBackground(Color.WHITE);
        open.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        pausePanel.add(restart); pausePanel.add(save); pausePanel.add(open); pausePanel.add(exit);
        pausePanel.setBounds(0,0,pong.getWidth()/4,pong.getHeight());
        gameBoard.add(pausePanel);
        gameBoard.repaint();
        prepareActionsPanelPause();
    }

    public void prepareActionsPanelPause(){
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausePanel.setVisible(false);
                restart();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionSave();
            }
        });

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionOpen();
            }
        });
    }


    public void optionSave(){
        try {
            JFileChooser save = new JFileChooser();
            save.showSaveDialog(pong);
            File file = save.getSelectedFile();
            if(file != null) poOngGame.save(file);
        }catch (IOException e){
            JOptionPane.showConfirmDialog(pong,e.getMessage(),"av", JOptionPane.YES_NO_OPTION);
        }
    }

    public void optionOpen(){
        try{
            System.out.println("YOO!!");
            JFileChooser open = new JFileChooser();
            open.showOpenDialog(pong);
            File file = open.getSelectedFile();
            if(file != null) {
                this.poOngGame = poOngGame.open(file);
                gameBoard.setPoOngGame(this.poOngGame);
                if (!times) poOngGame.run();
                gameBoard.repaint();
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args){
        POOngGameGUI poOngGameGUI = new POOngGameGUI();
        poOngGameGUI.start.setVisible(true);

    }

}