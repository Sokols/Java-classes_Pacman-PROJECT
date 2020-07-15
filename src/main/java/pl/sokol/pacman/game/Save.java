package pl.sokol.pacman.game;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.sokol.pacman.elements.map.Point;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelModel;

import java.util.List;

@Getter
@Setter
@Builder
public class Save {

    private List<Point> points;
    private java.awt.Point playerLocation;
    private int playerCurrentMovement;
    private List<java.awt.Point> enemiesLocations;
    private List<Integer> enemiesCurrentMovements;
    private List<Integer> enemiesImageNumbers;
    private StatsPanelModel stats;

}
