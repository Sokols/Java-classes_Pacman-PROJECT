package pl.sokol.pacman.gui.panels.endgame;

import javax.swing.*;
import java.awt.Dimension;

import static pl.sokol.pacman.Utils.FRAME_HEIGHT;
import static pl.sokol.pacman.Utils.FRAME_WIDTH;

public class EndgamePanelView extends JPanel {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel scoreLabel;
    private JButton applyButton;
    private JTextField nickTextField;

    public EndgamePanelView(String title, int score) {
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(mainPanel);
        titleLabel.setText(title);
        scoreLabel.setText(scoreLabel.getText() + score);
    }

    public JButton getApplyButton() {
        return applyButton;
    }

    public JTextField getNickTextField() {
        return nickTextField;
    }
}
