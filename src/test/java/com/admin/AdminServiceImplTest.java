package com.admin;



import com.admin.dto.Driver;

import com.admin.dto.Route;
import com.admin.dto.Vehicle;
import com.admin.exceptions.AdminServiceException;
import com.admin.exceptions.ResourceNotFoundException;
import com.admin.feign.DriverServiceInterface;
import com.admin.feign.RouteServiceInterface;
import com.admin.feign.VehicleServiceInterface;
import com.admin.repository.AdminRepository;
import com.admin.services.AdminServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private RouteServiceInterface routeServiceInterface;

    @Mock
    private VehicleServiceInterface vehicleServiceInterface;

    @Mock
    private DriverServiceInterface driverServiceInterface;

    private BCryptPasswordEncoder bcrypt;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bcrypt = new BCryptPasswordEncoder();
    }

    @Test
    public void testAddRoute_Success() {
        Route route = new Route(1, "Source", "Destination", 0, 0);
        when(routeServiceInterface.addRoute(any(Route.class))).thenReturn(route);

        Route result = adminService.addRoute(route);
        assertEquals(route, result);
        verify(routeServiceInterface).addRoute(route);
    }

    @Test
    public void testAddRoute_Failure() {
        Route route = new Route(1, "Source", "Destination", 0, 0);
        when(routeServiceInterface.addRoute(any(Route.class))).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.addRoute(route));
        assertEquals("Error adding route: Error", exception.getMessage());
    }

    @Test
    public void testViewAllRoutes_Success() {
        List<Route> routes = new ArrayList<>();
        routes.add(new Route(1, "Source1", "Destination1", 0, 0));
        routes.add(new Route(2, "Source2", "Destination2", 0, 0));
        when(routeServiceInterface.getAllRoutes()).thenReturn(routes);

        List<Route> result = adminService.viewAllRoutes();
        assertEquals(routes, result);
        verify(routeServiceInterface).getAllRoutes();
    }

    @Test
    public void testViewAllRoutes_Failure() {
        when(routeServiceInterface.getAllRoutes()).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.viewAllRoutes());
        assertEquals("Error fetching routes: Error", exception.getMessage());
    }

    @Test
    public void testGetRouteById_Success() {
        Route route = new Route(1, "Source", "Destination", 0, 0);
        when(routeServiceInterface.getRouteById(1)).thenReturn(route);

        Route result = adminService.getRouteById(1);
        assertEquals(route, result);
        verify(routeServiceInterface).getRouteById(1);
    }

    @Test
    public void testGetRouteById_Failure() {
        when(routeServiceInterface.getRouteById(1)).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> adminService.getRouteById(1));
        assertEquals("Route not found for id: 1", exception.getMessage());
    }

    @Test
    public void testModifyRoute_Success() {
        Route route = new Route(1, "Source", "Destination", 0, 0);
        when(routeServiceInterface.modifyRoute(eq(1), any(Route.class))).thenReturn(route);

        Route result = adminService.modifyRoute(1, route);
        assertEquals(route, result);
        verify(routeServiceInterface).modifyRoute(1, route);
    }

    @Test
    public void testModifyRoute_Failure() {
        Route route = new Route(1, "Source", "Destination", 0, 0);
        when(routeServiceInterface.modifyRoute(eq(1), any(Route.class))).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.modifyRoute(1, route));
        assertEquals("Error modifying route: Error", exception.getMessage());
    }

    @Test
    public void testDeleteRoute_Success() {
        doNothing().when(routeServiceInterface).deleteRoute(1);

        assertDoesNotThrow(() -> adminService.deleteRoute(1));
        verify(routeServiceInterface).deleteRoute(1);
    }

    @Test
    public void testDeleteRoute_Failure() {
        doThrow(new RuntimeException("Error")).when(routeServiceInterface).deleteRoute(1);

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.deleteRoute(1));
        assertEquals("Error deleting route: Error", exception.getMessage());
    }

    @Test
    public void testAddVehicle_Success() {
        Vehicle vehicle = new Vehicle(null, "V123", "VehicleName", 0, null, 0);
        when(vehicleServiceInterface.addVehicle(any(Vehicle.class))).thenReturn(vehicle);

        Vehicle result = adminService.addVehicle(vehicle);
        assertEquals(vehicle, result);
        verify(vehicleServiceInterface).addVehicle(vehicle);
    }

    @Test
    public void testAddVehicle_Failure() {
        Vehicle vehicle = new Vehicle(null, "V123", "VehicleName", 0, null, 0);
        when(vehicleServiceInterface.addVehicle(any(Vehicle.class))).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.addVehicle(vehicle));
        assertEquals("Error adding vehicle: Error", exception.getMessage());
    }

    @Test
    public void testGetAllVehicles_Success() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(null, "V123", "VehicleName1", 0, null, 0));
        vehicles.add(new Vehicle(null, "V124", "VehicleName2", 0, null, 0));
        when(vehicleServiceInterface.getAllVehicles()).thenReturn(vehicles);

        List<Vehicle> result = adminService.getAllVehicles();
        assertEquals(vehicles, result);
        verify(vehicleServiceInterface).getAllVehicles();
    }

    @Test
    public void testGetAllVehicles_Failure() {
        when(vehicleServiceInterface.getAllVehicles()).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.getAllVehicles());
        assertEquals("Error fetching vehicles: Error", exception.getMessage());
    }

    @Test
    public void testGetVehicleById_Success() {
        Vehicle vehicle = new Vehicle(null, "V123", "VehicleName", 0, null, 0);
        when(vehicleServiceInterface.getVehicleById("V123")).thenReturn(vehicle);

        Vehicle result = adminService.getVehicleById("V123");
        assertEquals(vehicle, result);
        verify(vehicleServiceInterface).getVehicleById("V123");
    }

    @Test
    public void testGetVehicleById_Failure() {
        when(vehicleServiceInterface.getVehicleById("V123")).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> adminService.getVehicleById("V123"));
        assertEquals("Vehicle not found for id: V123", exception.getMessage());
    }

    @Test
    public void testUpdateVehicle_Success() {
        Vehicle vehicle = new Vehicle(null, "V123", "UpdatedVehicleName", 0, null, 0);
        when(vehicleServiceInterface.updateVehicle(eq("V123"), any(Vehicle.class))).thenReturn(vehicle);

        Vehicle result = adminService.updateVehicle("V123", vehicle);
        assertEquals(vehicle, result);
        verify(vehicleServiceInterface).updateVehicle("V123", vehicle);
    }

    @Test
    public void testUpdateVehicle_Failure() {
        Vehicle vehicle = new Vehicle(null, "V123", "UpdatedVehicleName", 0, null, 0);
        when(vehicleServiceInterface.updateVehicle(eq("V123"), any(Vehicle.class))).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.updateVehicle("V123", vehicle));
        assertEquals("Error updating vehicle: Error", exception.getMessage());
    }

    @Test
    public void testAddDriver_Success() {
        Driver driver = new Driver(1L, "DriverName", null, null, null);
        when(driverServiceInterface.addDriver(any(Driver.class))).thenReturn(driver);

        Driver result = adminService.addDriver(driver);
        assertEquals(driver, result);
        verify(driverServiceInterface).addDriver(driver);
    }

    @Test
    public void testAddDriver_Failure() {
        Driver driver = new Driver(1L, "DriverName", null, null, null);
        when(driverServiceInterface.addDriver(any(Driver.class))).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.addDriver(driver));
        assertEquals("Error adding driver: Error", exception.getMessage());
    }

    @Test
    public void testGetAllDrivers_Success() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver(1L, "Driver1", null, null, null));
        drivers.add(new Driver(2L, "Driver2", null, null, null));
        when(driverServiceInterface.getAllDrivers()).thenReturn(drivers);

        List<Driver> result = adminService.getAllDrivers();
        assertEquals(drivers, result);
        verify(driverServiceInterface).getAllDrivers();
    }

    @Test
    public void testGetAllDrivers_Failure() {
        when(driverServiceInterface.getAllDrivers()).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.getAllDrivers());
        assertEquals("Error fetching drivers: Error", exception.getMessage());
    }

    @Test
    public void testGetDriverById_Success() {
        Driver driver = new Driver(1L, "DriverName", null, null, null);
        when(driverServiceInterface.getDriverById(1L)).thenReturn(driver);

        Driver result = adminService.getDriverById(1L);
        assertEquals(driver, result);
        verify(driverServiceInterface).getDriverById(1L);
    }

    @Test
    public void testGetDriverById_Failure() {
        when(driverServiceInterface.getDriverById(1L)).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> adminService.getDriverById(1L));
        assertEquals("Driver not found for id: 1", exception.getMessage());
    }

    @Test
    public void testDeleteDriver_Success() {
        doNothing().when(driverServiceInterface).deleteDriver(1L);

        assertDoesNotThrow(() -> adminService.deleteDriver(1L));
        verify(driverServiceInterface).deleteDriver(1L);
    }

    @Test
    public void testDeleteDriver_Failure() {
        doThrow(new RuntimeException("Error")).when(driverServiceInterface).deleteDriver(1L);

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.deleteDriver(1L));
        assertEquals("Error deleting driver: Error", exception.getMessage());
    }

    @Test
    public void testUpdateDriver_Success() {
        Driver driver = new Driver(1L, "UpdatedDriverName", null, null, null);
        when(driverServiceInterface.updateDriver(eq(1L), any(Driver.class))).thenReturn(driver);

        Driver result = adminService.updateDriver(1L, driver);
        assertEquals(driver, result);
        verify(driverServiceInterface).updateDriver(1L, driver);
    }

    @Test
    public void testUpdateDriver_Failure() {
        Driver driver = new Driver(1L, "UpdatedDriverName", null, null, null);
        when(driverServiceInterface.updateDriver(eq(1L), any(Driver.class))).thenThrow(new RuntimeException("Error"));

        Exception exception = assertThrows(AdminServiceException.class, () -> adminService.updateDriver(1L, driver));
        assertEquals("Error updating driver: Error", exception.getMessage());
    }
}
