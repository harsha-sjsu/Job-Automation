package com.applicationbot.dao;

import com.applicationbot.model.Details;

public interface DetailsDAO {

	public boolean find(Details d);
	
	public void save(Details d);
}
