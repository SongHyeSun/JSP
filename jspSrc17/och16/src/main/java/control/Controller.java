package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CommandProcess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.swing.text.AbstractDocument.Content;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//controller를 실행하면 무조건 Map이 따라다닌다.
	//String url , Object 객체로 움직인다.
	private Map<String, Object> commandMap = new HashMap<String, Object>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    
    //init은 응애하는 느낌
	public void init(ServletConfig config) throws ServletException {
		// web.xml에서 propertyConfig에 해당하는 init-param 의 값을 읽어옴
		//init parameter  -> config parameter 명 : /WEB-INF/command.properties (web.xml)
		String props = config.getInitParameter("config");
		// /WEB-INF/command.properties
		System.out.println("1. init String props=>"+props); 	// /och16/com
		
		//key(url) = value(logic) 값!! Map 방식의 일종
		Properties  pr = new Properties();
		FileInputStream f = null;
		
		try {
			String configFilePath = config.getServletContext().getRealPath(props);
			System.out.println("2. init String configFilePath=>"+ configFilePath);
			//configFilePath가 file 로 바꿔준다.
			f = new FileInputStream(configFilePath);
			//Memory Up (file로 업로드 시켜놓은 것)
			pr.load(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException ex) {
					System.out.println("ex.getMessage()-> "+ex.getMessage());
				}
		}
		
//		key -> /list.do 와 /Content.do 
//		 key	=	value
//		/list.do=service.ListAction
//		/content.do=service.ContentAction
		Iterator keyIter = pr.keySet().iterator();
		
		while (keyIter.hasNext()) {
			// /list.do가 들어온다
			String command = (String) keyIter.next();
			//	get 방식이니까 service.ListAction이 들어온다.
			String className = pr.getProperty(command);
			System.out.println("3. init Command =>" + command);	// /och16/com
			System.out.println("4. itit className=>"+className);
			
			try {
//				CommandProcess la = new ListAction();  ==> 이렇게 사용하는 것이 가장 쉬운 방법!
//				소멸 Class
//				Class commandClass = Class.forName(className);
//				Object connandInstance = commandClass.newInstance();
//				new Class	--> 제네릭의 요점은 클래스 유형을 모른다.
				Class<?> commandClass = Class.forName(className);	//해당 문자열을 클래스로 만든다.
				//class를 가지고 instance를 만들어준다. service.ListAction에 대한 instance
				CommandProcess commandInstance = 
						(CommandProcess)commandClass.getDeclaredConstructor().newInstance();
				
				//		list.do		service.ListAction
				//	content.do		service.ContentAction
				commandMap.put(command, commandInstance);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// client에서 보내온 객체를 그대로 선택한다 -> requdstServletPro를 걸어두어서
		//get 방식이던, post 방식이던 무조건 실행하게 해 두었다.
		requestServletPro(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// client에서 보내온 객체를 그대로 선택한다 -> requdstServletPro를 걸어두어서
			//get 방식이던, post 방식이던 무조건 실행하게 해 두었다.
		requestServletPro(request, response);
	}
	
	protected void requestServletPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String view = null;
		CommandProcess com = null;
		String command = request.getRequestURI();
		// ==위 아래가 같은 것!
		System.out.println("1. requdstServletPro command=>"+command); // /och16/list.do
		
		//command를 얘만큼 잘라내면 list.do가 온다
		command = command.substring(request.getContextPath().length());
		System.out.println("2. requdstServletPro command substring =>"+command); // /och16/com
		
		try {
			//	service.ListAction Instance
			com = (CommandProcess) commandMap.get(command);
			System.out.println("3. requdstServletPro command=>"+command);	// /och16/com
			// 해시코드가 있는 것은 instance!!
			System.out.println("4. requdstServletPro com=>"+com);	// /och16/com
			//	com --> service.ListAction@32a22787
			//1. view를 받아서 (list.jsp)
			view = com.requestPro(request, response);
			System.out.println("5. requdstServletPro view =>"+view);	// /och16/com
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		//Ajax String을 포함하고 있으면
		if (command.contains("ajaxGet")) {
			//ajax가 여러개면 switch문으로 해줄 수 있다!!
			System.out.println("ajaxGet String ->"+command);
			String writer = (String) request.getAttribute("writer");
			//나를 호출ㅎ는 화면에 띄어주면 된다.
			PrintWriter pw = response.getWriter();
			//writer 가 list.jsp의 function(writer)에 들어가진다.
			pw.write(writer);
			pw.flush();
			
			//일반적인 경우
		} else {
			//2. 페이지를 이동시켜주겠다
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
		
	}
}
