package pl.sokol.pacman.elements.map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sokol.pacman.elements.Renderable;

import javax.persistence.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Point extends Rectangle implements Renderable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private int id;
    private int positionX;
    private int positionY;

    @Transient
    private final int POINT_WIDTH = 8;

    @Transient
    private final int POINT_HEIGHT = 8;

    public Point(int x, int y) {
        this.positionX = x;
        this.positionY = y;
        this.x = x;
        this.y = y;
        setBounds(x + 10, y + 10, POINT_WIDTH, POINT_HEIGHT);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(x, y, POINT_WIDTH, POINT_HEIGHT);
    }
}
