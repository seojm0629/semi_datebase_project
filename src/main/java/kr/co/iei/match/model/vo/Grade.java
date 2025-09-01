package kr.co.iei.match.model.vo;

import org.apache.ibatis.type.Alias;

import kr.co.iei.model.member.vo.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias(value="grade")
public class Grade {
	private int gradeNo;
	private String memberGrade;
	private int gradePrice;
	private int	passCount;
	private int	matchCount;
	private int	memberAge;
	private String memberGender;
	private Member memberList;
}
