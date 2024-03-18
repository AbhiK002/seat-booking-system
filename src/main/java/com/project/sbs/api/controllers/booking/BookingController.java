package com.project.sbs.api.controllers.booking;

import com.project.sbs.api.responses.AnyListResponse;
import com.project.sbs.api.responses.ErrorResponse;
import com.project.sbs.api.responses.SimpleResponse;
import com.project.sbs.api.services.user.UserBookingService;
import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Floor;
import com.project.sbs.database.entities.Office;
import com.project.sbs.database.entities.Seat;
import com.project.sbs.database.repositories.OfficeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {

    private final UserBookingService userBookingService;

    public BookingController(UserBookingService userBookingService) {
        this.userBookingService = userBookingService;
    }

    @GetMapping("/offices")
    public SimpleResponse getOffices(
            @RequestHeader("Authorization") String token)
    {
        List<Office> offices= userBookingService.getAllOffices();
        return new AnyListResponse<Office>(offices,true);
    }

    @GetMapping("/floors/{office-id}")
    public SimpleResponse getFloors(
            @RequestHeader("Authorization") String token,
            @PathVariable("office-id") Integer officeId
            )
    {
        List<Floor> floors= userBookingService.getAllFloorsWithOfficeId(officeId);
        if(floors==null)return new ErrorResponse("office does not exists");
        return new AnyListResponse<Floor>(floors,true);
    }
//
    @GetMapping("/seats/{floor-id}")
    public SimpleResponse getSeats(
            @RequestHeader("Authorization") String token,
            @PathVariable("floor-id") Integer floorId
    )
    {
        List<Seat> seats=userBookingService.getAllSeatsWithFloorId(floorId);
        if(seats==null)return new ErrorResponse("Floor does not exist ");
        return new AnyListResponse<Seat>(seats,true);
    }


}
