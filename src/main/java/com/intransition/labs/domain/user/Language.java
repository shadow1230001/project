package com.intransition.labs.domain.user;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String name = "English";

    @Column(unique = true, nullable = false)
    private String code = "en_US";

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "language")
    private Set<UserSettings> users;

    public Language() {
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
