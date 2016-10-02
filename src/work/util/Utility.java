package work.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class Utility {

	/** java.lang.Math#random()�� �̿����� �� */
	public static String getSecureCode() {
		StringBuilder nos = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			nos.append((int) (Math.random() * 10));
		}
		return nos.toString();
	}

	public static String getSecureCode(int length) {
		StringBuilder nos = new StringBuilder();
		for (int i = 0; i < length; i++) {
			nos.append((int) (Math.random() * 10));
		}
		return nos.toString();
	}

	/** java.util.Random�� �̿����� �� */
	public static String getSecureCode1() {
		StringBuilder nos = new StringBuilder();
		Random rnd = new Random();

		for (int i = 0; i < 4; i++) {
			nos.append(rnd.nextInt(10)); // 0 ���� 9����
		}
		return nos.toString();
	}

	public static String getSecureCode1(int length) {
		StringBuilder nos = new StringBuilder();
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
			nos.append(rnd.nextInt(10));
		}
		return nos.toString();
	}

	public static String[] ascSort(String[] array) {
		String temp = null;
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i].compareTo(array[j]) > 0) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
			System.out.println(i + 1 + " : " + array[i]);
		}

		return array;
	}

	public static String[] descSort(String[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i].compareTo(array[j]) < 0) {
					String temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
			System.out.println(i + 1 + " : " + array[i]);
		}
		return array;
	}

	public static int[] ascSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] - array[j] > 0) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
			System.out.println(i + 1 + " : " + array[i]);
		}
		return array;
	}

	public static int[] descSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] - array[j] < 0) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
			System.out.println(i + 1 + " : " + array[i]);
		}
		return array;
	}

	public static void getSecureCodeNumberAndAlphabet1() {
		StringBuilder nos = new StringBuilder();
		for (int i = 0; i < 2; i++) {
			nos.append((int) (Math.random() * 10 + 1));
		}
		for (int i = 0; i < 2; i++) {
			nos.append((char) (Math.random() * 25 + 65));
		}
		System.out.println(nos);
	}

	public static void getSecureCodeNumberAndAlphabet1(int num) {
		StringBuilder nos = new StringBuilder();
		for (int i = 0; i < 2; i++) {
			nos.append((int) (Math.random() * 8 + 1));
		}
		for (int i = 0; i < num - 2; i++) {
			nos.append((char) (Math.random() * 25 + 65));
		}
		System.out.println(nos);
	}

	public static String getSecureCodeNumberAlphabet(int num) {
		StringBuilder nos = new StringBuilder();
		for (int i = 0; i < 2; i++) {
			nos.append((int) (Math.random() * 8 + 1));
		}
		for (int i = 0; i < num - 2; i++) {
			nos.append((char) (Math.random() * 25 + 65));
		}
		return nos.toString();
	}

	public static void getSecureCodeNumberAndAlphabet() {
		StringBuilder nos = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			nos.append((char) (Math.random() * 25 + 65));
		}
		System.out.println(nos);
	}

	public static void getSecureCodeNumberAndAlphabet(int num) {
		StringBuilder nos = new StringBuilder();
		for (int i = 0; i < num; i++) {
			nos.append((char) (Math.random() * 25 + 65));
		}
		System.out.println(nos);
	}

	/**
	 * ��4�ڸ�/��2�ڸ�/��2�ڸ� �޾ƿ���
	 * @return
	 */
	public static String getCurrentDate() {
		// // ��¥����(�⺻) : ��4�ڸ�/��2�ڸ�/��2�ڸ�
		// String pattern = "yyyy/MM/dd";
		// SimpleDateFormat date = new SimpleDateFormat(pattern);
		// // ��¥���� ��ü�� �޼��带 �̿��ؼ� ������ ��¥�� ������ ������ ���ڿ� ��ȯ
		//
		// return date.format(new Date());

		return getCurrentDate("yyyy/MM/dd");
	}
	
	/**
	 * ��4�ڸ�/��2�ڸ�/��2�ڸ�/�ð�2:��2:��2
	 * @return
	 */
	public static String getCurrentDateTime() {
		// // ��¥����(�⺻) : ��4�ڸ�/��2�ڸ�/��2�ڸ�

		return getCurrentDate("yyyy/MM/dd HH:mm:ss");
	}

	public static String getCurrentDate(String pattern) {
		SimpleDateFormat date = new SimpleDateFormat(pattern);
		return date.format(new Date());
	}

	public static String getCurrentDate(String pattern, Locale locale) {
		// SimpleDateFormat date = new SimpleDateFormat(pattern, locale);
		return new SimpleDateFormat(pattern, locale).format(new Date());
	}

	/**
	 * @see java.text.NumberFormat
	 * 
	 * @param data
	 * @return
	 */
	public static String convertNumber(long data) {
		return NumberFormat.getInstance().format(data);
	}

	public static String convertCurrency(long data1) {
		return NumberFormat.getCurrencyInstance().format(data1);
	}

	public static String convertCurrency(long data1, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(data1);
	}

	/**
	 * ��й�ȣ�� 2�ڸ��� �����ְ� �������� * ǥ��
	 * @param pass
	 * @return
	 */
	public static String convertSecureCode(String pass) {
		StringBuilder nos = new StringBuilder();
		
		if (pass != null && pass.length() > 2) {
			nos.append(pass.substring(0, 2));
			for (int i = 0; i < pass.length() - 2; i++) {
				nos.append("*");
			}
		}
		return nos.toString();
	}

	/**
	 * �����ø����̼��� get ����� �ѱ� ���ڵ� ��ȯ �޼���
	 * 
	 * @param args
	 */
	public static String toKor(String data) {
		try {
			return new String(data.getBytes("8859_1"), "euc-kr");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	//---------------------------------------------------------------------------------------//
    public static void writeImage(HttpServletResponse response, BufferedImage bi) {
        
        response.setHeader("Cache-Control", "private,no-cache,no-store");
        response.setContentType("image/png");
         
        try {
            writeImage(response.getOutputStream(), bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
 
    public static void writeImage(OutputStream os, BufferedImage bi) {
         
        try {
            ImageIO.write(bi, "png", os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
    
	public static void main(String[] args) {
		System.out.println("## java.lang.Math#random()�� �̿����� ��");
		System.out.println(Utility.getSecureCode());
		System.out.println(Utility.getSecureCode(6));
		System.out.println();

		System.out.println("## java.util.Random�� �̿����� ��");
		System.out.println(Utility.getSecureCode1());
		System.out.println(Utility.getSecureCode1(6));
		System.out.println();

		String names[] = { "���ϴ�", "�ڿ���", "������", "������", "���̺�" };
		System.out.println("## ����");
		for (int i = 0; i < names.length; i++) {
			System.out.println(i + 1 + " : " + names[i]);
		}
		System.out.println();

		System.out.println("## �̸� �ø�����");
		Utility.ascSort(names);
		System.out.println();

		System.out.println("## �̸� ��������");
		Utility.descSort(names);
		System.out.println();

		int num[] = { 9, 1, 6, 2, 4, 3, 7 };
		System.out.println("## ���� �ø�����");
		Utility.ascSort(num);
		System.out.println();

		System.out.println("## ���� ��������");
		Utility.descSort(num);
		System.out.println();

		System.out.println("## ���� ���� + �빮�� 4�� ");
		getSecureCodeNumberAndAlphabet1();
		System.out.println();

		System.out.println("## ���� ���� 2�� + �빮�� 3 �� ");
		getSecureCodeNumberAndAlphabet1(5);
		System.out.println();

		System.out.println("## ���� �빮�� 4�� ���ĺ�");
		Utility.getSecureCodeNumberAndAlphabet();
		System.out.println();

		System.out.println("## ���� �빮�� 6�� ���ĺ�");
		Utility.getSecureCodeNumberAndAlphabet(6);
		System.out.println();
	}

}
