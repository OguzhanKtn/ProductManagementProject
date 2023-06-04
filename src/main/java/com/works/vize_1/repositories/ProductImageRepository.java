package com.works.vize_1.repositories;

import com.works.vize_1.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByPidEquals(Long pid);

    @Transactional
    long deleteByPidEquals(Long pid);

}