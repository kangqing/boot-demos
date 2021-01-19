package com.yunqing.demo.elasticsearch;

import com.yunqing.demo.elasticsearch.utils.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangqing
 * @since 2020/10/22 16:22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public JsonResult<?> findAll() {
        return JsonResult.success(personService.findAll());
    }


    @PostMapping
    public JsonResult<?> add(@RequestBody Person person) {
        return JsonResult.success(personService.add(person));
    }

    @DeleteMapping("/{id}")
    public JsonResult<?> delete(@PathVariable String id) {
        personService.delete(id);
        return JsonResult.success();
    }

    @GetMapping("{id}")
    public JsonResult<?> findById(@PathVariable String id) {
        return JsonResult.success(personService.findById(id));
    }

    @GetMapping("/findByName/{name}")
    public JsonResult<?> findByName(@PathVariable String name) {
        return JsonResult.success(personService.findByName(name));
    }

    /**
     * 根据名字分页查询
     * @param name 名字
     * @param page 页码
     * @param pageSize 页容量
     * @return 返回结果
     */
    @GetMapping("/findByName")
    public JsonResult<?> findByAgeBetween(String name, int page, int pageSize) {
        /*
          这里排序条件不能使用id，会报错
         */
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.Direction.ASC, "age");
        return JsonResult.success(personService.findByName(name, pageRequest));
    }

    /**
     * 单条件分页查询
     * @param field 字段
     * @param keyword 字段值
     * @param page 页
     * @param pageSize 页容量
     * @return 结果
     */
    @GetMapping("/search")
    public JsonResult<?> conditionPageSearch(@RequestParam String field, @RequestParam String keyword, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        return JsonResult.success(personService.conditionPageSearch(field, keyword, pageRequest));
    }

    /**
     * 多条件分页查询
     * @param name 名字
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @param page 第几页
     * @param pageSize 页容量
     * @return 结果
     */
    @GetMapping("/condition")
    public JsonResult<?> condition(String name, int minAge, int maxAge, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        return JsonResult.success(personService.conditionPageSearch(name, minAge, maxAge, pageRequest));
    }
 }
