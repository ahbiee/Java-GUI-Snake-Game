import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel{
    static final int PANEL_WIDTH = 800;
    static final int PANEL_HEIGHT = 500;

    int score = 0;
    String difficulty;

    GameFrame gameFrame;
    JButton homeButton;
    JButton newGameButton;

    JLabel gameOverLabel;
    JLabel gameOverLabel2;

    GameOverPanel(GameFrame frame){
        this.gameFrame = frame;
        this.setBackground(Color.darkGray);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        setButton();
    }

    public void setScore(){
        this.score = gameFrame.getScore();
        int dif = gameFrame.getDifficulty();
        if(dif == 100) difficulty = "Hard";
        else if(dif == 150) difficulty = "Medium";
        else difficulty = "Easy";
        gameOverLabel2.setText("<html>You Scored " + score + " in " + difficulty + "!</html>");
    }

    private void setButton(){
        gameOverLabel = new JLabel();
        gameOverLabel.setFont(new Font("MV Boli", Font.BOLD, 100));
        gameOverLabel.setText("GAME OVER");
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setBounds(75, 50, 700, 100);
        gameOverLabel.setVisible(true);

        gameOverLabel2 = new JLabel();
        gameOverLabel2.setFont(new Font("MV Boli", Font.BOLD, 50));
        gameOverLabel2.setForeground(Color.WHITE);
        gameOverLabel2.setBounds(100, 150, 700, 150);
        gameOverLabel2.setVisible(true);

        homeButton = new JButton("回主畫面 Menu");
        homeButton.setFont(new Font("宋体", Font.PLAIN, 30));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(Color.darkGray);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setBounds(200, 400, 400, 50);
        homeButton.addActionListener(e->gameFrame.showPanel("Menu"));

        newGameButton = new JButton("新遊戲 New Game");
        newGameButton.setFont(new Font("宋体", Font.PLAIN, 30));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setBackground(Color.darkGray);
        newGameButton.setBorderPainted(false);
        newGameButton.setFocusPainted(false);
        newGameButton.setBounds(200, 330, 400, 50);
        newGameButton.addActionListener(e->gameFrame.showPanel("Game"));

        this.add(gameOverLabel);
        this.add(gameOverLabel2);
        this.add(newGameButton);
        this.add(homeButton);
    }
}
