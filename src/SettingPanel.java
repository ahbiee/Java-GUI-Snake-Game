import javax.swing.*;
import java.awt.*;

public class SettingPanel extends JPanel{
    static final int PANEL_WIDTH = 800;
    static final int PANEL_HEIGHT = 500;

    int difficulty;

    GameFrame gameFrame;
    JButton homeButton;
    JRadioButton easyButton;
    JRadioButton mediumButton;
    JRadioButton hardButton;
    ButtonGroup difficultyGroup;

    Font difficultyFont;
    JLabel setDifficulty;

    SettingPanel(GameFrame frame){
        difficultyFont = new Font("MV Boli", Font.BOLD, 20);

        difficulty = 150;

        setButtons();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.gameFrame = frame;
        this.setLayout(null);
        this.setBackground(Color.darkGray);
        // change sound volume
    }

    private void setButtons(){
        homeButton = new JButton("← Home");
        homeButton.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(Color.darkGray);
        homeButton.setBorderPainted(false);
        homeButton.setBounds(25, 400, 200, 50);
        homeButton.addActionListener(e->gameFrame.showPanel("Menu"));

        this.add(homeButton);

        setDifficulty();
    }

    private void setDifficulty(){
        setDifficulty = new JLabel("Difficulty:");
        setDifficulty.setFont(new Font("MV Boli", Font.BOLD, 30));
        setDifficulty.setForeground(Color.WHITE);
        setDifficulty.setBackground(Color.darkGray);
        setDifficulty.setBounds(50, 90, 200, 50);

        easyButton = new JRadioButton("Easy");
        easyButton.setFont(difficultyFont);
        easyButton.setForeground(Color.WHITE);
        easyButton.setBackground(Color.darkGray);
        easyButton.setBorderPainted(false);
        easyButton.setFocusPainted(false);
        easyButton.setBounds(250, 100, 100, 30);
        easyButton.addActionListener(e->{
            difficulty = 200;
            gameFrame.setDifficulty(difficulty);
        });

        mediumButton = new JRadioButton("Medium");
        mediumButton.setFont(difficultyFont);
        mediumButton.setForeground(Color.WHITE);
        mediumButton.setBackground(Color.darkGray);
        mediumButton.setBorderPainted(false);
        mediumButton.setFocusPainted(false);
        mediumButton.setBounds(400, 100, 150, 30);
        mediumButton.addActionListener(e->{
            difficulty = 150;
            gameFrame.setDifficulty(difficulty);
        });
        mediumButton.setSelected(true);

        hardButton = new JRadioButton("Hard");
        hardButton.setFont(difficultyFont);
        hardButton.setForeground(Color.WHITE);
        hardButton.setBackground(Color.darkGray);
        hardButton.setBorderPainted(false);
        hardButton.setFocusPainted(false);
        hardButton.setBounds(600, 100, 150, 30);
        hardButton.addActionListener(e->{
            difficulty = 100;
            gameFrame.setDifficulty(difficulty);
        });

        difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easyButton);
        difficultyGroup.add(mediumButton);
        difficultyGroup.add(hardButton);

        this.add(setDifficulty);
        this.add(easyButton);
        this.add(mediumButton);
        this.add(hardButton);
    }
}
