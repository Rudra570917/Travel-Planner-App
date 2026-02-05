-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 21, 2025 at 07:33 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `travel_dbms`
--

-- --------------------------------------------------------

--
-- Table structure for table `accommodation_booking_data`
--

CREATE TABLE `accommodation_booking_data` (
  `hotel_id` int(11) NOT NULL,
  `hotel_name` varchar(30) DEFAULT NULL,
  `address` varchar(90) DEFAULT NULL,
  `total_expense` double DEFAULT NULL,
  `uid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accommodation_booking_data`
--

INSERT INTO `accommodation_booking_data` (`hotel_id`, `hotel_name`, `address`, `total_expense`, `uid`) VALUES
(215, 'Hilltop Residency', 'Observatory Road, Near Bryant Park, Kodaikanal, Tamil Nadu 624101', 10000, 4);

-- --------------------------------------------------------

--
-- Table structure for table `accommodation_hotels`
--

CREATE TABLE `accommodation_hotels` (
  `hotel_id` int(11) NOT NULL,
  `hotel_name` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `expense_fullday` double DEFAULT NULL,
  `expense_night` double DEFAULT NULL,
  `total_rooms` int(11) DEFAULT NULL,
  `available_rooms` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accommodation_hotels`
--

INSERT INTO `accommodation_hotels` (`hotel_id`, `hotel_name`, `address`, `expense_fullday`, `expense_night`, `total_rooms`, `available_rooms`) VALUES
(201, 'Goa Beach Resort', 'Calangute Beach Road, Calangute, Goa 403516', 5000, 3000, 80, 25),
(202, 'Snow Valley Hotel', 'Log Huts Area, Hadimba Road, Manali, Himachal Pradesh 175131', 6000, 3500, 60, 18),
(203, 'Raj Palace', 'Zorawar Singh Gate, Amer Road, Jaipur, Rajasthan 302002', 4500, 2800, 75, 20),
(204, 'Lakeview Resort', 'Finishing Point Road, Punnamada, Alleppey, Kerala 688013', 7000, 4200, 90, 30),
(205, 'Hilltop Inn', 'Mall Road, Near Christ Church, Shimla, Himachal Pradesh 171001', 4000, 2500, 70, 22),
(206, 'Lake Palace', 'Pichola Lake, City Palace Complex, Udaipur, Rajasthan 313001', 6000, 3700, 100, 40),
(207, 'SeaShell Hotel', 'Marine Hill, Port Blair, Andaman & Nicobar Islands 744101', 10000, 6000, 55, 15),
(208, 'Ladakh Residency', 'Changspa Road, Near Shanti Stupa, Leh, Ladakh 194101', 12000, 8000, 45, 10),
(209, 'Glenburn Estate', 'Peshok Tea Estate, Singtam, Darjeeling, West Bengal 734102', 5000, 3000, 50, 12),
(210, 'Coorg Jungle Camp', 'Herur, Kushalnagar, Coorg, Karnataka 571234', 4500, 2500, 65, 18),
(211, 'Desert Retreat Camp', 'Sam Sand Dunes, Sam Village, Jaisalmer, Rajasthan 345001', 6500, 4000, 40, 12),
(212, 'Houseboat Stay', 'Ghat No. 9, Boulevard Road, Dal Lake, Srinagar, Jammu & Kashmir 190001', 9000, 5500, 35, 8),
(213, 'Sterling Ooty', 'Fern Hill, Ooty, Nilgiris District, Tamil Nadu 643004', 5000, 3200, 85, 28),
(214, 'Ganga Resort', 'Muni Ki Reti, Rishikesh, Tehri Garhwal, Uttarakhand 249201', 3500, 2000, 75, 20),
(215, 'Hilltop Residency', 'Observatory Road, Near Bryant Park, Kodaikanal, Tamil Nadu 624101', 5000, 3000, 60, 12);

-- --------------------------------------------------------

--
-- Table structure for table `bank_account`
--

CREATE TABLE `bank_account` (
  `ac_no` int(11) NOT NULL,
  `bank_name` varchar(30) DEFAULT NULL,
  `ac_holder_name` varchar(30) DEFAULT NULL,
  `card_type` varchar(30) DEFAULT NULL,
  `card_number` decimal(16,0) DEFAULT NULL,
  `upi_id` decimal(10,0) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `transaction_pin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bank_account`
--

INSERT INTO `bank_account` (`ac_no`, `bank_name`, `ac_holder_name`, `card_type`, `card_number`, `upi_id`, `balance`, `transaction_pin`) VALUES
(1, 'BOB', 'PARTH RATHOD', 'DEBIT', 76981481671234, 9867542310, 27025, 1234),
(2, 'BOB', 'ADMIN', 'DEBIT', 8488973712569867, 9867324521, 70400, 3456);

-- --------------------------------------------------------

--
-- Table structure for table `bus_booking_data`
--

CREATE TABLE `bus_booking_data` (
  `ticket_no` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `mobile_no` varchar(10) DEFAULT NULL,
  `bus_no` varchar(10) DEFAULT NULL,
  `seat_no` varchar(30) DEFAULT NULL,
  `source` varchar(30) DEFAULT NULL,
  `destination` varchar(30) DEFAULT NULL,
  `timing` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bus_booking_data`
--

INSERT INTO `bus_booking_data` (`ticket_no`, `name`, `mobile_no`, `bus_no`, `seat_no`, `source`, `destination`, `timing`, `date`, `price`, `u_id`) VALUES
(1, 'VANSH', '9876543213', 'DL-MN-001', 'A1,', 'Delhi', 'Manali', '06:00:00', '2025-07-26', 1200, NULL),
(2, 'HARSHIT', '9876567890', 'DL-MN-001', 'A2,', 'Delhi', 'Manali', '06:00:00', '2025-07-26', 1200, NULL),
(3, 'RUTU', '9876567890', 'DL-MN-001', 'A3,', 'Delhi', 'Manali', '06:00:00', '2025-07-26', 1200, NULL),
(4, 'MITUL', '9876543212', 'DL-MN-001', 'A5,', 'Delhi', 'Manali', '06:00:00', '2025-07-26', 1200, NULL),
(5, 'MIT', '9876543212', 'AB-1671', 'A1,', 'MAHESANA', 'SARKHEJ', '07:00:00', '2025-07-26', 125, NULL),
(6, 'MIT', '9876543212', 'AS-1234', 'A38,', 'AHEMDABAD', 'PATAN', '11:00:00', '2025-07-26', 250, 1),
(7, 'MIT', '9876543212', 'AS-1234', 'A25,', 'AHEMDABAD', 'PATAN', '11:00:00', '2025-07-26', 250, 1),
(8, 'MIT', '9876543212', 'AS-1234', 'A1,', 'AHEMDABAD', 'PATAN', '11:00:00', '2025-07-26', 250, 1),
(9, 'RUDRA', '9876543212', 'AS-1234', 'A2,', 'AHEMDABAD', 'PATAN', '11:00:00', '2025-07-26', 250, 7);

--
-- Triggers `bus_booking_data`
--
DELIMITER $$
CREATE TRIGGER `after_bus_booking_insert` AFTER INSERT ON `bus_booking_data` FOR EACH ROW BEGIN
    INSERT INTO trip_summary(date, source, destination, uid, trip_by)
    VALUES (
        NEW.date,
        NEW.source,
        NEW.destination,
        NEW.u_id,         -- directly use uid from booking table
        'BUS'
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bus_transport`
--

CREATE TABLE `bus_transport` (
  `root_no` int(11) NOT NULL,
  `bus_no` varchar(30) DEFAULT NULL,
  `source` varchar(30) DEFAULT NULL,
  `destination` varchar(30) DEFAULT NULL,
  `timing` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `total_seat` int(11) DEFAULT NULL,
  `available_seat` int(11) DEFAULT NULL,
  `seat_booked` int(11) DEFAULT NULL,
  `seat_no_of_total_seats` text DEFAULT NULL,
  `seat_no_of_available_seats` text DEFAULT NULL,
  `seat_no_of_booked_seats` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bus_transport`
--

INSERT INTO `bus_transport` (`root_no`, `bus_no`, `source`, `destination`, `timing`, `date`, `price`, `total_seat`, `available_seat`, `seat_booked`, `seat_no_of_total_seats`, `seat_no_of_available_seats`, `seat_no_of_booked_seats`) VALUES
(111, 'Ab7181', 'AHEMDABAD', 'MAHESANA', '11:00:00', '2025-08-21', 200, 48, 0, 0, 'A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29, A30, A31, A32, A33, A34, A35, A36, A37, A38, A39, A40, A41, A42, A43, A44, A45, A46, A47, A48', 'A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29, A30, A31, A32, A33, A34, A35, A36, A37, A38, A39, A40, A41, A42, A43, A44, A45, A46, A47, A48', ''),
(112, 'AS-1234', 'AHEMDABAD', 'PATAN', '11:00:00', '2025-08-21', 250, 48, 38, 1, 'A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31,A32,A33,A34,A35,A36,A37,A38,A39,A40', 'A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31,A32,A33,A34,A35,A36,A37,A38,A39,A40', 'A2'),
(125, 'AB-1671', 'MAHESANA', 'SARKHEJ', '07:00:00', '2025-08-21', 125, 48, 47, 1, ' A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29, A30, A31, A32, A33, A34, A35, A36, A37, A38, A39, A40, A41, A42, A43, A44, A45, A46, A47, A48', 'A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29, A30, A31, A32, A33, A34, A35, A36, A37, A38, A39, A40, A41, A42, A43, A44, A45, A46, A47, A48', 'A1'),
(201, 'DL-MN-001', 'Delhi', 'Manali', '06:00:00', '2025-08-21', 1200, 40, 40, 0, 'A1,A2,A3,A4,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31,A32,A33,A34,A35', 'A1,A2,A3,A4,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31,A32,A33,A34,A35', ''),
(202, 'CH-SH-002', 'Chandigarh', 'Shimla', '08:30:00', '2025-08-22', 800, 35, 30, 5, 'A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31,A32,A33,A34,A35', 'A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30', 'A31,A32,A33,A34,A35'),
(203, 'BL-CR-003', 'Bangalore', 'Coorg', '07:15:00', '2025-09-08', 900, 40, 38, 2, 'A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31,A32,A33,A34,A35,A36,A37,A38,A39,A40', 'A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31,A32,A33,A34,A35,A36,A37,A38', 'A39,A40');

-- --------------------------------------------------------

--
-- Table structure for table `flight_booking_data`
--

CREATE TABLE `flight_booking_data` (
  `ticket_no` int(11) NOT NULL,
  `passportNo` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `mobile_no` varchar(10) DEFAULT NULL,
  `airline_name` varchar(30) DEFAULT NULL,
  `flightNumber` varchar(20) DEFAULT NULL,
  `class` varchar(10) DEFAULT NULL,
  `seat_no` varchar(10) DEFAULT NULL,
  `source` varchar(30) DEFAULT NULL,
  `destination` varchar(30) DEFAULT NULL,
  `departuretime` time DEFAULT NULL,
  `arrivaltime` time DEFAULT NULL,
  `flightduration` time DEFAULT NULL,
  `boardingtime` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flight_booking_data`
--

INSERT INTO `flight_booking_data` (`ticket_no`, `passportNo`, `name`, `mobile_no`, `airline_name`, `flightNumber`, `class`, `seat_no`, `source`, `destination`, `departuretime`, `arrivaltime`, `flightduration`, `boardingtime`, `date`, `price`, `u_id`) VALUES
(4, 'A1234567', 'MIT', '9876543221', 'Air India', 'AI245', 'ECONOMY', 'E51', 'Delhi', 'Leh', '06:00:00', '07:15:00', '01:15:00', '05:15:00', '2025-07-26', 6500, 1),
(5, 'A1234567', 'AADI', '9876543215', 'Air India', 'AI245', 'ECONOMY', 'E52', 'Delhi', 'Leh', '06:00:00', '07:15:00', '01:15:00', '05:15:00', '2025-07-26', 6500, 6),
(6, 'A1234567', 'AADI', '9876543215', 'Air India', 'AI245', 'ECONOMY', 'E53', 'Delhi', 'Leh', '06:00:00', '07:15:00', '01:15:00', '05:15:00', '2025-07-26', 6500, 1);

--
-- Triggers `flight_booking_data`
--
DELIMITER $$
CREATE TRIGGER `after_flight_booking_insert` AFTER INSERT ON `flight_booking_data` FOR EACH ROW BEGIN
    INSERT INTO trip_summary(date, source, destination, uid, trip_by)
    VALUES (
        NEW.date,
        NEW.source,
        NEW.destination,
        NEW.u_id,         -- directly use uid
        'FLIGHT'
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `flight_transport`
--

CREATE TABLE `flight_transport` (
  `root_no` int(11) NOT NULL,
  `airline_name` varchar(30) DEFAULT NULL,
  `flightNumber` varchar(30) DEFAULT NULL,
  `source` varchar(30) DEFAULT NULL,
  `destination` varchar(30) DEFAULT NULL,
  `departuretime` time DEFAULT NULL,
  `arrivaltime` time DEFAULT NULL,
  `flightDuration` time DEFAULT NULL,
  `boardingtime` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `classtype` varchar(30) DEFAULT NULL,
  `total_seat` int(11) DEFAULT NULL,
  `available_seat` int(11) DEFAULT NULL,
  `seat_booked` int(11) DEFAULT NULL,
  `seat_no_of_total_seats` text DEFAULT NULL,
  `seat_no_of_available_seats` text DEFAULT NULL,
  `seat_no_of_booked_seats` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flight_transport`
--

INSERT INTO `flight_transport` (`root_no`, `airline_name`, `flightNumber`, `source`, `destination`, `departuretime`, `arrivaltime`, `flightDuration`, `boardingtime`, `date`, `price`, `classtype`, `total_seat`, `available_seat`, `seat_booked`, `seat_no_of_total_seats`, `seat_no_of_available_seats`, `seat_no_of_booked_seats`) VALUES
(501, 'IndiGo', '6E203', 'Bengaluru', 'Kochi', '07:30:00', '09:00:00', '01:30:00', '06:45:00', '2025-08-21', 4500, 'Economy', 60, 60, 0, 'E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,E11,E12,E13,E14,E15,E16,E17,E18,E19,E20,E21,E22,E23,E24,E25,E26,E27,E28,E29,E30,E31,E32,E33,E34,E35,E36,E37,E38,E39,E40,E41,E42,E43,E44,E45,E46,E47,E48,E49,E50,E51,E52,E53,E54,E55,E56,E57,E58,E59,E60', 'E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,E11,E12,E13,E14,E15,E16,E17,E18,E19,E20,E21,E22,E23,E24,E25,E26,E27,E28,E29,E30,E31,E32,E33,E34,E35,E36,E37,E38,E39,E40,E41,E42,E43,E44,E45,E46,E47,E48,E49,E50,E51,E52,E53,E54,E55,E56,E57,E58,E59,E60', ''),
(502, 'Air India', 'AI245', 'Delhi', 'Leh', '06:00:00', '07:15:00', '01:15:00', '05:15:00', '2025-09-01', 6500, 'Economy', 60, 10, 52, 'E51,E52,E53,E54,E55,E56,E57,E58,E59,E60', 'E51,E52,E53,E54,E55,E56,E57,E58,E59,E60', 'E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,E11,E12,E13,E14,E15,E16,E17,E18,E19,E20,E21,E22,E23,E24,E25,E26,E27,E28,E29,E30,E31,E32,E33,E34,E35,E36,E37,E38,E39,E40,E41,E42,E43,E44,E45,E46,E47,E48,E49,E50,E51,E52'),
(503, 'Vistara', 'UK810', 'Kolkata', 'Port Blair', '11:00:00', '13:45:00', '02:45:00', '10:15:00', '2025-08-28', 7200, 'Business', 24, 6, 18, 'B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15,B16,B17,B18,B19,B20,B21,B22,B23,B24', 'B19,B20,B21,B22,B23,B24', 'B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15,B16,B17,B18');

-- --------------------------------------------------------

--
-- Table structure for table `holiday_plans`
--

CREATE TABLE `holiday_plans` (
  `id` int(11) NOT NULL,
  `package_name` varchar(50) DEFAULT NULL,
  `source` varchar(30) DEFAULT NULL,
  `destination` varchar(30) DEFAULT NULL,
  `day_of_plan` int(11) DEFAULT NULL,
  `night_of_plan` int(11) DEFAULT NULL,
  `expense` double DEFAULT NULL,
  `hotel_name` varchar(50) DEFAULT NULL,
  `hotel_expense` double DEFAULT NULL,
  `via_plan` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `holiday_plans`
--

INSERT INTO `holiday_plans` (`id`, `package_name`, `source`, `destination`, `day_of_plan`, `night_of_plan`, `expense`, `hotel_name`, `hotel_expense`, `via_plan`, `date`) VALUES
(1, 'Goa Getaway', 'Mumbai', 'Goa', 3, 2, 15000, 'Goa Beach Resort', 5000, 'train', '2025-08-10'),
(2, 'Manali Adventure', 'Delhi', 'Manali', 5, 4, 18000, 'Snow Valley Hotel', 6000, 'bus', '2025-08-15'),
(3, 'Jaipur Heritage', 'Ahmedabad', 'Jaipur', 4, 3, 14000, 'Raj Palace', 4500, 'train', '2025-08-18'),
(4, 'Kerala Backwaters', 'Bangalore', 'Alleppey', 6, 5, 22000, 'Lakeview Resort', 7000, 'flight', '2025-08-20'),
(5, 'Shimla Escape', 'Chandigarh', 'Shimla', 3, 2, 12000, 'Hilltop Inn', 4000, 'bus', '2025-08-22'),
(6, 'Udaipur Romance', 'Mumbai', 'Udaipur', 4, 3, 16000, 'Lake Palace', 6000, 'train', '2025-08-25'),
(7, 'Andaman Islands', 'Kolkata', 'Port Blair', 5, 4, 30000, 'SeaShell Hotel', 10000, 'flight', '2025-08-28'),
(8, 'Leh Ladakh Trip', 'Delhi', 'Leh', 6, 5, 35000, 'Ladakh Residency', 12000, 'flight', '2025-09-01'),
(9, 'Darjeeling Delight', 'Kolkata', 'Darjeeling', 4, 3, 17000, 'Glenburn Estate', 5000, 'train', '2025-09-05'),
(10, 'Mysore-Coorg Tour', 'Bangalore', 'Coorg', 4, 3, 13000, 'Coorg Jungle Camp', 4500, 'bus', '2025-09-08');

-- --------------------------------------------------------

--
-- Table structure for table `payment_history`
--

CREATE TABLE `payment_history` (
  `id` int(11) NOT NULL,
  `method` varchar(50) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment_history`
--

INSERT INTO `payment_history` (`id`, `method`, `details`, `timestamp`) VALUES
(1, 'DEBIT CARD', '76981481671234', '2025-08-17 21:20:25'),
(2, 'DEBIT CARD', '76981481671234', '2025-08-18 10:48:34'),
(3, 'DEBIT CARD', '76981481671234', '2025-08-18 11:52:49'),
(4, 'DEBIT CARD', '1234', '2025-08-18 11:54:30'),
(5, 'DEBIT CARD', '76981481671234', '2025-08-18 14:51:27'),
(6, 'DEBIT CARD', '76981481671234', '2025-08-19 15:33:47'),
(7, 'DEBIT CARD', '76981481671234', '2025-08-20 19:07:25'),
(13, 'DEBIT CARD', '76981481671234', '2025-08-21 00:20:39'),
(15, 'DEBIT CARD', '76981481671234', '2025-08-21 00:34:12'),
(16, 'DEBIT_CARD', '76981481671234', '2025-08-21 01:12:03'),
(17, 'DEBIT_CARD', '76981481671234', '2025-08-21 01:15:28'),
(18, 'DEBIT_CARD', '76981481671234', '2025-08-21 01:16:48'),
(19, 'DEBIT CARD', '76981481671234', '2025-08-21 08:35:40'),
(20, 'UPI', '9867542310', '2025-08-21 10:00:16'),
(21, 'UPI', '9867542310', '2025-08-21 10:02:01'),
(22, 'UPI', '9867542310', '2025-08-21 10:44:46'),
(23, 'DEBIT_CARD', '76981481671234', '2025-08-21 10:59:24');

-- --------------------------------------------------------

--
-- Table structure for table `train_booking_data`
--

CREATE TABLE `train_booking_data` (
  `ticket_no` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `mobile_no` varchar(10) DEFAULT NULL,
  `train_name` varchar(30) DEFAULT NULL,
  `coach` varchar(1) DEFAULT NULL,
  `seat_no` varchar(30) DEFAULT NULL,
  `source` varchar(30) DEFAULT NULL,
  `destination` varchar(30) DEFAULT NULL,
  `timing` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `train_booking_data`
--

INSERT INTO `train_booking_data` (`ticket_no`, `name`, `mobile_no`, `train_name`, `coach`, `seat_no`, `source`, `destination`, `timing`, `date`, `price`, `u_id`) VALUES
(1, 'RUTU', '9876567890', 'Goa Express', 'A', 'A13,', 'Mumbai', 'Goa', '09:30:00', '2025-07-26', 1800, NULL),
(2, 'MIT', '9876543212', 'Goa Express', 'A', 'A14,', 'Mumbai', 'Goa', '09:30:00', '2025-07-26', 1800, NULL),
(3, 'MIT', '9876543212', 'Goa Express', 'B', 'B15,', 'Mumbai', 'Goa', '09:30:00', '2025-07-26', 1800, 1),
(4, 'AADI', '9876543213', 'Goa Express', 'D', 'D14,', 'Mumbai', 'Goa', '09:30:00', '2025-07-26', 1800, 1),
(5, 'harshit', '9725409345', 'Goa Express', 'B', 'B14,B13,', 'Mumbai', 'Goa', '09:30:00', '2025-07-26', 3600, 7);

--
-- Triggers `train_booking_data`
--
DELIMITER $$
CREATE TRIGGER `after_train_booking_insert` AFTER INSERT ON `train_booking_data` FOR EACH ROW BEGIN
    INSERT INTO trip_summary(date, source, destination, uid, trip_by)
    VALUES (
        NEW.date,
        NEW.source,
        NEW.destination,
        NEW.u_id,         -- directly use uid
        'TRAIN'
    );
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `train_transport`
--

CREATE TABLE `train_transport` (
  `root_no` int(11) NOT NULL,
  `train_name` varchar(30) DEFAULT NULL,
  `source` varchar(30) DEFAULT NULL,
  `destination` varchar(30) DEFAULT NULL,
  `timing` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `total_coach` int(11) DEFAULT NULL,
  `total_seat` int(11) DEFAULT NULL,
  `available_seat` int(11) DEFAULT NULL,
  `seat_booked` int(11) DEFAULT NULL,
  `seat_no_of_total_seats` text DEFAULT NULL,
  `seat_no_of_available_seats` text DEFAULT NULL,
  `seat_no_of_booked_seats` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `train_transport`
--

INSERT INTO `train_transport` (`root_no`, `train_name`, `source`, `destination`, `timing`, `date`, `price`, `total_coach`, `total_seat`, `available_seat`, `seat_booked`, `seat_no_of_total_seats`, `seat_no_of_available_seats`, `seat_no_of_booked_seats`) VALUES
(401, 'Goa Express', 'Mumbai', 'Goa', '09:30:00', '2025-08-21', 1800, 4, 60, 9, 2, 'A13,A14,A15,B15,C13,C14,C15,D13,D15', 'A13,A14,A15,B13,B14,B15,C13,C14,C15,D13,D15', 'B14,B13'),
(402, 'Pink City SF', 'Ahmedabad', 'Jaipur', '07:00:00', '2025-08-21', 1500, 4, 60, 60, 0, 'A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,D1,D2,D3,D4,D5,D6,D7,D8,D9,D10,D11,D12,D13,D14,D15', 'A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,D1,D2,D3,D4,D5,D6,D7,D8,D9,D10,D11,D12,D13,D14,D15', ''),
(403, 'Himalayan Queen', 'Chandigarh', 'Shimla', '07:15:00', '2025-08-22', 600, 2, 40, 10, 30, 'A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15,B16,B17,B18,B19,B20', 'A16,A17,A18,A19,A20,B16,B17,B18,B19,B20', 'A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15');

-- --------------------------------------------------------

--
-- Table structure for table `trip_summary`
--

CREATE TABLE `trip_summary` (
  `sid` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `Source` varchar(30) DEFAULT NULL,
  `destination` varchar(30) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `trip_by` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `trip_summary`
--

INSERT INTO `trip_summary` (`sid`, `date`, `Source`, `destination`, `uid`, `trip_by`) VALUES
(1, '2025-08-15', 'Delhi', 'Manali', 1, 'BUS'),
(2, '2025-08-15', 'Delhi', 'Manali', 1, 'BUS'),
(3, '2025-08-15', 'Delhi', 'Manali', 4, 'BUS'),
(4, '2025-08-20', 'Mumbai', 'Goa', 4, 'TRAIN'),
(5, '2025-08-20', 'Mumbai', 'Goa', 1, 'TRAIN'),
(6, '2025-08-15', 'Delhi', 'Manali', 1, 'BUS'),
(7, '2025-08-21', 'MAHESANA', 'SARKHEJ', 1, 'BUS'),
(10, '2025-07-26', 'Delhi', 'Leh', 1, 'FLIGHT'),
(11, '2025-07-26', 'Mumbai', 'Goa', 1, 'TRAIN'),
(12, '2025-07-26', 'AHEMDABAD', 'PATAN', 1, 'BUS'),
(13, '2025-07-26', 'AHEMDABAD', 'PATAN', 1, 'BUS'),
(14, '2025-07-26', 'AHEMDABAD', 'PATAN', 1, 'BUS'),
(15, '2025-07-26', 'Mumbai', 'Goa', 1, 'TRAIN'),
(16, '2025-07-26', 'Delhi', 'Leh', 6, 'FLIGHT'),
(17, '2025-07-26', 'Delhi', 'Leh', 1, 'FLIGHT'),
(18, '2025-07-26', 'Mumbai', 'Goa', 7, 'TRAIN'),
(19, '2025-07-26', 'AHEMDABAD', 'PATAN', 7, 'BUS');

-- --------------------------------------------------------

--
-- Table structure for table `user_data`
--

CREATE TABLE `user_data` (
  `user_id` int(11) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `mobile_no` varchar(10) DEFAULT NULL,
  `pass` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_data`
--

INSERT INTO `user_data` (`user_id`, `name`, `mobile_no`, `pass`) VALUES
(1, 'MIT', '9876543212', '12345MIT'),
(2, 'BHAVASAR RUDRA', '9876545678', 'Rudra@12'),
(3, 'ADMIN', '9876543780', 'Admin@12'),
(4, 'RUTU', '9876567890', 'RUTU@123'),
(5, 'Pasu', '9876542729', 'Ps8767@#'),
(6, 'AADI', '9876543215', '1234AADI'),
(7, 'harshit', '9794424823', 'Tiwari@2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accommodation_booking_data`
--
ALTER TABLE `accommodation_booking_data`
  ADD PRIMARY KEY (`hotel_id`),
  ADD KEY `uid` (`uid`);

--
-- Indexes for table `accommodation_hotels`
--
ALTER TABLE `accommodation_hotels`
  ADD PRIMARY KEY (`hotel_id`);

--
-- Indexes for table `bank_account`
--
ALTER TABLE `bank_account`
  ADD PRIMARY KEY (`ac_no`);

--
-- Indexes for table `bus_booking_data`
--
ALTER TABLE `bus_booking_data`
  ADD PRIMARY KEY (`ticket_no`);

--
-- Indexes for table `bus_transport`
--
ALTER TABLE `bus_transport`
  ADD PRIMARY KEY (`root_no`);

--
-- Indexes for table `flight_booking_data`
--
ALTER TABLE `flight_booking_data`
  ADD PRIMARY KEY (`ticket_no`);

--
-- Indexes for table `flight_transport`
--
ALTER TABLE `flight_transport`
  ADD PRIMARY KEY (`root_no`);

--
-- Indexes for table `holiday_plans`
--
ALTER TABLE `holiday_plans`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment_history`
--
ALTER TABLE `payment_history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `train_booking_data`
--
ALTER TABLE `train_booking_data`
  ADD PRIMARY KEY (`ticket_no`);

--
-- Indexes for table `train_transport`
--
ALTER TABLE `train_transport`
  ADD PRIMARY KEY (`root_no`);

--
-- Indexes for table `trip_summary`
--
ALTER TABLE `trip_summary`
  ADD PRIMARY KEY (`sid`);

--
-- Indexes for table `user_data`
--
ALTER TABLE `user_data`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bus_booking_data`
--
ALTER TABLE `bus_booking_data`
  MODIFY `ticket_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `flight_booking_data`
--
ALTER TABLE `flight_booking_data`
  MODIFY `ticket_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `holiday_plans`
--
ALTER TABLE `holiday_plans`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `payment_history`
--
ALTER TABLE `payment_history`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `train_booking_data`
--
ALTER TABLE `train_booking_data`
  MODIFY `ticket_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `trip_summary`
--
ALTER TABLE `trip_summary`
  MODIFY `sid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `user_data`
--
ALTER TABLE `user_data`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accommodation_booking_data`
--
ALTER TABLE `accommodation_booking_data`
  ADD CONSTRAINT `accommodation_booking_data_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user_data` (`user_id`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `reset_train_seats_daily` ON SCHEDULE EVERY 1 DAY STARTS '2025-08-21 10:48:14' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    UPDATE train_transport
    SET available_seat = total_seat,
        seat_booked = 0,
        seat_no_of_available_seats = seat_no_of_total_seats,
        seat_no_of_booked_seats = '',
        date = CURDATE()
    WHERE date < CURDATE();
END$$

CREATE DEFINER=`root`@`localhost` EVENT `reset_flight_seats_daily` ON SCHEDULE EVERY 1 DAY STARTS '2025-08-21 10:48:43' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    UPDATE flight_transport
    SET available_seat = total_seat,
        seat_booked = 0,
        seat_no_of_available_seats = seat_no_of_total_seats,
        seat_no_of_booked_seats = '',
        date = CURDATE()
    WHERE date < CURDATE();
END$$

CREATE DEFINER=`root`@`localhost` EVENT `reset_bus_seats_daily` ON SCHEDULE EVERY 1 DAY STARTS '2025-08-21 10:49:20' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    UPDATE bus_transport
    SET available_seat = total_seat,
        seat_booked = 0,
        seat_no_of_available_seats = seat_no_of_total_seats,
        seat_no_of_booked_seats = '',
        date = CURDATE()
    WHERE date < CURDATE();
END$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
