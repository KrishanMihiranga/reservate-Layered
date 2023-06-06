create database reservate;

create table complaints
(
    ComplaintId       varchar(15)  not null
        primary key,
    Date              date         null,
    time              varchar(15)  null,
    guestid           varchar(15)  null,
    mealorderid       varchar(15)  null,
    hallreservationid varchar(15)  null,
    roomreservationid varchar(15)  null,
    description       varchar(200) null
);

create table employeeDTO
(
    EmpId    varchar(15) not null
        primary key,
    Nic      varchar(15) not null,
    Fullname text        not null,
    Address  text        not null,
    Mobile   varchar(50) not null,
    Date     date        not null,
    JObRole  varchar(60) not null,
    Email    text        not null
);

create table hallDTO
(
    HallNumber varchar(15)    not null
        primary key,
    UserId     varchar(15)    null,
    HallType   varchar(15)    null,
    Price      decimal(15, 2) null,
    Status     varchar(50)    null
);

create table hallmaintenance
(
    HallMaintenanceId varchar(15) not null
        primary key,
    Date              date        null,
    startTime         varchar(15) null,
    EndTime           varchar(15) null,
    HallNumber        varchar(15) null
);

create table meal
(
    PackageId   varchar(15)    not null
        primary key,
    MealPlan    varchar(15)    null,
    MealType    varchar(15)    null,
    Description text           null,
    Price       decimal(15, 2) null
);

create table mealorder
(
    MealOrderId varchar(15) not null
        primary key,
    Qty         int         null,
    GuestId     varchar(15) null,
    PackageId   varchar(15) null,
    Date        date        null
);

create table mealorderdetails
(
    PackageId   varchar(15) null,
    MealOrderId varchar(15) null,
    constraint mealorderdetails_ibfk_1
        foreign key (PackageId) references meal (PackageId),
    constraint mealorderdetails_ibfk_2
        foreign key (MealOrderId) references mealorder (MealOrderId)
);

create index MealOrderId
    on mealorderdetails (MealOrderId);

create index PackageId
    on mealorderdetails (PackageId);

create table paymentDTO
(
    paymentid         varchar(15) not null
        primary key,
    guestid           varchar(15) null,
    MealOrderId       varchar(15) null,
    hallreservationid varchar(15) null,
    roomreservationid varchar(15) null,
    date              date        null,
    time              varchar(15) null,
    amount            double      null
);

create table roomDTO
(
    RoomNumber varchar(15)    not null
        primary key,
    RoomType   varchar(15)    null,
    Price      decimal(15, 2) null,
    Status     varchar(50)    null
);

create table roommaintenance
(
    RoomMaintenanceId varchar(15) not null
        primary key,
    Date              date        null,
    startTime         varchar(15) null,
    EndTime           varchar(15) null,
    RoomNumber        varchar(15) null
);

create table userDTO
(
    UserId   varchar(15) not null,
    EmpId    varchar(15) not null,
    UserName text        null,
    Password varchar(15) null,
    primary key (UserId, EmpId)
);

create table guestDTO
(
    GuestId  varchar(15) not null
        primary key,
    UserId   varchar(15) null,
    Nic      varchar(15) null,
    Fullname text        null,
    Address  text        null,
    Mobile   varchar(50) null,
    Date     date        null,
    Email    text        null,
    constraint guest_ibfk_1
        foreign key (UserId) references userDTO (UserId)
);

create index UserId
    on guestDTO (UserId);

create table hallreservation
(
    CheckIn           date        null,
    CheckOut          date        null,
    HallReservationId varchar(15) not null
        primary key,
    GuestId           varchar(15) null,
    HallNumber        varchar(15) null,
    constraint hallreservation_ibfk_1
        foreign key (GuestId) references guestDTO (GuestId),
    constraint hallreservation_ibfk_2
        foreign key (HallNumber) references hallDTO (HallNumber)
);

create index GuestId
    on hallreservation (GuestId);

create index HallNumber
    on hallreservation (HallNumber);

create table hallreservationdetails
(
    HallReservationId varchar(15) null,
    HallNumber        varchar(15) not null
        primary key,
    constraint hallreservationdetails_ibfk_1
        foreign key (HallNumber) references hallDTO (HallNumber),
    constraint hallreservationdetails_ibfk_2
        foreign key (HallReservationId) references hallreservation (HallReservationId)
);

create index HallReservationId
    on hallreservationdetails (HallReservationId);

create table roomreservation
(
    CheckIn           date        null,
    CheckOut          date        null,
    RoomReservationId varchar(15) not null
        primary key,
    GuestId           varchar(15) null,
    RoomNumber        varchar(15) null,
    constraint roomreservation_ibfk_1
        foreign key (GuestId) references guestDTO (GuestId),
    constraint roomreservation_ibfk_2
        foreign key (RoomNumber) references roomDTO (RoomNumber)
);

create table orders
(
    OrderId           varchar(15)   not null
        primary key,
    GuestId           varchar(15)   null,
    RoomReservationId varchar(15)   null,
    HallReservationId varchar(15)   null,
    MealOrderId       varchar(15)   null,
    UserId            varchar(15)   null,
    ComplaintId       varchar(15)   null,
    OrderDate         date          null,
    Qty               int           null,
    Payments          double(15, 2) null,
    constraint orders_ibfk_1
        foreign key (GuestId) references guestDTO (GuestId),
    constraint orders_ibfk_2
        foreign key (RoomReservationId) references roomreservation (RoomReservationId),
    constraint orders_ibfk_3
        foreign key (HallReservationId) references hallreservation (HallReservationId),
    constraint orders_ibfk_4
        foreign key (MealOrderId) references mealorder (MealOrderId),
    constraint orders_ibfk_5
        foreign key (UserId) references userDTO (UserId)
);

create index GuestId
    on orders (GuestId);

create index HallReservationId
    on orders (HallReservationId);

create index MealOrderId
    on orders (MealOrderId);

create index RoomReservationId
    on orders (RoomReservationId);

create index UserId
    on orders (UserId);

create index GuestId
    on roomreservation (GuestId);

create index RoomNumber
    on roomreservation (RoomNumber);

create table roomreservationdetails
(
    RoomReservationId varchar(15) null,
    RoomNumber        varchar(15) not null
        primary key,
    constraint roomreservationdetails_ibfk_1
        foreign key (RoomNumber) references roomDTO (RoomNumber),
    constraint roomreservationdetails_ibfk_2
        foreign key (RoomReservationId) references roomreservation (RoomReservationId)
);

create index RoomReservationId
    on roomreservationdetails (RoomReservationId);

create index EmpId
    on userDTO (EmpId);

