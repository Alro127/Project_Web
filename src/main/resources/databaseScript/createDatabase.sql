-- Khởi tạo database
CREATE DATABASE CVHub;
GO

-- Sử dụng database vừa tạo
USE CVHub;
GO

-- Tạo Bảng tài khoản
CREATE TABLE TaiKhoan (
    id INT PRIMARY KEY IDENTITY(1,1),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50)
);
GO

-- Bảng Ứng Viên
CREATE TABLE UngVien (
    IdUV INT PRIMARY KEY IDENTITY(1,1), -- Khóa chính, tự động tăng
    fullName VARCHAR(100) NOT NULL,      -- Tên đầy đủ
    gender ENUM('Nam', 'Nữ', 'Khác') NOT NULL,   -- Giới tính
    dob DATE NOT NULL,                    -- Ngày sinh
    phone VARCHAR(15),                    -- Số điện thoại
    email VARCHAR(100) NOT NULL UNIQUE,   -- Email
    location VARCHAR(50),                 -- Tỉnh thành
    address TEXT,                         -- Địa chỉ
    introduction TEXT,                    -- Giới thiệu bản thân
    avatar VARCHAR(MAX)                   -- Ảnh đại diện
);
GO

-- Bảng CV
CREATE TABLE CV (
    IdCV INT PRIMARY KEY IDENTITY(1,1),
    IdUV INT,
    position VARCHAR(255),
    careerGoals TEXT,
    --FOREIGN KEY (IdUV) REFERENCES UngVien(IdUV)
);
GO

-- Bảng HocVan
CREATE TABLE HocVan (
    id INT PRIMARY KEY IDENTITY(1,1),
    idCV INT,
    startDate DATE,
    endDate DATE,
    school VARCHAR(255),
    major VARCHAR(255),
    description TEXT,
    --FOREIGN KEY (idCV) REFERENCES CV(IdCV)
);
GO

-- Bảng KinhNghiem
CREATE TABLE KinhNghiem (
    id INT PRIMARY KEY IDENTITY(1,1),
    idCV INT,
    startDate DATE,
    endDate DATE,
    company VARCHAR(255),
    position VARCHAR(255),
    description TEXT,
    --FOREIGN KEY (idCV) REFERENCES CV(IdCV)
);
GO

-- Bảng ChungChi
CREATE TABLE ChungChi (
    id INT PRIMARY KEY IDENTITY(1,1),
    idCV INT,
    name VARCHAR(255),
    --FOREIGN KEY (idCV) REFERENCES CV(IdCV)
);
GO

-- Bảng KyNang
CREATE TABLE KyNang (
    id INT PRIMARY KEY IDENTITY(1,1),
    idCV INT,
    name VARCHAR(255),
    level VARCHAR(255),
    --FOREIGN KEY (idCV) REFERENCES CV(IdCV)
);
GO

-- Bảng Công việc
CREATE TABLE CongViec (
    idCongViec INT PRIMARY KEY IDENTITY(1,1),
    idCT INT,
    ten VARCHAR(255),
    diaDiem VARCHAR(255),
    luong FLOAT,  -- Sử dụng kiểu dữ liệu FLOAT thay vì DOUBLE
    namKinhNghiem INT,
    linhVuc VARCHAR(255),
    thoiGianDang TIMESTAMP,
    thoiGianHetHan TIMESTAMP, -- Chỉ được phép có 1 kiểu TimeStamp trong bảng
    moTa TEXT,
    yeuCau TEXT,
    quyenLoi TEXT,
    luotXem INT,
    luotNop INT,
    --FOREIGN KEY (idCT) REFERENCES CongTy(idCT)  -- Giả sử bảng CongTy có trường idCT làm khóa chính
);
GO
