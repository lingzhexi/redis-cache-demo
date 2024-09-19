package com.codeling.rediscache.controller;

import com.codeling.rediscache.model.Product;
import com.codeling.rediscache.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * 根据ID获取产品信息
     * 通过@Cacheable注解，当请求的产品ID在缓存中存在时，直接从缓存中获取产品信息，减少数据库查询
     *
     * @param id 产品ID
     * @return 返回查询结果，包含指定ID的产品信息
     */
    @GetMapping("/getProductById")
    @Cacheable(value = "productsCache", key = "#id")
    public Result<Product> getProductById(Long id) {
        // 当从数据库获取数据时会打印，如果是从缓存中查询并不会执行到这里。
        log.info("从数据库获取产品: id = {}", id);
        Product product = new Product(id, "product", 100, "课本", 10);
        return Result.success(product);
    }

    /**
     * 更新产品信息
     * 通过@CacheEvict注解，当更新产品时，清除缓存中对应产品的数据，确保获取到最新的数据
     * 设置allEntries为true，表示清除整个缓存中的所有产品数据
     *
     * @param product 产品对象，包含更新后的详细信息
     * @return 返回更新结果，成功更新时返回成功标志
     */
    @PutMapping("/updateProduct")
    @CacheEvict(value = "productsCache", key = "#product.id")
    public Result updateProduct(@RequestBody Product product) {
        // 更新操作
        return Result.success();
    }

    /**
     * 删除指定ID的产品
     * 通过@CacheEvict注解，当删除产品时，清除缓存中对应产品的数据
     *
     * @param id 待删除产品的ID
     * @return 返回删除结果，成功删除时返回成功标志
     */
    @DeleteMapping("/deleteProductById")
    @CacheEvict(value = "productsCache", key = "#id")
    public Result deleteProductById(Long id) {
        // 删除操作
        return Result.success();
    }


}

