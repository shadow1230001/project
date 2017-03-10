package com.intransition.labs.domain.user;

import com.intransition.labs.domain.content.Award;
import com.intransition.labs.domain.content.Creative;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(name = "first_name", unique = false, nullable = false)
    private String firstName = "Anonym";

    @Column(name = "last_name", unique = false, nullable = false)
    private String lastName = "Anonymous";

    @Column(unique = false)
    private String password;

    @Column
    private boolean banned = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id", nullable = true))
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "settings_id", unique = true)
    private UserSettings settings = new UserSettings();

    @Column
    private Date registered;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_award", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "award_id"))
    private Set<Award> awards = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Creative> creatives = new HashSet<>();

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void ban(boolean banned) {
        this.banned = banned;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role... roles) {
        for (Role role : roles) {
            this.roles.add(role);
        }
    }

    public void setUserSettings(UserSettings settings) {
        this.settings = settings;
    }

    public UserSettings getUserSettings() {
        return settings;
    }

    public void setRegistrationDate(Date registered) {
        this.registered = registered;
    }

    public Date getRegistrationDate() {
        return registered;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = awards;
    }

    public void addAward(Award... awards) {
        for (Award award : awards) {
            this.awards.add(award);
        }
    }

    public Set<Award> getAwards() {
        return awards;
    }

    public void setCreatives(Set<Creative> creatives) {
        this.creatives = creatives;
    }

    public Set<Creative> getCreatives() {
        return creatives;
    }

}
