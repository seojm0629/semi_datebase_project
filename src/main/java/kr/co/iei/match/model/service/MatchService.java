package kr.co.iei.match.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.match.model.dao.MatchDao;
import kr.co.iei.match.model.vo.Grade;

@Service
public class MatchService {
	
	@Autowired
	private MatchDao matchDao;
	
	public Grade calcPrice(Grade g) {
		Grade grade = matchDao.calcPrice(g);
		return grade;
	}
	
}
