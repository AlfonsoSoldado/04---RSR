package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Folder;

@Service
@Transactional
public class FolderService {

	// Managed repository

	@Autowired
	private FolderRepository folderRepository;

	// Supporting services

	// Constructors

	public FolderService() {
		super();
	}

	// Simple CRUD methods

	public Collection<Folder> findAll() {
		Collection<Folder> res;
		res = this.folderRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Folder findOne(int folder) {
		Assert.isTrue(folder != 0);
		Folder res;
		res = this.folderRepository.findOne(folder);
		Assert.notNull(res);
		return res;
	}

	public Folder save(Folder folder) {
		Assert.notNull(folder);
		Folder res;
		res = this.folderRepository.save(folder);
		return res;
	}

	public void delete(Folder folder) {
		Assert.notNull(folder);
		Assert.isTrue(folder.getId() != 0);
		Assert.isTrue(this.folderRepository.exists(folder.getId()));
		this.folderRepository.delete(folder);
	}

	// Other business methods

}
