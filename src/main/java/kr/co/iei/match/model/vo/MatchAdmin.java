package kr.co.iei.match.model.vo;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias(value="matchAdmin")
public class MatchAdmin {
	
	private int adminNo;
	private String status;
	private String memberPass1;
	private String memberPass2;
	private Date   matchDate;
	private int    matchNo1;
	private int    matchNo2;
	private String memberName1;
	private String memberName2;
	
}
