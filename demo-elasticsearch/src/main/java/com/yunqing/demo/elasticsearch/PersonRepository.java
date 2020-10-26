package com.yunqing.demo.elasticsearch;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yx
 * @since 2020/10/22 14:12
 */
@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, String> {


}
