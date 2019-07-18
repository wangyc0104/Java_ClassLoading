package wyc;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * �����ļ�ϵͳ�м��ܺ��class�ֽ�����������(�Ȱ�λȡ���ٶ�ȡ)
 * 
 * @author Yicheng Wang
 *
 */
public class DecrptClassLoaderPrac extends ClassLoader {
	// class�ļ�·��
	private String rootDir;

	public DecrptClassLoaderPrac(String rootDir) {
		this.rootDir = rootDir;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);

		// �Ȳ�ѯ�����Ƿ񱻼��ع�������Ѿ������أ���ֱ�ӷ��ؼ��غõ��ࡣ
		if (c != null) {
			return c;
		} else {
			ClassLoader parent = this.getParent();
			try {
				c = parent.loadClass(name);
			} catch (Exception e) {
				System.err.println("�������ʧ���ˣ���������ϣ�");
			}
		}
		if (c != null) {
			// ����Ǹ�����������ص���
			return c;
		} else {
			// ������ͨ���ֽ����������ļ���
			byte[] classData = getClassData(name);
			if (classData == null) {
				throw new ClassNotFoundException();
			}
			// �˴�defineClass()������ʵ�ֵ�����ط���
			c = defineClass(name, classData, 0, classData.length);
		}
		return c;
	}

	/**
	 * �Ѽ��ܺ��class�ļ�ͨ����λȡ�����ܣ�Ȼ�󷵻�һ���ֽ�����
	 * 
	 * @param name
	 * @return
	 */
	private byte[] getClassData(String classname) {
		// path��class�ļ���ȫ�޶�·��
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
