package wyc;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * �Զ����ļ�ϵͳ�������
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
	 * ������������Class����
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);
		// �Ȳ�ѯ��û�м��ع�����࣬����Ѿ����أ���ֱ�ӷ��ؼ��غõ��ࡣ���û�У�������µ��ࡣ
		if (c != null) {
			// ��ʵ�������������������ִ�У���ΪClassLoader�е�loadClass���Ѿ���һ���������
			// ����findLoadedClass����������֤��
			System.out.println("�����Ѿ������ع���");
			return c;
		} else {
			ClassLoader parent = this.getParent();
			try {
				// ί�ɸ��������
				c = parent.loadClass(name);
			} catch (Exception e) {
				System.err.println(parent + "����" + name + "ʧ�ܣ�����������أ�");
			}
		}
		if (c != null) { // �˴�Ӧ�Ǹ�����������سɹ�ʱ�õ���Class����
			return c;
		} else {
			byte[] classData = getClassData(name);
			if (classData == null) {
				throw new ClassNotFoundException();
			} else {
				// ����������ֽ��������nameת����һ��Class����
				c = defineClass(name, classData, 0, classData.length);
			}
		}
		return c;
	}

	/**
	 * �����ļ�ת�����ֽ�����
	 * 
	 * @param classname
	 * @return
	 */
	private byte[] getClassData(String classname) {
		// ���ļ���·�����������ļ�����
		String path = rootDir + "/" + classname.replace('.', '/') + ".class";
		// ʹ��IO����class�ļ�ת��Ϊ�ֽ�����
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
			System.err.println("���ļ�ת�����ֽ�����ʧ���ˣ�");
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
