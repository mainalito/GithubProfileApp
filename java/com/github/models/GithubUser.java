package com.github.models;

public class GithubUser {

	public GithubUser() {}
		
		private String name;
		private String avatar_url;
		private int followers;
		private int following;
		private String bio;
		private String repos_url;
		private String login;
		private String html_url;

	public String getHtml_url() {
		return this.html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

		
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getRepos_url() {
			return repos_url;
		}
		public void setRepos_url(String repos_url) {
			this.repos_url= repos_url;
			}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAvatar_url() {
			return avatar_url;
		}
		public void setAvatar_url(String avatar_url) {
			this.avatar_url = avatar_url;
		}
		public int getFollowers() {
			return followers;
		}
		public void setFollowers(int followers) {
			this.followers = followers;
		}
		public int getFollowing() {
			return following;
		}
		public void setFollowing(int following) {
			this.following = following;
		}
		public String getBio() {
			return bio;
		}
		public void setBio(String bio) {
			this.bio = bio;
		}
		
	
		
	

}
