package creative.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 学生类，也实现Cloneable
 * @author kangqing
 * @since 2023/4/12 07:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Cloneable, Serializable {
    private String name;

    @Override
    public Student clone() {
        Student stu = null;
        try {
            stu = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }
}
