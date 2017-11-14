package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Tag;
import domain.Value;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class TagServiceTest extends AbstractTest{
	
	// Service under test ---------
	
	@Autowired 
	private TagService tagService;
	
	
	// Supporting services --------
	
	@Autowired
	private ValueService valueService;
	
	// Test -----------------------
	
	@Test
	public void testCreateTag(){
		Tag tag;
		tag = this.tagService.create();
		Assert.notNull(tag);
	}
	
	@Test
	public void testFindAllTag(){
		Collection<Tag> tag;
		tag = new ArrayList<Tag>();
		tag = this.tagService.findAll();
		Assert.notNull(tag);
	}
	
	@Test
	public void testFindOneTag(){
		Tag tag;
		tag = this.tagService.findOne(super.getEntityId("tag1"));
		Assert.notNull(tag);
	}
	
	@Test 
	public void testSaveTag(){
		Tag tag;
		tag = this.tagService.create();
		Value value;
		value = this.valueService.create();
		
		tag.setName("Sample");
		tag.setValue(value);
		
		this.tagService.save(tag);
	}
	
	@Test 
	public void testUpdateTag(){
		Tag tag;
		String newName;
		
		tag = this.tagService.findOne(super.getEntityId("tag1"));
		newName = "New";
		
		this.tagService.update(tag, newName);
	}
	
	@Test
	public void testDeleteTag(){
		Tag tag;
		tag = this.tagService.findOne(super.getEntityId("tag2"));
		
		this.tagService.delete(tag);
	}
}
