package com.spring.tx.service;

import com.spring.tx.dao.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kangqing
 * @since 2021/11/18 20:13
 */
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductDao productDao;

    /**
     * 扣减库存
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateProductStockCountById(Long id, Integer stockCount) {
        productDao.updateProductStockCountById(id, stockCount);
        // 故意制造异常
        int i = 1 / 0;
    }
}
