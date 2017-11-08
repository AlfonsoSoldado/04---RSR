package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.StoryRepository;

@Service
@Transactional
public class StoryService {

	@Autowired
	private StoryRepository storyRepository;
	
	public StoryService(){
		super();
	}
}
