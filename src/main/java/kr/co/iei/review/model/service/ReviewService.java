package kr.co.iei.review.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.review.model.dao.ReviewDao;
import kr.co.iei.review.model.vo.Review;

@Service
public class ReviewService {
	
	@Autowired
	ReviewDao reviewDao;

	

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
		System.out.println(photoList);
		return photoList;
	}



	public int insertReview(Review r) {
		int result = reviewDao.insertReview(r);
		return result;
	}
}



































