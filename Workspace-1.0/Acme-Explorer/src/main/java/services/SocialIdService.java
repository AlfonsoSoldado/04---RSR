package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialIdRepository;
import domain.SocialId;

@Service
@Transactional
public class SocialIdService {

	// Managed repository

	@Autowired
	private SocialIdRepository socialIdRepository;

	// Supporting services

	// Constructors

	public SocialIdService() {
		super();
	}

	// Simple CRUD methods

	public Collection<SocialId> findAll() {
		Collection<SocialId> res;
		res = this.socialIdRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public SocialId findOne(int socialId) {
		Assert.isTrue(socialId != 0);
		SocialId res;
		res = this.socialIdRepository.findOne(socialId);
		Assert.notNull(res);
		return res;
	}

	public SocialId save(SocialId socialId) {
		Assert.notNull(socialId);
		SocialId res;
		res = this.socialIdRepository.save(socialId);
		return res;
	}

	public void delete(SocialId socialId) {
		Assert.notNull(socialId);
		Assert.isTrue(socialId.getId() != 0);
		Assert.isTrue(this.socialIdRepository.exists(socialId.getId()));
		this.socialIdRepository.delete(socialId);
	}

	// Other business methods

}
