package com.guavapay.testtask;

import com.guavapay.testtask.api.rest.AdminController;
import com.guavapay.testtask.api.rest.BaseUserAuthController;
import com.guavapay.testtask.api.rest.CourierController;
import com.guavapay.testtask.api.rest.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= TesttaskApplication.class)
public class TesttaskApplicationTests2 {
    @Autowired
    private AdminController adminController;
    @Autowired
    private CourierController courierController;
    @Autowired
    private UserController userController;
    @Autowired
    private BaseUserAuthController baseUserAuthController;
    @Test
    public void contextLoads() throws Exception {
        assertThat(adminController).isNotNull();
        assertThat(courierController).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(baseUserAuthController).isNotNull();
    }
}
