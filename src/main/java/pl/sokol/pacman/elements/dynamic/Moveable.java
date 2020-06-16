package pl.sokol.pacman.elements.dynamic;

import pl.sokol.pacman.game.Level;
import pl.sokol.pacman.gui.frame.GameFrameModel;

import java.awt.Rectangle;

public interface Moveable {

    void move();

    default boolean canMove(int movement, int SPEED, Rectangle obj) {
        int toX = 0, toY = 0;
        switch (movement) {
            case 0:
                toX = obj.x;
                toY = obj.y - SPEED;
                break;

            case 1:
                toX = obj.x + SPEED;
                toY = obj.y;
                break;

            case 2:
                toX = obj.x;
                toY = obj.y + SPEED;
                break;

            case 3:
                toX = obj.x - SPEED;
                toY = obj.y;
                break;

            default:
                break;
        }

        Rectangle bounds = new Rectangle(toX, toY, (int) obj.getWidth(), (int) obj.getHeight());
        Level level = GameFrameModel.level;

        for (int i = 0; i < level.getTiles().size(); i++) {
            if (bounds.intersects(level.getTiles().get(i))) {
                return false;
            }
        }
        return true;
    }

    default void makeMove(int movement, int SPEED, Rectangle obj) {
        switch (movement) {
            // up
            case 0:
                obj.y -= SPEED;
                break;

            // right
            case 1:
                obj.x += SPEED;
                break;

            // down
            case 2:
                obj.y += SPEED;
                break;

            // left
            case 3:
                obj.x -= SPEED;
                break;

            default:
                break;
        }
    }
}
