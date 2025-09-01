package kr.co.iei.model.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.model.member.dao.MemberDao;
import kr.co.iei.model.member.vo.Member;
import kr.co.iei.pay.model.vo.pay;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;

	@Transactional
	public int insertMember(Member m) {
		int result = memberDao.insertMember(m);
		return result;
	}

	
	public Member selectOneMember(String memberId) {
		Member m = new Member();
		m.setMemberId(memberId);
		m = memberDao.selectOneMember(m);
		return m;
	}


	public Member login(Member m) {
		Member member = memberDao.selectOneMember(m);
		return member;
	}


	public int updateMemberPayment(pay p) {
		int update = memberDao.updateMemberPayment(p);
		return 0;
	}
}
