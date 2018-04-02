package fr.grp404.projetjee.persistence.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Basic(optional = false)
    private String login;

    //TODO Chiffrer ?
    @Basic(optional = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    private Role role;

    @Basic(optional = false)
    @Column(name = "birth_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    @Basic(optional = false)
    private String email;

    public User() {
        super();
    }

    public User(String login, String password, Role role, Date birthDate, String email) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.birthDate = birthDate;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
