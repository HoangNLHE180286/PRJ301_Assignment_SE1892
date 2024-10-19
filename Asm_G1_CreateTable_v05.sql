-- Intitialize
USE master
GO

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'Asm_G1')
BEGIN
	ALTER DATABASE Asm_G1 SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE Asm_G1 SET ONLINE;
	DROP DATABASE Asm_G1;
END
GO

CREATE DATABASE Asm_G1;
GO

USE Asm_G1
GO

-- 1
CREATE TABLE UserAccounts(
UserID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
Username NVARCHAR(60) NOT NULL,
[Password] NVARCHAR(20) NOT NULL,
Email NVARCHAR(200) NOT NULL CHECK (Email LIKE '%@%.%'),
CreatedDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
Phone NVARCHAR(10) NOT NULL
);
GO

-- 2
CREATE TABLE Categories( 
CategoryID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
CategoryName NVARCHAR (60),
[Description] TEXT
);
GO

-- 3
CREATE TABLE Products(
ProductID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
ProductName NVARCHAR(300) NOT NULL,
CategoryID INT FOREIGN KEY REFERENCES Categories(CategoryID) ON DELETE CASCADE,
UnitsInStock INT NOT NULL
);
GO

-- 4
CREATE TABLE Payments(
PaymentID NVARCHAR (10) NOT NULL PRIMARY KEY,
UserID INT NOT NULL FOREIGN KEY REFERENCES UserAccounts(UserID) ON DELETE CASCADE,
PaymentMethod NVARCHAR(50),
PaymentDate DATETIME DEFAULT CURRENT_TIMESTAMP,
Price DECIMAL(10,3) DEFAULT NULL
);
GO

-- 5
CREATE TABLE Orders(
OrderID NVARCHAR (10) NOT NULL PRIMARY KEY,
OrderDate DATETIME,
Freight MONEY,
RequiredDate DATETIME,
PaymentID NVARCHAR (10) FOREIGN KEY REFERENCES Payments(PaymentID) DEFAULT NULL
);
GO

-- 6
CREATE TABLE OrderDetails(
OrderID NVARCHAR (10) NOT NULL,
ProductID INT NOT NULL,
Quantity INT,
UnitPrice DECIMAL(10, 3),
Discount REAL,
PRIMARY KEY (OrderID, ProductID),
FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE,
FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);
GO
	
-- 7
CREATE TABLE Reviews(
UserID INT NOT NULL FOREIGN KEY REFERENCES UserAccounts(UserID) ON DELETE CASCADE,
ProductID INT NOT NULL FOREIGN KEY REFERENCES Products(ProductID) ON DELETE CASCADE,
Rating FLOAT NOT NULL CHECK(Rating >= 1 AND Rating <= 5),
ReviewDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT pk_review PRIMARY KEY(UserID, ProductID)
);
GO

-- Function
CREATE VIEW CalPriceInPayments
AS
SELECT p.PaymentID, SUM((od.Quantity * od.UnitPrice)*(1-od.Discount)) + o.Freight AS TotalPrice
FROM Payments AS p
INNER JOIN Orders AS o
ON p.PaymentID = o.PaymentID
INNER JOIN OrderDetails AS od
ON od.OrderID = o.OrderID
GROUP BY p.PaymentID, o.Freight;
GO

UPDATE Payments
SET Price = cp.TotalPrice
FROM Payments p
INNER JOIN [dbo].[CalPriceInPayments] AS cp
ON p.PaymentID = cp.PaymentID;
GO
