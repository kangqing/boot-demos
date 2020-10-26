package com.yunqing.demo.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * @author yx
 * @since 2020/10/22 16:22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/findAll")
    public AjaxResult<?> findAll() {
        return AjaxResult.OK(personService.findAll());
    }


    @PostMapping("/add")
    public AjaxResult<?> add(@RequestBody Person person) {
        return AjaxResult.OK(personService.add(person));
    }

    @DeleteMapping("/delete")
    public AjaxResult<?> delete(@RequestParam String id) {
        personService.delete(id);
        return AjaxResult.OK();
    }

    @GetMapping("/search")
    public AjaxResult<?> conditionPageSearch(@RequestParam String field, @RequestParam String keyword, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        return AjaxResult.OK(personService.conditionPageSearch(field, keyword, pageable));
    }
 }
