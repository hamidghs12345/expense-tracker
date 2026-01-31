package com.snapppay.expensetracker.domain.service.tag;

import com.snapppay.expensetracker.domain.model.tag.Tag;
import com.snapppay.expensetracker.domain.model.tag.TagFilter;
import org.springframework.data.domain.Page;

public interface TagRetrievalService {

  Page<Tag> getAll(TagFilter tagFilter);

}
