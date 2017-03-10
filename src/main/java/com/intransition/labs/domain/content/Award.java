package com.intransition.labs.domain.content;

import com.intransition.labs.domain.user.User;
import com.intransition.labs.service.AwardType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "award")
public class Award {

    @Id
    @GeneratedValue
    @Column(name = "award_id")
    private int id;

    @ManyToMany(mappedBy = "awards", cascade = CascadeType.ALL)
    private Set<User> users;

    private String name;

    private String codename;

    private String description;

    private Timestamp awarded;

    public Award() {
    }

    public Award(AwardType type) {
        name = type.getName();
        codename = type.getCodename();
        description = type.getDescription();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getAwardedTimestamp() {
        return awarded;
    }

    public void setAwardedTimestamp(Timestamp awarded) {
        this.awarded = awarded;
    }


}