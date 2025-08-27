package kr.co.iei.model.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.model.member.dao.MemberDao;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
}
