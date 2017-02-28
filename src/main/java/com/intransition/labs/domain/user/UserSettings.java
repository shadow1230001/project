package com.intransition.labs.domain.user;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "user_settings" )
public class UserSettings {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JoinColumn( name = "theme" )
	private Theme theme;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JoinColumn( name = "language" )
	private Language language;
	
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public UserSettings() {
		setTheme(new Theme());
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
}
