package com.guavapay.testtask.api.rest;

import com.guavapay.testtask.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin")
@Api(tags = {"admin"})
public class AdminController {
    @Autowired
    private AdminService adminService;

}
