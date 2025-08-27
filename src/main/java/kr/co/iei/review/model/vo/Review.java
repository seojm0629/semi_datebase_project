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
	private int reviewNo;
	private String reviewWriter;
	private String reviewTitle;
	private String reviewContent;
	private String reviewImg;
}
