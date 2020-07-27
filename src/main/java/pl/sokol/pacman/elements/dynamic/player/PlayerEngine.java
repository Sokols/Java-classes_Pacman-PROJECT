package pl.sokol.pacman.elements.dynamic.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerEngine {

    private final int DIRECTIONS = 4;
    private final int IMAGES = 4;
    private final int CHANGE_IMAGE_TIMER = 5;

    private int currentMovement;
    private int numberOfTheImage;
    private int changeImageCounter;

    public PlayerEngine() {
        this.currentMovement = 0;
        this.numberOfTheImage = 0;
        this.changeImageCounter = 0;
    }

    public void changePlayerImage() {
        if (++changeImageCounter > CHANGE_IMAGE_TIMER) {
            numberOfTheImage = ++numberOfTheImage % IMAGES;
            changeImageCounter = 0;
        }
    }
}
