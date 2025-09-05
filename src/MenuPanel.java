import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

public class MenuPanel extends JPanel{
    static final int PANEL_WIDTH = 800;
    static final int PANEL_HEIGHT = 500;

    GameFrame gameFrame;

    JLabel titleLabel;

    JButton startButton;
    JButton settingButton;
    JButton helpButton;
    JButton exitButton;

    Font titleFont = new Font("MV Boli", Font.BOLD, 100);
    Font selectFont = new Font("宋体", Font.PLAIN, 30);

    Color wordC = Color.WHITE;
    Color backgroundC = Color.darkGray;

    MenuPanel(GameFrame frame){
        this.gameFrame = frame;
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(backgroundC);
        this.setLayout(null);

        setLabel();
        setButton();
    }

    private void setLabel(){
        titleLabel = new JLabel("Snake Game");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(wordC);
        titleLabel.setBounds(100, 50, 600, 100);
        this.add(titleLabel);
    }

    private void setButton(){
        startButton = new JButton("開始遊戲 Start");
        startButton.setFont(selectFont);
        startButton.setForeground(wordC);
        startButton.setBackground(backgroundC);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setBounds(200, 250, 400, 50);
        startButton.addActionListener(e -> gameFrame.showPanel("Game"));

        settingButton = new JButton("設定 Settings");
        settingButton.setFont(selectFont);
        settingButton.setForeground(wordC);
        settingButton.setBackground(backgroundC);
        settingButton.setBorderPainted(false);
        settingButton.setFocusPainted(false);
        settingButton.setBounds(200, 320, 400, 50);
        settingButton.addActionListener(e -> gameFrame.showPanel("Setting"));

        helpButton = new JButton("幫助 Help");
        helpButton.setFont(selectFont);
        helpButton.setForeground(wordC);
        helpButton.setBackground(backgroundC);
        helpButton.setBorderPainted(false);
        helpButton.setFocusPainted(false);
        helpButton.setBounds(200, 390, 400, 50);
        helpButton.addActionListener(e->gameFrame.showPanel("Help"));

        exitButton = new JButton("EXIT →");
        exitButton.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        exitButton.setForeground(wordC);
        exitButton.setBackground(backgroundC);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(575, 400, 200, 50);
        exitButton.addActionListener(e->System.exit(0));

        this.add(exitButton);
        this.add(startButton);
        this.add(settingButton);
        this.add(helpButton);
    }
}
