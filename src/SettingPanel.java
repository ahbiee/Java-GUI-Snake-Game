import javax.swing.*;
import java.awt.*;

public class SettingPanel extends JPanel{
    static final int PANEL_WIDTH = 800;
    static final int PANEL_HEIGHT = 500;

    GameFrame gameFrame;
    JButton homeButton;

    SettingPanel(GameFrame frame){
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.gameFrame = frame;
        this.setLayout(null);
        this.setBackground(Color.darkGray);

        homeButton = new JButton("← Home");
        homeButton.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(Color.darkGray);
        homeButton.setBorderPainted(false);
        homeButton.setBounds(25, 400, 200, 50);
        homeButton.addActionListener(e->gameFrame.showPanel("Menu"));

        this.add(homeButton);
        // change sound volume
    }
}
