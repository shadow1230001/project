package com.intransition.labs.domain.user;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "theme")
public class Theme {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme")
    private Set<UserSettings> users;

    public Theme() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
