package pl.sokol.pacman.database.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import pl.sokol.pacman.elements.map.Point;
import pl.sokol.pacman.gui.panels.game.stats.StatsPanelModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// Lombok annotations
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Hibernate annotations
@Entity
public class Save {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "save_id")
    private List<Point> points;

    @Column(name = "player_location")
    private java.awt.Point playerLocation;

    @Column(name = "player_current_movement")
    private int playerCurrentMovement;

    @ElementCollection
    @CollectionTable(name = "enemies_locations", joinColumns = @JoinColumn(name = "save_id"))
    private List<java.awt.Point> enemiesLocations;

    @ElementCollection
    @CollectionTable(name = "enemies_current_movements", joinColumns = @JoinColumn(name = "save_id"))
    private List<Integer> enemiesCurrentMovements;

    @ElementCollection
    @CollectionTable(name = "enemies_image_numbers", joinColumns = @JoinColumn(name = "save_id"))
    private List<Integer> enemiesImageNumbers;

    @Embedded
    @Column(name = "stats")
    private StatsPanelModel stats;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
