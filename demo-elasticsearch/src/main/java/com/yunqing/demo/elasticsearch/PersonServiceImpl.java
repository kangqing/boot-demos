package com.yunqing.demo.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author yx
 * @since 2020/10/22 16:20
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;



    @Override
    public List<Person> findAll() {
        Iterable<Person> iterable = personRepository.findAll();
        List<Person> resList = new ArrayList<>();
        iterable.forEach(resList::add);
        return resList;
    }

    @Override
    public Person add(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(String id) {
        personRepository.deleteById(id);
    }

    @Override
    public Page<Person> conditionPageSearch(String field, String keyword, Pageable pageable) {
        QueryBuilder builder = new MatchQueryBuilder(field, keyword);
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(pageable)
                .build();
        SearchHits<Person> search = elasticsearchRestTemplate.search(build, Person.class);
        long totalHits = search.getTotalHits();
        List<Person> collect = search.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, totalHits);

    }


}
