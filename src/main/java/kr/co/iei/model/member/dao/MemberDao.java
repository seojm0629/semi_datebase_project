package kr.co.iei.model.member.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.model.member.vo.Member;
import kr.co.iei.pay.model.vo.pay;
import kr.co.iei.model.member.vo.MemberMoreInfo;

@Mapper
public interface MemberDao {

	int insertMember(Member m);

	Member selectOneMember(Member m);

	int updateMemberPayment(Member m);

	int joinManager(Member m);

	int deleteMember(String memberId);

	MemberMoreInfo searchMemberMoreINfo(int memberNo);

	int useMatchCount(Member m);
	int updateMember(Member m);

}
