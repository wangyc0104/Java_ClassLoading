package wyc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ���ܹ����ࣨ��Դ�ļ��ֽڰ�λȡ��������ļ���
 * 
 * @author Yicheng Wang
 */
public class EncrptUtilPrac {
	public static void main(String[] args) {
		encrypt("E:/IDE/eclipse/testspace/encrypted/Hi.class","E:/IDE/eclipse/testspace/decrypted/Hi.class");
	}

	public static void encrypt(String src, String dest) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dest);
			int temp = -1;
			while ((temp = fis.read()) != -1) {
				fos.write(temp ^ 11111111); // ^���λ�����
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
