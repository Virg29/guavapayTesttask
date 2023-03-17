package com.guavapay.testtask;

import com.guavapay.testtask.entity.Admin;
import com.guavapay.testtask.entity.Courier;
import com.guavapay.testtask.entity.User;
import com.guavapay.testtask.service.AdminService;
import com.guavapay.testtask.service.CourierService;
import com.guavapay.testtask.service.ParcelService;
import com.guavapay.testtask.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= TesttaskApplication.class)
@DataJpaTest
class TesttaskApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourierService courierService;
	@Autowired
	private ParcelService parcelService;


	@Test
	public void integrityTestExample() {
		// given
		Courier courier = new Courier("testCourier","somepassword");
		courierService.createCourier(courier);

		User user = new User("testUser","somepassword");
		userService.createUser(user);

		Admin admin = new Admin("testAdmin","somepassword");
		adminService.createAdmin(admin);

		Courier foundCourier = (Courier) courierService.getBaseUserByLogin("testCourier");
		Admin foundAdmin = (Admin) courierService.getBaseUserByLogin("testCourier");
		User foundUser = (User) courierService.getBaseUserByLogin("testCourier");

		assertThat(foundCourier).isNotNull();
		assertThat(foundAdmin).isNotNull();
		assertThat(foundUser).isNotNull();

		courierService.deleteCourier(foundCourier.getId());
		adminService.deleteAdmin(foundAdmin.getId());
		userService.deleteUser(foundUser.getId());

		Courier foundCourier2 = (Courier) courierService.getBaseUserByLogin("testCourier");
		Admin foundAdmin2 = (Admin) courierService.getBaseUserByLogin("testCourier");
		User foundUser2 = (User) courierService.getBaseUserByLogin("testCourier");

		assertThat(foundCourier2).isNull();
		assertThat(foundAdmin2).isNull();
		assertThat(foundUser2).isNull();
	}


}
