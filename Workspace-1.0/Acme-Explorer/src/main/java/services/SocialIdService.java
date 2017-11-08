package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SocialIdRepository;

@Service
@Transactional
public class SocialIdService {

	@Autowired
	private SocialIdRepository socialIdRepository;
	
	public SocialIdService(){
		super();
	}
}
