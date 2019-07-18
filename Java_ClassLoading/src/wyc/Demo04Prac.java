package wyc;

/**
 * 测试简单加密解密（取反）操作
 * 
 * @author Yicheng Wang
 */
public class Demo04Prac {
	public static void main(String[] args) throws ClassNotFoundException {
//		// 加密后的class文件，正常的类加载器无法加载，报classFormatError
//		FileSystemClassLoaderPrac loader = new FileSystemClassLoaderPrac("E:/IDE/eclipse/testspace");
//		Class<?> c = loader.loadClass("testspace.Hi");
//		System.out.println(c);
		
		DecrptClassLoaderPrac loader = new DecrptClassLoaderPrac("E:/IDE/eclipse/testspace");
		Class<?> c = loader.loadClass("testspace.Hi");
		System.out.println(c);
				
	}
}
