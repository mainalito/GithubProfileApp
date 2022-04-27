package com.github.models;

public class Repo {

	public Repo() {

	}

	private String name;
	private int id;
	private String url;
	private int stargazers_count;



	public int getStargazers_count() {
		return this.stargazers_count;
	}

	public void setStargazers_count(int stargazers_count) {
		this.stargazers_count = stargazers_count;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{" +
			" name='" + getName() + "'" +
			", id='" + getId() + "'" +
			", url='" + getUrl() + "'" +
			", stargazers_count='" + getStargazers_count() + "'" +
			"}";
	}


}
