package com.yunqing.demo.elasticsearch;

import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

@SpringBootTest
class DemoElasticsearchApplicationTests {

	//是使用高级REST客户端的ElasticsearchOperations接口的实现。
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	@Test
	void contextLoads() {
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		//聚合可以有多个,所以add
		//terms词条聚合,传入聚合名称   field("聚合字段")   size(结果集大小)
		NativeSearchQuery query = nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("ages").field("age"))
				//结果集过滤  这里设置不需要结果集(不添加包含与不包含,会自动生成长为0数组)
				.withSourceFilter(new FetchSourceFilterBuilder().build())
				.build();
		SearchHits<Person> hits = elasticsearchRestTemplate.search(query, Person.class, IndexCoordinates.of("db-index"));
		System.out.println(hits);
		//获取聚合结果集   因为结果为字符串类型 所以用ParsedStringTerms接收   还有ParsedLongTerms接收数字  ParsedDoubleTerms接收小数
		Aggregations aggregations = hits.getAggregations();
		assert aggregations != null;
		ParsedLongTerms ages = aggregations.get("ages");
		//获取桶
		ages.getBuckets().forEach(bucket -> {
			//获取桶中的key   与    记录数
			System.out.println(bucket.getKeyAsString()+"岁的人数为： "+bucket.getDocCount());
		});
	}

}
