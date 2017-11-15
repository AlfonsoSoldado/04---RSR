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
import domain.Category;
import domain.Trip;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class CategoryServiceTest extends AbstractTest {

	// Service under test -------------------

	@Autowired
	private CategoryService categoryService;

	// Supporting services ----------------
	@Autowired
	private TripService tripService;

	// Test -------------------------------

	@Test
	public void testCreateCategory() {
		this.authenticate("admin");
		Category category;
		category = this.categoryService.create();
		Assert.notNull(category);
		unauthenticate();
	}

	@Test
	public void testFindAllCategory() {
		Collection<Category> categories;
		categories = this.categoryService.findAll();
		Assert.notNull(categories);
	}

	@Test
	public void testFindOneCategory() {
		Category category;
		category = this.categoryService.findOne(super.getEntityId("category1"));
		Assert.notNull(category);
	}

	@Test
	public void testSaveCategory() {
		authenticate("admin");
		Category category;
		category = this.categoryService.create();

		category.setName("SampleCategory");

		Category parent;
		parent = this.categoryService.findOne(super.getEntityId("CATEGORY"));
		category.setCategoryParent(parent);

		Category cat;
		cat = this.categoryService.create();
		Collection<Category> categories = new ArrayList<Category>();
		categories = category.getCategories();
		categories.add(cat);
		category.setCategories(categories);

		Trip trip;
		trip = this.tripService.findOne(super.getEntityId("trip1"));
		Collection<Trip> trips = new ArrayList<Trip>();
		trips = category.getTrip();
		trips.add(trip);
		category.setTrip(trips);

		this.categoryService.save(category);

		unauthenticate();
	}

	@Test
	public void testDeleteCategory() {
		authenticate("admin");
		Category category;
		category = this.categoryService.findOne(super.getEntityId("category1"));
		this.categoryService.delete(category);
		unauthenticate();
	}
}
