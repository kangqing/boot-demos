package com.yunqing.demo.elasticsearch;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kangqing
 * @since 2020/10/22 14:12
 */
@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, String>{

    List<Person> findByName(String name);

    Page<Person> findByName(String name, Pageable pageable);

}
