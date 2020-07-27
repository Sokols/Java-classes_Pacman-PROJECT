package pl.sokol.pacman.elements.dynamic.enemy;

import pl.sokol.pacman.elements.dynamic.Moveable;
import pl.sokol.pacman.elements.dynamic.player.Player;
import pl.sokol.pacman.elements.map.Junction;
import pl.sokol.pacman.elements.Renderable;
import pl.sokol.pacman.game.Level;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle implements Renderable, Moveable {

    private final String[] ENEMY_IMAGES = {
            "/graphics/enemies/enemy1.png",
            "/graphics/enemies/enemy2.png",
            "/graphics/enemies/enemy3.png",
            "/graphics/enemies/enemy4.png",
            "/graphics/enemies/enemy5.png"
    };

    private final int ENEMY_WIDTH = 32;
    private final int ENEMY_HEIGHT = 32;
    private final int ENEMY_SPEED = 1;
    private final int ENEMY_SEARCH_RANGE = 160;

    private Player player;
    private Level level;
    private List<Junction> junctions;
    private int numberOfTheImage;
    private int currentMovement;
    private int previousMovement;
    private Random random;
    private BufferedImage imageOfEnemy;

    public static final class Builder {
        private Player player;
        private Level level;
        private List<Junction> junctions;
        private int numberOfTheImage;
        private int currentMovement;
        private int previousMovement;

        public Builder player(Player player) {
            this.player = player;
            return this;
        }

        public Builder level(Level level) {
            this.level = level;
            return this;
        }

        public Builder junctions(List<Junction> junctions) {
            this.junctions = junctions;
            return this;
        }

        public Builder numberOfTheImage(int numberOfTheImage) {
            this.numberOfTheImage = numberOfTheImage;
            return this;
        }

        public Builder currentMovement(int currentMovement) {
            this.currentMovement = currentMovement;
            return this;
        }

        public Builder previousMovement(int previousMovement) {
            this.previousMovement = previousMovement;
            return this;
        }

        public Enemy build(int x, int y) throws IOException {
            Enemy enemy = new Enemy();
            enemy.player = this.player;
            enemy.level = this.level;
            enemy.junctions = this.junctions;
            enemy.numberOfTheImage = this.numberOfTheImage;
            enemy.currentMovement = this.currentMovement;
            enemy.previousMovement = this.previousMovement;
            enemy.random = new Random();
            enemy.imageOfEnemy = ImageIO.read(getClass().getResourceAsStream(enemy.ENEMY_IMAGES[numberOfTheImage]));
            enemy.setBounds(x, y, enemy.ENEMY_WIDTH, enemy.ENEMY_HEIGHT);
            return enemy;
        }
    }

    @Override
    public void render(Graphics graphics) {
        move();
        graphics.drawImage(imageOfEnemy, x, y, width, height, null);
    }

    @Override
    public void move() {
        // vector position of the enemy relative to the player
        int vectorX = player.x - x;
        int vectorY = player.y - y;
        boolean findPlayerFlag = false;
        boolean findJunctionFlag = false;

        // check if player is in search range
        if (Math.abs(vectorX) < ENEMY_SEARCH_RANGE && Math.abs(vectorY) < ENEMY_SEARCH_RANGE) {
            findPlayerFlag = true;
        }

        // check if enemy is on the junction
        for (Junction junction : junctions) {
            if (junction.contains(this)) {
                findJunctionFlag = true;
                break;
            }
        }

        // make move if enemy is on junction and if found the player
        if (findJunctionFlag && findPlayerFlag) {
            // List of movements priorities
            List<Integer> priorities = new ArrayList<>();
            boolean vectorsDifference = (Math.abs(vectorX) >= Math.abs(vectorY));
            // I QUARTER
            if (vectorX >= 0 && vectorY <= 0) {
                if (vectorsDifference) {
                    priorities.add(1);
                    priorities.add(0);
                    priorities.add(2);
                    priorities.add(3);
                } else {
                    priorities.add(0);
                    priorities.add(1);
                    priorities.add(3);
                    priorities.add(2);
                }
            }
            // II QUARTER
            else if (vectorX <= 0 && vectorY <= 0) {
                if (vectorsDifference) {
                    priorities.add(3);
                    priorities.add(0);
                    priorities.add(2);
                    priorities.add(1);
                } else {
                    priorities.add(0);
                    priorities.add(3);
                    priorities.add(1);
                    priorities.add(2);
                }
            }
            // III QUARTER
            else if (vectorX <= 0) {
                if (vectorsDifference) {
                    priorities.add(3);
                    priorities.add(2);
                    priorities.add(0);
                    priorities.add(1);
                } else {
                    priorities.add(2);
                    priorities.add(3);
                    priorities.add(1);
                    priorities.add(0);
                }
            }
            // IV QUARTER
            else {
                if (vectorsDifference) {
                    priorities.add(1);
                    priorities.add(2);
                    priorities.add(0);
                    priorities.add(3);
                } else {
                    priorities.add(2);
                    priorities.add(1);
                    priorities.add(3);
                    priorities.add(0);
                }
            }
            // make move by highest priority
            for (int movement : priorities) {
                if (canMove(movement, ENEMY_SPEED, this, level) && movement != previousMovement) {
                    previousMovement = currentMovement;
                    currentMovement = movement;
                    break;
                }
            }
        }

        // make move if enemy is on junction
        else if (findJunctionFlag) {
            previousMovement = currentMovement;
            do {
                currentMovement = random.nextInt(4);
            } while (!canMove(currentMovement, ENEMY_SPEED, this, level));
        }

        // make current move or find new one
        else {
            int tempMovement = currentMovement;
            while (!canMove(currentMovement, ENEMY_SPEED, this, level)) {
                currentMovement = random.nextInt(4);
            }
            if (currentMovement != tempMovement) {
                previousMovement = tempMovement;
            }
        }
        makeMove(currentMovement, ENEMY_SPEED, this);
    }

    public void setJunctions(List<Junction> junctions) {
        this.junctions = junctions;
    }

    public void setCurrentMovement(int currentMovement) {
        this.currentMovement = currentMovement;
    }

    public int getCurrentMovement() {
        return currentMovement;
    }

    public int getNumberOfTheImage() {
        return numberOfTheImage;
    }
}
