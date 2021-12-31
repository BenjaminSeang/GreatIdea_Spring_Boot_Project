package com.ben.greatideas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ben.greatideas.models.Idea;
import com.ben.greatideas.models.User;
import com.ben.greatideas.repositories.IdeaRepository;

@Service
public class IdeaService {
	
	@Autowired 
	private IdeaRepository ideaRepository;
	
	public List<Idea> getAllIdeas(){
		return ideaRepository.findAll();
	}
	
	public List<Idea> getAllIdeasSortByLikesAsc(){
		return ideaRepository.findDistinctByOrderByLikersAsc();
	}
	
	public List<Idea> getAllIdeasSortByLikesDesc(){
		return ideaRepository.findDistinctByOrderByLikersDesc();
	}
	
	public Idea createIdea(Idea idea) {
		return ideaRepository.save(idea);
	}
	
	public void deleteIdea(Long id) {
		ideaRepository.deleteById(id);
	}
	
	public Idea getOneIdeaById(Long id) {
		return ideaRepository.findById(id).orElse(null);
	}
	
	public Idea updateIdea(Long id, String ideaName, String ideaContent) {
		Idea idea = getOneIdeaById(id);
		idea.setIdeaName(ideaName);
		idea.setIdeaContent(ideaContent);
		return ideaRepository.save(idea);
	}
	
	public void likeIdea(Idea idea, User user) {
		List<User> likers = idea.getLikers();
		likers.add(user);
		ideaRepository.save(idea);
	}
	
	public void unlikeIdea(Idea idea, User user) {
		List<User> likers = idea.getLikers();
		likers.remove(user);
		ideaRepository.save(idea);
	}
}
