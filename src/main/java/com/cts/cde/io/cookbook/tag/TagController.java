package com.cts.cde.io.cookbook.tag;

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
@RequestMapping(path = "/tag")
public class TagController {

	@Autowired
	private TagService tagService;

	

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Tag>> getAllTag() {
		List<Tag> tags= tagService.findAll();
		return new ResponseEntity<List<Tag>>(tags,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/{id}")
	public ResponseEntity<Tag> getTagById(@PathVariable("id") Long id) {
		
		Tag tag= tagService.findById(id);
		return new ResponseEntity<Tag>(tag,HttpStatus.OK);

	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Tag>  createTag(@RequestBody Tag tag) {

		Tag tagData=  tagService.createTag(tag);
		return new ResponseEntity<Tag>(tagData,HttpStatus.OK);

	}

	
	@RequestMapping(method=RequestMethod.PUT,value="/{id}")
	public ResponseEntity<Tag>  updateTag(@RequestBody Tag tag,@PathVariable("id") Long id) {

		Tag tagData=  tagService.updateTag(tag,id);
		return new ResponseEntity<Tag>(tagData,HttpStatus.OK);

	}

	

	@RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
	public ResponseEntity<String> deleteTag(@PathVariable("id") Integer id) {
		tagService.deleteTag(id);
		return new ResponseEntity<String>("Tag Deleted Successfully",HttpStatus.OK);

	}
}