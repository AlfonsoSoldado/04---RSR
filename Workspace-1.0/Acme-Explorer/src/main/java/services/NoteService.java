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

	// Constructors

	public NoteService() {
		super();
	}

	// Simple CRUD methods

	// 33.1
	
	public Note create() {
		auditorService.checkAuthority();

		Note res = new Note();
		Auditor a = new Auditor();
		Date d = new Date();
		a = auditorService.findByPrincipal();
		Assert.notNull(a);
		res.setMoment(d);
		res.setAuditor(a);
		return res;
	}

	public Collection<Note> findAll() {
		auditorService.checkAuthority();

		Collection<Note> res;
		res = this.noteRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Note findOne(int note) {
		auditorService.checkAuthority();

		Assert.isTrue(note != 0);
		Note res;
		res = this.noteRepository.findOne(note);
		Assert.notNull(res);
		return res;
	}

	public Note save(Note note) {
		auditorService.checkAuthority();

		Assert.notNull(note);
		Assert.isTrue(note.getId() == 0);

		Note res;
		res = this.noteRepository.save(note);
		return res;
	}

	// Other business methods

	// 33.1
	
	public Collection<Note> findNotesByAuditor(int id) {
		Collection<Note> res = new ArrayList<Note>();

		res.addAll(noteRepository.findNotesByAuditor(id));
		Assert.notNull(res);
		return res;
	}
}
