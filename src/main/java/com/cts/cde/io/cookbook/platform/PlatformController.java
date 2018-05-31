package com.cts.cde.io.cookbook.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/platform")
public class PlatformController {

	@Autowired
	private PlatformService platformService;
	

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Platform>> getAllPlatform() {
		List<Platform> platforms = platformService.findAll();
		return new ResponseEntity<List<Platform>>(platforms,HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.GET,value="/{id}")
	public ResponseEntity<Platform> getPlatformById(@PathVariable("id") Long id) {
		
		Platform platform = platformService.findById(id);
		return new ResponseEntity<Platform>(platform,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Platform> createPlatform(@RequestBody Platform platform) {

		Platform platformData = platformService.createPlatform(platform);
		return new ResponseEntity<Platform>(platformData,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/{id}")
	public ResponseEntity<Platform> updatePlatform(@RequestBody Platform platform,@PathVariable("id") Long id) {

		Platform platformData = platformService.updatePlatform(platform,id);
		return new ResponseEntity<Platform>(platformData,HttpStatus.OK);
	}	

	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	public ResponseEntity<String> deletePlatform(@PathVariable("id") Long id) {
		platformService.deletePlatform(id);
		return new ResponseEntity<String>("Platform Deleted Successfully",HttpStatus.OK);

	}
}