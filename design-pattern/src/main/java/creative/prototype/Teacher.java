package creative.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 原型模式 --> 浅拷贝
 * 实现 Cloneable 重写 Object.clone()
 * @author kangqing
 * @since 2023/4/12 07:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Cloneable, Serializable {

    private String name;

    private Student student;

    @Override
    public Teacher clone() {
        Teacher teacher = null;
        try {
            teacher =  (Teacher) super.clone();
            // 深拷贝方案一：把所有引用类型克隆，直到基本数据类型
//            Student student = teacher.getStudent().clone();
//            teacher.setStudent(student);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return teacher;
    }
}
