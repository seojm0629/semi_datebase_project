package kr.co.iei.review.model.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.review.model.vo.Review;

@Mapper
public interface ReviewDao {
	int insertReview(Review r);

	int selectPartyTotalCount();

	List selectPhotoList(HashMap<String, Object> param);
	
	int insertReview(Review r);
	
}
