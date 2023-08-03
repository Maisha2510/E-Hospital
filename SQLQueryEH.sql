-- department table
create table Department
(
    SerialNo       int identity
        constraint PK_Department
            primary key,
    DepartmentName varchar(50) not null,
    Description    varchar(255),
    Status         varchar(50) not null,
    DepartmentID   varchar(50)
);

-- doctor table
create table DoctorTable
(
    SerialNo     int identity
        primary key,
    FirstName    varchar(max),
    LastName     varchar(max),
    Department   varchar(max),
    EmailAddress varchar(max),
    MobileNo     varchar(max),
    PhoneNo      varchar(max),
    Address      varchar(max),
    Sex          varchar(max),
    BloodGroup   varchar(max),
    DOB          date,
    JoinDate     date,
    Status       varchar(max),
    Fees         int,
    DoctorId     as concat(left([Department], 3), [SerialNo], right([MobileNo], 3))
);

-- patient table
create table Patient
(
    SerialNo     int identity,
    FirstName    varchar(max),
    LastName     varchar(max),
    EmailAddress varchar(max),
    MobileNo     varchar(max),
    PhoneNo      varchar(max),
    Address      varchar(max),
    Gender       varchar(max),
    BloodGroup   varchar(max),
    DOB          date,
    CreateDate   date,
    Status       varchar(max),
    PatientId    as concat(left([Gender], 3), [SerialNo], right([MobileNo], 3))
);

--login info table
create table LoginInfo
(
    SerialNo int         not null
        constraint PK_LoginInfo
            primary key,
    UserName varchar(50) not null,
    Password varchar(50) not null,
    UserRole varchar(50)
);

-- CREATE TABLE Product
-- (
--     ID INTEGER IDENTITY(1,1) NOT NULL
--         CONSTRAINT UC_Product_ID UNIQUE,
--     Product_No AS RIGHT ('PDT0000' + CAST(ID AS VARCHAR(10)), 10) PERSISTED
--            CONSTRAINT PK_Product PRIMARY KEY CLUSTERED,
--     Product_Name VARCHAR(50) NOT NULL
-- )
