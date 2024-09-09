package com.admin;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.admin.controllers.AdminController;
import com.admin.dto.BookingDto;
import com.admin.dto.Driver;
import com.admin.dto.Route;
import com.admin.dto.Vehicle;
import com.admin.feign.BookingServiceInterface;
import com.admin.repository.AdminRepository;
import com.admin.services.AdminServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AdminControllerTest {

    @Mock
    private AdminServiceImpl adminServiceImpl;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private BookingServiceInterface bookingService;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void testAddRoute() throws Exception {
        Route route = new Route();
        route.setRouteId(1);
        // Set other fields as necessary

        doReturn(route).when(adminServiceImpl).addRoute(any(Route.class));

        mockMvc.perform(post("/admin/Addroute")
                .contentType("application/json")
                .content("{ \"routeId\": 1 }")) // Provide actual JSON content
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));
    }

    @Test
    void testViewAllRoutes() throws Exception {
        List<Route> routes = new ArrayList<>();
        Route route = new Route();
        route.setRouteId(1);
        // Set other fields as necessary
        routes.add(route);

        doReturn(routes).when(adminServiceImpl).viewAllRoutes();

        mockMvc.perform(get("/admin/ViewAllRoutes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].routeId").value(1));
    }

    @Test
    void testGetRouteById() throws Exception {
        Route route = new Route();
        route.setRouteId(1);
        // Set other fields as necessary

        doReturn(route).when(adminServiceImpl).getRouteById(anyInt());

        mockMvc.perform(get("/admin/ViewRouteById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));
    }

    @Test
    void testModifyRoute() throws Exception {
        Route route = new Route();
        route.setRouteId(1);
        // Set other fields as necessary

        doReturn(route).when(adminServiceImpl).modifyRoute(anyInt(), any(Route.class));

        mockMvc.perform(put("/admin/ModifyRoute/1")
                .contentType("application/json")
                .content("{ \"routeId\": 1 }")) // Provide actual JSON content
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routeId").value(1));
    }

    @Test
    void testDeleteRoute() throws Exception {
        doNothing().when(adminServiceImpl).deleteRoute(anyInt());

        mockMvc.perform(delete("/admin/DeleteRoute/1"))
                .andExpect(status().isOk());

        verify(adminServiceImpl).deleteRoute(1);
    }

    @Test
    void testAddVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNo("ABC123");
        // Set other fields as necessary

        doReturn(vehicle).when(adminServiceImpl).addVehicle(any(Vehicle.class));

        mockMvc.perform(post("/admin/AddVehicle")
                .contentType("application/json")
                .content("{ \"vehicleNo\": \"ABC123\" }")) // Provide actual JSON content
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleNo").value("ABC123"));
    }

    @Test
    void testDeleteVehicle() throws Exception {
        doNothing().when(adminServiceImpl).deleteVehicle(anyString());

        mockMvc.perform(delete("/admin/DeleteVehicle/ABC123"))
                .andExpect(status().isOk());

        verify(adminServiceImpl).deleteVehicle("ABC123");
    }

    @Test
    void testGetAllVehicles() throws Exception {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNo("ABC123");
        // Set other fields as necessary
        vehicles.add(vehicle);

        doReturn(vehicles).when(adminServiceImpl).getAllVehicles();

        mockMvc.perform(get("/admin/ViewAllVehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].vehicleNo").value("ABC123"));
    }

    @Test
    void testGetVehicleById() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNo("ABC123");
        // Set other fields as necessary

        doReturn(vehicle).when(adminServiceImpl).getVehicleById(anyString());

        mockMvc.perform(get("/admin/ViewVehicleById/ABC123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleNo").value("ABC123"));
    }

    @Test
    void testUpdateVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNo("ABC123");
        // Set other fields as necessary

        doReturn(vehicle).when(adminServiceImpl).updateVehicle(anyString(), any(Vehicle.class));

        mockMvc.perform(put("/admin/UpdateVehicle/ABC123")
                .contentType("application/json")
                .content("{ \"vehicleNo\": \"ABC123\" }")) // Provide actual JSON content
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleNo").value("ABC123"));
    }

    @Test
    void testAddDriver() throws Exception {
        Driver driver = new Driver();
        driver.setId(1L);
        // Set other fields as necessary

        doReturn(driver).when(adminServiceImpl).addDriver(any(Driver.class));

        mockMvc.perform(post("/admin/addDriver")
                .contentType("application/json")
                .content("{ \"id\": 1 }")) // Provide actual JSON content
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetAllDrivers() throws Exception {
        List<Driver> drivers = new ArrayList<>();
        Driver driver = new Driver();
        driver.setId(1L);
        // Set other fields as necessary
        drivers.add(driver);

        doReturn(drivers).when(adminServiceImpl).getAllDrivers();

        mockMvc.perform(get("/admin/viewAllDrivers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testGetDriverById() throws Exception {
        Driver driver = new Driver();
        driver.setId(1L);
        // Set other fields as necessary

        doReturn(driver).when(adminServiceImpl).getDriverById(anyLong());

        mockMvc.perform(get("/admin/viewDriverById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDeleteDriver() throws Exception {
        doNothing().when(adminServiceImpl).deleteDriver(anyLong());

        mockMvc.perform(delete("/admin/deleteDriver/1"))
                .andExpect(status().isOk());

        verify(adminServiceImpl).deleteDriver(1L);
    }

    @Test
    void testUpdateDriver() throws Exception {
        Driver driver = new Driver();
        driver.setId(1L);
        // Set other fields as necessary

        doReturn(driver).when(adminServiceImpl).updateDriver(anyLong(), any(Driver.class));

        mockMvc.perform(put("/admin/updateDriver/1")
                .contentType("application/json")
                .content("{ \"id\": 1 }")) // Provide actual JSON content
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testViewAllBookings() throws Exception {
        List<BookingDto> bookings = new ArrayList<>();
        BookingDto bookingDto = new BookingDto();
        // Set properties as necessary
        bookings.add(bookingDto);

        doReturn(bookings).when(bookingService).viewAllBookings();

        mockMvc.perform(get("/admin/viewAllBookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists()); // Adjust based on properties
    }
}
