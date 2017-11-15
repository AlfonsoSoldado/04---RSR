package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.Emergency;
import domain.Explorer;
import domain.Finder;
import domain.Folder;
import domain.Message;
import domain.SocialId;
import domain.Story;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class ExplorerServiceTest extends AbstractTest{

	// Service under test -------------------------
	
	@Autowired
	private ExplorerService explorerService;
	
	// Supporting services -----------------------
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private SocialIdService socialIdService;
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private EmergencyService emergencyService;
	
	@Autowired
	private FinderService finderService;

	@Autowired
	private FolderService folderService;
			
	// Test --------------------------------------
	
	@Test
	public void testCreateExplorer() {
		Explorer explorer;
		explorer = this.explorerService.create();
		Assert.notNull(explorer);
	}
	
	@Test
	public void testFindAllExplorer() {
		Collection<Explorer> explorers;
		explorers = this.explorerService.findAll();
		Assert.notNull(explorers);
	}
	
	@Test
	public void testFindOneExplorer() {
		Explorer explorer;
		explorer = this.explorerService.findOne(super.getEntityId("explorer1"));
		Assert.notNull(explorer);
	}
	
	@Test
	public void testSaveExplorer() {
		authenticate("explorer01");
		Explorer explorer;
		explorer = this.explorerService.create();
		
		explorer.setName("ExplorerNuevo");
		explorer.setPhoneNumber("655112233");
		
		Message received;
		received = this.messageService.create();
		explorer.setReceived(received);
		
		Message sent;
		sent = this.messageService.create();
		Collection<Message> sents = explorer.getSent();
		sents.add(sent);
		explorer.setSent(sents);
		
		SocialId socialId;
		socialId = this.socialIdService.create();
		Collection<SocialId> socialIds = explorer.getSocialId();
		socialIds.add(socialId);
		explorer.setSocialId(socialIds);
		
		Story story;
		story = this.storyService.create();
		Collection<Story> stories = explorer.getStories();
		stories.add(story);
		explorer.setStories(stories);
		
		explorer.setSurname("García");
		explorer.setAddress("C/ Barcelona");
		
		Application application;
		application = this.applicationService.create();
		explorer.setApplication(application);
	
		explorer.setEmail("explorernuevo@gmail.com");

		Emergency emergency;
		emergency = this.emergencyService.create();
		Collection<Emergency> emergencies = explorer.getEmergency();
		emergencies.add(emergency);
		explorer.setEmergency(emergencies);
		
		Finder finder;
		finder = this.finderService.create();
		Collection<Finder> finders = explorer.getFinder();
		finders.add(finder);
		explorer.setFinder(finders);
		
		Folder customFolder;
		customFolder = this.folderService.create();
		Collection<Folder> folders = explorer.getFolders();
		folders.add(customFolder);
		explorer.setFolders(folders);
		this.explorerService.save(explorer);
		unauthenticate();
	}
	
	@Test
	public void testDeleteExplorer() {
		Explorer explorer;
		explorer = this.explorerService.findOne(super.getEntityId("explorer1"));
		this.explorerService.delete(explorer);
	}
	
	@Test
	public void testFindByPrincipalExplorer(){
		authenticate("explorer01");
		Explorer explorer;
		explorer = this.explorerService.findByPrincipal();
		Assert.notNull(explorer);
		unauthenticate();
	}
}
