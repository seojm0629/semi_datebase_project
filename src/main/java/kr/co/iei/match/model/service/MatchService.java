package kr.co.iei.match.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.match.model.dao.MatchDao;
import kr.co.iei.match.model.vo.Grade;

@Service
public class MatchService {
	
	@Autowired
	private MatchDao matchDao;
	
	public int calcPrice(Grade g) {
		int result = matchDao.calcPrice(g);
		return 0;
	}
	
}
