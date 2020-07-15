package pl.sokol.pacman.gui.frame;

import lombok.Getter;
import pl.sokol.pacman.gui.panels.menu.MenuPanelView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import static pl.sokol.pacman.Utils.FRAME_HEIGHT;
import static pl.sokol.pacman.Utils.FRAME_WIDTH;
import static pl.sokol.pacman.Utils.MENU;
import static pl.sokol.pacman.Utils.TITLE;

@Getter
public class GameFrameView extends JFrame {

    private CardLayout card;
    private JPanel mainPanel;

    GameFrameView(MenuPanelView menu) {
        super(TITLE);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);

        this.mainPanel = new JPanel();
        this.card = new CardLayout();
        this.mainPanel.setLayout(card);
        this.mainPanel.add(MENU, menu);
        this.setLayout(new BorderLayout());
        this.add(mainPanel);
    }
}
