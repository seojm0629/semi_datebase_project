package kr.co.iei.party.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.iei.party.model.dao.PartyDao;
import kr.co.iei.party.model.vo.Party;

@Service
public class PartyService {

	@Autowired
	private PartyDao partyDao;

	// 모든 파티 불러오기
	public List<Party> getAllParties() {
		return partyDao.getAllParties();
	}
}
