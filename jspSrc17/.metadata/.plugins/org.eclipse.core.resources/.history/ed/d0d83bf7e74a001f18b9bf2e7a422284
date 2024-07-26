package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//service --> dao인 단계
public class ListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//프로그램을 최소화해서 보여주고 있는 로직
		System.out.println("ListAction Service start....");
		
		// DAO Logic -> request SetAttribute에 관련된 parameter 값을 저장해놓을 것이다.
		//singleton 선언
		BoardDao bd = BoardDao.getInstance();
		int totCnt=0;
		
		try {
			totCnt = bd.getTotalCnt(); //38
			
			//처음에는 돌아가지 않는다. 다음부터 돌아간다. -> list.jsp에서 받아온!
			String pageNum = request.getParameter("pageNum");
			if(pageNum==null || pageNum.equals("")) { pageNum = "1"; }
			int currentPage = Integer.parseInt(pageNum);		//1
			int pageSize = 10, blockSize = 10;
			int startRow = (currentPage - 1) * pageSize +1;		//1		11
			int endRow = startRow + pageSize -1;				//10	20
			int startNum = totCnt - startRow +1;				//
			
			//Board 조회							1		10
			List<Board> list = bd.boardList(startRow,endRow);
			//										38	/	10
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);	//4
			//startpage를 해주는 이유 : 10페이지가 넘어갔을 때!
			//							1
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;	//1
			int startPage3 = (int)(currentPage-1)+1;	//1
			int endPage = startPage + blockSize -1;							//10
			//공갈 Page 방지	10	>	4
			if (endPage > pageCnt) endPage = pageCnt;
			
			//페이지를 넘겨주기 위함!!
			request.setAttribute("list", list);
			request.setAttribute("totCnt", totCnt);
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		//		View 명칭
//		return "listForm.jsp";
		return "list.jsp";
	}

}
