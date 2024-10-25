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
UnitPrice INT,
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
(N'Lắc chân', N'Thêm chút duyên dáng với bộ sưu tập lắc chân tinh tế!')

INSERT INTO Products(ProductName, CategoryID, UnitsInStock, UnitPrice, Images) VALUES
(N'Hoa tai 1', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_1.png'),
(N'Hoa tai 2', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_2.png'),
(N'Hoa tai 3', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_3.png'),
(N'Hoa tai 4', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_4.png'),
(N'Hoa tai 5', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_5.png'),
(N'Hoa tai 6', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_6.png'),
(N'Hoa tai 7', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_7.png'),
(N'Hoa tai 8', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_8.png'),
(N'Hoa tai 9', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_9.png'),
(N'Hoa tai 10', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_10.png'),
(N'Hoa tai 11', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_11.png'),
(N'Hoa tai 12', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_12.png'),
(N'Hoa tai 13', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_13.png'),
(N'Hoa tai 14', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_14.png'),
(N'Hoa tai 15', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_15.png'),
(N'Hoa tai 16', 1, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/earrings_16.png'),

(N'Lắc tay 1', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_1.png'),
(N'Lắc tay 2', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_2.png'),
(N'Lắc tay 3', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_3.png'),
(N'Lắc tay 4', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_4.png'),
(N'Lắc tay 5', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_5.png'),
(N'Lắc tay 6', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_6.png'),
(N'Lắc tay 7', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_7.png'),
(N'Lắc tay 8', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_8.png'),
(N'Lắc tay 9', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_9.png'),
(N'Lắc tay 10', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_10.png'),
(N'Lắc tay 11', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_11.png'),
(N'Lắc tay 12', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_12.png'),
(N'Lắc tay 13', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_13.png'),
(N'Lắc tay 14', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_14.png'),
(N'Lắc tay 15', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_15.png'),
(N'Lắc tay 16', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_16.png'),
(N'Lắc tay 17', 2, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/bracelet_17.png'),

(N'Dây chuyền 1', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_1.png'),
(N'Dây chuyền 2', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_2.png'),
(N'Dây chuyền 3', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_3.png'),
(N'Dây chuyền 4', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_4.png'),
(N'Dây chuyền 5', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_5.png'),
(N'Dây chuyền 6', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_6.png'),
(N'Dây chuyền 7', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_7.png'),
(N'Dây chuyền 8', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_8.png'),
(N'Dây chuyền 9', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_9.png'),
(N'Dây chuyền 10', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_10.png'),
(N'Dây chuyền 11', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_11.png'),
(N'Dây chuyền 12', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_12.png'),
(N'Dây chuyền 13', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_13.png'),
(N'Dây chuyền 14', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_14.png'),
(N'Dây chuyền 15', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_15.png'),
(N'Dây chuyền 16', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_16.png'),
(N'Dây chuyền 17', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_17.png'),
(N'Dây chuyền 18', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_18.png'),
(N'Dây chuyền 19', 3, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/necklace_19.png'),

(N'Nhẫn 1', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_1.png'),
(N'Nhẫn 2', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_2.png'),
(N'Nhẫn 3', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_3.png'),
(N'Nhẫn 4', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_4.png'),
(N'Nhẫn 5', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_5.png'),
(N'Nhẫn 6', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_6.png'),
(N'Nhẫn 7', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_7.png'),
(N'Nhẫn 8', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_8.png'),
(N'Nhẫn 9', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_9.png'),
(N'Nhẫn 10', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_10.png'),
(N'Nhẫn 11', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_11.png'),
(N'Nhẫn 12', 4, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/rings_12.png'),

(N'Lắc chân 1', 5, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/anklet_1.png'),
(N'Lắc chân 2', 5, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/anklet_2.png'),
(N'Lắc chân 3', 5, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/anklet_3.png'),
(N'Lắc chân 4', 5, ROUND(RAND() * 100, 0), ROUND(ROUND(RAND() * 500000 + 750000, 0) / 1000, 0) * 1000, 'images/products/anklet_4.png')