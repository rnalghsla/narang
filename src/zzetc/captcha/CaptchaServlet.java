package zzetc.captcha;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;



public class CaptchaServlet extends HttpServlet{
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");   
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
			  		case "hani":
			  			hani(request,response);
		            break;
		            
			  		case "mc":
			  			mc(request,response);
		            break;


			  }
		 } else {
			 
		 }

	}

	private void mc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		String captcha = (String) session.getAttribute("captcha");
		System.out.println("captcha :" + captcha);
        String answer = request.getParameter("answer");
    	System.out.println("answer :"+answer);
    	
    	String responseType = request.getParameter("responseType");
    	boolean check = false;
		PrintWriter out = response.getWriter();
		// �������� contentType(mime-type: ���� : @see tomcat\conf\web.xml
		response.setContentType("text/plain");
		
		if(answer != null && answer.trim().length() > 0) {
			if(answer.equals(captcha)) {
				out.write("true");
				return;
			} else {
				out.write("false");
				return;
			}
		} else {
			//���̵� ���Է�
			out.write("required");
			return;
		}		
    	
	}

	private void hani(HttpServletRequest request, HttpServletResponse response) {
	      /*
	       *  [�ڵ����Թ��� ���� �κ�]
	       *  200 * 50 ���ش��ϴ� �̹��� ����� �����ϰ�, �ڵ����Թ��� ���� ������ �����Ѵ�.
	       *  gimp(),addNoise(),addText() �Լ��� ������ ȣ���� �� �ִ�.
	       */
	       Captcha captcha = new Captcha.Builder(200, 35)
	                                .addText() //Default�� 5���� ������ ���ĺ��� ���ڸ� ����
	                                //.addBackground() //������ ���� - Default
	                                .addBackground(new GradiatedBackgroundProducer()) //Gradiated ��׶��� ȿ�� �߰�
	                                .addNoise()// �ѹ� ȣ���� ������ �ϳ��� ������ �߰��ȴ�.
	                                .addBorder() //���� �׵θ� �� ����
	                                .build(); //�ʼ� ȣ�� �Լ�

	        response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache"); // ������ ĳ���� ����� ���� ����� ����

	        response.setDateHeader("Expires", 0);
	        response.setContentType("image/jpeg"); // ���ϰ��� image ���·� ����
	        
	        //**** �ٽ��ڵ� **************************************************
	        // �ڵ����� ���� Image�� �����Ѵ�
	        CaptchaServletUtil.writeImage(response, captcha.getImage());
	        // Captcha�� ������ �ڵ����Թ��� ���ڸ� return �޾Ƽ� String ������ �Ҵ�
	        String captcha_str = captcha.getAnswer();
	        //*************************************************************
	        
	        //����ȭ�鿡�� '����� �Է°�'�� '�ڵ����Թ�������'�� ���� �� �ֵ��� Session�� �����Ѵ�. 
	        request.getSession().setAttribute("captcha", captcha_str);

	        /*test*/System.out.println("captcha �ڵ����Թ��� ���� : " + captcha_str); 

	}

}