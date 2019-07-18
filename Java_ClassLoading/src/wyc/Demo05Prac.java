package wyc;

import com.bjsxt.test.FileSystemClassLoader;

/**
 * 线程上下文类加载器的测试
 * @author Yicheng Wang
 *
 */
public class Demo05Prac {
	public static void main(String[] args) throws ClassNotFoundException {
		// 本类的类加载器是AppClassLoader
		ClassLoader loader1 = Demo05Prac.class.getClassLoader();
		System.out.println(loader1);
		
		// 项目中类所在主线程的上下文类加载器为AppClassLoader
		ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
		System.out.println(loader2);
		
		// 可以自定义线程使用的类加载器
		Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("此处填写path"));
		System.out.println(Thread.currentThread().getContextClassLoader());
		
		// 使用当前线程上下文类加载器加载指定类
		Class<?> c = (Class<?>) Thread.currentThread().getContextClassLoader().loadClass("com.bjsxt.test.Demo01Prac");
		System.out.println(c);
		System.out.println(c.getClassLoader());
	}
}
