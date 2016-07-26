package com.applicatiobot.dao.impl;

import com.applicationbot.dao.DetailsDAO;
import com.applicationbot.dao.FailedDetailsDAO;
import com.applicationbot.model.Details;
import com.applicationbot.model.FailedDetails;

public class FailedDetailsDAOImpl implements FailedDetailsDAO {

	//@Autowired
    private DAO dao;
    
    public FailedDetailsDAOImpl() {
    	dao = new DAO();
    }
	
	@Override
	public boolean find(FailedDetails d) {
		
		return dao.get(Details.class, d.getJobId())!=null;
	}

	@Override
	public void save(FailedDetails d) {
		dao.saveOrUpdate(d);

	}

}
