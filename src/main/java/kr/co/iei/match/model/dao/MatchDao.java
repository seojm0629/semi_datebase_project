package kr.co.iei.match.model.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.match.model.vo.Grade;

@Mapper
public interface MatchDao {

	Grade calcPrice(Grade g);
	
}
