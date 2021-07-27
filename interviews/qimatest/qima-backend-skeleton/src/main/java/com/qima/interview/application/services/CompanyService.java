package com.qima.interview.application.services;

import com.qima.interview.application.entity.Company;
import com.qima.interview.application.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public long add(@Validated @NotNull Company company) {
        if (company.getName() == null || company.getName().isEmpty() ||
                company.getName().length() < 10 || company.getName().length() > 400) {
            throw new IllegalArgumentException("The name should not be null, blank and size between 10 and 400");
        }
        if (companyRepository.findByName(company.getName()).isPresent()) {
            throw new IllegalArgumentException("The name should not be present in the \"Database\"");
        }
        Company result = companyRepository.save(company);
        return result.getId();
    }

    public Optional<Company> get(long id) {
        return companyRepository.findById(id);
    }

    public List<Company> getAll() {
        return companyRepository.getAllCompanies();
    }
}
