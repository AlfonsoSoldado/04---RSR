package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ValueRepository;
import domain.Value;

@Service
@Transactional
public class ValueService {

	// Managed repository

	@Autowired
	private ValueRepository valueRepository;

	// Supporting services

	// Constructors

	public ValueService() {
		super();
	}

	// Simple CRUD methods

	public Value create() {
		Value value;
		value = new Value();
		return value;
	}

	public Collection<Value> findAll() {
		Collection<Value> res;
		res = this.valueRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Value findOne(int value) {
		Assert.isTrue(value != 0);
		Value res;
		res = this.valueRepository.findOne(value);
		Assert.notNull(res);
		return res;
	}

	public Value save(Value value) {
		Assert.notNull(value);
		Value res;
		res = this.valueRepository.save(value);
		return res;
	}

	public void delete(Value value) {
		Assert.notNull(value);
		Assert.isTrue(value.getId() != 0);
		Assert.isTrue(this.valueRepository.exists(value.getId()));
		this.valueRepository.delete(value);
	}

	// Other business methods

}
