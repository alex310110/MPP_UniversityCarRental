USE
    db GO
CREATE TABLE 'car'(
    'carID' BIGINT NOT NULL,
    'registerationNumber' VARCHAR(20) NOT NULL,
    'carBrand' VARCHAR(20) NOT NULL,
    'color' VARCHAR(20) NOT NULL,
    'rentalFees' INT NOT NULL,
    'isSUV' INT NOT NULL,
    PRIMARY KEY('carID')
) CREATE TABLE customer(
    customerID BIGINT NOT NULL,
    fName VARCHAR(20) NOT NULL,
    lName VARCHAR(20) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    DOB DATETIME NOT NULL,
    licenseNumber VARCHAR(20) NOT NULL,
    universityID VARCHAR(20) NOT NULL,
    PRIMARY KEY(customerID)
) CREATE TABLE CustomerRentCar(
    carID BIGINT NOT NULL,
    customerID BIGINT NOT NULL,
    rentalDate DATE NOT NULL,
    bookingStatus INT NOT NULL,
    CRC_ID BIGINT NOT NULL,
    PRIMARY KEY(CRC_ID)
) CREATE TABLE UserProfile(
    userId INT NOT NULL,
    userName VARCHAR(20) NOT NULL,
    userPassword VARCHAR(20) NOT NULL
) INSERT
    Car(
        carID,
        registerationNumber,
        carBrand,
        color,
        rentalFees,
        isSUV
    ) VALUES(
        1,
        N '11A',
        N 'Toyota',
        N 'Black',
        50,
        0
    )
INSERT
    Car(
        carID,
        registerationNumber,
        carBrand,
        color,
        rentalFees,
        isSUV
    ) VALUES(
        2,
        N '192AAS',
        N 'Suzuki',
        N 'White',
        100,
        1
    )
INSERT
    Car(
        carID,
        registerationNumber,
        carBrand,
        color,
        rentalFees,
        isSUV
    ) VALUES(
        3,
        N 'ACC11',
        N 'Nissan',
        N 'Golden',
        40,
        0
    )
INSERT
    Car(
        carID,
        registerationNumber,
        carBrand,
        color,
        rentalFees,
        isSUV
    ) VALUES(
        4,
        N 'JES1124',
        N 'Honda',
        N 'Black',
        100,
        1
    )
INSERT
    Customer(
        customerID,
        fName,
        lName,
        gender,
        DOB,
        licenseNumber,
        universityID
    ) VALUES(
        1,
        N 'Ilia',
        N 'nan',
        N 'Female',
        CAST(
            N '1991-10-10T00:00:00.000' AS DATETIME
        ),
        N '1992A1',
        N '19101910'
    )
INSERT
    Customer(
        customerID,
        fName,
        lName,
        gender,
        DOB,
        licenseNumber,
        universityID
    ) VALUES(
        2,
        N 'Ramesh',
        N 'Kumar',
        N 'Male',
        CAST(
            N '1991-11-10T00:00:00.000' AS DATETIME
        ),
        N '14588A',
        N '12459852'
    )
INSERT
    CustomerRentCar(
        carID,
        customerID,
        rentalDate,
        bookingStatus,
        CRC_ID
    ) VALUES(
        1,
        1,
        CAST(N '2018-07-01' AS DATE),
        1,
        1
    )
INSERT
    CustomerRentCar(
        carID,
        customerID,
        rentalDate,
        bookingStatus,
        CRC_ID
    ) VALUES(
        2,
        1,
        CAST(N '2018-09-11' AS DATE),
        1,
        2
    )
INSERT
    CustomerRentCar(
        carID,
        customerID,
        rentalDate,
        bookingStatus,
        CRC_ID
    ) VALUES(
        3,
        2,
        CAST(N '2018-09-10' AS DATE),
        1,
        3
    )
INSERT
    CustomerRentCar(
        carID,
        customerID,
        rentalDate,
        bookingStatus,
        CRC_ID
    ) VALUES(
        4,
        2,
        CAST(N '2018-09-09' AS DATE),
        -1,
        4
    )
INSERT
    CustomerRentCar(
        carID,
        customerID,
        rentalDate,
        bookingStatus,
        CRC_ID
    ) VALUES(
        1,
        1,
        CAST(N '2018-08-08' AS DATE),
        0,
        5
    )
INSERT
    CustomerRentCar(
        carID,
        customerID,
        rentalDate,
        bookingStatus,
        CRC_ID
    ) VALUES(
        1,
        1,
        CAST(N '2018-09-05' AS DATE),
        -1,
        6
    )
INSERT
    CustomerRentCar(
        carID,
        customerID,
        rentalDate,
        bookingStatus,
        CRC_ID
    ) VALUES(
        1,
        1,
        CAST(N '2018-09-11' AS DATE),
        -1,
        7
    )
INSERT
    CustomerRentCar(
        carID,
        customerID,
        rentalDate,
        bookingStatus,
        CRC_ID
    ) VALUES(
        2,
        1,
        CAST(N '2018-09-12' AS DATE),
        -1,
        8
    )
INSERT
    CustomerRentCar(
        carID,
        customerID,
        rentalDate,
        bookingStatus,
        CRC_ID
    ) VALUES(
        1,
        1,
        CAST(N '2018-09-14' AS DATE),
        -1,
        9
    )
INSERT
    UserProfile(userId, userName, userPassword) VALUES(1, N 'admin', N 'admin')
ALTER TABLE
    Car ADD DEFAULT((0)) FOR isSUV
ALTER TABLE
    CustomerRentCar WITH
CHECK ADD FOREIGN KEY
    (carID) REFERENCES Car(carID) GO
ALTER TABLE
    CustomerRentCar WITH
CHECK ADD FOREIGN KEY
    (customerID) REFERENCES Customer(customerID) GO
    
    
--    INSERT INTO `UserProfile` (`userId`, `userName`, `userPassword`) VALUES ('1', 'admin', 'admin');
    
--    ALTER TABLE `CustomerRentCar` ADD FOREIGN KEY (`carID`) REFERENCES `Car`(`carID`) ON DELETE RESTRICT ON UPDATE RESTRICT;