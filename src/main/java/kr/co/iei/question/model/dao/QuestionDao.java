package kr.co.iei.question.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionDao {

	List selectQuestionList(HashMap<String, Object> param);

	int selectQuestionTotalCount();
	
	

}
