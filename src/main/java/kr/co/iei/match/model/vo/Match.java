package kr.co.iei.match.model.vo;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias(value="match")
public class Match {
	private int matchNo;
	private String memberId;
	private String memberGrade;
	private Date matchDate;
	private String matchPass;
	private String matchField;
	private int matchAge;
	private	String	matchJob;
	private String	matchMbti;
	private String	matchHobby;
	private	String	matchEdu;
	private int		matchSalary;
	private String matchWorkType;
	private String memberGender;
}
