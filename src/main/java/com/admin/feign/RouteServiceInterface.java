package com.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.dto.Route;



@FeignClient("ROUTESERVICE")
public interface RouteServiceInterface {
	
	@GetMapping("route/ViewAllRoutes")
	public List<Route> getAllRoutes();
	
	@GetMapping("route/ViewRouteById/{routeId}")
	public Route getRouteById(@PathVariable int routeId);
	
	@PutMapping("route/ModifyRoute/{routeId}")
	public Route modifyRoute(@PathVariable int routeId,@RequestBody Route route);
	
	@DeleteMapping("route/DeleteRoute/{routeId}")
	public void deleteRoute(@PathVariable int routeId) ;
	
	@PostMapping("route/Addroute")
	public Route addRoute(@RequestBody Route route) ;

}
