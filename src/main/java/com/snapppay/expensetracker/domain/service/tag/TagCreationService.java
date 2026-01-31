package com.snapppay.expensetracker.domain.service.tag;

import com.snapppay.expensetracker.domain.model.tag.Tag;
import com.snapppay.expensetracker.domain.model.tag.TagInfo;

public interface TagCreationService {

  Tag create(TagInfo tagInfo);
}
