package com.guavapay.testtask.api.rest;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/courier")
@Api(tags = {"courier"})
public class CourierController {
}
