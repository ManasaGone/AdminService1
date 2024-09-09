package com.admin.controllers;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.BookingDto;
import com.admin.dto.Driver;
import com.admin.dto.Route;
import com.admin.dto.Vehicle;
import com.admin.feign.BookingServiceInterface;
import com.admin.repository.AdminRepository;
import com.admin.services.AdminServiceImpl;



@CrossOrigin("*")
@RestController
@RequestMapping("/admin")

public class AdminController {

	@Autowired
	AdminServiceImpl adminServiceImpl;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	private BookingServiceInterface bookingService;
	@PostMapping("/Addroute")
	public Route addRoute(@RequestBody Route route) {
		return adminServiceImpl.addRoute(route);
	}
	@GetMapping("/ViewAllRoutes")
	public List<Route> viewAllRoutes(){
		return adminServiceImpl.viewAllRoutes();
	}

    @GetMapping("/ViewRouteById/{routeId}")
    public Route getRouteById(@PathVariable int routeId) {
        return adminServiceImpl.getRouteById(routeId);
    }

    @PutMapping("/ModifyRoute/{routeId}")
    public Route modifyRoute(@PathVariable int routeId, @RequestBody Route route) {
        return adminServiceImpl.modifyRoute(routeId, route);
    }

    @DeleteMapping("/DeleteRoute/{routeId}")
    public void deleteRoute(@PathVariable int routeId) {
        adminServiceImpl.deleteRoute(routeId);
    }
    @PostMapping("/AddVehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return adminServiceImpl.addVehicle(vehicle);
    }

    @DeleteMapping("/DeleteVehicle/{vehicleNo}")
    public void deleteVehicle(@PathVariable String vehicleNo) {
        adminServiceImpl.deleteVehicle(vehicleNo);
    }

    @GetMapping("/ViewAllVehicles")
    public List<Vehicle> getAllVehicles() {
        return adminServiceImpl.getAllVehicles();
    }

    @GetMapping("/ViewVehicleById/{vehicleNo}")
    public Vehicle getVehicleById(@PathVariable String vehicleNo) {
        return adminServiceImpl.getVehicleById(vehicleNo);
    }

    @PutMapping("/UpdateVehicle/{vehicleNo}")
    public Vehicle updateVehicle(@PathVariable String vehicleNo, @RequestBody Vehicle vehicle) {
        return adminServiceImpl.updateVehicle(vehicleNo, vehicle);
    }
    @PostMapping("/addDriver")
    public Driver addDriver(@RequestBody Driver driver) {
        return adminServiceImpl.addDriver(driver);
    }

    @GetMapping("/viewAllDrivers")
    public List<Driver> getAllDrivers() {
        return adminServiceImpl.getAllDrivers();
    }

    @GetMapping("/viewDriverById/{id}")
    public Driver getDriverById(@PathVariable Long id) {
        return adminServiceImpl.getDriverById(id);
    }

    @DeleteMapping("/deleteDriver/{id}")
    public void deleteDriver(@PathVariable Long id) {
        adminServiceImpl.deleteDriver(id);
    }

    @PutMapping("/updateDriver/{id}")
    public Driver updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        return adminServiceImpl.updateDriver(id, driver);
    }
    @GetMapping("/viewAllBookings")
    public List<BookingDto> viewAllBookings() {
        return bookingService.viewAllBookings();
    }
   

  
}
