package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("ContentAction Service start....");
		//DAO logic
		//DB 를 거쳐서 num 값으로 받아온 것!!
		// 1. num , pageNum Get
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		// DAO Logic
		// 2. BoardDao bd Instance
        // 3. Board board = bd.select(num);
		try {
			BoardDao bd = BoardDao.getInstance();
			bd.readCount(num);
			Board board = bd.select(num);
			
			// 4. request 객체에 num , pageNum , board
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 4. request 객체에 num , pageNum , board
		// view
		return "content.jsp";
	}

}
