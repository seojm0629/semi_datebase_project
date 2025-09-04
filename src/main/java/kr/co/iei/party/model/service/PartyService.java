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

	public List<Party> getAllParties() {
		return partyDao.getAllParties();
	}

	public int insertParty(Party party) {
		return partyDao.insertParty(party);
	}

	public List<Party> getPartyByType(String type) {
		return partyDao.getPartyByType(type);
	}

	public Party getPartyByNo(int partyNo) {
		// TODO Auto-generated method stub
		return partyDao.getPartyByNo(partyNo);
	}
}
