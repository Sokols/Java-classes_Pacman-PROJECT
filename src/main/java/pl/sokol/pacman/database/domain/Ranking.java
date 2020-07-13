package pl.sokol.pacman.database.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

// Lombok annotations
@Data
@Builder
// Hibernate annotations
@Entity
@Table(name = "ranking")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "nick")
    private String nick;

    @Column(name = "points")
    private int points;

    @Column(name = "date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
