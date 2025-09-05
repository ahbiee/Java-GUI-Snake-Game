import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Objects;

public class GameFrame extends JFrame{
    private int score = 0;
    private int difficulty = 150;
    private float soundVolume = 0.0f;
    private String color = "綠色 Green";

    CardLayout cardLayout;
    JPanel mainPanel;

    GamePanel gamePanel;
    MenuPanel menuPanel;
    SettingPanel settingPanel;
    HelpPanel helpPanel;
    GameOverPanel gameOverPanel;

    Clip menuMusic;
    Clip inGameMusic;

    FloatControl menuMusicControl;
    FloatControl inGameMusicControl;

    GameFrame(){
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        setPanel();
        loadMusic();
        playMenuMusic();

        this.add(mainPanel);
        this.setTitle("SnakeGame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void setPanel(){
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        settingPanel = new SettingPanel(this);
        helpPanel = new HelpPanel(this);
        gameOverPanel = new GameOverPanel(this);

        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(gamePanel, "Game");
        mainPanel.add(settingPanel, "Setting");
        mainPanel.add(helpPanel, "Help");
        mainPanel.add(gameOverPanel, "GameOver");
    }

    public void showPanel(String panelName){
        cardLayout.show(mainPanel, panelName);
        if("Game".equals(panelName)){
            playInGameMusic();
            gamePanel.startGame();
        }
        else if("GameOver".equals(panelName)){
            if(inGameMusic.isRunning()) inGameMusic.stop();
            gameOverPanel.setScore();
        }
        else if("Menu".equals(panelName)){
            playMenuMusic();
        }
        else{
            if(!menuMusic.isRunning()) menuMusic.start();
        }
    }

    public void setScore(int score){
        if(score >= 0) this.score = score;
    }

    public int getScore(){return score;}

    public void setDifficulty(int difficulty){this.difficulty = difficulty;}

    public int getDifficulty(){return difficulty;}

    public void setVolume(float volume){
        this.soundVolume = volume;
        menuMusicControl.setValue(soundVolume);
        inGameMusicControl.setValue(soundVolume);
    }

    public float getVolume(){return soundVolume;}

    public void setColor(String color){this.color = color;}

    public String getColor(){return color;}

    private void loadMusic(){
        try{
            InputStream is1 = Objects.requireNonNull(getClass().getResourceAsStream("/menuMusic.wav"));
            AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(new BufferedInputStream(is1));
            menuMusic = AudioSystem.getClip();
            menuMusic.open(audioInputStream1);
            menuMusicControl = (FloatControl) menuMusic.getControl(FloatControl.Type.MASTER_GAIN);

            InputStream is2 = Objects.requireNonNull(getClass().getResourceAsStream("/inGameMusic.wav"));
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new BufferedInputStream(is2));
            inGameMusic = AudioSystem.getClip();
            inGameMusic.open(audioInputStream2);
            inGameMusicControl = (FloatControl) inGameMusic.getControl(FloatControl.Type.MASTER_GAIN);

        }
        catch(Exception e){
            System.out.println("Error" + e);
        }
    }

    public void playMenuMusic(){
        if(menuMusic.isRunning()) menuMusic.stop();
        if(inGameMusic.isRunning()) inGameMusic.stop();
        menuMusic.start();
        menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playInGameMusic(){
        if(menuMusic.isRunning()) menuMusic.stop();
        if(inGameMusic.isRunning()) inGameMusic.stop();
        inGameMusic.setFramePosition(0);
        inGameMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
