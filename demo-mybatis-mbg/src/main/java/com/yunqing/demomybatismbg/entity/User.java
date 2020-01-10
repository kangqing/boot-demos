package com.yunqing.demomybatismbg.entity;

public class User {
    /**
     * 	主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 	姓名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 	年龄
     *
     * @mbggenerated
     */
    private Integer age;

    /**
     * 	邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 	状态
     *
     * @mbggenerated
     */
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}