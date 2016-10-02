package zzetc.captcha;




import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;



public class CaptchaServlet2 extends HttpServlet{
 
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
     throws ServletException, IOException{
        doPost(request, response);
   }

 

  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
     throws ServletException, IOException {
        
      /*
       *  [자동가입문자 정의 부분]
       *  200 * 50 에해당하는 이미지 사이즈를 지정하고, 자동가입방지 문자 개수를 설정한다.
       *  gimp(),addNoise(),addText() 함수는 여러번 호출할 수 있다.
       */
       Captcha captcha = new Captcha.Builder(200, 35)
                                .addText() //Default로 5개의 랜덤한 알파벳과 숫자를 생성
                                //.addBackground() //바탕색 힌색 - Default
                                .addBackground(new GradiatedBackgroundProducer()) //Gradiated 백그라운드 효과 추가
                                .addNoise()// 한번 호출할 때마다 하나의 라인이 추가된다.
                                .addBorder() //검정 테두리 선 생성
                                .build(); //필수 호출 함수

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache"); // 브라우저 캐쉬를 지우기 위한 헤더값 설정

        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg"); // 리턴값을 image 형태로 설정
        
        //**** 핵심코드 **************************************************
        // 자동가입 문자 Image를 생성한다
        CaptchaServletUtil.writeImage(response, captcha.getImage());
        // Captcha가 생성한 자동가입방지 문자를 return 받아서 String 변수에 할당
        String captcha_str = captcha.getAnswer();
        //*************************************************************
        
        //검증화면에서 '사용자 입력값'과 '자동가입방지문자'를 비교할 수 있도록 Session에 저장한다. 
        request.getSession().setAttribute("captcha", captcha_str);

        /*test*/System.out.println("captcha 자동가입방지 문자 : " + captcha_str);  
   }
}