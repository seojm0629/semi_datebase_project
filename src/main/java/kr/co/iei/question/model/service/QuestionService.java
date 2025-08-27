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
		// reqPage : 사용자가 요청한 페이지 번호
		// 한 페이지에 보여줄 게시물 수(지정) : 10개
		int numPerPage = 10;
				
		// 쿼리문은 변경되지 않고 시작번호와 끝번호가 변경
		// 사용자가 요청한 페이지에 따라서 게시물의 시작번호와 끝번호를 계산
		int end = numPerPage*reqPage; //ex) 10 * 1 = 10
		int start = end - numPerPage +1 ; //ex) 10 - 10 + 1 = 1
				
		//Mybaits에 매개변수를 전달할 때는 1개의 객체를 묶어서 전달
		// 전달하고 싶은 데이터를 담을 vo가 있으면 vo로 묶어서 전달
		// 담을 vo가 없으면 -> 1) vo를 생성  2) HashMap사용
		HashMap<String, Object> param = new HashMap<String, Object>(); 
		param.put("start", start);	 // 1 전달
		param.put("end", end);		 // 10 전달
				
		//페이지네비게이션을 제작(사용자가 클릭해서 다른 페이지를 요청할 수 있는 요소)
		//페이지 네비를 Service에서 만드는 이유 -> 총 게시물 수, reqPage, numPerPage 등과 같은 데이터가 필요함
		//전체 게시물 수를 조회 -> 전체 게시물 수를 알아야 numPerPage와 연산을 통해서 총 페이지 수 계산
				
		int totalCount = questionDao.selectQuestionTotalCount();
		//System.out.println("총 게시물 수 : "+totalCount);
				
		// 총 페이지 수 계산
		/* 방법1) 
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;			
		} else {
			totalPage = totalCount/numPerPage+1;
		}
		*/
				
		/* 방법2) 
		int totalPage = totalCount / numPerPage;
		if(totalCount%numPerPage != 0) {
			totalPage += 1;
		}
		*/
		int totalPage = (int)Math.ceil(totalCount/(double)numPerPage);
		
		//System.out.println("totalPage : "+totalPage);
				
		// 페이지 네비게이션 길이 지정
		int pageNaviSize = 5;
				
		// 페이지 네비 시작번호 지정
		//reqPage 1~5 : 1 2 3 4 5
		//reqPage 6~10 : 6 7 8 9 10
		//reqPage 11~15 : 11 12 13 14 15
		//....
		
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
