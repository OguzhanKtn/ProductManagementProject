package com.works.vize_1.controllers;

import com.works.vize_1.entities.Product;
import com.works.vize_1.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class DetailController {

    final ProductService productService;

    Product product;
    @GetMapping("/detail")
    public String details(Model model){
        model.addAttribute("product",product);
        return "detail";
    }

    @GetMapping("/details/{pid}")
    public String productDetail(@PathVariable Long pid){
        product = productService.singleProduct(pid);
        return "redirect:/detail";
    }
}
