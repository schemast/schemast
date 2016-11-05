
CREATE TABLE CUSTOMERS(
   ID       INT              NOT NULL,
   NAME     VARCHAR (20)     NOT NULL,
   AGE      INT              NOT NULL,
   ADDRESS  CHAR (25) ,
   SALARY   DECIMAL (18, 2),       
   PRIMARY KEY (ID)
);

CREATE TABLE Orders(
    O_Id        int     NOT NULL,
    OrderNo     int     NOT NULL,
    P_Id        int,
    PRIMARY KEY (O_Id),
    FOREIGN KEY (P_Id) REFERENCES Persons(P_Id)
)
