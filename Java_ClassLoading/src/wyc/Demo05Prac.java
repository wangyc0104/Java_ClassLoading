package wyc;

import com.bjsxt.test.FileSystemClassLoader;

/**
 * �߳���������������Ĳ���
 * @author Yicheng Wang
 *
 */
public class Demo05Prac {
	public static void main(String[] args) throws ClassNotFoundException {
		// ��������������AppClassLoader
		ClassLoader loader1 = Demo05Prac.class.getClassLoader();
		System.out.println(loader1);
		
		// ��Ŀ�����������̵߳��������������ΪAppClassLoader
		ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
		System.out.println(loader2);
		
		// �����Զ����߳�ʹ�õ��������
		Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("�˴���дpath"));
		System.out.println(Thread.currentThread().getContextClassLoader());
		
		// ʹ�õ�ǰ�߳������������������ָ����
		Class<?> c = (Class<?>) Thread.currentThread().getContextClassLoader().loadClass("com.bjsxt.test.Demo01Prac");
		System.out.println(c);
		System.out.println(c.getClassLoader());
	}
}
