package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StoryRepository;
import domain.Story;

@Service
@Transactional
public class StoryService {

	// Managed repository

	@Autowired
	private StoryRepository storyRepository;

	// Supporting services

	// Constructors

	public StoryService() {
		super();
	}

	// Simple CRUD methods

	public Collection<Story> findAll() {
		Collection<Story> res;
		res = this.storyRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Story findOne(int story) {
		Assert.isTrue(story != 0);
		Story res;
		res = this.storyRepository.findOne(story);
		Assert.notNull(res);
		return res;
	}

	public Story save(Story story) {
		Assert.notNull(story);
		Story res;
		res = this.storyRepository.save(story);
		return res;
	}

	public void delete(Story story) {
		Assert.notNull(story);
		Assert.isTrue(story.getId() != 0);
		Assert.isTrue(this.storyRepository.exists(story.getId()));
		this.storyRepository.delete(story);
	}

	// Other business methods

}
