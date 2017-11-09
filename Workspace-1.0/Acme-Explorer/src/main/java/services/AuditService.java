package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditRepository;
import domain.Audit;

@Service
@Transactional
public class AuditService {
	
	// Managed repository

	@Autowired
	private AuditRepository auditRepository;
	
	// Supporting services
	
	// Constructors
	
	public AuditService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Audit create() {
		Audit res;
		
		res = new Audit();
		return res;
	}
	
	public Collection<Audit> findAll() {
		Collection<Audit> res;
		res = this.auditRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Audit findOne(int auditId) {
		Assert.isTrue(auditId != 0);
		Audit res;
		res = this.auditRepository.findOne(auditId);
		Assert.notNull(res);
		return res;
	}
	
	public Audit save(Audit audit) {
		Assert.notNull(audit);
		Audit res;
		res = this.auditRepository.save(audit);
		return res;
	}
	
	public void delete(Audit audit) {
		Assert.notNull(audit);
		Assert.isTrue(audit.getId() != 0);
		Assert.isTrue(this.auditRepository.exists(audit.getId()));
		this.auditRepository.delete(audit);
	}
	
	// Other business methods
	
}
