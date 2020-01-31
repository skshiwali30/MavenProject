package com.sk.ars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.arsbackend.dao.FlightDAO;
import com.sk.arsbackend.dto.Flight;

@Service("bookingService")
public class BookingService {
	
	@Autowired
	private FlightDAO flightDAO;

	public Flight getAvailableFlight(int id) {
		
		Flight flight = flightDAO.get(id);
		
		if(flight!=null && validateAvailability(flight)) 
			return flight;
		
		return null;
	}
	
	public boolean decreaseSeats(Flight flight)
	{
		if(flight!=null && validateAvailability(flight))
		{
			flight.setNoOfSeats(flight.getNoOfSeats()-1);
			return flightDAO.update(flight);
		}
		
		return false;
	}

	private boolean validateAvailability(Flight flight) {
		return flight.getNoOfSeats() > 0;
	}
}
