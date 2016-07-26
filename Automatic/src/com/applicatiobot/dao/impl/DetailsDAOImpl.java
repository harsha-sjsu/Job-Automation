package com.applicatiobot.dao.impl;

import com.applicationbot.dao.DetailsDAO;
import com.applicationbot.model.Details;

public class DetailsDAOImpl implements DetailsDAO {

	//@Autowired
    private DAO dao;
    
    public DetailsDAOImpl() {
    	dao = new DAO();
    }
	
	@Override
	public boolean find(Details d) {
		
		return dao.get(Details.class, d.getJobId())!=null;
	}

	@Override
	public void save(Details d) {
		dao.saveOrUpdate(d);

	}

}
