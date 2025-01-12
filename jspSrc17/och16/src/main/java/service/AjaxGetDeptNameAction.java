package service;

import java.io.IOException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjaxGetDeptNameAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AjaxGetDeptNameAction Start...");
		try {
			//본인 필요 DB Text 가져옴 (DAO 연결)
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDao bd = BoardDao.getInstance();
			Board board = bd.select(num);
			//글쓴이만 전달해준 것
			request.setAttribute("writer", board.getWriter());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// ajax인 경우 --> 더미 Return
		//의미 없는 data return 해줘야해서 그냥 써놓은 것!
		return "ajax";
	}

}
