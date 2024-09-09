package com.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.dto.Driver;

@FeignClient("DRIVERSERVICE")
public interface DriverServiceInterface {

	  @PostMapping("/drivers/addDriver")
	    public Driver addDriver(@RequestBody Driver driver);

	    @GetMapping("/drivers/viewAllDrivers")
	    public List<Driver> getAllDrivers();

	    @GetMapping("/drivers/viewDriverById/{id}")
	    public Driver getDriverById(@PathVariable Long id);

	    @PutMapping("/drivers/updateDriver/{id}")
	    public Driver updateDriver(@PathVariable Long id, @RequestBody Driver driver);

	    @DeleteMapping("/drivers/deleteDriver/{id}")
	    public void deleteDriver(@PathVariable Long id);
	}
