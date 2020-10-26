package com.yunqing.demo.elasticsearch;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author yx
 * @since 2020/10/22 14:09
 */
@Data
@Document(indexName = "db-index", shards = 5, replicas = 1)
public class Person {

    private String id;

    private String name;

    private Integer age;

    private String mobile;

    private String email;
}
