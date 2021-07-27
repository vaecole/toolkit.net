# qima-interview-api

## Description
QIMA Interview skeleton for backend developpers

### Getting Started

This a SpringBoot project configured one with the full implementation and the tests
the others only with the tests with assured-rest

## Dependencies provided with the project:

* SpringBoot
* JUnit 5
* AssertJ
* MapStruct
* Lombock
* Mockito

## Repositories:

* CompanyRepository: same here but populated with some companies
* ProductRepository: a fake repository populated with some products and with some queries (not thread safe)

## Entites:

* Company
* Product

## Endpoints to implement for the interview

### Companies

#### API

**POST** /api/companies


#### Body

```json
{
  "name": "the name cannot be blank and the size should be between 10 and 400",
  "address": "the address cannot be blank and the size should be between 10 and 400"
}
```

#### Validation

* The name should not be null, blank and size between 10 and 400
* The name should not be present in the "Database"

#### Result

the URI for getting the company: http://localhost:8080/api/companies/1
in case of error a bad request is enough

#### API

**GET** /api/companies/{id}

#### Result

```json
{
  "id": 1,
  "name": "the company name",
  "address": "the address"
}
```

If the company is not present the HTTP status is **NOT_FOUND**

#### API

**GET** /api/companies/

#### Result

```json
[
  { "id": 1, "name": "the company name", "address": "the address" },
  { "id": 2, "name": "a second company name", "address": "the address" }
]
```

#### API

**PATCH** /api/companies/

#### Body

```json
{
  "id": 1,
  "companies": [ 2, 3, 4 ]
}
```

#### Validation

The company id should be present
Same for all company ids also the company id should not be present in the array

#### Result

* **OK** if updated
* **BAD_REQUEST** if there is an error

### Products

#### API

**POST** /api/products

#### Body

```json
{
  "name": "a product name",
  "gtin13": "612345678",
  "company": 1
}
```

#### Validation

* The name should not be blank and not be present in the db
* The GTIN13 should be a valid one here is an example of the validation:

| Positions                                                                                             | N1 | N2 | N3 | N4 | N5 | N6 | N7 | N8 | N9 | N10 | N11 | N12 | N13 |
|-------------------------------------------------------------------------------------------------------|----|----|----|----|----|----|----|----|----|-----|-----|-----|-----|
| Number **without** <br>  Check Digit                                                                  |  6 |  2 |  9 |  1 |  0 |  4 |  1 |  5 |  0 |   0 |   2 |   1 | -   |
| **Step 1** Multiply                                                                                   |  x |  x |  x |  x |  x |  x |  x |  x |  x |   x |   x |   x | -   |
| by                                                                                                    |  1 |  3 |  1 |  3 |  1 |  3 |  1 |  3 |  1 |   3 |   1 |   3 | -   |
| **Step 2** add <br>  results                                                                          |  = |  = |  = |  = |  = |  = |  = |  = |  = |   = |   = |   = | -   |
| to create **sum**                                                                                     |  6 |  6 |  9 |  3 |  0 | 12 |  1 | 15 |  0 |   0 |   2 |   3 | =57 |
| **Step 3**: Substract the sum from nearest equal or higher multiple of ten: <br>  **60 - 57** = **3** |    |    |    |    |    |    |    |    |    |     |     |     |     |
| Number **with** <br>  Check Digit                                                                     |  6 |  2 |  9 |  1 |  0 |  4 |  1 |  5 |  0 |   0 |   2 |   1 | 3   |

#### Result

* Bad request in case of error
* Created with the URI: http://localhost:8080/api/products/1

**GET** /api/products/{id}

Return:
* Not found if the product is not present
* The JSON:
```json
{
  "id": 1,
  "name": "a product",
  "gtin13": "612345678",
  "company": {
    "id": 1,
    "name": "a company",
    "address": "an address"
  }
}
```

**GET** /api/products

Return:

```json
[
  { "id": 1 },
  { "id": 2 }
]
```

### All products from one company and its network

**GET** /api/companies/1/products

Returns all the the products

Return:

```json
[
  { "id": 1, "name": "a product" },
  { "id": 2, "name": "a product 2" }
]
```

