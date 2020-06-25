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

    public static final class Builder {
        private List<Point> points;
        private java.awt.Point playerLocation;
        private int playerCurrentMovement;
        private List<java.awt.Point> enemiesLocations;
        private List<Integer> enemiesCurrentMovements;
        private List<Integer> enemiesImageNumbers;
        private StatsPanelModel stats;

        public Builder points(List<Point> points) {
            this.points = points;
            return this;
        }

        public Builder playerLocation(java.awt.Point playerLocation) {
            this.playerLocation = playerLocation;
            return this;
        }

        public Builder playerCurrentMovement(int playerCurrentMovement) {
            this.playerCurrentMovement = playerCurrentMovement;
            return this;
        }

        public Builder enemiesLocations(List<java.awt.Point> enemiesLocations) {
            this.enemiesLocations = enemiesLocations;
            return this;
        }

        public Builder enemiesCurrentMovements(List<Integer> enemiesCurrentMovements) {
            this.enemiesCurrentMovements = enemiesCurrentMovements;
            return this;
        }

        public Builder enemiesImageNumbers(List<Integer> enemiesImageNumbers) {
            this.enemiesImageNumbers = enemiesImageNumbers;
            return this;
        }

        public Builder stats(StatsPanelModel stats) {
            this.stats = stats;
            return this;
        }

        public Save build() {
            Save save = new Save();
            save.points = this.points;
            save.playerLocation = this.playerLocation;
            save.playerCurrentMovement = this.playerCurrentMovement;
            save.enemiesLocations = this.enemiesLocations;
            save.enemiesCurrentMovements = this.enemiesCurrentMovements;
            save.enemiesImageNumbers = this.enemiesImageNumbers;
            save.stats = this.stats;
            return save;
        }
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
