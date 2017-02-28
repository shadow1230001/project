package com.intransition.labs.editor;

import com.intransition.labs.domain.content.Creative;

public interface EditorService  {
	
	Creative convertFromJsonToJpaCreative(JsonCreative creative );

	void saveAndFlush( Creative creative );

	Alert validate(Creative creative);

}
