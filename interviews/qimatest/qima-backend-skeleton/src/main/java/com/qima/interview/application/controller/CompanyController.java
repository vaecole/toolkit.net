package com.qima.interview.application.controller;

import com.qima.interview.application.entity.Company;
import com.qima.interview.application.services.CompanyService;
import org.apache.catalina.util.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Controller("api/companies")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody Company company) {
        long createdId = companyService.add(company);
        return ResponseEntity.created(URI.create("/api/companies/" + createdId)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> get(@PathVariable long id) {
        Optional<Company> company = companyService.get(id);
        if (company.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(company.get());
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAll(){
        return ResponseEntity.ok(companyService.getAll());
    }

}
