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