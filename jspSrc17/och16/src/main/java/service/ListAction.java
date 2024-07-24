package service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//프로그램을 최소화
		System.out.println("ListAction Service start....");
		
		// DAO Logic -> request SetAttribute에 관련된 parameter 값을 저장해줄 것이다
		request.setAttribute("totCnt", 5);
		
		//		View 명칭
//		return "listForm.jsp";
		return "list.jsp";
	}

}
