package ${basePackage}.controller;

import ${basePackageFrame}.pojo.APIResult;
import ${basePackageFrame}.pojo.ResultGenerator;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping
    public APIResult add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.getSuccessResult();
    }

    @GetMapping("/{id}")
    public APIResult delete(@PathVariable String id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.getSuccessResult();
    }

    @PutMapping
    public APIResult update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.getSuccessResult();
    }

    @GetMapping("/{id}")
    public APIResult detail(@PathVariable String id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.getSuccessResult(${modelNameLowerCamel});
    }

    @GetMapping
    public APIResult list(@RequestParam(defaultValue = "0") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.getSuccessResult(pageInfo);
    }
}
