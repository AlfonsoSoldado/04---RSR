package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Explorer;
import domain.Story;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class StoryServiceTest extends AbstractTest {

	// Service under test -------------

	@Autowired
	private StoryService storyService;

	// Supporting services ------------

	@Autowired
	private TripService tripService;

	@Autowired
	private ExplorerService explorerService;

	// Test ---------------------------

	@Test
	public void testCreateStory() {
		Story story;
		story = this.storyService.findOne(super.getEntityId("story1"));
		Assert.notNull(story);
	}

	@Test
	public void testFindAllStory() {
		Collection<Story> stories;
		stories = new ArrayList<Story>();
		stories = this.storyService.findAll();
		Assert.notNull(stories);
	}

	@Test
	public void testFindOneStory() {
		Story story;
		story = this.storyService.findOne(super.getEntityId("story1"));
		Assert.notNull(story);
	}

	@Test
	public void testSaveStory() {
		Story story;
		String title, pieceText, link1;
		Collection<String> link;
		Trip trip;
		Explorer writer;

		story = this.storyService.findOne(super.getEntityId("story1"));
		title = "Sample";
		pieceText = "Piece text";
		link1 = "http://www.google.es";
		link = new ArrayList<String>();
		link.add(link1);
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		writer = this.explorerService.findOne(super.getEntityId("explorer1"));

		story.setTitle(title);
		story.setPieceText(pieceText);
		story.setLink(link);
		story.setTrip(trip);
		story.setWriter(writer);

		this.storyService.save(story);
	}

	@Test
	public void testDeleteStory() {
		Story story;
		story = this.storyService.findOne(super.getEntityId("story1"));
		this.storyService.delete(story);
	}

	@Test
	public void testWriteStory() {
		authenticate("explorer01"); // <---- writer
		Story story;
		Trip trip;
		String title, pieceText, link1;
		Collection<String> link;

		title = "Sample Title";
		pieceText = "Another example.";
		link1 = "http://www.google.com";
		link = new ArrayList<String>();
		link.add(link1);
		trip = this.tripService.findOne(super.getEntityId("trip2"));

		story = this.storyService.writeStory(trip, title, pieceText, link);
		Assert.notNull(story);
		unauthenticate();
	}
}
