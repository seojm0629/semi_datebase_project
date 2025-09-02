package kr.co.iei.match.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@Autowired
	MatchService matchService;
	@Autowired
	MemberService memberService;
	
	@Value(value="${file.root}")
	private String root;
	
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
		int useCount = matchService.useMatchCount(m.getMemberId(), myMatchingCount);
		if(useCount == 1 ) {
				int result = matchService.matchEnroll(m);
			if(result == 1) {
				model.addAttribute("title", "신청 성공");
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
		model.addAttribute("title", "신청 실패");
		model.addAttribute("text", "신청에 실패했습니다. 다시 시도해주세요.");
		model.addAttribute("icon", "error");
		model.addAttribute("loc", "match/matchWrite");
		
		return "common/msg";
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
		model.addAttribute("list", matchList);
		return "match/management";
	}
	@PostMapping(value="/findMatch")
	@ResponseBody
	public List findMatch(Match m) {
		System.out.println(m);
		if(m.getMemberGender() == "m") {
			m.setMemberGender("f");
		}else if(m.getMemberGender() == "f") {
			m.setMemberGender("m");
		}
		List matchList = matchService.findMatch(m);
		System.out.println(matchList);
		return matchList;
	}
}
