package wyc;

/**
 * 测试自定义的FileSystemClassLoaderPrac
 * 
 * @author Yicheng Wang
 */
public class Demo03Prac {
	public static void main(String[] args) throws Exception {
		FileSystemClassLoaderPrac loader1 = new FileSystemClassLoaderPrac("E:/IDE/eclipse/testspace");
		FileSystemClassLoaderPrac loader2 = new FileSystemClassLoaderPrac("E:/IDE/eclipse/testspace");
		
		Class<?> c1 = loader1.loadClass("testspace.Hi");
		Class<?> c2 = loader1.loadClass("testspace.Hi");
		Class<?> c3 = loader2.loadClass("testspace.Hi");
		Class<?> c4 = loader2.loadClass("java.lang.String");
		Class<?> c5 = loader2.loadClass("com.bjsxt.test.Demo01");
		
		System.out.println(c1.hashCode());
		System.out.println(c2.hashCode());
		System.out.println(c3.hashCode());
		System.out.println(c4.hashCode());
		System.out.println(c5.hashCode());
	}
}
