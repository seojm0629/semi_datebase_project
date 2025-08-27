package kr.co.iei.model.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.model.member.dao.MemberDao;
import kr.co.iei.model.member.vo.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;

	public int insertMember(Member m) {
		int result = memberDao.insertMember(m);
		return result;
	}
}
