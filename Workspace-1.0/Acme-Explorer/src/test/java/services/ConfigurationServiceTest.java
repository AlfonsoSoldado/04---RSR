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

import domain.Configuration;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
public class ConfigurationServiceTest extends AbstractTest {

	// Service under test --------------

	@Autowired
	private ConfigurationService configurationService;

	// Supporting services -------------

	// Test ----------------------------

	@Test
	public void testCreateConfiguration() {
		Configuration conf;
		conf = this.configurationService.create();
		Assert.notNull(conf);
	}

	@Test
	public void testFindOneConfiguration() {
		Configuration conf;
		conf = this.configurationService.findOne(super
				.getEntityId("configuration"));
		Assert.notNull(conf);
	}

	@Test
	public void testFindAllConfiguration() {
		Collection<Configuration> conf;
		conf = new ArrayList<Configuration>();
		conf = this.configurationService.findAll();
		Assert.notNull(conf);
	}

	@Test
	public void testSaveConfiguration() {
		Configuration conf;
		Double tax;
		String banner, message, tag, countryCode, category, text, oth;
		Collection<String> catalogueTag, treeCategory, catalogueText, other;

		conf = this.configurationService.create();
		catalogueTag = new ArrayList<String>();
		treeCategory = new ArrayList<String>();
		catalogueText = new ArrayList<String>();
		other = new ArrayList<String>();
		banner = "http://creek-tours.com/wp-content/uploads/Kenya-Tanzania-Family-Safari-banner.jpg";
		message = "Tanzanika is an adven-ture company that ...";
		tax = 0.22;
		countryCode = "+34";
		tag = "country";
		catalogueTag.add(tag);
		category = "CATEGORY";
		treeCategory.add(category);
		text = "Text 1";
		catalogueText.add(text);
		oth = "Other";
		other.add(oth);

		conf.setBanner(banner);
		conf.setMessage(message);
		conf.setTax(tax);
		conf.setCountryCode(countryCode);
		conf.setCatalogueTag(catalogueTag);
		conf.setTreeCategory(treeCategory);
		conf.setCatalogueText(catalogueText);
		conf.setOther(other);

		conf = this.configurationService.create();
	}

	@Test
	public void testDeleteConfiguration() {
		Configuration conf;
		conf = this.configurationService.findOne(super
				.getEntityId("configuration"));
		this.configurationService.delete(conf);
	}
}
