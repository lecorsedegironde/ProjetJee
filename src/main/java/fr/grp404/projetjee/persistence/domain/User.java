package fr.grp404.projetjee.persistence.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    /**
     * Sequence generated Id
     */
    @Id
    @GeneratedValue
    private Integer id;


    /**
     * Unique login used to identify user
     */
    @NotNull
    @Size(min = 6, max = 30)
    @Column(nullable = false, unique = true)
    private String login;

    /**
     * Password of the user
     */
    //TODO Chiffrer ?
    @NotNull
    @Column(nullable = false)
    private String password;

    /**
     * Role of the user
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Role role;

    /**
     * Birth Date of the user
     */
    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    /**
     * User email
     */
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * User ban status
     */
    @NotNull
    @Column(nullable = false)
    private boolean ban;

    /**
     * User favorite game
     */
    @ManyToMany
    private List<Game> games;

    /**
     * Constructor that do nothing
     */
    public User() {
        //Do nothing
    }

    /**
     * Constructor used to create a new user
     *
     * @param login     of the user
     * @param password  of the user
     * @param role      of the user
     * @param birthDate of the user
     * @param email     of the user
     * @param games     favorite games of the user
     */
    public User(@NotNull @Size(min = 6, max = 30) final String login, @NotNull final String password,
                @NotNull final Role role, @NotNull final LocalDate birthDate, @NotNull final String email,
                final List<Game> games) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.birthDate = birthDate;
        this.email = email;
        this.ban = false;
        this.games = games;
    }

    /**
     * Add a game to user's favorite games
     *
     * @param g the game to add
     */
    public void addToFavoriteGame(Game g) {
        if (games == null) {
            games = new ArrayList<>();
        }
        games.add(g);
    }

    /**
     * Ban user
     */
    public void ban() {
        if (role == Role.USER) {
            ban = true;
        }
    }

    /**
     * Unban user
     */
    public void unBan() {
        ban = false;
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
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Anemic setter
     *
     * @param login the new login
     */
    public void setLogin(final String login) {
        this.login = login;
    }

    /**
     * Anemic getter
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Anemic setter
     *
     * @param password the new password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Anemic getter
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Anemic setter
     *
     * @param role the new role
     */
    public void setRole(final Role role) {
        this.role = role;
    }

    /**
     * Anemic getter
     *
     * @return the birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Anemic setter
     *
     * @param birthDate the new birth date
     */
    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    /**
     * Anemic getter
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Anemic setter
     *
     * @param email the new email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Anemic getter
     *
     * @return ban status
     */
    public boolean isBan() {
        return ban;
    }

    /**
     * Anemic setter
     *
     * @param ban new ban status
     */
    public void setBan(boolean ban) {
        this.ban = ban;
    }

    /**
     * Anemic getter
     *
     * @return the favorite games
     */
    public List<Game> getGames() {

        return games;
    }

    /**
     * Anemic setter
     *
     * @param favoriteGames the new favorite games
     */
    public void setGames(final List<Game> favoriteGames) {
        this.games = favoriteGames;
    }
}
