package kr.co.iei.match.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import kr.co.iei.match.model.dao.MatchDao;
import kr.co.iei.match.model.vo.Grade;
import kr.co.iei.match.model.vo.Match;
import kr.co.iei.model.member.dao.MemberDao;
import kr.co.iei.model.member.vo.Member;
import kr.co.iei.pay.model.vo.pay;

@Service
public class MatchService {
	
	@Autowired
	private MatchDao matchDao;
	@Autowired
	private MemberDao memberDao;
	
	public Grade calcPrice(Grade g) {
		Grade grade = matchDao.calcPrice(g);
		return grade;
	}
	
	@Transactional
	public int insertPayData(pay p) {
		int result = matchDao.insertPayData(p);
		return result;
	}
	
	@Transactional
	public int insertMatch(Match m) {
		int result = matchDao.matchEnroll(m);
		return result;
	}
	
	@Transactional
	public int useMatchCount(String memberId, int myMatchingCount) {
			myMatchingCount = myMatchingCount - 1;
			Member m = new Member();
			m.setMemberId(memberId);
			m.setMyMatchingCount(myMatchingCount);
			int useCount = memberDao.useMatchCount(m);
		return useCount;
	}
	
	@Transactional
	public int matchEnroll(Match m) {
			
			int result = matchDao.matchEnroll(m);
		return result;
	}
	
	public List selectMatchList() {
			List matchList = matchDao.selectMatchList();
			
		return matchList;
	}

	public List findMatch(Match m) {
			
			List findMatchList = matchDao.findMatch(m);
		return findMatchList;
	}
	@Transactional
	public int matchComplete(HashMap<String, Object> param) {
			int result = matchDao.matchComplete(param);
			
		return result;
	}
	
	@Transactional
	public int updateStatus(HashMap<String, Object> param) {
			int updateStatus = matchDao.updateStatus(param);
		return updateStatus;
	}

	public List matchingList() {
			List matchingList = matchDao.matchingList();
		return matchingList;
	}
	
}
