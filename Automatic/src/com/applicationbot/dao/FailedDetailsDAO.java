package com.applicationbot.dao;

import com.applicationbot.model.FailedDetails;

public interface FailedDetailsDAO {

	public boolean find(FailedDetails d);
	
	public void save(FailedDetails d);
}
