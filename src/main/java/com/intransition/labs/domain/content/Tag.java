package com.intransition.labs.domain.content;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "tag" )
public class Tag {

	@Id
	@GeneratedValue
	private int id;
	
	@Column( unique = true, nullable = false )
	private String name;
	
	@Column( unique = true, nullable = true )
	private String address;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags", cascade = CascadeType.ALL)
	private Set<Creative> creatives;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags", cascade = CascadeType.ALL)
	private Set<Chapter> chapters;
	
	public Tag() {}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Creative> getCreatives() {
		return creatives;
	}

	public void setCreatives(Set<Creative> creatives) {
		this.creatives = creatives;
	}

	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}
	
}
