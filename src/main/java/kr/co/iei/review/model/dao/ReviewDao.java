package kr.co.iei.review.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.review.model.vo.Review;
import kr.co.iei.review.model.vo.ReviewBlind;

@Mapper
public interface ReviewDao {
	int insertReview(Review r);

	int insertReviewBlind(ReviewBlind r);
	
	int selectPartyTotalCount();

	List selectPhotoList(HashMap<String, Object> param);


	int selectBlindTotalCount();

	List selectBlindList(HashMap<String, Object> param);

	Review selectOneReview(int reviewPartyNo);
	
	
	
}
