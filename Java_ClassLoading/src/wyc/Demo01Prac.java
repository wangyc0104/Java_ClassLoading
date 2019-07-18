package wyc;

public class Demo01Prac {
	static {
//		System.out.println("静态初始化Demo01Prac");
	}

	public static void main(String[] args) throws Exception {
//		System.out.println("Demo01Prac的main方法");
//		System.out.println(System.getProperty("java.class.path"));
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(Demo01Prac.class.getClassLoader().getResource("").getPath());

		// 主动引用
//		new AA();

		// 被动引用
//		System.out.println(AA.width); // 调用静态变量会触发类的初始化
//		System.out.println(AA.MAX); // 调用常量不会触发类的初始化（编译时已经存在于堆内存中）
//		AA[] aas = new AA[10]; // 调用相应类的数组不会触发类的初始化
		System.out.println(BB.width); // 子类调用父类的静态变量，不会触发子类的初始化
		
	}
}

class AA_Father extends Object {
	static {
		System.out.println("静态初始化AA_Father");
	}
}

class AA extends AA_Father {
	public static int width = 100; // 静态变量，静态域 field
	public static final int MAX = 1000;
	static {
		System.out.println("静态块初始化类AA");
		width = 300;
	}

	public AA() {
		System.out.println("创建类AA的对象");
	}
}

class BB extends AA {
	static {
		System.out.println("静态块初始化类BB");
	}
}