package service;

import java.io.IOException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. num , pageNum, writer ,  email , subject , passwd , content   Get
		String pageNum = request.getParameter("pageNum");
//		int num = Integer.getInteger(request.getParameter("num"));
		String writer = request.getParameter("writer");
//		String email = request.getParameter("email");
//		String subject = request.getParameter("subject");
//		String passwd = request.getParameter("passwd");
//		String content = request.getParameter("content");

		try {
			
			// 2. Board board 생성하고 Value Setting
			Board board = new Board();
			board.setNum(Integer.parseInt(request.getParameter("num")));
			board.setWriter(writer);
			board.setEmail(request.getParameter("email"));
			board.setSubject(request.getParameter("subject"));
			board.setPasswd(request.getParameter("passwd"));
			board.setContent(request.getParameter("content"));
			board.setIp(request.getRemoteAddr());
			// 3. BoardDao bd Instance
			BoardDao bd = BoardDao.getInstance();
			int result = bd.update(board);
			// 4. request 객체에 result, num , pageNum 
			request.setAttribute("result", result);
			request.setAttribute("num", board.getNum());
			request.setAttribute("pageNum", pageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    // 5.updatePro.jsp Return
		return "updatePro.jsp";
	}

}
