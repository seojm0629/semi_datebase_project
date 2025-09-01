package kr.co.iei.model.member.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.model.member.vo.Member;

@Mapper
public interface MemberDao {

	int insertMember(Member m);

	Member selectOneMember(Member m);

	int joinManager(Member m);

}
