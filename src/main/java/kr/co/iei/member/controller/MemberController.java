package kr.co.iei.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import kr.co.iei.model.member.service.MemberService;
import kr.co.iei.model.member.vo.Member;
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
		m.setMemberEmail(memberEmailF+domainTxt);
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
}//controller
