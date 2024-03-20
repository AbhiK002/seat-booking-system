# Office Nest Seat Booking System

## Team Members and Roles

- **Abhineet Kelley (Backend)**
    - Database planning
    - Authentication (EP1-OSB1 and EP1-OSB2)
    - User Profile Management (EP1-OSB3)
    - User Bookings Page (EP3-OSB1 and EP3-OSB2)

- **Mohammad Ali Hassan (Backend)**
    - API planning
    - Admin Dashboard (EP2-OSB1 and EP2-OSB3)
    - Manage My Infrastructure (EP2-OSB2)
    - Bookings Approval (EP2-OSB4)
    - Seat Swap approval (EP3-OSB3)

- **Sukhad Sharma (Frontend)**
    - UI and App Flow
    - User Bookings Page UI (EP3-OSB2)
    - User Profile UI (EP1-OSB3)
    - User Registration and Login UI (EP1-OSB1, EP1-OSB2 and EP2-OSB1)
    - Admin Dashboard UI (EP2-OSB1 and EP2-OSB3)
    - Infrastructure Management System UI (EP2-OSB2)

## Features

### App Flow
![image](https://github.com/AbhiK002/seat-booking-system/assets/68178267/3d44fec2-d02e-4ccd-bd8b-fbd32798eff4)

### Seat Features
- Seat location: Office (tech hub, HQ), Floor (1st, 2nd, 3rd), Seat (unique seat number for each floor)
- Coordinates: Office-Floor-Seat (Example: HQ-2-B3)

### User Features
- Register
    - Verify email via OTP
    - Validate inputs
- Login
    - Login with registered email and password
    - Validate inputs
- Booking System
    - Book a seat with a schedule
        - Check if the seat is not taken
        - Start Time, End Time
    - Search for a seat
        - Office, Floor, Seat
        - Green: booked, Grey: unavailable
        - No collisions with another booking schedule
- Profile
    - Edit details
    - Change password
    - Logout
- Dashboard
    - Current datetime visible in the navbar for convenience
    - Send a cancellation request to admin
    - View pending cancellation requests
    - View pending swap requests
    - View my bookings

### Admin Features
- Login (same as user)
- Booking system
    - Accept/reject booking request
- Seat Management
    - Add/Remove/Edit Locations/Floors/Seats
    - If removing a seat that’s already booked, give a warning to admin

## Database Schema

### User
- user_id SERIAL PRIMARY KEY
- user_fullname VARCHAR
- user_email VARCHAR UNIQUE
- user_password (hashed) VARCHAR(70)
- user_roles VARCHAR { comma separated roles in a string }

### Office
- office_id SERIAL PRIMARY KEY
- office_name VARCHAR

### Floor
- floor_id SERIAL PRIMARY KEY
- floor_number INTEGER
- floor_office_id Office FOREIGN KEY

### Seat
- seat_id SERIAL PRIMARY KEY
- seat_number VARCHAR
- seat_type VARCHAR (“SEAT”, “CUBICLE”)
- seat_floor_id Floor FOREIGN KEY
- seat_booked BOOLEAN

### Booking
- booking_id SERIAL PRIMARY KEY
- booking_user_id User FOREIGN KEY
- booking_floor_id Floor FOREIGN KEY
- booking_seat_id Seat FOREIGN KEY
- booking_start_datetime
- booking_end_datetime
- booking_status { PENDING, ACCEPTED, REJECTED }

### (Swap)Request
- request_id SERIAL PRIMARY KEY
- request_booking_1_id FOREIGN KEY
- request_booking_2_id FOREIGN KEY
- request_status { PENDING, ACCEPTED, REJECTED }

### Cancellations
- cancellation_id SERIAL PRIMARY KEY
- cancellation_user_id FOREIGN KEY
- cancellation_booking_id FOREIGN KEY
- cancellation_status { PENDING, ACCEPTED, REJECTED }

## API Endpoints

### Landing/Login/Register Page

- POST /register
    - Request:
        - user_fullname (String)
        - user_email (String)
        - user_password (String)
        - user_otp (String)
    - Response:
        - data
            - user_id
            - user_fullname
            - user_email
            - user_roles
            - token
        - success: true


- POST /verify-email
    - Request:
        - email (String)
    - Response:
        - true (OTP sent successfully)
        - false (Some error occurred)


- POST /login
    - Request:
        - user_email
        - user_password
    - Response:
        - data
            - user_id
            - user_fullname
            - user_email
            - user_roles
            - token
        - success: true


- POST /autologin
    - Headers:
        - Authorization = token (String)
    - Response:
        - data
            - user_id
            - user_fullname
            - user_email
            - user_roles
            - token
        - success: true


### User Dashboard

- GET /user/incoming-swap-requests
    - Headers:
        - Authorization = token (String)
    - Response:
        - data
            - list of Swap Requests
        - success: true or false


- POST /user/modify-swap-request
    - Headers:
        - Authorization = token(String)
    - Request:
        - Request_id
        - Accepted:true or false
    - Response:
        - Success:(true or false)


- GET /user/bookings
    - Headers:
        - Authorization = token (String)
    - Response:
        - data:
            - list of Booking objects
        - success: true


- GET /user/cancellations
    - Headers:
        - Authorization = token (String)
    - Response:
        - data:
            - list of Cancellation objects
        - success: true


- POST /user/cancellation
    - Headers:
        - Authorization = token (String)
    - Request:
        - booking_id
    - Response:
        - data:
            - Cancellation object
        - success: true

### User Profile Page


- PATCH /user/change-password
    - Headers:
        - Authorization = token (String)
    - Request:
        - user_old_password
        - user_new_password
    - Response (correct old password):
        - success: true
    - Response (wrong old password):
        - success: false


- PATCH /user/edit-details [ NAME for now ]
    - Headers:
        - Authorization = token (String)
    - Request:
        - user_fullname
    - Response:
        - success: true or false

### Booking Page

- GET /offices
    - Headers:
        - Authorization = token (String)
    - Response:
        - data:
            - list of Office objects
        - success: true


- GET /floors/{office_id}
    - URL Parameters:
        - office_id
    - Headers:
        - Authorization = token (String)
    - Response:
        - data:
            - list of Floor objects
        - success: true


- GET /seats/{floor_id} [ Dependent on prev 2 reqs, officeID, floor ID ]
    - Headers:
        - Authorization = token (String)
    - URL Parameters:
        - floor_id
    - Response:
        - data
            - list of Seat objects
        - success: true


- GET /seat/details/{floor_id}
    - Headers:
        - Authorization = token (String)
    - URL Parameters:
        - floor_id
    - Response:
        - data
            - list of Booking objects
        - success: true


- POST /seat/book [Can book multiple seats for now]
    - Headers:
        - Authorization = token (String)
    - Request:
        - floor_id
        - seat_id
        - start_datetime
        - end_datetime
    - Response:
        - data
            - Booking object
        - success: true


- POST /seat/swap
    - Headers:
        - Authorization = token (String)
    - Request:
        - Data
            - Booking object
    - Response:
        - Data
            - Swap request object (status:pending)
        - success:true

### Admin Dashboard

- GET /bookings (status: pending)
    - Headers:
        - Authorization = token (String)
    - Response:
        - data
            - list of Booking objects with booking_status: PENDING
        - success: true


- PATCH/modify-booking
    - Headers:
        - Authorization = token (String)
    - Request:
        - booking_id
        - accepted: true or false
    - Response:
        - data
            - Booking object
        - success: true


- GET /cancellations
    - Headers: **
    - Response:
        - data
            - list of Cancellations
        - success: true


- POST /modify-cancellation
    - Headers: **
    - Request:
        - cancellation_id Cancellation
        - accepted: true or false

### IMS (Infrastructure Management System)

- (GET offices, floors/office-id, POST get-seats from before)


- POST /office?office_name=OFFICE_NAME
    - Headers:
        - Authorization = token (String)
    - URL Parameter:
        - office_name (String)
    - Response:
        - data
            - Office object
        - success: true


- POST /floor?floor_number=NUMBER&office_id=OFFICE_ID
    - Headers:
        - Authorization = token (String)
    - URL Parameters:
        - office_id
        - floor_number
    - Response:
        - data
            - Floor object
        - success: true


- POST /seats (Create multiple seats for a floor ID)
    - Headers:
        - Authorization = token (String)
    - Request:
        - seats
            - list of Seat objects
                - Each Seat object contains:
                    - floor_id ->Floor
                    - seat_number ->String
                    - seat_type: CUBICLE or SEAT
    - Response:
        - success: true
