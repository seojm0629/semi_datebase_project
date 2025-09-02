package kr.co.iei.match.model.service;

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
		return 0;
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
			System.out.println(m);
			int result = matchDao.matchEnroll(m);
		return result;
	}
	
	public List selectMatchList() {
			List matchList = matchDao.selectMatchList();
			
		return matchList;
	}
	
}
