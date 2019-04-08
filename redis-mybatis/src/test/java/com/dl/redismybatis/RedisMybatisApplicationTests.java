package com.dl.redismybatis;

import com.dl.redismybatis.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class RedisMybatisApplicationTests {


    private int port=8080;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {
        long productId = 1;
        Product product = restTemplate.getForObject("http://localhost:" + port + "/product/" + productId, Product.class);
        assertThat(product.getPrice()).isEqualTo(200);

        Product newProduct = new Product();
//        long newPrice = new Random().nextLong();
        long newPrice = 200;
        newProduct.setName("new name");
        newProduct.setPrice(newPrice);
        restTemplate.put("http://localhost:" + port + "/product/" + productId, newProduct);

        Product testProduct = restTemplate.getForObject("http://localhost:" + port + "/product/" + productId, Product.class);
        assertThat(testProduct.getPrice()).isEqualTo(newPrice);
    }

}
