package kr.co.iei.review.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.iei.question.model.vo.Question;
import kr.co.iei.question.model.vo.QuestionComment;
import kr.co.iei.review.model.dao.ReviewDao;
import kr.co.iei.review.model.vo.Review;
import kr.co.iei.review.model.vo.ReviewBlind;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDao reviewDao;

	@Transactional
	public int insertReview(Review r) {
		int result = reviewDao.insertReview(r);
		return result;
	}
	
	@Transactional
	public int insertReviewBlind(ReviewBlind r) {
		int result = reviewDao.insertReviewBlind(r);
		return result;
	}

	public int selectPartyTotalCount() {
		int partyTotalCount = reviewDao.selectPartyTotalCount();
		return partyTotalCount;
	}



	public List selectPhotoList(int start, int amount) {
		int end = start + amount - 1;	//한 줄당 나올 페이지의 수 (1~3)
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("end", end);
		List photoList = reviewDao.selectPhotoList(param);
		return photoList;
	}



	public int selectBlindTotalCount() {
		int blindTotalCount = reviewDao.selectBlindTotalCount();
		return blindTotalCount;
	}


	public List selectBlindList(int start, int amount) {
		int end = start + amount - 1;	//한 줄당 나올 페이지의 수 (1~3)
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("end", end);
		List photoList = reviewDao.selectBlindList(param);
		return photoList;
	}

	public Review selectOneReview(int reviewPartyNo, int memberNo) {
		Review r = reviewDao.selectOneReview(reviewPartyNo);
		return null;
	}

	

	


	



}



































