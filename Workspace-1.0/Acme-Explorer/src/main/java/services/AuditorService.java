package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuditorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Audit;
import domain.Auditor;
import domain.Folder;
import domain.Note;
import domain.SocialId;

@Service
@Transactional
public class AuditorService {

	// Managed repository

	@Autowired
	private AuditorRepository auditorRepository;

	// Supporting services

	@Autowired
	private FolderService folderService;

	// Constructors

	public AuditorService() {
		super();
	}

	// Simple CRUD methods

	public Auditor create() {
		Auditor res = new Auditor();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<SocialId> socialId = new ArrayList<SocialId>();
		Collection<Folder> folder = new ArrayList<Folder>();
		Collection<Note> note = new ArrayList<Note>();
		Collection<Audit> audit = new ArrayList<Audit>();
		folder = this.folderService.systemFolders();
		
		authority.setAuthority(Authority.AUDITOR);
		userAccount.addAuthority(authority);
		res.setUserAccount(userAccount);
		res.setSocialId(socialId);
		res.setFolders(folder);
		res.setNote(note);
		res.setAudit(audit);
		return res;
	}

	public Collection<Auditor> findAll() {
		Collection<Auditor> res;
		res = this.auditorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Auditor findOne(int auditorId) {
		Assert.isTrue(auditorId != 0);
		Auditor res;
		res = this.auditorRepository.findOne(auditorId);
		Assert.notNull(res);
		return res;
	}

	public Auditor save(Auditor auditor) {
		Assert.notNull(auditor);
		Auditor res;
		res = this.auditorRepository.save(auditor);
		return res;
	}

	public void delete(Auditor auditor) {
		Assert.notNull(auditor);
		Assert.isTrue(auditor.getId() != 0);
		Assert.isTrue(this.auditorRepository.exists(auditor.getId()));
		this.auditorRepository.delete(auditor);
	}

	// Other business methods

	public Auditor findByPrincipal() {
		Auditor res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.auditorRepository.findAuditorByUserAccountId(userAccount
				.getId());
		Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("AUDITOR");
		Assert.isTrue(authority.contains(res));
	}
}
