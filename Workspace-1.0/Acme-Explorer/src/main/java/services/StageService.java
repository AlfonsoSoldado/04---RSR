package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StageRepository;
import domain.Stage;

@Service
@Transactional
public class StageService {

	// Managed repository

	@Autowired
	private StageRepository stageRepository;

	// Supporting services

	// Constructors

	public StageService() {
		super();
	}

	// Simple CRUD methods

	public Stage create() {
		Stage stage;
		stage = new Stage();
		return stage;
	}

	public Collection<Stage> findAll() {
		Collection<Stage> res;
		res = this.stageRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Stage findOne(int stage) {
		Assert.isTrue(stage != 0);
		Stage res;
		res = this.stageRepository.findOne(stage);
		Assert.notNull(res);
		return res;
	}

	public Stage save(Stage stage) {
		Assert.notNull(stage);
		Stage res;
		res = this.stageRepository.save(stage);
		return res;
	}

	public void delete(Stage stage) {
		Assert.notNull(stage);
		Assert.isTrue(stage.getId() != 0);
		Assert.isTrue(this.stageRepository.exists(stage.getId()));
		this.stageRepository.delete(stage);
	}

	// Other business methods

}
