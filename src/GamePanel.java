import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.Random;
import java.util.LinkedList;
import java.awt.Point;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.BasicStroke;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class GamePanel extends JPanel{
    static final int PANEL_WIDTH = 800;
    static final int PANEL_HEIGHT = 500;
    static final int BLOCK_SIZE = 50;
    static final int xBlocks = PANEL_WIDTH / BLOCK_SIZE;
    static final int yBlocks = PANEL_HEIGHT / BLOCK_SIZE;
    static final int INITIAL_DELAY_MS = 150;
    static final double SPEED_MULTIPLIER = 0.9;

    int delay = INITIAL_DELAY_MS;
    int score = 0;
    int foodX = 0;
    int foodY = 0;
    int level = 0;
    char direction = 'd';
    boolean directionChanged = false;
    boolean isRunning = false;

    GameFrame gameFrame;

    Color foodC = Color.red;
    Color snakeC = Color.GREEN;
    Color backgroundC = Color.darkGray;
    Color scoreC = Color.magenta;

    Random random;
    LinkedList<Point> snake;
    Timer timer;
    InputMap inputMap;
    ActionMap actionMap;

    Clip eatSound;
    Clip gameOverSound;
    Clip levelUpSound;

    GamePanel(GameFrame frame){
        this.gameFrame = frame;
        random = new Random();
        snake = new LinkedList<>();
        timer = new Timer(INITIAL_DELAY_MS, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                move();
                repaint();
            }
        });

        setKeyBindings();
        loadSound();

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(backgroundC);
        this.setOpaque(true);
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); // 用來畫黑色背景
        Graphics2D g2d = (Graphics2D) g;

        if(isRunning){
            drawFood(g2d);
            drawSnake(g2d);
            drawScore(g2d);
        }
    }

    private void drawScore(Graphics2D g2d){
        g2d.setColor(scoreC);
        g2d.setFont(new Font("MV Boli", Font.PLAIN, 25));
        String levelText = " Level: " + level;
        if(level == 5) levelText += " (max)";
        g2d.drawString("Score: " + score + levelText, 280, 50);
    }

    private void drawFood(Graphics2D g2d){
        g2d.setColor(foodC);
        g2d.setStroke(new BasicStroke(1));
        g2d.fillRect(foodX, foodY, BLOCK_SIZE, BLOCK_SIZE);
    }

    private void drawSnake(Graphics2D g2d){
        g2d.setColor(snakeC);
        for(Point point : snake){
            g2d.fillRect(point.x, point.y, BLOCK_SIZE, BLOCK_SIZE);
        }
    }

    private void newFood(){
        boolean samePos;
        do {
            samePos = false;
            foodX = random.nextInt(xBlocks) * BLOCK_SIZE;
            foodY = random.nextInt(yBlocks) * BLOCK_SIZE;
            for(Point point : snake){
                if(Objects.equals(point, new Point(foodX, foodY))){
                    samePos = true;
                    break;
                }
            }
        }while(samePos);
    }

    public void startGame(){
        delay = INITIAL_DELAY_MS;
        timer.setDelay(delay);
        level = 0;
        score = 0;
        isRunning = true;
        direction = 'd';
        directionChanged = false;

        initSnake();
        newFood();
        timer.start();
        this.requestFocusInWindow();
        repaint();
    }

    private void initSnake(){
        snake.clear();
        for(int i=6; i>0; --i){
            snake.add(new Point(i*BLOCK_SIZE, 4*BLOCK_SIZE)); // add往list後加
        }
    }

    private void move(){
        Point head = snake.getFirst();
        int headX = head.x;
        int headY = head.y;
        switch(direction){
            case 'w' ->headY -= BLOCK_SIZE;
            case 'a' ->headX -= BLOCK_SIZE;
            case 's' ->headY += BLOCK_SIZE;
            case 'd' ->headX += BLOCK_SIZE;
        }
        Point newHead = new Point(headX, headY);

        if(checkGameOver(newHead)){
            gameOver();
            return;
        }

        snake.addFirst(newHead);
        if(Objects.equals(snake.getFirst(), new Point(foodX, foodY))){
            ++score;
            playEatSound();
            if(score % 3 == 0 && level < 5){
                delay = (int)(delay*SPEED_MULTIPLIER);
                playLevelUpSound();
                ++level;
                timer.setDelay(delay);
            }
            newFood();
        }
        else{
            snake.removeLast();
        }
        directionChanged = false;

    }

    private void setKeyBindings(){
        inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW); // 當視窗被選中時才啟用按鍵事件
        actionMap = this.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke('w'), "upAction");
        inputMap.put(KeyStroke.getKeyStroke('a'), "leftAction");
        inputMap.put(KeyStroke.getKeyStroke('s'), "downAction");
        inputMap.put(KeyStroke.getKeyStroke('d'), "rightAction");
        inputMap.put(KeyStroke.getKeyStroke((char)KeyEvent.VK_SPACE), "restartAction");

        actionMap.put("upAction", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(direction != 's' && isRunning && !directionChanged){
                    direction = 'w';
                    directionChanged = true;
                }
            }
        });
        actionMap.put("leftAction", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(direction != 'd' && isRunning && !directionChanged){
                    direction = 'a';
                    directionChanged = true;
                }
            }
        });
        actionMap.put("downAction", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(direction != 'w' && isRunning && !directionChanged){
                    direction = 's';
                    directionChanged = true;
                }
            }
        });
        actionMap.put("rightAction", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(direction != 'a' && isRunning && !directionChanged){
                    direction = 'd';
                    directionChanged = true;
                }
            }
        });
        actionMap.put("restartAction", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(!isRunning) startGame();
            }
        });
    }

    private boolean checkGameOver(Point point){
        boolean gameOver = false;
        for(int i=1; i<snake.size(); ++i){
            if(Objects.equals(snake.get(i), point)){
                gameOver = true;
                break;
            }
        }
        if(point.x >= PANEL_WIDTH || point.x < 0 || point.y >= PANEL_HEIGHT || point.y < 0){
            gameOver = true;
        }
        return gameOver;
    }

    private void gameOver(){
        timer.stop();
        gameOverSound.setFramePosition(0);
        gameOverSound.start();
        isRunning = false;
        gameFrame.setScore(score);
        gameFrame.showPanel("GameOver");
    }

    private void loadSound(){
        try{
            InputStream is1 = Objects.requireNonNull(getClass().getResourceAsStream("/getPoint.wav"));
            AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(new BufferedInputStream(is1));
            eatSound = AudioSystem.getClip();
            eatSound.open(audioInputStream1);

            InputStream is2 = Objects.requireNonNull(getClass().getResourceAsStream("/gameOver.wav"));
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new BufferedInputStream(is2));
            gameOverSound = AudioSystem.getClip();
            gameOverSound.open(audioInputStream2);

            InputStream is3 = Objects.requireNonNull(getClass().getResourceAsStream("/levelUp.wav"));
            AudioInputStream audioInputStream3 = AudioSystem.getAudioInputStream(new BufferedInputStream(is3));
            levelUpSound = AudioSystem.getClip();
            levelUpSound.open(audioInputStream3);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    private void playEatSound(){
        if(eatSound.isRunning()) eatSound.stop();
        eatSound.setFramePosition(0);
        eatSound.start();
    }

    private void playLevelUpSound(){
        if(levelUpSound.isRunning()) levelUpSound.stop();
        levelUpSound.setFramePosition(0);
        levelUpSound.start();
    }
}