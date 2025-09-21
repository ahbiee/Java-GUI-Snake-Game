import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.*;

public class HelpPanel extends JPanel{
    static final int PANEL_WIDTH = 800;
    static final int PANEL_HEIGHT = 500;

    GameFrame gameFrame;
    JLabel label;
    JButton homeButton;

    HelpPanel(GameFrame frame){
        this.gameFrame = frame;
        this.setBackground(Color.darkGray);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        setButton();
        setLabel();
    }

    private void setButton(){
        homeButton = new JButton("← Home");
        homeButton.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBackground(Color.darkGray);
        homeButton.setBorderPainted(false);
        homeButton.setBounds(25, 400, 200, 50);
        homeButton.addActionListener(e->gameFrame.showPanel("Menu"));
        this.add(homeButton);
    }

    private void setLabel(){
        label = new JLabel();
        label.setFont(new Font("MV Boli", Font.BOLD, 50));
        label.setText("<html>HOW TO PLAY：<br>用WASD控制上下左右<br>記得要切成英文輸入法!</html>");
        label.setForeground(Color.WHITE);
        label.setBounds(150, 50, 600, 300);
        label.setFocusable(false);
        label.setVisible(true);
        this.add(label);
    }
}
