package wyc;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 加载文件系统中加密后的class字节码的类加载器(先按位取反再读取)
 * 
 * @author Yicheng Wang
 *
 */
public class DecrptClassLoaderPrac extends ClassLoader {
	// class文件路径
	private String rootDir;

	public DecrptClassLoaderPrac(String rootDir) {
		this.rootDir = rootDir;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);

		// 先查询该类是否被加载过，如果已经被加载，则直接返回加载好的类。
		if (c != null) {
			return c;
		} else {
			ClassLoader parent = this.getParent();
			try {
				c = parent.loadClass(name);
			} catch (Exception e) {
				System.err.println("父类加载失败了，子类继续上！");
			}
		}
		if (c != null) {
			// 这个是父类加载器加载的类
			return c;
		} else {
			// 由子类通过字节数组进行类的加载
			byte[] classData = getClassData(name);
			if (classData == null) {
				throw new ClassNotFoundException();
			}
			// 此处defineClass()是子类实现的类加载方法
			c = defineClass(name, classData, 0, classData.length);
		}
		return c;
	}

	/**
	 * 把加密后的class文件通过按位取反解密，然后返回一个字节数组
	 * 
	 * @param name
	 * @return
	 */
	private byte[] getClassData(String classname) {
		// path是class文件的全限定路径
		String path = rootDir + "/" + classname.replace('.', '/') + ".class";
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try {
			fis = new FileInputStream(path);
			baos = new ByteArrayOutputStream();
			int temp = -1;
			while ((temp = fis.read()) != -1) {
				baos.write(temp ^ 11111111);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null)
					baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
