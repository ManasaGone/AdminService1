package com.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.dto.Vehicle;

@FeignClient("VEHICLESERVICE")
public interface VehicleServiceInterface {
	 @PostMapping("/vehicles/AddVehicle")
	    public Vehicle addVehicle(@RequestBody Vehicle vehicle);

	    @DeleteMapping("/vehicles/DeleteVehicle/{vehicleNo}")
	    public void deleteVehicle(@PathVariable String vehicleNo);

	    @GetMapping("/vehicles/ViewAllVehicles")
	    public List<Vehicle> getAllVehicles();

	    @GetMapping("/vehicles/ViewVehicleById/{vehicleNo}")
	    public Vehicle getVehicleById(@PathVariable String vehicleNo);
	    
	    @PutMapping("/vehicles/UpdateVehicle/{vehicleNo}")
	    Vehicle updateVehicle(@PathVariable("vehicleNo") String vehicleNo, @RequestBody Vehicle vehicle);

	

	}
