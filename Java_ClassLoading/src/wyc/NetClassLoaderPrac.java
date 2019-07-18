package wyc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * �����������(��ʽ���ļ����������࣬Ψһ��ͬ��ͨ��URL������InputStream������)
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
				System.err.println("�������ʧ���ˣ��������~~~");
			}
		}
		if (c != null) {
			// �˴��Ǹ���õ���Class����
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
