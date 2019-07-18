package wyc;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义文件系统类加载器
 * 
 * @author Yicheng Wang
 *
 */
public class FileSystemClassLoaderPrac extends ClassLoader {
	private String rootDir;

	public FileSystemClassLoaderPrac(String rootDir) {
		this.rootDir = rootDir;
	}

	/**
	 * 根据类名返回Class对象
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);
		// 先查询有没有加载过这个类，如果已经加载，则直接返回加载好的类。如果没有，则加载新的类。
		if (c != null) {
			// 事实上这个条件语句根本不会执行，因为ClassLoader中的loadClass中已经有一个条件语句
			// 经过findLoadedClass方法检查后验证了
			System.out.println("该类已经被加载过！");
			return c;
		} else {
			ClassLoader parent = this.getParent();
			try {
				// 委派给父类加载
				c = parent.loadClass(name);
			} catch (Exception e) {
				System.err.println(parent + "加载" + name + "失败，子类继续加载！");
			}
		}
		if (c != null) { // 此处应是父类加载器加载成功时得到的Class对象
			return c;
		} else {
			byte[] classData = getClassData(name);
			if (classData == null) {
				throw new ClassNotFoundException();
			} else {
				// 将解析后的字节数组根据name转换成一个Class对象
				c = defineClass(name, classData, 0, classData.length);
			}
		}
		return c;
	}

	/**
	 * 将类文件转换成字节数组
	 * 
	 * @param classname
	 * @return
	 */
	private byte[] getClassData(String classname) {
		// 类文件的路径名（包括文件名）
		String path = rootDir + "/" + classname.replace('.', '/') + ".class";
		// 使用IO流将class文件转换为字节数组
		InputStream is = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			is = new FileInputStream(path);
			byte[] buffer = new byte[1024];
			int temp = 0;
			while ((temp = is.read(buffer)) != -1) {
				baos.write(buffer, 0, temp);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			System.err.println("类文件转换成字节数组失败了！");
			return null;
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
