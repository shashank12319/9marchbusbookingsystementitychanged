

package com.wittybrains.busbookingsystem.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.wittybrains.busbookingsystem.dto.BookingDTO;
import com.wittybrains.busbookingsystem.dto.BusDTO;
import com.wittybrains.busbookingsystem.dto.UserDTO;
import com.wittybrains.busbookingsystem.model.Booking;
import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.model.TravelSchedule;
import com.wittybrains.busbookingsystem.model.TravelScheduleSeat;
import com.wittybrains.busbookingsystem.model.User;
import com.wittybrains.busbookingsystem.repository.BookingRepository;
import com.wittybrains.busbookingsystem.repository.BusRepository;
import com.wittybrains.busbookingsystem.repository.TravelScheduleRepository;
import com.wittybrains.busbookingsystem.repository.TravelScheduleSeatRepository;
import com.wittybrains.busbookingsystem.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
	private final UserRepository userRepository;
	private final TravelScheduleRepository travelScheduleRepository;
	private final Logger logger = LoggerFactory.getLogger(BookingService.class);

	public BookingService(BookingRepository bookingRepository, BusRepository busRepository,
			TravelScheduleRepository travelScheduleRepository, UserRepository userRepository,
			TravelScheduleSeatRepository travelScheduleSeatRepository) {
		this.bookingRepository = bookingRepository;
		this.userRepository = userRepository;
		this.travelScheduleRepository = travelScheduleRepository;

	}

	public BookingDTO createBooking(BookingDTO bookingDTO) {
		logger.info("Creating booking with bookingDTO: {}", bookingDTO);
		Booking booking = new Booking();

		booking.setBookingCode(UUID.randomUUID());

		UserDTO userDTO = bookingDTO.getUser();
		Optional<User> optionalUser = userRepository.findById(userDTO.getId());
		optionalUser.ifPresent(user -> booking.setUser(user));
		Optional<TravelSchedule> optionalTravelSchedule = travelScheduleRepository
				.findById(bookingDTO.getSchedule().getId());
		optionalTravelSchedule.ifPresent(travelSchedule -> booking.setSchedule(travelSchedule));
		
		booking.setBookingCode(UUID.randomUUID());
		booking.setDateOfBooking(bookingDTO.getDateOfBooking());

		booking.setTotalAmount(bookingDTO.getTotalAmount());
		booking.setSeatStatus(bookingDTO.getSeatStatus());

//		TravelScheduleSeat travelScheduleSeat = new TravelScheduleSeat();
//		travelScheduleSeat.setSeatStatus("booked");

		Booking savedBooking = bookingRepository.save(booking);
		logger.info("Booking created with savedBooking: {}", savedBooking);
		return new BookingDTO(savedBooking);
	}
}
