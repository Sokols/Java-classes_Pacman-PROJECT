package pl.sokol.pacman.gui;

import pl.sokol.pacman.elements.dynamic.Player;
import pl.sokol.pacman.game.Level;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 480;

    private Player player;
    private Level level;

    public GamePanel(Player player, Level level) {
        this.player = player;
        this.level = level;
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        setFocusable(true);
        addKeyListener(this);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // fill the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        level.render(g);
        player.render(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // switch by pressed key
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.setCurrentMovement(0);
                break;

            case KeyEvent.VK_RIGHT:
                player.setCurrentMovement(1);
                break;

            case KeyEvent.VK_DOWN:
                player.setCurrentMovement(2);
                break;

            case KeyEvent.VK_LEFT:
                player.setCurrentMovement(3);
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // unused
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // unused
    }

    public Player getPlayer() {
        return player;
    }
}
