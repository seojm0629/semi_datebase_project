package kr.co.iei.pay.model.vo;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import kr.co.iei.model.member.vo.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias(value="pay")
public class pay {
	private int payNo;
	private String memberId;
	private int payPrice;
	private String payName;
	private String payState;
	private Date   payDate;
	private Member myMatchingCount;
	private Member myPassCount;
}
