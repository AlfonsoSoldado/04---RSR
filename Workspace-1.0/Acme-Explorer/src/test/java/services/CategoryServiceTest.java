package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Category;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class CategoryServiceTest extends AbstractTest{
	
	// Service under test -------------------
	
	@Autowired
	private CategoryService categoryService;
	
	// Supporting services ----------------
	
	
	// Test -------------------------------
	
	@Test 
	public void testCreateCategory(){
		Category category;
		category = this.categoryService.create();
		Assert.notNull(category);
	}
	
	@Test
	public void testFindAllCategory(){
		Collection<Category> categories;
		categories = this.categoryService.findAll();
		Assert.notNull(categories);
	}
	
	@Test
	public void testFindOneCategory(){
		Category category;
		category = this.categoryService.findOne(super.getEntityId("category1"));
		Assert.notNull(category);
	}
	
	@Test
	public void testSaveCategory(){
		
	}
	
	
	
	
}
