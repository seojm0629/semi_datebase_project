package kr.co.iei.question.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.question.model.vo.Question;
import kr.co.iei.question.model.vo.QuestionComment;
import kr.co.iei.question.model.vo.QuestionFile;

@Mapper
public interface QuestionDao {

	List selectQuestionList(HashMap<String, Object> param);

	int selectQuestionTotalCount();

	List searchWriter(String search);

	int getQuestionNo();

	int insertQuestion(Question q);

	int insertQuestionFile(QuestionFile questionFile);

	Question selectOneQuestion(int questionNo);

	List selectQuestionFile(int questionNo);

	List<QuestionComment> selectQuestionCommentList(int questionNo);

	int selectQuestionCommentLikeCount(int questionCommentNo);

	QuestionFile selectOneQuestionFile(int questionFileNo);

	int insertQuestionComment(QuestionComment qc);

	int updateQuestionComment(QuestionComment qc);

	int deleteQuestionComment(int questionCommentNo);


	List selectQuestionFileList(int[] delFileNo);

	int deleteQuestionFile(int questionFileNo);

	int updateQuestion(Question q);

	int deleteQuestion(int questionNo);

	

	

	
	
	

}
