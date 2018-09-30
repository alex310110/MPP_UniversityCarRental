-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 30, 2018 at 09:07 AM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.1.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `university_car_rental`
--

-- --------------------------------------------------------

--
-- Table structure for table `Car`
--

CREATE TABLE `Car` (
  `carID` bigint(20) NOT NULL,
  `registerationNumber` varchar(20) NOT NULL,
  `carBrand` varchar(20) NOT NULL,
  `color` varchar(20) NOT NULL,
  `rentalFees` int(11) NOT NULL,
  `isSUV` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Car`
--

INSERT INTO `Car` (`carID`, `registerationNumber`, `carBrand`, `color`, `rentalFees`, `isSUV`) VALUES
(1, '11A', 'Toyota', 'Black', 50, 0),
(2, '192AAS', 'Suzuki', 'White', 100, 1),
(3, 'ACC11', 'Nissan', 'Golden', 40, 0),
(4, 'JES1124', 'Honda', 'Black', 100, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

CREATE TABLE `Customer` (
  `customerID` bigint(20) NOT NULL,
  `fName` varchar(20) NOT NULL,
  `lName` varchar(20) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `DOB` datetime NOT NULL,
  `licenseNumber` varchar(20) NOT NULL,
  `universityID` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`customerID`, `fName`, `lName`, `gender`, `DOB`, `licenseNumber`, `universityID`) VALUES
(1, 'Ilia', 'nan', 'Female', '1991-10-10 00:00:00', '1992A1', '19101910'),
(2, 'Ramesh', 'Kumar', 'Male', '1991-11-10 00:00:00', '14588A', '12459852');

-- --------------------------------------------------------

--
-- Table structure for table `CustomerRentCar`
--

CREATE TABLE `CustomerRentCar` (
  `carID` bigint(20) NOT NULL,
  `customerID` bigint(20) NOT NULL,
  `rentalDate` date NOT NULL,
  `bookingStatus` int(11) NOT NULL,
  `CRC_ID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CustomerRentCar`
--

INSERT INTO `CustomerRentCar` (`carID`, `customerID`, `rentalDate`, `bookingStatus`, `CRC_ID`) VALUES
(1, 1, '2018-07-01', 1, 1),
(2, 1, '2018-09-11', 1, 2),
(3, 2, '2018-09-10', 1, 3),
(4, 2, '2018-09-09', -1, 4),
(1, 1, '2018-08-08', 0, 5),
(1, 1, '2018-09-05', -1, 6),
(1, 1, '2018-09-11', -1, 7),
(2, 1, '2018-09-12', -1, 8),
(1, 1, '2018-09-14', -1, 9),
(1, 1, '2018-09-13', 1, 10),
(4, 1, '2018-09-28', 1, 11),
(3, 1, '2018-09-13', 1, 12),
(3, 1, '2018-09-13', 1, 13),
(2, 2, '2018-09-13', 1, 14);

-- --------------------------------------------------------

--
-- Table structure for table `UserProfile`
--

CREATE TABLE `UserProfile` (
  `userId` int(11) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `userPassword` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `UserProfile`
--

INSERT INTO `UserProfile` (`userId`, `userName`, `userPassword`) VALUES
(1, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Car`
--
ALTER TABLE `Car`
  ADD PRIMARY KEY (`carID`);

--
-- Indexes for table `Customer`
--
ALTER TABLE `Customer`
  ADD PRIMARY KEY (`customerID`);

--
-- Indexes for table `CustomerRentCar`
--
ALTER TABLE `CustomerRentCar`
  ADD PRIMARY KEY (`CRC_ID`);

--
-- Indexes for table `UserProfile`
--
ALTER TABLE `UserProfile`
  ADD UNIQUE KEY `userId` (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `UserProfile`
--
ALTER TABLE `UserProfile`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
