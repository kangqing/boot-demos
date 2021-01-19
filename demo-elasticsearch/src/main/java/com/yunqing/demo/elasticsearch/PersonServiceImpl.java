package com.yunqing.demo.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;


/**
 * @author kangqing
 * @since 2020/10/22 16:20
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    /**
     * ES模板
     */
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;


    /**
     * 查询所有
     * @return 返回所有Person
     */
    @Override
    public List<Person> findAll() {
        Iterable<Person> iterable = personRepository.findAll();
        List<Person> resList = new ArrayList<>();
        iterable.forEach(resList::add);
        return resList;
    }

    /**
     * 根据id查询
     * @param id id
     * @return 返回结果
     */
    @Override
    public Person findById(String id) {
        return personRepository.findById(id).orElse(null);
    }

    /**
     * 根据名字查询
     * @param name 名字
     * @return 结果
     */
    @Override
    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    /**
     * 添加一条person数据
     * @param person 添加的person
     * @return 返回添加的person
     */
    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }

    /**
     * 根据id删除
     * @param id id
     */
    @Override
    public void delete(String id) {
        personRepository.deleteById(id);
    }

    /**
     * 根据name查询并分页
     * @param name 名字
     * @param pageRequest 分页条件
     * @return 结果
     */
    @Override
    public Page<Person> findByName(String name, PageRequest pageRequest) {
        return personRepository.findByName(name, pageRequest);
    }

    /**
     * 单条件分页查询
     * @param field 字段
     * @param keyword 字段值
     * @param pageRequest 分页条件
     * @return 返回结果
     */
    @Override
    public PageResult<Person> conditionPageSearch(String field, String keyword, PageRequest pageRequest) {
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(regexpQuery(field, ".*" + keyword + ".*"))
                .withPageable(pageRequest)
                //这里的排序如果使用id,需要id.keyword
                .withSort(new FieldSortBuilder("id.keyword").order(SortOrder.ASC))
                .build();
        SearchHits<Person> search = elasticsearchRestTemplate.search(searchQuery, Person.class, IndexCoordinates.of("person"));
        return getPersonPageResult(search);

    }

    /**
     * 结果转换为分页输出
     * @param search 查询结果
     * @return 分页结果
     */
    private PageResult<Person> getPersonPageResult(SearchHits<Person> search) {
        List<Person> collect = search.stream().map(SearchHit::getContent).collect(Collectors.toList());
        PageResult<Person> pageResult = new PageResult<>();
        pageResult.setList(collect);
        pageResult.setTotal(search.getTotalHits());
        return pageResult;
    }

    /**
     * 按照年龄区间、姓名进行分页条件查询
     * @param name 姓名
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @param pageRequest 分页条件
     * @return
     */
    @Override
    public PageResult<Person> conditionPageSearch(String name, int minAge, int maxAge, PageRequest pageRequest) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (null != name) {
            boolQueryBuilder.must(new MatchQueryBuilder("name", name));
        }
        boolQueryBuilder.must(new RangeQueryBuilder("age").from(minAge).to(maxAge));
        NativeSearchQuery builder = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withPageable(pageRequest)
                .build();
        SearchHits<Person> search = elasticsearchRestTemplate.search(builder, Person.class);
        return getPersonPageResult(search);
    }


}
