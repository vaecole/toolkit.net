package com.qima.interview.application.interview;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpStatus;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.with;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InterviewApplicationTests {

    @Test
    @Order(10)
    @DisplayName("When request is post And company name is invalid Then company is not created")
    void whenRequestPostAndCompanyNameIsInvalid_thenCompanyIsNotCreated() {

        final Map<String, String> company = new HashMap<>();
        company.put("name", "");
        company.put("address", "a valid address");
        with().body(company).when().contentType(ContentType.JSON).request(Method.POST, "/api/companies").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Order(11)
    @DisplayName("When request is post And address name is invalid Then company is not created")
    void whenRequestPostAndAddressIsInvalid_thenCompanyIsNotCreated() {

        final Map<String, String> company = new HashMap<>();
        company.put("name", "a valid name");
        company.put("address", "");
        with().body(company).when().contentType(ContentType.JSON).request(Method.POST, "/api/companies").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Order(20)
    @DisplayName("When request is post Then company is not created")
    void whenRequestPost_thenCompanyIsCreated() {

        Map<String, String> company = new HashMap<>();
        company.put("name", "a company 1");
        company.put("address", "a valid address");
        with().body(company).when().contentType(ContentType.JSON).request(Method.POST, "/api/companies").then().statusCode(HttpStatus.CREATED.value());

        company = new HashMap<>();
        company.put("name", "a company 2");
        company.put("address", "a second valid address");
        with().body(company).when().contentType(ContentType.JSON).request(Method.POST, "/api/companies").then().statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @Order(30)
    @DisplayName("When request is get Then company is retrieved")
    void whenRequestGet_thenCompanyIsRetrieved() {
		Map<String, String> retrievedCompanies = with().when().contentType(ContentType.JSON).request(Method.GET, "/api/companies/1").then().statusCode(HttpStatus.OK.value()).extract().as(HashMap.class);
        assertThat(retrievedCompanies).containsEntry("name", "a company 1");
        assertThat(retrievedCompanies).containsEntry("address", "a valid address");

        retrievedCompanies = with().when().contentType(ContentType.JSON).request(Method.GET, "/api/companies/2").then().statusCode(HttpStatus.OK.value()).extract().as(HashMap.class);
        assertThat(retrievedCompanies).containsEntry("name", "a company 2");
        assertThat(retrievedCompanies).containsEntry("address", "a second valid address");
	}

    @Test
    @Order(30)
    @DisplayName("When request is get And company is not present Then company is not retrieved")
    void whenRequestGetAndCompanyIsNotFound_thenCompanyIsNotRetrieved() {
        with().when().contentType(ContentType.JSON).request(Method.GET, "/api/companies/300").then().statusCode(HttpStatus.NOT_FOUND.value());
    }

	@Test
    @Order(40)
    @DisplayName("When request is get Then all companies are retrieved")
    void whenRequestGet_theAllCompaniesAreRetrieved() {
        final List<Map<String, Object>> retrievedCompanies = with().when().contentType(ContentType.JSON).request(Method.GET, "/api/companies").then().statusCode(HttpStatus.OK.value()).extract().as(List.class);
        assertThat(retrievedCompanies).hasSize(12);
        List<Map<String, Object>> sortedMap = retrievedCompanies.stream().sorted(Comparator.comparing(o -> ((Integer) o.get("id")))).collect(Collectors.toList());
        assertThat(sortedMap.get(0)).containsEntry("name", "a company 1");
        assertThat(sortedMap.get(0)).containsEntry("address", "a valid address");
        assertThat(sortedMap.get(1)).containsEntry("name", "a company 2");
        assertThat(sortedMap.get(1)).containsEntry("address", "a second valid address");
        for (int i = 2; i < 11; i++) {
            assertThat(sortedMap.get(i)).containsEntry("name", "company " + (998 + i));
            assertThat(sortedMap.get(i)).containsEntry("address", "a valid address");
        }
    }

    @Test
    @Order(50)
    @DisplayName("When request is post and company is not present Then product is not created")
    void whenRequestPostAndCompanyIsNotPresent_thenProductIsNotCreated() {

        final Map<String, Object> product = new HashMap<>();

        // Company is not present
        product.put("companyId", -1);
        product.put("name", "a valid name");
        product.put("gtin13", "629104150021");
        with().body(product).when().contentType(ContentType.JSON).request(Method.POST, "/api/products").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Order(52)
    @DisplayName("When request is post and product name is invalid Then product is not created")
    void whenRequestPostAndProductNameIsInvalid_thenProductIsNotCreated() {

        final Map<String, Object> product = new HashMap<>();

        // Wrong name
        product.put("companyId", 1);
        product.put("name", "");
        product.put("gtin13", "629104150021");
        with().body(product).when().contentType(ContentType.JSON).request(Method.POST, "/api/products").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Order(53)
    @DisplayName("When request is post and GTIN 13 is invalid Then product is not created")
    void whenRequestPostAndGtin13IsInvalid_thenProductIsNotCreated() {

        final Map<String, Object> product = new HashMap<>();

        // Invalid GTIN 13
        product.put("companyId", 1);
        product.put("name", "a valid name");
        product.put("gtin13", "629104150022");
        with().body(product).when().contentType(ContentType.JSON).request(Method.POST, "/api/products").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @Order(60)
    @DisplayName("When request is post Then product is created")
    void whenRequestPost_thenProductIsCreated() {

        final Map<String, Object> product = new HashMap<>();
        product.put("companyId", 1);
        product.put("name", "a valid name");
        product.put("gtin13", "629104150021");
        with().body(product).when().contentType(ContentType.JSON).request(Method.POST, "/api/products").then().statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @Order(70)
    @DisplayName("When request is get Then product is retrieved")
    void whenRequestGet_thenProductIsRetrieved() {
        final Map<String, Object> retrievedProduct = with().when().contentType(ContentType.JSON).request(Method.GET, "/api/products/1").then().statusCode(HttpStatus.OK.value()).extract().as(HashMap.class);
        assertThat(retrievedProduct).containsEntry("id", 1);
        assertThat(retrievedProduct).containsEntry("name", "a valid name");
        assertThat(retrievedProduct).containsEntry("gtin13", "629104150021");

        final Map<String, Object> company = new HashMap<>();
        company.put("id", 1);
        company.put("name", "a company 1");
        company.put("address", "a valid address");
        company.put("network", null);

        assertThat(retrievedProduct).containsEntry("company", company);
    }

    @Test
    @Order(71)
    @DisplayName("When request is get And product is not present Then product is not retrieved")
    void whenRequestGetAndProductIsNotPresent_thenProductIsNotRetrieved() {
        with().when().contentType(ContentType.JSON).request(Method.GET, "/api/products/256").then().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @Order(80)
    @DisplayName("When request is get Then all products of the company and its network are retrieved")
    void whenRequestGet_thenAllProductsOfNetworkAreRetrieved() {

        List<Map<String, Object>> productList = with().when().contentType(ContentType.JSON).request(Method.GET, "/api/companies/1000/products").then().statusCode(HttpStatus.OK.value()).extract().as(List.class);
        assertThat(productList).hasSize(10);
        List<Map<String, Object>> sortedProducts = productList.stream().sorted(Comparator.comparing(o -> ((Integer) o.get("id")))).collect(Collectors.toList());
        AtomicInteger i = new AtomicInteger(1000);
        sortedProducts.forEach(product -> {
            assertThat(product).containsEntry("id", i.get());
            assertThat(product).containsEntry("name", "product " + i.get());
            i.incrementAndGet();
        });
    }
}
