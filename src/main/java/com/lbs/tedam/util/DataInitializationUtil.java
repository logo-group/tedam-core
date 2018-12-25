package com.lbs.tedam.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.lbs.tedam.data.service.ProjectService;
import com.lbs.tedam.data.service.PropertyService;
import com.lbs.tedam.data.service.TedamFolderService;
import com.lbs.tedam.data.service.TedamUserService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.model.Property;
import com.lbs.tedam.model.TedamFolder;
import com.lbs.tedam.model.TedamUser;
import com.lbs.tedam.util.EnumsV2.TedamFolderType;
import com.lbs.tedam.util.EnumsV2.TedamUserRole;

@Component
public class DataInitializationUtil {

	@Autowired
	private TedamUserService userService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TedamFolderService folderService;

	@Autowired
	private PropertyService propertyService;

	public void loadInitialData() throws LocalizedException {
		if (userService.getAll().isEmpty() && projectService.getAll().isEmpty() && folderService.getAll().isEmpty()
				&& propertyService.getAll().isEmpty()) {
			loadProjectData();
			loadUserData();
			loadFolderData();
			loadPropertyData();
		}
	}

	private void loadProjectData() throws LocalizedException {
		Project project = new Project("TEDAM", "Tedam default project");
		projectService.save(project);
	}

	private void loadUserData() throws LocalizedException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		TedamUser user = new TedamUser("admin", passwordEncoder.encode("2018tedaM"), TedamUserRole.ADMIN);
		user.setProjects(projectService.getAll());
		userService.save(user);
	}

	private void loadFolderData() throws LocalizedException {
		Project project = projectService.getProjectList().get(0);
		TedamFolder testCaseFolder = new TedamFolder("Root", null, project, TedamFolderType.TESTCASE);
		TedamFolder testSetFolder = new TedamFolder("Root", null, project, TedamFolderType.TESTSET);
		folderService.save(testCaseFolder);
		folderService.save(testSetFolder);
	}

	private void loadPropertyData() throws LocalizedException {
		String config = "CONFIG";
		List<Property> propertyList = new ArrayList<>();
		Property moduleConfigPoolPathProperty = new Property(config, "moduleConfigPoolPath", "C:\\");
		Property moduleConfigOrderPathProperty = new Property(config, "moduleConfigOrderPath", "C:\\");
		Property tempFilePathProperty = new Property(config, "tempFilePath", "C:\\temp");
		Property snapshotFileFolderProperty = new Property(config, "snapshotFileFolder", "C:\\");
		Property scorderProperty = new Property(config, "scorder", "C:\\");
		Property jobrunnerRestUrlProperty = new Property(config, "jobrunnerRestUrl",
				"http://localhost:9080/TedamManager/api/JobRunnerRestService/");
		propertyList.add(moduleConfigPoolPathProperty);
		propertyList.add(moduleConfigOrderPathProperty);
		propertyList.add(tempFilePathProperty);
		propertyList.add(snapshotFileFolderProperty);
		propertyList.add(scorderProperty);
		propertyList.add(jobrunnerRestUrlProperty);
		propertyService.save(propertyList);
	}

}
