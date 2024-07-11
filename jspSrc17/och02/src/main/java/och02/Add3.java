package och02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PrimitiveIterator;

/**
 * Servlet implementation class Add3
 */
public class Add3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet start... ");
		//num의 누적분을 return 목표!
		int num = Integer.parseInt(request.getParameter("num"));
		String han = request.getParameter("han");
		int sum = 0;
		for (int i = 1; i<=num; i++) {
			sum += i;
		}
		//위까지는 자바로 코딩해주는 것 (받아오는 값은 request로!)
		//browser에 display 하기 위한 방법 (보여주는 것은 response 객체에!)
		response.setContentType("text/html;charset=utf-8");
		//browser에 상세내용을 보여줌
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.printf("<h1>han Test:  %s</h1>",han);
		out.printf("<h1>1부터 %d까지 합계</h1>",num);
		out.println(sum);
		out.println("<body></html>");
		out.close();
	};

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost Start.....");
	}

}