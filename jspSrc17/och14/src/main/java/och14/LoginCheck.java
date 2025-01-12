package och14;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//어떤 프로그램이든 간에 sub2 에 있는 것은 다 체크 할거야
@WebFilter("/sub2/*")
public class LoginCheck extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("LoginCheck doFilter...");

		// pass the request along the filter chain
		//request.gets
		//HttpServletRequest으로 형변환 시킨 이유 : ServletRequest는 session을 갖고 있지 않아서!!
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		//session 도출 방법!
		HttpSession session = httpServletRequest.getSession();
		System.out.println("LoginCheck doFilter...");
		if (session == null || session.equals("")) {
			httpServletResponse.sendRedirect("../login.jsp");
		}
		System.out.println("doFilter session !=null");
		String id = (String) session.getAttribute("id");
		System.out.println("doFilter session id->"+id);
		if (id==null || id.equals("")) {
			httpServletResponse.sendRedirect("../login.jsp");
		}
		System.out.println("doFilter session id not Null->"+id);
		
		//chain으로 가라
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
