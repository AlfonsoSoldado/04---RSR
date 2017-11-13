package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TagRepository;
import domain.Tag;

@Service
@Transactional
public class TagService {

	// Managed repository

	@Autowired
	private TagRepository tagRepository;

	// Supporting services
	@Autowired
	private ActorService actorService;
	
	// Constructors

	public TagService() {
		super();
	}

	// Simple CRUD methods
	
	public Tag create() {
		Tag tag;
		tag = new Tag();
		return tag;
	}

	public Collection<Tag> findAll() {
		Collection<Tag> res;
		res = this.tagRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Tag findOne(int tag) {
		Assert.isTrue(tag != 0);
		Tag res;
		res = this.tagRepository.findOne(tag);
		Assert.notNull(res);
		return res;
	}

	public Tag save(Tag tag) {
		Assert.notNull(tag);
		Tag res;
		res = this.tagRepository.save(tag);
		return res;
	}
	
	//14.3
	 public Tag update(int id, String newName){
		 Collection<Tag> t = new ArrayList<Tag>();
		 Tag tmod = new Tag();
		 t.addAll(tagRepository.findTagNotTrip());
		 tmod = tagRepository.findOne(id);
		 Assert.notNull(tagRepository.findOne(id));
		 actorService.checkAuthority("ADMINISTRATOR");
		 tmod.setName(newName);
		 return tmod;
	 }

	public void delete(Tag tag) {
		Assert.notNull(tag);
		Assert.isTrue(tag.getId() != 0);
		actorService.checkAuthority("ADMINISTRATOR");
		Assert.isTrue(this.tagRepository.exists(tag.getId()));
		this.tagRepository.delete(tag);
	}

	// Other business methods
	
	
	

}
