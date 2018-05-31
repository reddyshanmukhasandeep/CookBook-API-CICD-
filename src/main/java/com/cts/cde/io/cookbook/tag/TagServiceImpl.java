package com.cts.cde.io.cookbook.tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagServiceImpl implements TagService {

	@Autowired
	TagRepository tagRepository;
	
	@Override
	public List<Tag> findAll() {
		
		 List<Tag> tags = new ArrayList();
		 tagRepository.findAll().forEach(tags::add);
		 return tags;
	}

	@Override
	public Tag findById(long id) {
		
	  return tagRepository.findOne(id);
	}

	@Override
	public Tag createTag(Tag tag) {
		
		return tagRepository.save(tag);
	}

	@Override
	public Tag updateTag(Tag tag, long id) {
		
		return tagRepository.save(tag);
	}

	@Override
	public void deleteTag(long id) {
		
		tagRepository.delete(id);
	}

	
}
