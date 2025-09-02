package kr.co.iei.match.model.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.match.model.vo.Grade;
import kr.co.iei.match.model.vo.Match;
import kr.co.iei.pay.model.vo.pay;

@Mapper
public interface MatchDao {

	Grade calcPrice(Grade g);

	int insertPayData(pay p);

	int matchEnroll(Match m);
	
}
