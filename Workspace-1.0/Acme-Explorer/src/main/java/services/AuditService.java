package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Audit;
import domain.Auditor;
import domain.Trip;

@Service
@Transactional
public class AuditService {

	// Managed repository

	@Autowired
	private AuditRepository auditRepository;

	// Supporting services
	@Autowired
	private AuditorService auditorService;

	// Constructors

	public AuditService() {
		super();
	}

	// Simple CRUD methods

	public Audit create() {
		auditorService.checkAuthority();

		Audit res = new Audit();
		Auditor a = new Auditor();
		a = auditorService.findByPrincipal();
		Assert.notNull(a);
		Trip trip = new Trip();
		Date d = new Date(System.currentTimeMillis() - 1);
		Collection<String> attachments = new ArrayList<String>();

		res.setDraftMode(true);
		res.setMoment(d);
		res.setAttachment(attachments);
		res.setAuditor(a);
		res.setTrip(trip);

		return res;
	}

	public Collection<Audit> findAll() {
		auditorService.checkAuthority();

		Collection<Audit> res;
		res = this.auditRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Audit findOne(int auditId) {
		auditorService.checkAuthority();

		Assert.isTrue(auditId != 0);
		Audit res;
		res = this.auditRepository.findOne(auditId);
		Assert.notNull(res);
		return res;
	}

	// 33.2

	public Audit save(Audit audit) {
		auditorService.checkAuthority();

		Assert.isTrue(audit.getDraftMode() == true);
		UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);
		Actor a = auditorService.findOne(ua.getId());
		Assert.notNull(a);
		Assert.notNull(audit);
		Audit res;
		res = this.auditRepository.save(audit);
		return res;
	}

	// 33.2

	public void delete(Audit audit) {
		auditorService.checkAuthority();

		Assert.isTrue(audit.getDraftMode() == true);
		Assert.notNull(audit);
		Assert.isTrue(audit.getId() != 0);
		Assert.isTrue(this.auditRepository.exists(audit.getId()));
		this.auditRepository.delete(audit);
	}

	// Other business methods

}
