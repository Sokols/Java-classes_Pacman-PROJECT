package pl.sokol.pacman.elements.dynamic;

import org.apache.log4j.Logger;
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

    private final Logger LOG;


    private final int ENEMY_WIDTH = 32;
    private final int ENEMY_HEIGHT = 32;
    private final int ENEMY_SPEED = 1;
    private final int ENEMY_SEARCH_RANGE = 160;
    private final String[] ENEMY_IMAGES = {
            "/graphics/enemies/enemy1.png",
            "/graphics/enemies/enemy2.png",
            "/graphics/enemies/enemy3.png",
            "/graphics/enemies/enemy4.png",
            "/graphics/enemies/enemy5.png"
    };

    private Random random;

    private Player player;

    private Level level;

    private List<Junction> junctions;

    private BufferedImage imageOfEnemy;
    private int numberOfTheImage;

    private int currentMovement;
    private int previousMovement;

    public Enemy(int x, int y, Player player, Level level, List<Junction> junctions, int numberOfTheImage) throws IOException {
        this.LOG = Logger.getLogger(Enemy.class.getName());
        this.random = new Random();
        this.player = player;
        this.level = level;
        this.imageOfEnemy = ImageIO.read(getClass().getResourceAsStream(ENEMY_IMAGES[numberOfTheImage]));
        this.numberOfTheImage = numberOfTheImage;
        this.junctions = junctions;
        this.currentMovement = 0;
        this.previousMovement = 0;
        setBounds(x, y, ENEMY_WIDTH, ENEMY_HEIGHT);
    }

    @Override
    public void move() {

        // vector position of the enemy relative to the player
        // HERE WE USE JAVA COORDINATE SYSTEM
        int vectorX = player.x - x;
        int vectorY = player.y - y;
        boolean findPlayerFlag = false;

        // check if player is in search range
        if (Math.abs(vectorX) < ENEMY_SEARCH_RANGE && Math.abs(vectorY) < ENEMY_SEARCH_RANGE) {
            findPlayerFlag = true;
        }

        // check if enemy is on the junction
        boolean findJunctionFlag = false;
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
            int temp = currentMovement;
            while (!canMove(currentMovement, ENEMY_SPEED, this, level)) {
                currentMovement = random.nextInt(4);
            }
            if (currentMovement != temp) {
                previousMovement = temp;
            }
        }
        makeMove(currentMovement, ENEMY_SPEED, this);
    }

    public void render(Graphics g) {
        move();
        g.drawImage(imageOfEnemy, x, y, width, height, null);
    }

    public void setImageOfEnemyByNumber(int numberOfTheImage) {
        try {
            this.imageOfEnemy = ImageIO.read(getClass().getResourceAsStream(ENEMY_IMAGES[numberOfTheImage]));
        } catch (IOException e) {
            LOG.warn(e);
        }
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
