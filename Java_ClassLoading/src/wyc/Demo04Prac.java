package wyc;

/**
 * ���Լ򵥼��ܽ��ܣ�ȡ��������
 * 
 * @author Yicheng Wang
 */
public class Demo04Prac {
	public static void main(String[] args) throws ClassNotFoundException {
//		// ���ܺ��class�ļ�����������������޷����أ���classFormatError
//		FileSystemClassLoaderPrac loader = new FileSystemClassLoaderPrac("E:/IDE/eclipse/testspace");
//		Class<?> c = loader.loadClass("testspace.Hi");
//		System.out.println(c);
		
		DecrptClassLoaderPrac loader = new DecrptClassLoaderPrac("E:/IDE/eclipse/testspace");
		Class<?> c = loader.loadClass("testspace.Hi");
		System.out.println(c);
				
	}
}
