package com.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.admin.dto.BookingDto;


@FeignClient("BOOKINGSERVICE")
public interface BookingServiceInterface {
	 @GetMapping("bookings/viewAllBookings")
	    public List<BookingDto> viewAllBookings();
}
