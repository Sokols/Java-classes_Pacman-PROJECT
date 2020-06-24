package pl.sokol.pacman.game;

import pl.sokol.pacman.elements.Point;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelModel;

import java.util.List;

public class Save {

    private List<Point> points;
    private java.awt.Point playerLocation;
    private int playerCurrentMovement;
    private List<java.awt.Point> enemiesLocations;
    private List<Integer> enemiesCurrentMovements;
    private List<Integer> enemiesNumberOfTheImages;
    private StatsPanelModel stats;

    public Save(List<Point> points, java.awt.Point playerLocation, int playerCurrentMovement, List<java.awt.Point> enemiesLocations, List<Integer> enemiesCurrentMovements, List<Integer> enemiesNumberOfTheImages, StatsPanelModel stats) {
        this.points = points;
        this.playerLocation = playerLocation;
        this.playerCurrentMovement = playerCurrentMovement;
        this.enemiesLocations = enemiesLocations;
        this.enemiesCurrentMovements = enemiesCurrentMovements;
        this.enemiesNumberOfTheImages = enemiesNumberOfTheImages;
        this.stats = stats;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public java.awt.Point getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(java.awt.Point playerLocation) {
        this.playerLocation = playerLocation;
    }

    public int getPlayerCurrentMovement() {
        return playerCurrentMovement;
    }

    public void setPlayerCurrentMovement(int playerCurrentMovement) {
        this.playerCurrentMovement = playerCurrentMovement;
    }

    public List<java.awt.Point> getEnemiesLocations() {
        return enemiesLocations;
    }

    public void setEnemiesLocations(List<java.awt.Point> enemiesLocations) {
        this.enemiesLocations = enemiesLocations;
    }

    public List<Integer> getEnemiesCurrentMovements() {
        return enemiesCurrentMovements;
    }

    public void setEnemiesCurrentMovements(List<Integer> enemiesCurrentMovements) {
        this.enemiesCurrentMovements = enemiesCurrentMovements;
    }

    public List<Integer> getEnemiesNumberOfTheImages() {
        return enemiesNumberOfTheImages;
    }

    public void setEnemiesNumberOfTheImages(List<Integer> enemiesNumberOfTheImages) {
        this.enemiesNumberOfTheImages = enemiesNumberOfTheImages;
    }

    public StatsPanelModel getStats() {
        return stats;
    }

    public void setStats(StatsPanelModel stats) {
        this.stats = stats;
    }
}
