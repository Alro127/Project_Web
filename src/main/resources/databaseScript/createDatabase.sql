-- Khởi tạo database
CREATE DATABASE IF NOT EXISTS CVHub;

-- Sử dụng database vừa tạo
USE CVHub;

-- Bảng Ứng Viên
ALTER OR CREATE TABLE UngVien (
    IdUV INT AUTO_INCREMENT PRIMARY KEY, --Reference qua bên Id tài khoản
    HoTen VARCHAR(100) NOT NULL,
    GioiTinh ENUM('Nam', 'Nữ', 'Khác') NOT NULL,
    NgaySinh DATE NOT NULL,
    SDT VARCHAR(15),
    Email VARCHAR(100) NOT NULL UNIQUE,
    TinhThanh VARCHAR(50),
    DiaChi TEXT,
    GioiThieu TEXT,
    Avatar VARCHAR(MAX)   
);



-- Tạo bảng Education
CREATE TABLE Education (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- ID của bản ghi, khóa chính
    idCV INT
    IdUV INT;
    educationStart DATE NOT NULL,              -- Ngày bắt đầu học
    educationEnd DATE NOT NULL,                -- Ngày kết thúc học
    educationSchool VARCHAR(255) NOT NULL,     -- Tên trường học
    educationMajor VARCHAR(255) NOT NULL,      -- Ngành học
    educationDescription TEXT                  -- Mô tả về chương trình học
    FOREIGN KEY (IdCV) REFERENCES CV(IdCV),           -- Tham chiếu đến khóa chính bảng CV
    FOREIGN KEY (IdUV) REFERENCES UngVien(IdUV)       -- Tham chiếu đến khóa chính bảng UngVien
);

-- Tạo bảng Experience với khóa ngoại tham chiếu từ bảng CV và UngVien
CREATE TABLE Experience (
    id INT AUTO_INCREMENT PRIMARY KEY,               -- ID của bản ghi, khóa chính
    experienceStart DATE NOT NULL,                    -- Ngày bắt đầu công việc
    experienceEnd DATE NOT NULL,                      -- Ngày kết thúc công việc
    experienceCompany VARCHAR(255) NOT NULL,          -- Tên công ty
    experiencePosition VARCHAR(255) NOT NULL,         -- Vị trí công việc
    experienceDescription TEXT,                       -- Mô tả công việc
    IdCV INT NOT NULL,                               -- Khóa ngoại từ bảng CV
    IdUV INT NOT NULL,                               -- Khóa ngoại từ bảng UngVien
    FOREIGN KEY (IdCV) REFERENCES CV(IdCV),           -- Tham chiếu đến khóa chính bảng CV
    FOREIGN KEY (IdUV) REFERENCES UngVien(IdUV)       -- Tham chiếu đến khóa chính bảng UngVien
);

