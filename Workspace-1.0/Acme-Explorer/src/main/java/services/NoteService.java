package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Note;

@Service
@Transactional
public class NoteService {

	// Managed repository

	@Autowired
	private NoteRepository noteRepository;

	// Supporting services

	// Constructors

	public NoteService() {
		super();
	}

	// Simple CRUD methods

	public Collection<Note> findAll() {
		Collection<Note> res;
		res = this.noteRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Note findOne(int note) {
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

	public void delete(Note note) {
		Assert.notNull(note);
		Assert.isTrue(note.getId() != 0);
		Assert.isTrue(this.noteRepository.exists(note.getId()));
		this.noteRepository.delete(note);
	}

	// Other business methods

}
