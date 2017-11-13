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
	
	@Autowired
	private ActorService actorService;
	
	// Constructors
	
	public AuditService(){
		super();
	}
	
	// Simple CRUD methods
	
	
	public Audit create() {
		Assert.isTrue(this.actorService.checkAuthority("AUDITOR"));
		Auditor a = new Auditor();
		Trip trip = new Trip();//TODO: revisar lo del trip
		Audit res = new Audit();
		Date d = new Date(System.currentTimeMillis() -1);
		//atributo obligatorio
		Collection<String> attachments = new ArrayList<String>();
		//compruebo que el actor registrado sea un auditor
		a = auditorService.findByPrincipal();
		Assert.notNull(a);
		//le meto los valores
		res.setDraftMode(true);
		res.setMoment(d);
		res.setAttachment(attachments);
		res.setAuditor(a);
		res.setTrip(trip);
		return res;
	}
	
	public Collection<Audit> findAll() {
		Assert.isTrue(this.actorService.checkAuthority("AUDITOR"));
		Collection<Audit> res;
		res = this.auditRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Audit findOne(int auditId) {
		Assert.isTrue(this.actorService.checkAuthority("AUDITOR"));
		Assert.isTrue(auditId != 0);
		Audit res;
		res = this.auditRepository.findOne(auditId);
		Assert.notNull(res);
		return res;
	}
	
	//33.2
	public Audit save(Audit audit) {
		Assert.isTrue(this.actorService.checkAuthority("AUDITOR"));
		UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);
		Actor a = actorService.findOne(ua.getId());
		Assert.notNull(a);
		Assert.isTrue(audit.getDraftMode() == true);
		Assert.notNull(audit);
		Audit res;
		res = this.auditRepository.save(audit);
		return res;
	}
	
	//33.2
	public void delete(Audit audit) {
		Assert.isTrue(this.actorService.checkAuthority("AUDITOR"));
		Assert.notNull(audit);
		Assert.isTrue(audit.getId() != 0);
		Assert.isTrue(this.auditRepository.exists(audit.getId()));
		//can be modified or deleted as long as they are saved in draft mode.
		Assert.isTrue(audit.getDraftMode() == true);
		this.auditRepository.delete(audit);
	}
	
	// Other business methods
	
	//33.2 (listing)
//	public Collection<Audit> findAuditsByAuditor(int id){
//		Collection<Audit> res = new ArrayList<Audit>();
//		res = auditRepository.findAuditsByAuditor(id);
//		Assert.notNull(res);
//		return res;
//	}
//	
//	
//	//33.2 (modifying)
//	public Audit editAudit(int id){
//		Audit res = new Audit();
//		Audit a = new Audit();
//		Auditor ar = new Auditor();
//		Auditor ar2 = new Auditor();
//		//selecciono el audit que quiero editar
//		a = auditRepository.findOne(id);
//		//can be modified or deleted as long as they are saved in draft mode.
//		Assert.isTrue(a.getDraftMode() == true);
//		// comprobamos que el audit seleccionado sea de este auditor
//		Assert.notNull(a);
//		ar = a.getAuditor();
//		ar2 = auditorService.findByPrincipal();
//		Assert.isTrue(ar.equals(ar2));
//		
//		res = auditRepository.save(a);
//		return res;
//	}
	
	//33.2
	public Collection<Audit> findAuditDraftTrue(int id){
		Collection<Audit> res = new ArrayList<Audit>();
		//añadimos todos los audit mediante la query
		res.addAll(auditRepository.findAuditDraftTrue(id));
		Assert.notNull(res);
		return res;
	}
}
