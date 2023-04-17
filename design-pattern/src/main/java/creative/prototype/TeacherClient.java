package creative.prototype;
import java.io.*;

/**
 * @author kangqing
 * @since 2023/4/12 07:31
 */
public class TeacherClient {
    public static void main(String[] args) {
        //test2();
        test3();
    }

    /**
     * 无引用数据类型，则没有浅拷贝异常
     */
    private void test1() {
//        Teacher teacher = new Teacher("李老师");
//        Teacher tea = teacher.clone();
//        tea.setName("田老师");
//        System.out.println(tea.getName());
//        System.out.println(teacher.getName());
    }

    /**
     * 老师类下面有个引用类型的学生类，这个学生类clone会出现引用数据类型
     * 原来老师 = 李老师，学生 = 小刚
     * clone老师 = 王老师，学生 = 小刚
     * 可以看到引用数据类型学生类出现浅拷贝
     */
    private static void test2() {
        Student student1 = new Student("小红");
        Teacher teacher = new Teacher("李老师", student1);

        Teacher tea = teacher.clone();
        tea.setName("王老师");
        tea.getStudent().setName("小刚");

        System.out.println("原来老师 = " + teacher.getName() + "，学生 = " + teacher.getStudent().getName());
        System.out.println("clone老师 = " + tea.getName() + "，学生 = " + tea.getStudent().getName());
    }

    /**
     * 如何解决浅拷贝的问题
     * 方案一：见 Teacher 类
     * 方案二：序列化、反序列化,引用类型的类都需要实现 Serializable
     */
    private static void test3() {
        Student student1 = new Student("小红");
        Teacher teacher = new Teacher("李老师", student1);

        Teacher tea = null;
        try {
            tea = (Teacher) deepCopy(teacher);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert tea != null;
        tea.getStudent().setName("小刚");

        System.out.println("原来老师 = " + teacher.getName() + "，学生 = " + teacher.getStudent().getName());
        System.out.println("clone老师 = " + tea.getName() + "，学生 = " + tea.getStudent().getName());
    }

    /**
     * 深拷贝，序列化，反序列化
     * @param obj
     * @return
     */
    private static Object deepCopy(Object obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }
}
