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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.co.iei.model.member.vo.Member;
import kr.co.iei.question.model.vo.Question;
import kr.co.iei.review.model.service.ReviewService;
import kr.co.iei.review.model.vo.Review;
import kr.co.iei.review.model.vo.ReviewBlind;
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
		int blindTotalCount = reviewService.selectBlindTotalCount();
		model.addAttribute("partyTotalCount", partyTotalCount);
		model.addAttribute("blindTotalCount", blindTotalCount);
		return "review/list";
	}
	@GetMapping(value="/writeFrm")
	public String writeFrm() {
		return "review/writeFrm";
	}
	
	/*
	@PostMapping(value="/write")
	public String write(Review r, MultipartFile imageFile, Model model) {
		String savepath = root+"/review_party/";
		String filepath = fileUtil.upload(savepath, imageFile);
		r.setReviewPartyImg(filepath);
		int result = reviewService.insertReview(r);     
		if(result == 0) {
			model.addAttribute("title", "문의사항 입력 실패");
			model.addAttribute("text", "문의사항을 작성할 수 없습니다.");
			model.addAttribute("icon", "info");
			model.addAttribute("loc", "/review/list");
			return "common/msg";
		} else {
			model.addAttribute("title", "문의사항 입력 완료!");
			model.addAttribute("text", "문의사항을 작성 완료했습니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/review/list");
			return "common/msg";
		}
		
	}
	*/
	//두 클래스, 테이블에서 가져오기 위한 값
	@PostMapping(value="/write")
	public String write(String theme, String reviewPartyTitle, String reviewPartyWriter, String reviewPartyJob, String reviewPartyContent, 
			String reviewBlindTitle, String reviewBlindWriter, String reviewBlindContent, String reviewBlindJob,
			MultipartFile imageFile, Model model) {
		int result = 0;
		String savepath = "";
		
		
		if(theme.equals("party")) {
			Review r = new Review();
			savepath = root + "/review_party/";
			String filepath = fileUtil.upload(savepath, imageFile);
			r.setReviewPartyTitle(reviewPartyTitle);
			r.setReviewPartyWriter(reviewPartyWriter);
			r.setReviewPartyContent(reviewPartyContent);
			r.setReviewPartyImg(filepath);
			r.setReviewPartyJob(reviewPartyJob);
			
			
			result = reviewService.insertReview(r);
		} else if(theme.equals("blind")){
			ReviewBlind rb = new ReviewBlind();
			savepath = root + "/review_blind/";
			String filepath = fileUtil.upload(savepath, imageFile);
			rb.setReviewBlindTitle(reviewBlindTitle);
			rb.setReviewBlindWriter(reviewBlindWriter);
			rb.setReviewBlindContent(reviewBlindContent);
			rb.setReviewBlindImg(filepath);
			rb.setReviewBlindJob(reviewBlindJob);
			
			result = reviewService.insertReviewBlind(rb);
		}
		if(result == 0) {
			model.addAttribute("title", "문의사항 입력 실패");
			model.addAttribute("text", "문의사항을 작성할 수 없습니다.");
			model.addAttribute("icon", "info");
			model.addAttribute("loc", "/review/list");
			return "common/msg";
		} else {
			model.addAttribute("title", "문의사항 입력 완료!");
			model.addAttribute("text", "문의사항을 작성 완료했습니다.");
			model.addAttribute("icon", "success");
			model.addAttribute("loc", "/review/list");
			return "common/msg";
		}
		
		
	}
	
	@GetMapping(value="/partyMore")
	@ResponseBody
	public List more(int start, int amount) {
		List photoList = reviewService.selectPhotoList(start, amount);	
		return photoList;
	}
	
	@GetMapping(value="/blindMore")
	@ResponseBody
	public List moreBlind(int start, int amount) {
		List photoList = reviewService.selectBlindList(start, amount);	
		return photoList;
	}
	
	@GetMapping(value="/detail")
	public String detail(int reviewPartyNo, @SessionAttribute(required = false) Member member, Model model) {
		int memberNo = member==null ? 0 : member.getMemberNo();
		Review r = reviewService.selectOneReview(reviewPartyNo, memberNo);
		System.out.println(r);
		if(r == null) {
			model.addAttribute("title", "문의사항 조회 실패");
			model.addAttribute("text", "이미 삭제된 게시글입니다.");
			model.addAttribute("icon", "info");
			model.addAttribute("loc", "/review/detail");
			return "common/msg";
		} else {
			model.addAttribute("r", r);
			return "review/detail";
		}
	}
	
	
}












































