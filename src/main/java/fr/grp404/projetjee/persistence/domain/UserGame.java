package fr.grp404.projetjee.persistence.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserGame")
public class UserGame {

    /**
     * Sequence generated Id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * User concerned
     */
    @ManyToOne
    private User user;

    /**
     * Game concerned
     */
    @ManyToOne
    private Game game;

    /**
     * Start date of the game
     */
    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    /**
     * End date of the game
     */
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * Constructor that do nothing
     */
    public UserGame() {
        //DO nothing
    }

    /**
     * Create a new UserGame
     *
     * @param user the concerned user
     * @param game the concerned game
     */
    public UserGame(final User user, final Game game) {
        this.user = user;
        this.game = game;
        this.startDate = LocalDateTime.now();
    }

    /**
     * Start the current game session only if not already started
     */
    public void start() {
        if (getStartDate() == null) {
            setStartDate(LocalDateTime.now());
        }
    }

    /**
     * End the current game session only if not already stopped
     */
    public void stop() {
        if (getEndDate() == null) {
            setEndDate(LocalDateTime.now());
        }
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
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Anemic getter
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Anemic setter
     *
     * @param u the new user
     */
    public void setUser(User u) {
        this.user = u;
    }

    /**
     * Anemic getter
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Anemic setter
     *
     * @param g the new game
     */
    public void setGame(final Game g) {
        this.game = g;
    }

    /**
     * Anemic getter
     *
     * @return the start date
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Anemic setter
     *
     * @param startDate the new start date
     */
    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Anemic getter
     *
     * @return the end date
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Anemic setter
     *
     * @param endDate the new end date
     */
    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
