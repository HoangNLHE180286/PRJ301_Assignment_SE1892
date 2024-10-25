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
Username NVARCHAR(60) NOT NULL UNIQUE,
[Password] NVARCHAR(20) NOT NULL,
[Role] NVARCHAR(15) NOT NULL, 
Email NVARCHAR(200) NOT NULL CHECK (Email LIKE '%@%.%'),
CreatedDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
Phone NVARCHAR(10) NOT NULL
);
GO

-- 2
CREATE TABLE Categories( 
CategoryID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
CategoryName NVARCHAR (60),
[Description] NVARCHAR(3000)
);
GO

-- 3
CREATE TABLE Products(
ProductID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
ProductName NVARCHAR(300) NOT NULL,
CategoryID INT FOREIGN KEY REFERENCES Categories(CategoryID) ON DELETE CASCADE,
UnitsInStock INT NOT NULL,
UnitPrice DECIMAL(10, 3),
Images NVARCHAR(300) DEFAULT NULL
);
GO

-- 4
CREATE TABLE Orders(
ID INT IDENTITY(1,1),
OrderID NVARCHAR (10) NOT NULL PRIMARY KEY,
UserID INT NOT NULL FOREIGN KEY REFERENCES UserAccounts(UserID) ON DELETE CASCADE,
OrderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
Freight MONEY,
RequiredDate DATETIME DEFAULT DATEADD(DAY, 7, CURRENT_TIMESTAMP)
);
GO

-- 5
CREATE TABLE Payments(
PaymentID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY, 
OrderID NVARCHAR (10) FOREIGN KEY REFERENCES Orders(OrderID) ON DELETE CASCADE,
PaymentMethod NVARCHAR(50),
PaymentDate DATETIME DEFAULT CURRENT_TIMESTAMP,
Price DECIMAL(10,3) DEFAULT NULL
);
GO

-- 6
CREATE TABLE OrderDetails(
OrderID NVARCHAR (10) NOT NULL,
ProductID INT NOT NULL,
Quantity INT,
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
Comment NVARCHAR(300),
ReviewDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT pk_review PRIMARY KEY(UserID, ProductID)
);
GO

-- Function
CREATE VIEW CalPriceInPayments
AS
SELECT p.PaymentID, SUM((od.Quantity * pd.UnitPrice)*(1-od.Discount)) + o.Freight AS TotalPrice
FROM Payments AS p
INNER JOIN Orders AS o
ON p.OrderID = o.OrderID
INNER JOIN OrderDetails AS od
ON od.OrderID = o.OrderID
INNER JOIN Products AS pd
ON pd.ProductID = od.ProductID
GROUP BY p.PaymentID, o.Freight;
GO

UPDATE Payments
SET Price = cp.TotalPrice
FROM Payments p
INNER JOIN [dbo].[CalPriceInPayments] AS cp
ON p.PaymentID = cp.PaymentID;
GO

-- Data
INSERT INTO UserAccounts(Username, Password, Role, Email, Phone)
VALUES
('Admin_1', '123', 'Admin', 'admin1@gmail.com', 05715155122),
('Admin_2', '123', 'Admin', 'admin2@gmail.com', 01715155122)

INSERT INTO Categories(CategoryName, Description)
VALUES
(N'Hoa tai', N'Bông tai bạc nữ đính đá'),
(N'Lắc tay', N'Vòng tay nữ phong cách'),
(N'Dây chuyền', N'Dây chuyền bạc nữ đẹp cao cấp'),
(N'Nhẫn', N'Những chiếc nhẫn bạc nữ được thiết kế với đá quý lấp lánh, tạo điểm nhấn và sự quý phái cho người đeo.'),
(N'Lắc chân', N'Thêm chút duyên dáng với bộ sưu tập lắc chân tinh tế!');

INSERT INTO Products(ProductName, CategoryID, UnitsInStock, Images) 
VALUES(N'Hoa tai 1', 1, 30, 'images/earrings/1.png')
