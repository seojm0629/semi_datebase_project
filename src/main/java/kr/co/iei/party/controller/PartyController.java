package kr.co.iei.party.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.iei.party.model.service.PartyService;
import kr.co.iei.party.model.vo.Party;

@Controller
@RequestMapping("/party")
public class PartyController {

	@Autowired
	private PartyService partyService;

	@GetMapping
	public String partyPage(Model model) {
		List<Party> parties = partyService.getAllParties();
		model.addAttribute("parties", parties);
		return "party/party";
	}

	@GetMapping("/admin")
	public String partyAdmin() {
		return "party/partyadmin";
	}

	@GetMapping("/intro")
	public String intro() {
		return "party/intro";
	}

	@GetMapping("/new")
	public String partyFrm() {
		return "party/partyFrm";
	}

	@GetMapping("/user")
	public String partyUser(@RequestParam("partyNo") int partyNo, Model model) {
		Party party = partyService.getPartyByNo(partyNo);
		Map<String, Object> count = partyService.getPartyMemberCount(partyNo);

		int maleCount = 0;
		int femaleCount = 0;

		if (count != null) {
			if (count.get("male_count") != null) {
				maleCount = ((Number) count.get("male_count")).intValue();
			}
			if (count.get("female_count") != null) {
				femaleCount = ((Number) count.get("female_count")).intValue();
			}
		}

		model.addAttribute("party", party);
		model.addAttribute("maleCount", maleCount);
		model.addAttribute("femaleCount", femaleCount);

		return "party/partyuser";
	}

	@PostMapping("/new")
	public String createParty(Party party, @RequestParam("partyThumbFile") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				String rootPath = "C:/Temp/upload/party/";
				String fileName = file.getOriginalFilename();
				File saveFile = new File(rootPath, fileName);
				saveFile.getParentFile().mkdirs();
				file.transferTo(saveFile);
				party.setPartyThumb(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		partyService.insertParty(party);
		return "redirect:/party";
	}

	@ResponseBody
	@GetMapping("/admin/list")
	public List<Party> getPartyList(@RequestParam("type") String type) {
		return partyService.getPartyByType(type);
	}
}
