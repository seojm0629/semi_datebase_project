package kr.co.iei.model.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.model.member.dao.MemberDao;
import kr.co.iei.model.member.vo.Member;
import kr.co.iei.pay.model.vo.pay;
import kr.co.iei.model.member.vo.MemberMoreInfo;

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


	@Transactional
	public int updateMemberPayment(Member m) {
		int update = memberDao.updateMemberPayment(m);
		return update;
	}
	
	@Transactional
	public int joinManager(Member m) {
		int result = memberDao.joinManager(m);
		return result;
	}

	
	@Transactional
	public int deleteMember(String memberId) {
		int result = memberDao.deleteMember(memberId);
		return result;

	}


	public MemberMoreInfo searchMemberMoreINfo(int memberNo) {
		MemberMoreInfo memberMoreInfo = memberDao.searchMemberMoreINfo(memberNo);
		return memberMoreInfo;
	}

	@Transactional
	public int updateMember(Member m) {
		int result = memberDao.updateMember(m);
		return result;
	}

	
	@Transactional
	public int updatePlusInfo(MemberMoreInfo info) {
		int result = memberDao.updatePlusInfo(info);
		return result;
	}


	public int insertMoreInfo(MemberMoreInfo memberMoreInfo) {
		int result = memberDao.insertMoreInfo(memberMoreInfo);
		return result;
	}
}
