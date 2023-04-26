package com.etour.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.etour.dto.BookingRequest;
import com.etour.entities.Booking_Master;
import com.etour.services.InvoiceManager;

@RestController
@CrossOrigin("http://localhost:3000")
public class InvoiceController {

	@Autowired
	InvoiceManager manager;
	
		@GetMapping("api/invoice")
	 Booking_Master getInvoice()
	 {
		 return manager.printInvoice();
	 }
}
