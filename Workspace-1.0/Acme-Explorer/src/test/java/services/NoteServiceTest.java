package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Auditor;
import domain.Note;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class NoteServiceTest extends AbstractTest {

	// Service under test ----------

	@Autowired
	private NoteService noteService;

	// Supporting services ---------

	@Autowired
	private AuditorService auditorService;
	@Autowired
	private TripService tripService;

	// Test ------------------------

	@Test
	public void testCreateNote() {
		authenticate("auditor01");
		Note note;
		note = this.noteService.create();
		Assert.notNull(note);
		unauthenticate();
	}

	@Test
	public void testFindAllNote() {
		authenticate("auditor01");
		Collection<Note> notes;
		notes = new ArrayList<Note>();
		notes = this.noteService.findAll();
		Assert.notNull(notes);
		unauthenticate();
	}

	@Test
	public void testFindOneNote() {
		authenticate("auditor01");
		Note note;
		note = this.noteService.findOne(super.getEntityId("note1"));
		Assert.notNull(note);
		unauthenticate();
	}

	@Test
	public void testSaveNote() {
		authenticate("auditor01");
		Note note;
		note = this.noteService.create();

		Date moment = new Date(System.currentTimeMillis() - 1);
		note.setMoment(moment);

		note.setRemark("First");

		note.setReply("Replay");

		Date momentReply = new Date(System.currentTimeMillis() - 2);
		note.setMomentReply(momentReply);

		Auditor auditor;
		auditor = this.auditorService.findOne(super.getEntityId("auditor1"));
		note.setAuditor(auditor);

		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		note.setTrip(trip);

		this.noteService.save(note);
		unauthenticate();
	}

	@Test
	public void testFindNotesByAuditor() {
		Collection<Note> notes;
		Auditor auditor;
		int id;

		notes = new ArrayList<Note>();
		auditor = this.auditorService.findOne(super.getEntityId("auditor1"));
		id = auditor.getId();

		notes = this.noteService.findNotesByAuditor(id);
		Assert.notNull(notes);
	}
}
