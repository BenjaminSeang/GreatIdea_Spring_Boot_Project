package com.ben.greatideas.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ideas")
public class Idea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Title must not be empty")
	private String ideaName;

	@NotNull(message="Content must not be empty")
	private String ideaContent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
			name = "likes",
			joinColumns = @JoinColumn(name = "idea_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> likers;
	
	public List<User> getLikers() {
		return likers;
	}
	public void setLikers(List<User> likers) {
		this.likers = likers;
	}
	public String getIdeaName() {
		return ideaName;
	}
	public void setIdeaName(String ideaName) {
		this.ideaName = ideaName;
	}
	public String getIdeaContent(){
		return ideaContent;
	}
	public void setIdeaContent(String ideaContent) {
		this.ideaContent = ideaContent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User User) {
		this.user = User;
	}
	public Long getId() {
		return id;
	}

}
