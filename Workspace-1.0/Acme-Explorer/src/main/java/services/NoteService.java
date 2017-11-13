package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Auditor;
import domain.Note;

@Service
@Transactional
public class NoteService {

	// Managed repository

	@Autowired
	private NoteRepository noteRepository;

	// Supporting services
	@Autowired
	private AuditorService auditorService;
	
	@Autowired
	private ActorService actorService;

	// Constructors

	public NoteService() {
		super();
	}

	// Simple CRUD methods

	// 33
	public Note create() {
		Assert.isTrue(this.actorService.checkAuthority("AUDITOR"));
		Note res = new Note();
		Auditor a = new Auditor();
		Date d = new Date();
		// Confirmo que es un actor registrado
		a = auditorService.findByPrincipal();
		Assert.notNull(a);
		res.setMoment(d);
		res.setAuditor(a);
		return res;
	}

	public Collection<Note> findAll() {
		Assert.isTrue(this.actorService.checkAuthority("AUDITOR"));
		Collection<Note> res;
		res = this.noteRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Note findOne(int note) {
		Assert.isTrue(this.actorService.checkAuthority("AUDITOR"));
		Assert.isTrue(note != 0);
		Note res;
		res = this.noteRepository.findOne(note);
		Assert.notNull(res);
		return res;
	}

	public Note save(Note note) {
		Assert.notNull(note);
		Note res;
		res = this.noteRepository.save(note);
		return res;
	}

	// 33
	// Once a note is written, it cannot be modified at all or deleted.
	// public void delete(Note note) {
	// Assert.notNull(note);
	// Assert.isTrue(note.getId() != 0);
	// Assert.isTrue(this.noteRepository.exists(note.getId()));
	// this.noteRepository.delete(note);
	// }

	// Other business methods

	public Collection<Note> findNotesByAuditor(int id) {
		Collection<Note> res = new ArrayList<Note>();
		// añadimos todas las notes mediante la query
		res.addAll(noteRepository.findNotesByAuditor(id));
		Assert.notNull(res);
		return res;
	}

	// 32
//	public Collection<Note> findNotesByManagerID(int id) {
//
//		Collection<Note> res = new ArrayList<Note>();
//		res = this.noteRepository.findNotesByManager(id);
//		Assert.notNull(res);
//		return res;
//
//	}
}
