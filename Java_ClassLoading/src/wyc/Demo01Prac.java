package wyc;

public class Demo01Prac {
	static {
//		System.out.println("��̬��ʼ��Demo01Prac");
	}

	public static void main(String[] args) throws Exception {
//		System.out.println("Demo01Prac��main����");
//		System.out.println(System.getProperty("java.class.path"));
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(Demo01Prac.class.getClassLoader().getResource("").getPath());

		// ��������
//		new AA();

		// ��������
//		System.out.println(AA.width); // ���þ�̬�����ᴥ����ĳ�ʼ��
//		System.out.println(AA.MAX); // ���ó������ᴥ����ĳ�ʼ��������ʱ�Ѿ������ڶ��ڴ��У�
//		AA[] aas = new AA[10]; // ������Ӧ������鲻�ᴥ����ĳ�ʼ��
		System.out.println(BB.width); // ������ø���ľ�̬���������ᴥ������ĳ�ʼ��
		
	}
}

class AA_Father extends Object {
	static {
		System.out.println("��̬��ʼ��AA_Father");
	}
}

class AA extends AA_Father {
	public static int width = 100; // ��̬��������̬�� field
	public static final int MAX = 1000;
	static {
		System.out.println("��̬���ʼ����AA");
		width = 300;
	}

	public AA() {
		System.out.println("������AA�Ķ���");
	}
}

class BB extends AA {
	static {
		System.out.println("��̬���ʼ����BB");
	}
}