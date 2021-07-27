package com.qima.interview.application.repository;

import com.qima.interview.application.entity.Company;
import com.qima.interview.application.entity.Product;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductRepository {

    private final Map<Long, Product> productMap = new HashMap<>();
    private final Map<String, Product> productByNameMap = new HashMap<>();
    private final Map<Long, List<Product>> companyMap = new HashMap<>();
    private final AtomicLong lastId = new AtomicLong(0);

    public ProductRepository() {

        for (long id = 1000; id < 1010; id ++) {
            Company company = new Company().id(id).name("company " + id);
            Product product = new Product().id(id).name("product " + id).gtin13("123456789101").company(company);
            productMap.put(id, product);
            productByNameMap.put(product.getName(), product);
            companyMap.put(id, List.of(product));
        }

        for (long id = 1000; id < 1009; id++) {
            productMap.get(id).getCompany().setNetwork(List.of(productMap.get(id + 1).getCompany()));
        }

        productMap.get(1009L).getCompany().setNetwork(List.of(productMap.get(1000L).getCompany()));
    }
    public Product save(@NonNull final Product product) {
        Product savedProduct = new Product();
        BeanUtils.copyProperties(product, savedProduct);
        savedProduct.setId(lastId.incrementAndGet());
        productMap.put(savedProduct.getId(), savedProduct);
        List<Product> companyProducts = companyMap.getOrDefault(savedProduct.getCompany().getId(), new ArrayList<>());
        companyProducts.add(product);
        companyMap.put(savedProduct.getCompany().getId(), companyProducts);
        return savedProduct;
    }

    public Optional<Product> findOne(long id) {
        return Optional.ofNullable(this.productMap.get(id));
    }

    public List<Product> findOneByCompany(@NonNull final Company company) {
        return companyMap.getOrDefault(company.getId(), new ArrayList<>());
    }

    public Optional<Product> findByName(String name) {
        return Optional.ofNullable(this.productByNameMap.get(name));
    }
}
