package pl.sokol.pacman.gui.panels.endgame;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

import static pl.sokol.pacman.Utils.FRAME_HEIGHT;
import static pl.sokol.pacman.Utils.FRAME_WIDTH;

public class EndgamePanelView extends JPanel {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel scoreLabel;
    private JButton backToMenuButton;

    public EndgamePanelView(String title, int score) {
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(mainPanel);
        titleLabel.setText(title);
        scoreLabel.setText(scoreLabel.getText() + score);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public JButton getBackToMenuButton() {
        return backToMenuButton;
    }

    public void setBackToMenuButton(JButton backToMenuButton) {
        this.backToMenuButton = backToMenuButton;
    }
}
