package com.guavapay.testtask;

import com.guavapay.testtask.entity.Admin;
import com.guavapay.testtask.entity.Courier;
import com.guavapay.testtask.entity.User;
import com.guavapay.testtask.service.AdminService;
import com.guavapay.testtask.service.CourierService;
import com.guavapay.testtask.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TesttaskApplication {
	@Value("${app.admin.login}")
	private String adminLogin;

	@Value("${app.admin.password}")
	private String adminPassword;

	@Value("${app.user.login}")
	private String userLogin;

	@Value("${app.user.password}")
	private String userPassword;
	@Value("${app.courier.login}")
	private String courierLogin;

	@Value("${app.courier.password}")
	private String courierPassword;

	@Autowired
	private ApplicationContext context;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	@PostConstruct

	public void createFirtsUsers(){
		AdminService adminService = context.getBean(AdminService.class);
		UserService userService = context.getBean(UserService.class);
		CourierService courierService = context.getBean(CourierService.class);

		if(adminService.getBaseUserByLogin(adminLogin)==null)
			adminService.createAdmin(new Admin(adminLogin,bCryptPasswordEncoder.encode(adminPassword)));
		if(userService.getBaseUserByLogin(userLogin)==null)
			userService.createUser(new User(userLogin,bCryptPasswordEncoder.encode(userPassword)));
		if(courierService.getBaseUserByLogin(courierLogin)==null)
			courierService.createCourier(new Courier(courierLogin,bCryptPasswordEncoder.encode(courierPassword)));
	}
	public static void main(String[] args) {
		SpringApplication.run(TesttaskApplication.class, args);
	}

}
