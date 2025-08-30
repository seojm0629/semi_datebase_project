package kr.co.iei.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.iei.review.model.service.ReviewService;
import kr.co.iei.review.model.vo.Review;
import kr.co.iei.util.FileUtil;

@Controller
@RequestMapping(value="/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@Value(value="${file.root}")
	private String root;
	
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping(value="/list")
	public String list(Model model) {
		int partyTotalCount = reviewService.selectPartyTotalCount();
		System.out.println(partyTotalCount);
		model.addAttribute("partyTotalCount", partyTotalCount);
		return "review/list";
	}
	@GetMapping(value="/writeFrm")
	public String writeFrm() {
		return "review/writeFrm";
	}
	
	@PostMapping(value="/write")
	public String write(Review r, MultipartFile imageFile) {
		String savepath = root+"/review_party/";
		String filepath = fileUtil.upload(savepath, imageFile);
		r.setReviewImg(filepath);
		int result = reviewService.insertReview(r);
		return "redirect:/review/list";
	}
	
	@GetMapping(value="/more")
	@ResponseBody
	public List more(int start, int amount) {
		List photoList = reviewService.selectPhotoList(start, amount);
		return photoList;
	}
}












































