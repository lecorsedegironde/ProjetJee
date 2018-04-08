package fr.grp404.projetjee.persistence.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
     */
    public User(@NotNull @Size(min = 6, max = 30) final String login, @NotNull final String password,
                @NotNull final Role role, @NotNull final LocalDate birthDate, @NotNull final String email) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.birthDate = birthDate;
        this.email = email;
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
}
