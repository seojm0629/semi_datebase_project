package kr.co.iei.match.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.match.model.dao.MatchDao;
import kr.co.iei.match.model.vo.Grade;
import kr.co.iei.pay.model.vo.pay;

@Service
public class MatchService {
	
	@Autowired
	private MatchDao matchDao;
	
	public Grade calcPrice(Grade g) {
		Grade grade = matchDao.calcPrice(g);
		return grade;
	}
	
	@Transactional
	public int insertPayData(pay p) {
		int result = matchDao.insertPayData(p);
		return result;
	}
	
}
