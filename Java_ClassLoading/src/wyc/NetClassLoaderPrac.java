package wyc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 网络类加载器(形式和文件类加载器差不多，唯一不同是通过URL来传入InputStream加载类)
 * 
 * @author Yicheng Wang
 *
 */
public class NetClassLoaderPrac extends ClassLoader {
	private String rootUrl;

	public NetClassLoaderPrac(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = this.findLoadedClass(name);
		if (c != null) {
			return c;
		} else {
			ClassLoader parent = this.getParent();
			try {
				c = parent.loadClass(name);
			} catch (Exception e) {
				System.err.println("父类加载失败了！子类继续~~~");
			}
		}
		if (c != null) {
			// 此处是父类得到的Class对象
			return c;
		} else {
			byte[] classData = this.getClassData(name);
			if (classData == null)
				throw new ClassNotFoundException();
			else
				return this.defineClass(name, classData, 0, classData.length);
		}
	}

	private byte[] getClassData(String classname) {
		String path = rootUrl + "/" + classname.replace('.', '/') + ".class";
		InputStream is = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			URL url = new URL(path);
			is = url.openStream();
			byte[] buffer = new byte[1024];
			int temp = 0;
			while ((temp = is.read(buffer)) != -1) {
				baos.write(buffer, 0, temp);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
