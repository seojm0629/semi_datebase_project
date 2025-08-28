package kr.co.iei.question.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.question.model.vo.Question;
import kr.co.iei.question.model.vo.QuestionFile;

@Mapper
public interface QuestionDao {

	List selectQuestionList(HashMap<String, Object> param);

	int selectQuestionTotalCount();

	List searchWriter(String search);

	int getQuestionNo();

	int insertQuestion(Question q);

	int insertQuestionFile(QuestionFile questionFile);
	
	

}
