package com.qima.interview.application.repository;

import com.qima.interview.application.entity.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CompanyRepository {

    private final Map<Long, Company> companyMap = new LinkedHashMap<>();
    private final Map<String, Company> companyByNameMap = new LinkedHashMap<>();
    private final AtomicLong lastId = new AtomicLong();

    public CompanyRepository() {
        for (long id = 1000; id < 1010; id ++) {
            Company company = new Company().id(id).name("company " + id).address("a valid address");
            companyMap.put(id, company);
            companyByNameMap.put(company.getName(), company);
        }

        for (long id = 1000; id < 1009; id++) {
            companyMap.get(id).setNetwork(List.of(companyMap.get(id + 1)));
        }

        companyMap.get(1009L).setNetwork(List.of(companyMap.get(1000L)));
    }
    public Company save(Company company) {
        final long id = lastId.incrementAndGet();
        Company savedCompany = new Company();

        BeanUtils.copyProperties(company, savedCompany, "network");
        savedCompany.setId(id);
        this.companyMap.put(savedCompany.getId(), savedCompany);
        this.companyByNameMap.put(savedCompany.getName(), savedCompany);
        return savedCompany;
    }

    public Optional<Company> findById(long id) {
        return Optional.ofNullable(companyMap.get(id));
    }

    public Optional<Company> findByName(String name) {
        return Optional.ofNullable(this.companyByNameMap.get(name));
    }

    public List<Company> getAllCompanies() {
        return new ArrayList<>(this.companyMap.values());
    }
}
