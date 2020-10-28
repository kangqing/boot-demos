package com.yunqing.demo.elasticsearch;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author yx
 * @since 2020/10/22 16:19
 */
public interface PersonService {

    List<Person> findAll();

    Person findById(String id);

    List<Person> findByName(String name);

    Person add(Person person);

    void delete(String id);

    Page<Person> findByName(String name, PageRequest pageRequest);

    PageResult<Person> conditionPageSearch(String field, String keyword, PageRequest pageRequest);


    PageResult<Person> conditionPageSearch(String name, int minAge, int maxAge, PageRequest pageRequest);


}
