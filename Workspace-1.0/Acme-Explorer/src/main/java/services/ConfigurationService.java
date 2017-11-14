package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConfigurationRepository;
import domain.Configuration;

@Service
@Transactional
public class ConfigurationService {

	// Managed repository

	@Autowired
	private ConfigurationRepository configurationRepository;

	// Supporting services

	// Constructors

	public ConfigurationService() {
		super();
	}

	// Simple CRUD methods

	public Configuration create() {
		Configuration configuration;
		configuration = new Configuration();
		return configuration;
	}

	public Collection<Configuration> findAll() {
		Collection<Configuration> res;
		res = this.configurationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Configuration findOne(int configuration) {
		Assert.isTrue(configuration != 0);
		Configuration res;
		res = this.configurationRepository.findOne(configuration);
		Assert.notNull(res);
		return res;
	}

	public Configuration save(Configuration configuration) {
		Assert.notNull(configuration);
		Configuration res;
		res = this.configurationRepository.save(configuration);
		return res;
	}

	public void delete(Configuration configuration) {
		Assert.notNull(configuration);
		Assert.isTrue(configuration.getId() != 0);
		Assert.isTrue(this.configurationRepository.exists(configuration.getId()));
		this.configurationRepository.delete(configuration);
	}

	// Other business methods
}
