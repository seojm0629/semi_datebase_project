package kr.co.iei.review.model.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value="reviewBlind")
public class ReviewBlind {
	private int reviewBlindNo;
	private String reviewBlindWriter;
	private String reviewBlindTitle;
	private String reviewBlindContent;
	private String reviewBlindImg;
	private String reviewBlindJob;
	
}
