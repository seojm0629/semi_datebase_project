package kr.co.iei.match.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.match.model.vo.Grade;
import kr.co.iei.match.model.vo.Match;
import kr.co.iei.pay.model.vo.pay;

@Mapper
public interface MatchDao {

	Grade calcPrice(Grade g);

	int insertPayData(pay p);

	int matchEnroll(Match m);

	List selectMatchList();

	List findMatch(Match m);

	int matchComplete(HashMap<String, Object> param);

	int updateStatus(HashMap<String, Object> param);
	
}
