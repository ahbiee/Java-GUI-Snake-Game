import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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

    JSlider soundSlider;

    String[] colors;
    JComboBox<String> colorChooser;

    Font difficultyFont;
    JLabel setDifficulty;
    JLabel setSound;
    JLabel setColor;

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
        setSlider();
        setColor();
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

    private void setSlider(){
        setSound = new JLabel("Sound Volume:");
        setSound.setFont(new Font("MV Boli", Font.BOLD, 30));
        setSound.setForeground(Color.WHITE);
        setSound.setBackground(Color.darkGray);
        setSound.setBounds(50, 200, 300, 50);

        soundSlider = new JSlider(0, 100, 100);
        soundSlider.setBounds(300, 200, 400, 50);
        soundSlider.setForeground(Color.WHITE);
        soundSlider.setBackground(Color.darkGray);

        soundSlider.setPaintTicks(true);
        soundSlider.setPaintTrack(true);
        soundSlider.setMajorTickSpacing(25);
        soundSlider.setPaintLabels(true);
        soundSlider.addChangeListener(e ->{
            int soundVolume = soundSlider.getValue();
            float volume;
            if(soundVolume == 0) volume = -80.0f;
            else volume = (float)(Math.log10(soundVolume / 100.0) * 20.0);

            gameFrame.setVolume(volume);
            gameFrame.gamePanel.updateSoundVolume();
        });

        this.add(setSound);
        this.add(soundSlider);
    }

    private void setColor(){
        colors = new String[] {"綠色 Green", "藍色 Blue", "粉色 Pink"};

        setColor = new JLabel("Snake's Color:");
        setColor.setFont(new Font("MV Boli", Font.BOLD, 30));
        setColor.setForeground(Color.WHITE);
        setColor.setBackground(Color.darkGray);
        setColor.setBounds(50, 300, 300, 50);

        colorChooser = new JComboBox<>(colors);
        colorChooser.setSelectedIndex(0);
        colorChooser.setBounds(300, 300, 400, 50);
        colorChooser.setBackground(Color.lightGray);
        colorChooser.setForeground(Color.BLACK);
        colorChooser.setFont(new Font("宋体", Font.PLAIN, 30));
        colorChooser.addActionListener(e -> {
            String colorChose = Objects.requireNonNull(colorChooser.getSelectedItem()).toString();
            gameFrame.setColor(colorChose);
        });

        this.add(setColor);
        this.add(colorChooser);
    }
}
