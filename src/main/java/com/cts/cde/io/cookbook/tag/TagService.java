package com.cts.cde.io.cookbook.tag;

import java.util.List;



public interface TagService {
	Tag createTag(Tag tag);

	Tag updateTag(Tag tag, long id);

	void deleteTag(long id);

	Tag findById(long id);

	List<Tag> findAll();

}
