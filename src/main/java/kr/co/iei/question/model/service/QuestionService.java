package kr.co.iei.question.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.co.iei.question.model.dao.QuestionDao;
import kr.co.iei.question.model.vo.QuestionListData;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public QuestionListData selectQuestionList(int reqPage) {
	
		int numPerPage = 10;
				
		
		int end = numPerPage*reqPage; //ex) 10 * 1 = 10
		int start = end - numPerPage +1 ; //ex) 10 - 10 + 1 = 1
		
				
		
		HashMap<String, Object> param = new HashMap<String, Object>(); 
		param.put("start", start);	 // 1 전달
		param.put("end", end);		 // 10 전달
				
		
				
		int totalCount = questionDao.selectQuestionTotalCount();
		
		int totalPage = (int)Math.ceil(totalCount/(double)numPerPage);
		
		
		int pageNaviSize = 5;
				
		
		
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		// 페이지 네비 html을 생성
		String pageNavi = "<ul class='pagination circle-style'>";
		// 이전버튼(1페이지로 시작하는게 아닌경우에만 이전 버튼 생성
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/question/list?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'> chevron_left </span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		for(int i=0; i<pageNaviSize; i++) {
			pageNavi += "<li>";
			if(pageNo == reqPage) {
				pageNavi += "<a class='page-item active-page' href='/question/list?reqPage="+pageNo+"'>";
			} else {
				pageNavi += "<a class='page-item' href='/question/list?reqPage="+pageNo+"'>";				
			}
			pageNavi += pageNo;
			pageNavi += "</a>";
			pageNavi += "</li>";
			
			pageNo++;
			// 페이지를 제작하다가 마지막 페이지를 출력했으면 더이상 반복하지 않고 반복문 종료
			if(pageNo > totalPage) {
				break;
			}
		}
		// 다음버튼(최종 페이지를 출력하지 않은 경우)
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/question/list?reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'> chevron_right </span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>"; 
				
		//System.out.println(pageNavi);
			
		List list = questionDao.selectQuestionList(param);
			
		// 되돌려주고싶은 데이터가 List와 String 
		// 언어에서 메소드(함수)의 수행결과는 반드시 하나의 타입으로 리턴
		// -> 객체를 생성해서 사용(여러 데이터를 하나로 묶는 객체)
		
		QuestionListData qld = new QuestionListData(list, pageNavi);
		return qld;
		
	}
}
