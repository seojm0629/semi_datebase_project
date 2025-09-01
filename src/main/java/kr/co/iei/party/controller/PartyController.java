package kr.co.iei.party.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.iei.party.model.service.PartyService;
import kr.co.iei.party.model.vo.Party;

@Controller
@RequestMapping("/party") 
public class PartyController {

	@Autowired
	private PartyService partyService;

	// 파티 목록 페이지
	@GetMapping
	public String partyPage(Model model) {
		List<Party> parties = partyService.getAllParties();
		model.addAttribute("parties", parties);
		return "party/party"; // 
	}

	// 관리자 페이지
	@GetMapping("/admin")
	public String partyAdmin() {
		return "party/partyadmin"; 
	}

	// 사용자 페이지
	@GetMapping("/user")
	public String partyUser() {
		return "party/partyuser"; 
	}

	// 소개 페이지
	@GetMapping("/intro")
	public String intro() {
		return "party/intro"; 
	}

	// 파티 등록 폼 페이지 (GET)
	@GetMapping("/new")
	public String writeForm() {
		return "party/partyFrm"; 
	}

	// 파티 등록 처리 (POST)
	@PostMapping("/new")
	public String createParty(Party party, @RequestParam("partyThumb") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				String rootPath = "C:/Temp/upload/";
				String fileName = file.getOriginalFilename();
				File saveFile = new File(rootPath, fileName);
				saveFile.getParentFile().mkdirs();
				file.transferTo(saveFile);
				party.setPartyThumb(fileName); // DB에는 파일명 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// DB 저장
		partyService.insertParty(party);

		return "redirect:/party"; 
	}
}
