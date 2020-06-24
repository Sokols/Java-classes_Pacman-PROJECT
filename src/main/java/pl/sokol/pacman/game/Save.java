package pl.sokol.pacman.game;

import pl.sokol.pacman.elements.map.Point;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelModel;

import java.util.List;

public class Save {

    private List<Point> points;
    private java.awt.Point playerLocation;
    private int playerCurrentMovement;
    private List<java.awt.Point> enemiesLocations;
    private List<Integer> enemiesCurrentMovements;
    private List<Integer> enemiesImageNumbers;
    private StatsPanelModel stats;

    public Save(List<Point> points, java.awt.Point playerLocation, int playerCurrentMovement, List<java.awt.Point> enemiesLocations, List<Integer> enemiesCurrentMovements, List<Integer> enemiesImageNumbers, StatsPanelModel stats) {
        this.points = points;
        this.playerLocation = playerLocation;
        this.playerCurrentMovement = playerCurrentMovement;
        this.enemiesLocations = enemiesLocations;
        this.enemiesCurrentMovements = enemiesCurrentMovements;
        this.enemiesImageNumbers = enemiesImageNumbers;
        this.stats = stats;
    }

    public List<Point> getPoints() {
        return points;
    }

    public java.awt.Point getPlayerLocation() {
        return playerLocation;
    }

    public int getPlayerCurrentMovement() {
        return playerCurrentMovement;
    }

    public List<java.awt.Point> getEnemiesLocations() {
        return enemiesLocations;
    }

    public List<Integer> getEnemiesCurrentMovements() {
        return enemiesCurrentMovements;
    }

    public List<Integer> getEnemiesImageNumbers() {
        return enemiesImageNumbers;
    }

    public StatsPanelModel getStats() {
        return stats;
    }

    public void setStats(StatsPanelModel stats) {
        this.stats = stats;
    }
}
