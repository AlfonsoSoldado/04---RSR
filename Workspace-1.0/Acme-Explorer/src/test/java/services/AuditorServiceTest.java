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
import domain.Audit;
import domain.Auditor;
import domain.Curriculum;
import domain.Folder;
import domain.Message;
import domain.Note;
import domain.SocialId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class AuditorServiceTest extends AbstractTest{
	
	// Service under test -------------------------
	
	@Autowired
	private AuditorService auditorService;
	
	// Supporting services -----------------------
	
	@Autowired
	private NoteService noteService;

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private SocialIdService socialIdService;
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private FolderService folderService;
	
	// Test --------------------------------------
	
	@Test
	public void testCreateAuditor() {
		Auditor auditor;
		auditor = this.auditorService.create();
		Assert.notNull(auditor);
	}
	
	@Test
	public void testFindAllAuditor() {
		Collection<Auditor> auditors;
		auditors = this.auditorService.findAll();
		Assert.notNull(auditors);
	}
	
	@Test
	public void testFindOneAuditor() {
		Auditor auditor;
		auditor = this.auditorService.findOne(super.getEntityId("auditor1"));
		Assert.notNull(auditor);
	}
	
	@Test
	public void testSaveAuditor() {
		Auditor auditor;
		auditor = this.auditorService.create();
		
		auditor.setName("Roberto");
		
		Note note;
		note = this.noteService.create();
		Collection<Note> notes = auditor.getNote();
		notes.add(note);
		auditor.setNote(notes);
		
		auditor.setPhoneNumber("674123456");
		
		Message received;
		received = this.messageService.create();
		auditor.setReceived(received);
		
		Message sent;
		sent = this.messageService.create();
		Collection<Message> sents = auditor.getSent();
		sents.add(sent);
		auditor.setSent(sents);
		
		SocialId socialId;
		socialId = this.socialIdService.create();
		Collection<SocialId> socialIds = auditor.getSocialId();
		socialIds.add(socialId);
		auditor.setSocialId(socialIds);
		
		auditor.setSurname("Calvo");
		auditor.setAddress("C/ Adriano");
		
		Audit audit;
		audit = this.auditService.create();
		Collection<Audit> audits = auditor.getAudit();
		audits.add(audit);
		auditor.setAudit(audits);
		
		auditor.setEmail("caladri@gmail.com");
		
		Folder customFolder;
		customFolder = this.folderService.create();
		Collection<Folder> folders = auditor.getFolders();
		folders.add(customFolder);
		auditor.setFolders(folders);

		this.auditorService.save(auditor);
	}
	
	@Test
	public void testDeleteAuditor() {
		Auditor auditor;
		auditor = this.auditorService.findOne(super.getEntityId("auditor1"));
		this.auditorService.delete(auditor);
	}
}
