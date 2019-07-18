package wyc;

/**
 * 测试类加载器的层次结构、双亲委托机制
 * 
 * @author Yicheng Wang
 */
public class Demo02Prac {
	public static void main(String[] args) {
		System.out.println(ClassLoader.getSystemClassLoader());
		System.out.println(ClassLoader.getSystemClassLoader().getParent());
		System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
		System.out.println(System.getProperty("java.class.path")); // ClassLoader所在目录
		// 常量加载器
		System.out.println("#################################");
		String a = "wang";
		System.out.println(a.getClass().getClassLoader());
		System.out.println(a);
	}
}
