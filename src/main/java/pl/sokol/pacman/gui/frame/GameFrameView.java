package pl.sokol.pacman.gui.frame;

import pl.sokol.pacman.gui.panels.menu.MenuPanelView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import static pl.sokol.pacman.Utils.*;

public class GameFrameView extends JFrame {

    private CardLayout card;
    private JPanel mainPanel;

    public GameFrameView(MenuPanelView menu) {
        super(TITLE);
        Dimension dimension = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
        setSize(dimension);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(dimension);
        card = new CardLayout();
        mainPanel.setLayout(card);
        mainPanel.add(MENU, menu);
        this.setLayout(new BorderLayout());
        this.add(mainPanel);
    }

    public CardLayout getCard() {
        return card;
    }

    public void setCard(CardLayout card) {
        this.card = card;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
