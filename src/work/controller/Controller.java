package work.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jj.play.ns.nl.captcha.Captcha;
import nl.captcha.servlet.CaptchaServletUtil;
import work.model.service.UserService;
import work.util.Utility;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private UserService userService = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
	      doGet(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String action = request.getParameter("action");
		 if(action != null){
			  switch(action) {
			  		case "login":
			  			login(request,response);
		            break;
		            
			  		case "naverDupliUser":
			  			naverDupliUser(request,response);
		            break;
		            
			  		case "kakaoDupliUser":
			  			kakaoDupliUser(request,response);
		            break;
		            
			  		case "join":
			  			join(request,response);
		            break;
		            
			  		case "selectPw":
			  			selectPw(request,response);
			  		break;
			  		
			  		case "selectPw2":
			  			selectPw2(request,response);
			  		break;
			  		
			  		case "selectId":
			  			selectId(request,response);
			  		break;
			  }
		 } else {
			 
		 }

	}

	/**
	 * 아이디찾기
	 * @param request 요청
	 * @param response 응답
	 * @throws IOException 예외 
	 * @throws ServletException 예외 
	 */
	private void selectId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mobile = request.getParameter("mobile");
		String name = request.getParameter("name");
		String uId = userService.selectId(mobile, name);
		System.out.println("uId :"+uId);
		if(uId != null) {
			request.setAttribute("uId", uId);
			request.getRequestDispatcher("user/selectId2.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "해당된 회원이 없습니다");
			request.getRequestDispatcher("user/selectId.jsp").forward(request, response);
		}
	}

	/**
	 * 비밀번호찾기 2단계
	 * @param request 요청
	 * @param response 응답
	 * @throws ServletException 예외
	 * @throws IOException 예외
	 */
	private void selectPw2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uId = request.getParameter("uId");
		String uPw = request.getParameter("newUpw1");
		int check = userService.selectPw2(uId, uPw);
		if ( check > -1) {
			request.setAttribute("result", 1);
			request.getRequestDispatcher("user/selectPw2.jsp").forward(request, response);
		}else {
			request.setAttribute("error", "변경 실패");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * 비밀번호 찾기 1단계
	 * @param request 요청
	 * @param response 응답
	 * @throws ServletException 예외
	 * @throws IOException 예외
	 */
	private void selectPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uId = request.getParameter("uId");
		String name = request.getParameter("name");
		int check = userService.selectPw(uId, name);
		if(check > 0) {
			request.setAttribute("uId", uId);
			request.getRequestDispatcher("user/selectPw2.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "변경 실패");
			request.getRequestDispatcher("user/selectPw.jsp").forward(request, response);
		}
	}

	/**
	 * 일반 회원 가입
	 * @param request 요청
	 * @param response 응답
	 */
	private void join(HttpServletRequest request, HttpServletResponse response) {
		try {
			ArrayList saveFiles = new ArrayList();
			String uImg = "";
			String uploadPath = "C:\\00.practice\\workspace\\07.ajax\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\02_narang\\images\\user";
			int size = 10 * 1024 * 1024;
			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
	
			String uId = multi.getParameter("uId");
			String uPw = multi.getParameter("uPw");
			String name = multi.getParameter("name");
			String gender = multi.getParameter("gender");
			String age = multi.getParameter("age");
			String mobile = multi.getParameter("mobile");
			String email = multi.getParameter("email");
			
			Enumeration files = multi.getFileNames();
				if (files.hasMoreElements()) {
					String name1 = (String) files.nextElement();
					uImg = multi.getFilesystemName(name1);
					saveFiles.add(multi.getFilesystemName(name1)); // 올려진 파일명
				}
			
			System.out.println("controller uId :" + uId);
			System.out.println("controller uPw :" + uPw);
			System.out.println("controller name :" + name);
			System.out.println("controller gender :" + gender);
			System.out.println("controller age :" + age);
			System.out.println("controller mobile :" + mobile);
			System.out.println("controller uImg :" + uImg);
			
			int check = userService.insertUser(uId, uPw, name, gender, age, mobile, email, uImg);
				if(check > 0) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			} catch (IOException ioe) {
			System.out.println(ioe);
			} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	/**
	 * 네이버회원 회원 중복 조회 / db insert
	 * @param request 요청
	 * @param response 응답
	 * @throws IOException 예외 
	 * @throws ServletException 예외 
	 */
	private void naverDupliUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String uId = request.getParameter("uId");
		String uImg = request.getParameter("uImg");
		System.out.println("controller uid : " + uId);
		boolean check = userService.dupliUser(uId);
		System.out.println("controller check : " + check);
		
		if(check) {
			int check3 = userService.updateUserImg(uImg, uId);
			if (check3 > 0) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			String name = java.net.URLDecoder.decode(request.getParameter("name"), "UTF-8");
			String gender = request.getParameter("gender");
			String age = request.getParameter("age");
			String email = request.getParameter("email");
			int check2 = userService.insertUser(uId, name, gender, age, email, uImg);
			if(check2 > 0){
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	}
	
	/**
	 * 카카오 회원 중복 조회 / db insert
	 * @param request 요청
	 * @param response 응답
	 * @throws ServletException 예외
	 * @throws IOException 예외
	 */
	private void kakaoDupliUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String uId = request.getParameter("uId");
		String uImg = request.getParameter("uImg");
		System.out.println("controller uid : " + uId);
		System.out.println("controller uImg : " + uImg);
		boolean check = userService.dupliUser(uId);
		System.out.println("controller check : " + check);
		if(check) {
			
			int check3 = userService.updateUserImg(uImg, uId);
			System.out.println("controller check3 : " + check3);
			if (check3 > 0) {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			String name = request.getParameter("name");
			if(name != null){
				name = new String(name.getBytes("8859_1"),"UTF-8");
			}
			System.out.println("name : " + name);
			int check2 = userService.insertUser(uId, name, uImg);
			if(check2 > 0){
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	}

	/**
	 * 로그인
	 * @param request 요청
	 * @param response 응답
	 * @throws ServletException 예외
	 * @throws IOException 예외
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String uId = request.getParameter("uId");
		String uPw = request.getParameter("uPw");
		System.out.println("controller uID : "+uId);
		HashMap<String, String> map = userService.login(uId, uPw);
		
		if(map != null){
			System.out.println(map);
			session.setAttribute("uId", uId);
			session.setAttribute("grade", map.get("grade"));
			session.setAttribute("name", map.get("name"));
			if(map.get("grade").equals("X")){
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}
