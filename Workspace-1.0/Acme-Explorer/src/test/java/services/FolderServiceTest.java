package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Folder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
				"classpath:spring/datasource.xml",
				"classpath:spring/config/packages.xml"})
@Transactional
public class FolderServiceTest extends AbstractTest {

	// Service under test -------------------------
	
	 @Autowired
	 private FolderService folderService;
	 
	// Supporting services -----------------------
	
	// Test --------------------------------------
	 
	 @Test
	 public void testCreateFolder(){
		 this.authenticate("ranger1");
		 Folder folder;
		 folder = this.folderService.create();
		 Assert.notNull(folder);
		 unauthenticate();
	 }
	 
	 @Test
	 public void testFindAllFolder(){
		 Collection<Folder> folders;
		 folders = this.folderService.findAll();
		 Assert.notNull(folders);
	 }
	 
	 @Test
	 public void testFindOneFolder(){
		 Folder folder;
		 folder = this.folderService.findOne(super.getEntityId("outBoxRanger1"));
		 Assert.notNull(folder);
	 }
	 
	 @Test
	 public void testSaveFolder() {
		 this.authenticate("ranger1");
		 Folder folder;
		 folder = this.folderService.create();
		 folder.setName("outBoxModificado");
		 folder.setSystemFolder(false);
		 this.folderService.save(folder);
		 unauthenticate();
	 }
	 
	 @Test
	 public void testDeleteFolder(){
		 Folder folder;
		 folder = this.folderService.findOne(super.getEntityId("outBoxRanger1"));
		 this.folderService.delete(folder);
	 }
}
