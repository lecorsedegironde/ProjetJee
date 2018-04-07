package fr.grp404.projetjee.persistence.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Game")
public class Game {

    /**
     * Sequence generated id
     */
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Constructor that do nothing
     */
    public Game() {
        //Do nothing
    }

    /**
     * Constructor used to create a new Game
     *
     * @param name of the game
     */
    public Game(@NotNull String name) {
        this.name = name;
    }

    /**
     * Anemic getter
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Anemic setter
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Anemic getter
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Anemic setter
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
}
