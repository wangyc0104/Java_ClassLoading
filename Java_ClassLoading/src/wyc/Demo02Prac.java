package wyc;

/**
 * ������������Ĳ�νṹ��˫��ί�л���
 * 
 * @author Yicheng Wang
 */
public class Demo02Prac {
	public static void main(String[] args) {
		System.out.println(ClassLoader.getSystemClassLoader());
		System.out.println(ClassLoader.getSystemClassLoader().getParent());
		System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
		System.out.println(System.getProperty("java.class.path")); // ClassLoader����Ŀ¼
		// ����������
		System.out.println("#################################");
		String a = "wang";
		System.out.println(a.getClass().getClassLoader());
		System.out.println(a);
	}
}
