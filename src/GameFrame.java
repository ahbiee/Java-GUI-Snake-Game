import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class GameFrame extends JFrame{
    private int score = 0;

    CardLayout cardLayout;
    JPanel mainPanel;

    GamePanel gamePanel;
    MenuPanel menuPanel;
    SettingPanel settingPanel;
    HelpPanel helpPanel;
    GameOverPanel gameOverPanel;

    GameFrame(){
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        setPanel();

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
        if("Game".equals(panelName)){gamePanel.startGame();}
        else if("GameOver".equals(panelName)){gameOverPanel.setScore();}
    }

    public void setScore(int score){this.score = score;}

    public int getScore(){return score;}
}
