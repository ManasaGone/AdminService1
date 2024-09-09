package com.admin.services;

import java.util.List;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.admin.dto.BookingDto;
import com.admin.dto.Driver;
import com.admin.dto.Route;
import com.admin.dto.Vehicle;
import com.admin.entities.Admin;
import com.admin.exceptions.AdminServiceException;
import com.admin.exceptions.ResourceNotFoundException;
import com.admin.feign.BookingServiceInterface;
import com.admin.feign.DriverServiceInterface;
import com.admin.feign.RouteServiceInterface;
import com.admin.feign.VehicleServiceInterface;
import com.admin.repository.AdminRepository;


@Service
public class AdminServiceImpl implements AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminRepository adminRepository;
   
    @Autowired
    private RouteServiceInterface routeServiceInterface;
    
    @Autowired
    private VehicleServiceInterface vehicleServiceInterface;
    @Autowired
    private BookingServiceInterface bookingService;
    @Autowired
    private DriverServiceInterface driverServiceInterface;

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

   
    public Route addRoute(Route route) {
        try {
            return routeServiceInterface.addRoute(route);
        } catch (Exception e) {
            throw new AdminServiceException("Error adding route: " + e.getMessage());
        }
    }

    @Override
    public List<Route> viewAllRoutes() {
        try {
            return routeServiceInterface.getAllRoutes();
        } catch (Exception e) {
            throw new AdminServiceException("Error fetching routes: " + e.getMessage());
        }
    }

    public Route getRouteById(int routeId) {
        try {
            return routeServiceInterface.getRouteById(routeId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Route not found for id: " + routeId);
        }
    }

    public Route modifyRoute(int routeId, Route route) {
        try {
            return routeServiceInterface.modifyRoute(routeId, route);
        } catch (Exception e) {
            throw new AdminServiceException("Error modifying route: " + e.getMessage());
        }
    }

    public void deleteRoute(int routeId) {
        try {
            routeServiceInterface.deleteRoute(routeId);
        } catch (Exception e) {
            throw new AdminServiceException("Error deleting route: " + e.getMessage());
        }
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        try {
            return vehicleServiceInterface.addVehicle(vehicle);
        } catch (Exception e) {
            throw new AdminServiceException("Error adding vehicle: " + e.getMessage());
        }
    }

    public void deleteVehicle(String vehicleNo) {
        try {
            vehicleServiceInterface.deleteVehicle(vehicleNo);
        } catch (Exception e) {
            throw new AdminServiceException("Error deleting vehicle: " + e.getMessage());
        }
    }

    public List<Vehicle> getAllVehicles() {
        try {
            return vehicleServiceInterface.getAllVehicles();
        } catch (Exception e) {
            throw new AdminServiceException("Error fetching vehicles: " + e.getMessage());
        }
    }

    public Vehicle getVehicleById(String vehicleNo) {
        try {
            return vehicleServiceInterface.getVehicleById(vehicleNo);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Vehicle not found for id: " + vehicleNo);
        }
    }

    public Vehicle updateVehicle(String vehicleNo, Vehicle vehicle) {
        try {
            return vehicleServiceInterface.updateVehicle(vehicleNo, vehicle);
        } catch (Exception e) {
            throw new AdminServiceException("Error updating vehicle: " + e.getMessage());
        }
    }

    public Driver addDriver(Driver driver) {
        try {
            return driverServiceInterface.addDriver(driver);
        } catch (Exception e) {
            throw new AdminServiceException("Error adding driver: " + e.getMessage());
        }
    }

    public List<Driver> getAllDrivers() {
        try {
            return driverServiceInterface.getAllDrivers();
        } catch (Exception e) {
            throw new AdminServiceException("Error fetching drivers: " + e.getMessage());
        }
    }

    public Driver getDriverById(Long id) {
        try {
            return driverServiceInterface.getDriverById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Driver not found for id: " + id);
        }
    }

    public void deleteDriver(Long id) {
        try {
            driverServiceInterface.deleteDriver(id);
        } catch (Exception e) {
            throw new AdminServiceException("Error deleting driver: " + e.getMessage());
        }
    }

    public Driver updateDriver(Long id, Driver driver) {
        try {
            return driverServiceInterface.updateDriver(id, driver);
        } catch (Exception e) {
            throw new AdminServiceException("Error updating driver: " + e.getMessage());
        }
    }
    public List<BookingDto> viewAllBookings() {
        logger.info("Fetching all bookings");

        List<BookingDto> bookings = bookingService.viewAllBookings();
        if (bookings.isEmpty()) {
            logger.warn("No bookings found.");
        }

        return bookings;
    }

    @Override
    public String changePassword(String userName, String oldPassword, String newPassword) {
        Optional<Admin> opcust = adminRepository.findByUsername(userName);

        if (opcust.isPresent()) {
            Admin dbcust = opcust.get();
            if (bcrypt.matches(oldPassword, dbcust.getPassword())) {
                String encrypted = bcrypt.encode(newPassword);
                dbcust.setPassword(encrypted);
                adminRepository.save(dbcust);
                logger.info("Password changed successfully for user: " + userName);
                return "Password changed successfully";
            } else {
                logger.info("Incorrect password for user: " + userName);
                return "Incorrect password";
            }
        } else {
            logger.warn("User not found: " + userName);
            return "User not found";
        }
    }


}
