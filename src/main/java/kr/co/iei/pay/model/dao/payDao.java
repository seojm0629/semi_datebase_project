package kr.co.iei.pay.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.pay.model.vo.pay;

@Mapper
public interface payDao {

	List<pay> selectMemberPayList(String memberId);

}
