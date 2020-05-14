package pl.sokol.pacman.gui.elements.dynamic;

public interface Moveable {

    void move();

    boolean canMove(int movement);
}
