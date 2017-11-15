package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StoryRepository;
import domain.Explorer;
import domain.Story;
import domain.Trip;

@Service
@Transactional
public class StoryService {

	// Managed repository

	@Autowired
	private StoryRepository storyRepository;

	// Supporting services
	
	@Autowired
	private ExplorerService explorerService;

	// Constructors

	public StoryService() {
		super();
	}

	// Simple CRUD methods
	
	public Story create() {
		Story story;
		story = new Story();
		Explorer explorer;
		Trip trip;
		explorer = this.explorerService.findByPrincipal();
		Assert.notNull(explorer);
		trip = new Trip();
		story.setWriter(explorer);
		story.setTrip(trip);
		return story;
	}

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
	
	// 44.2
	
	public Story writeStory(Trip trip, String title, String pieceText, Collection<String> link){
		Assert.notNull(trip);
		Explorer ex;
		ex = this.explorerService.findByPrincipal();
		Collection<Story> stories = new ArrayList<Story>();
		stories.addAll(trip.getStory());
		
		Story story;
		story = create();
		
		story.setLink(link);
		story.setPieceText(pieceText);
		story.setTitle(title);
		story.setTrip(trip);
		story.setWriter(ex);
		
		stories.add(story);
		
		trip.setStory(stories);
		Assert.notNull(stories);
		return story;
	}
}
