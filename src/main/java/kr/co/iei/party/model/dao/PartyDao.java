package kr.co.iei.party.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.iei.party.model.vo.Party;

@Mapper
public interface PartyDao {

	List<Party> getAllParties();

	int insertParty(Party party);

	List<Party> getPartyByType(String type);

	Party getPartyById(int partyNo);

	Party selectPartyByNo(int partyNo);

	Map<String, Object> selectPartyMemberCount(int partyNo);

}
