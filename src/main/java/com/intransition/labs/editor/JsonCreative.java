package com.intransition.labs.editor;

import java.util.Set;

class JsonCreative {

	private String name;
	
	private Set<JsonTag> tags;

	private Set<JsonChapter> chapters;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<JsonTag> getTags() {
		return tags;
	}

	public void setTags(Set<JsonTag> tags) {
		this.tags = tags;
	}

	public Set<JsonChapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<JsonChapter> chapters) {
		this.chapters = chapters;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
