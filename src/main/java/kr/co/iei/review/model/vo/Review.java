package kr.co.iei.review.model.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Alias(value="review")
public class Review {
	private int reviewPartyNo;
	private String reviewPartyWriter;
	private String reviewPartyTitle;
	private String reviewPartyContent;
	private String reviewPartyImg;
	private String reviewPartyJob;
	
}
