package kr.co.iei.match.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kr.co.iei.party.controller.PartyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.iei.match.model.service.MatchService;
import kr.co.iei.match.model.vo.Grade;
import kr.co.iei.match.model.vo.Match;
import kr.co.iei.model.member.service.MemberService;
import kr.co.iei.model.member.vo.Member;

@Controller
@RequestMapping(value="/match")
public class MatchController {

    private final PartyController partyController;
	
	@Autowired
	MatchService matchService;
	@Autowired
	MemberService memberService;
	
	@Value(value="${file.root}")
	private String root;

    MatchController(PartyController partyController) {
        this.partyController = partyController;
    }
	
	@GetMapping(value="/view")
	public String view() {
		return "/match/view";
	}
	
	@GetMapping(value="/membershipFrm")
	public String membershipFrm(){
		return "/match/membershipFrm";
	}
	
	@PostMapping(value="/calcPrice")
	@ResponseBody
	public Grade calcPrice(String memberGrade , String memberAge , String matchCount , String memberGender ,String memberId, Model model) {
		
		int match = Integer.parseInt(matchCount);
		int age = Integer.parseInt(memberAge);
		Grade g = new Grade();
		g.setMatchCount(match);
		g.setMemberAge(age);
		g.setMemberGender(memberGender);
		g.setMemberGrade(memberGrade);
		g.setMemberGender(memberGender.toUpperCase());
		
		
		g.setMemberGender(g.getMemberGender().toUpperCase());
		Grade grade = matchService.calcPrice(g);
		grade.setMemberList(memberService.selectOneMember(memberId));
		
		
		return grade;
	}
	
	@GetMapping(value="/matchWrite")
	public String matchWrite() {
		return "/match/matchWrite";
	}
	
	@GetMapping(value="/enroll")
	public String matchEnroll(Match m, int myMatchingCount, Model model) {
			int result = matchService.matchEnroll(m);
			if(result == 1) {
				model.addAttribute("title", "결제 성공");
				model.addAttribute("text", "신청해주셔서 감사합니다. 좋은 인연을 만들어드리겠습니다.");
				model.addAttribute("icon", "success");
				model.addAttribute("loc", "/");
				
				return "common/msg";
			}else{
				model.addAttribute("title", "신청 실패");
				model.addAttribute("text", "신청에 실패했습니다. 다시 시도해주세요.");
				model.addAttribute("icon", "error");
				model.addAttribute("loc", "match/matchWrite");
				
				return "common/msg";
			}
		
		
	}
	
	@GetMapping(value="/management")
	public String management(Model model) {
		List matchList = matchService.selectMatchList();
		/*
		for(int i =0; i<matchList.size(); i++) {
			Match m = (Match)matchList.get(i);
			String memberImg = root+"/member/image/"+m.getMemberImgPath();
			m.setMemberImgPath(memberImg);
		}
		*/
		System.out.println(matchList);
		model.addAttribute("list", matchList);
		return "match/management";
	}
	@GetMapping(value="/findMatch")
	public String findMatch(Match m, Model model) {
		
		int matchNo1 = m.getMatchNo();
		if(m.getMemberGender().equals("남")) {
			m.setMemberGender("f");
		}else if(m.getMemberGender().equals("여")) {
			m.setMemberGender("m");
		}
		
		List matchList = matchService.findMatch(m);
		
		model.addAttribute("matchList", matchList);
		model.addAttribute("matchNo1", matchNo1);
		return "match/findMatch";
	}
	
	@PostMapping(value="/matchSuccess")
	public String matchSuccess(String matchNo1 , String matchNo2, Model model) {
		int newMatchNo1 = Integer.parseInt(matchNo1);
		int newMatchNo2 = Integer.parseInt(matchNo2);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("matchNo1", newMatchNo1);
		param.put("matchNo2", newMatchNo2);
		int result = matchService.matchComplete(param); 
		if(result == 1) {
			int updateStatus = matchService.updateStatus(param);
			if(updateStatus == 2) {
				model.addAttribute("title", "매칭 성공");
				model.addAttribute("text", "매칭 리스트에 등록되었습니다.");
				model.addAttribute("icon", "success");
				model.addAttribute("loc", "/match/management");
				return "common/msg";
			} else {
				model.addAttribute("title", "매칭 실패");
				model.addAttribute("text", "오류 발생");
				model.addAttribute("icon", "warning");
				model.addAttribute("loc", "/match/management");
				return "common/msg";
			}
		}else{
			model.addAttribute("title", "신청 실패");
			model.addAttribute("text", "존재하지 않는 신청자 입니다.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "match/management");
			
			return "common/msg";
		}
		
	}
}
