package kr.co.iei.member.controller;

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

import jakarta.servlet.http.HttpSession;
import kr.co.iei.model.member.service.MemberService;
import kr.co.iei.model.member.vo.Member;
import kr.co.iei.model.member.vo.MemberMoreInfo;
//import kr.co.iei.util.FileUtil;
import kr.co.iei.util.FileUtil;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Value(value = "${file.root}")
	private String root;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value = "/join-agree")
	public String joinagree() {
		return "/member/join-check";
	}
	
	@GetMapping(value = "/joinFrm")
	public String joinFrm() {
		return "member/joinFrm";
	}
	
	@PostMapping(value = "/join")
	public String join(MultipartFile memberImg, Member m,String memberEmailF, String domainTxt) {
		m.setMemberEmail(memberEmailF+"@"+domainTxt);
		String savepath = root+"/selfPhoto/";
		String filepath = fileUtil.upload(savepath, memberImg);
		
		m.setMemberImgPath(filepath);
		int result = memberService.insertMember(m);
		return "redirect:/";
	}
	
	@GetMapping(value = "/idCheck")
	@ResponseBody
	public int idCheck(String memberId) {
		Member m = memberService.selectOneMember(memberId);
		System.out.println(m);
		if(m != null) {
			return 1;
		}else {
			return 0;
		}
	}
	
	@GetMapping(value = "/loginFrm")
	public String loginFrm() {
		return "/member/loginFrm";
	}
	
	@PostMapping(value = "/login")
	public String login(Member m, HttpSession session, Model model) {
		Member member = memberService.login(m);
		if(member != null) {
			session.setAttribute("member", member);
			return "redirect:/";
		}else {
			model.addAttribute("title", "실패 메세지");
			model.addAttribute("text", "아이디 또는 비밀번호를 확인해주세요.");
			model.addAttribute("loc", "/");
			return "common/msg";
		}
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping(value = "/mypage")
	public String mypage(@SessionAttribute Member member, Model model) {
		System.out.println(member);
		int memberNo = member.getMemberNo();
		System.out.println(memberNo);
		MemberMoreInfo memberMoreInfo = memberService.searchMemberMoreINfo(memberNo);
		System.out.println(memberMoreInfo);
		model.addAttribute("memberMoreInfo", memberMoreInfo);
		return "/member/mypage";
	}
	
	@GetMapping(value = "/masterPage")
	public String masterPage() {
		return "/member/masterPage";
	}
	
	@GetMapping(value = "/creatManager")
	public String creatManager() {
		return "/member/creatManager";
	}
	
	@PostMapping(value = "/joinManager")
	public String joinManager(Member m, Model model, String memberEmailF, String domainTxt) {
		String email = memberEmailF+"@"+domainTxt;
		m.setMemberEmail(email);
		int result = memberService.joinManager(m);
		if(result == 1) {
			model.addAttribute("title", "생성 완료");
			model.addAttribute("text", "계정 생성 완료.");
			model.addAttribute("loc", "/member/masterPage");
			return "common/msg";
		}else {
			model.addAttribute("title", "생성 실패");
			model.addAttribute("text", "계정 생성에 실패하였습니다. 잠시 후 다시 시도해주십시오.");
			model.addAttribute("loc", "/member/masterPage");
			return "common/msg";
		}
	}//joinManager
	
	@ResponseBody
	@GetMapping(value = "/searchMember")
	public Member searchMember(String memberId) {
		Member m = memberService.selectOneMember(memberId);
		return m;
	}
	
	@ResponseBody
	@GetMapping(value = "/deleteMember")
	public int deleteMember(String memberId) {
		int result = memberService.deleteMember(memberId);
		return result;
	}
	
	@GetMapping(value = "/memberExit")
	public String memberExit(@SessionAttribute Member member, Model model) {
		String memberId = member.getMemberId();
		System.out.println(memberId);
		int result = memberService.deleteMember(memberId);
		if(result == 1) {
			model.addAttribute("title", "회원탈퇴 완료");
			model.addAttribute("text", "탈퇴가 왼료되었습니다.");
			model.addAttribute("loc", "/member/logout");
			return "/common/msg";
		}else {
			model.addAttribute("title", "회원탈퇴 실패");
			model.addAttribute("text", "잠시후에 다시 시도해주십시오.");
			model.addAttribute("loc", "/member/mypage");
			return "/common/msg";
		}
	}
	
	@GetMapping(value = "/memberUpdateFrm")
	public String memberUpdateFrm() {
		return "/member/memberUpdateFrm";
	}
	
	@PostMapping(value = "/memberUpdate")
	public String memberUpdate(@SessionAttribute Member member, Member m,Model model) {
		String memberId = member.getMemberId();
		m.setMemberId(memberId);
		int result = memberService.updateMember(m);
		if(result == 1) {
			model.addAttribute("title", "수정완료");
			model.addAttribute("text", "정보 수정이 완료되었습니다.");
			model.addAttribute("loc", "/member/mypage");
			return "/common/msg";
		}else {
			model.addAttribute("title", "수정실패");
			model.addAttribute("text", "잠시 후에 다시 시도해주세요.");
			model.addAttribute("loc", "/member/mypage");
			return "/common/msg";
		}
	}
	
	@GetMapping(value = "updatePlusFrm")
	public String updatePlus() {
		return "/member/updatePlusFrm";
	}
	
	@PostMapping(value = "updatePlus")
	public String updatePlus(MemberMoreInfo memberMoreInfo, Model model) {
		MemberMoreInfo more = memberService.searchMemberMoreINfo(memberMoreInfo.getMemberNo());
		if(more != null) {
			int result = memberService.updatePlusInfo(memberMoreInfo);
			if(result == 1) {
				model.addAttribute("title", "완료 메세지");
				model.addAttribute("text", "적용이 완료되었습니다.");
				model.addAttribute("loc", "/member/mypage");
				return "/common/msg";
			}else {
				model.addAttribute("title", "실패 메세지");
				model.addAttribute("text", "잠시 후에 다시 시도해주세요.");
				model.addAttribute("loc", "/member/mypage");
				return "/common/msg";
			}
		}else {
			int result = memberService.insertMoreInfo(memberMoreInfo);
			if(result == 1) {
				model.addAttribute("title", "완료 메세지");
				model.addAttribute("text", "적용이 완료되었습니다.");
				model.addAttribute("loc", "/member/mypage");
				return "/common/msg";
			}else {
				model.addAttribute("title", "실패 메세지");
				model.addAttribute("text", "잠시 후에 다시 시도해주세요.");
				model.addAttribute("loc", "/member/mypage");
				return "/common/msg";
			}
		}
	}//updatePlus
	
	@GetMapping(value = "/updateManagerFrm")
	public String updateManagerFrm() {
		return "/member/updateManagerFrm";
	}
	
	@PostMapping(value = "managerUpdate")
	public String managerUpdate(Member m, String memberEmailF, String domainTxt, Model model) {
		String memberEmail = memberEmailF+"@"+domainTxt;
		m.setMemberEmail(memberEmail);
		System.out.println(m);
		int result = memberService.managerUpdate(m);
		if(result == 1) {
			model.addAttribute("title", "완료 메세지");
			model.addAttribute("text", "수정이 완료되었습니다.");
			model.addAttribute("loc", "/member/masterPage");
			return "/common/msg";
		}else {
			model.addAttribute("title", "실패 메세지");
			model.addAttribute("text", "잠시 후 다시 시도해주세요.");
			model.addAttribute("loc", "/member/masterPage");
			return "/common/msg";
		}
	}
	
	@GetMapping(value = "/buyPage")
	public String buyPage() {
		return "/member/buyPageView";
	}
}//controller
