package br.com.fiap.customer;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerResourceTest {

    @Autowired
    private CustomerRepository customerRepository;
    @LocalServerPort
    private Integer port;

    @Before
    public void setup(){
        stubCreateCustomer();
        RestAssured.baseURI="http://localhost";
        RestAssured.port = this.port;
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type","application/json")
                .build();
    }

    private void stubCreateCustomer() {
        Customer customer = new Customer();
        customer.setName("Diego");
        customer.setLastName("Landi");
        customer.setGender("male");
        customer.setAge(31);
        customerRepository.save(customer);
    }

    @Test
    public void shouldFindCustomerById(){
        RestAssured.get("/customer/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", Matchers.is("Diego"))
                .body("lastName",Matchers.is("Landi"))
                .body("gender",Matchers.is("male"))
                .body("age",Matchers.is(31));
       }

       @Test
       public void canNotFindCustomerById(){
        RestAssured.get("/people/300")
                .then()
                .assertThat()
                .statusCode(404)
                .body("messageError",Matchers.is("Person Not Found"));
       }

       @Test
        public void shouldCreateCustomer(){
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("Diego");
        customerRequest.setLastName("Landi");
        customerRequest.setGender("male");
        customerRequest.setAge(31);
        RestAssured.given()
                .body(customerRequest)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(201)
                .body("customerID",Matchers.any(Integer.class));
       }

    @Test
    public void canNotCreateCustomerWhenGenderIsInvalid(){
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("Diego");
        customerRequest.setLastName("Landi");
        customerRequest.setGender("animal");
        customerRequest.setAge(31);
        RestAssured.given()
                .body(customerRequest)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(422)
                .body("messageError",Matchers.is("Gender is Invalid"));
    }
}

