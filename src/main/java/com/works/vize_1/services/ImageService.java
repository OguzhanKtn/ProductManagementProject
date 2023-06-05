package com.works.vize_1.services;

import com.works.vize_1.entities.ProductImage;
import com.works.vize_1.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ImageService {

    final ProductImageRepository repository;

    public ProductImage addImage(ProductImage productImage){
        repository.save(productImage);
        return productImage;
    }

    public List<ProductImage> list(Long pid){
        return repository.findByPidEquals(pid);
    }

    public boolean delete(Long pid){
        try{
            repository.deleteByPidEquals(pid);
            return true;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return false;
    }

    public List<ProductImage> list(){
        return repository.findAll();
    }
}
