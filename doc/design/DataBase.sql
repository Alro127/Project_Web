USE [master]
GO
/****** Object:  Database [CVHub]    Script Date: 3/2/2025 10:52:38 AM ******/
CREATE DATABASE [CVHub]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CVHub', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.DAT\MSSQL\DATA\CVHub.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CVHub_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.DAT\MSSQL\DATA\CVHub_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [CVHub] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CVHub].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CVHub] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CVHub] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CVHub] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CVHub] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CVHub] SET ARITHABORT OFF 
GO
ALTER DATABASE [CVHub] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CVHub] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CVHub] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CVHub] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CVHub] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CVHub] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CVHub] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CVHub] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CVHub] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CVHub] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CVHub] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CVHub] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CVHub] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CVHub] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CVHub] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CVHub] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CVHub] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CVHub] SET RECOVERY FULL 
GO
ALTER DATABASE [CVHub] SET  MULTI_USER 
GO
ALTER DATABASE [CVHub] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CVHub] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CVHub] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CVHub] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CVHub] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CVHub] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'CVHub', N'ON'
GO
ALTER DATABASE [CVHub] SET QUERY_STORE = OFF
GO
USE [CVHub]
GO
/****** Object:  Table [dbo].[ChungChi]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChungChi](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idCV] [int] NULL,
	[name] [nvarchar](255) NULL,
 CONSTRAINT [PK__ChungChi__3213E83F08E9DC8C] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongTy]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongTy](
	[IdCT] [int] NOT NULL,
	[TenCongTy] [nvarchar](255) NULL,
	[SDT] [nvarchar](15) NULL,
	[Email] [nvarchar](255) NULL,
	[MaSoThue] [nvarchar](20) NULL,
	[LinhVuc] [nvarchar](255) NULL,
	[QuyMoNhanSu] [nvarchar](255) NULL,
	[TinhThanh] [nvarchar](100) NULL,
	[DiaChi] [nvarchar](255) NULL,
	[URL] [nvarchar](255) NULL,
	[GioiThieu] [ntext] NULL,
	[Logo] [nvarchar](255) NULL,
	[Background] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[IdCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongViec]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongViec](
	[IdCongViec] [int] IDENTITY(1,1) NOT NULL,
	[IdCT] [int] NOT NULL,
	[Ten] [nvarchar](255) NOT NULL,
	[DiaDiem] [nvarchar](255) NULL,
	[Luong] [float] NULL,
	[NamKinhNghiem] [int] NULL,
	[LinhVuc] [nvarchar](255) NULL,
	[ThoiGianDang] [datetime] NULL,
	[ThoiGianHetHan] [datetime] NULL,
	[MoTa] [nvarchar](max) NULL,
	[YeuCau] [nvarchar](max) NULL,
	[QuyenLoi] [nvarchar](max) NULL,
	[LuotXem] [int] NULL,
	[LuotNop] [int] NULL,
 CONSTRAINT [PK__CongViec__4B14C7470568BDBA] PRIMARY KEY CLUSTERED 
(
	[IdCongViec] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CongViecYeuThich]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CongViecYeuThich](
	[idUV] [int] NOT NULL,
	[idCongViec] [int] NOT NULL,
 CONSTRAINT [PK_YeuThich] PRIMARY KEY CLUSTERED 
(
	[idUV] ASC,
	[idCongViec] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CV]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CV](
	[IdCV] [int] IDENTITY(1,1) NOT NULL,
	[IdUV] [int] NULL,
	[position] [nvarchar](255) NULL,
	[careerGoals] [ntext] NULL,
 CONSTRAINT [PK__CV__B7739095EF3E2527] PRIMARY KEY CLUSTERED 
(
	[IdCV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HinhAnhHoatDong]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HinhAnhHoatDong](
	[idCongTy] [int] NOT NULL,
	[duongDan] [nvarchar](255) NOT NULL,
 CONSTRAINT [pk_HinhAnhHoatDong] PRIMARY KEY CLUSTERED 
(
	[idCongTy] ASC,
	[duongDan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HocVan]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HocVan](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idCV] [int] NULL,
	[startDate] [date] NULL,
	[endDate] [date] NULL,
	[school] [nvarchar](255) NULL,
	[major] [nvarchar](255) NULL,
	[description] [ntext] NULL,
 CONSTRAINT [PK__HocVan__3213E83F6B9D4A33] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoSo]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoSo](
	[IdCongViec] [int] NOT NULL,
	[IdCV] [int] NOT NULL,
	[ThoiGianGui] [datetime] NULL,
	[TrangThai] [nvarchar](255) NULL,
 CONSTRAINT [PK_HoSo] PRIMARY KEY CLUSTERED 
(
	[IdCongViec] ASC,
	[IdCV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KinhNghiem]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KinhNghiem](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idCV] [int] NULL,
	[startDate] [date] NULL,
	[endDate] [date] NULL,
	[company] [nvarchar](255) NULL,
	[position] [nvarchar](255) NULL,
	[description] [ntext] NULL,
 CONSTRAINT [PK__KinhNghi__3213E83F95CC711D] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KyNang]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KyNang](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idCV] [int] NULL,
	[name] [nvarchar](255) NULL,
	[level] [nvarchar](255) NULL,
 CONSTRAINT [PK__KyNang__3213E83F5320C6AD] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[id_google] [varchar](255) NULL,
	[id_facebook] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[role] [varchar](255) NULL,
	[access_token] [varchar](255) NULL,
	[refresh_token] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UngVien]    Script Date: 3/2/2025 10:52:38 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UngVien](
	[IdUV] [int] NOT NULL,
	[fullName] [nvarchar](100) NULL,
	[gender] [nvarchar](50) NULL,
	[dob] [date] NULL,
	[phone] [varchar](15) NULL,
	[email] [varchar](100) NULL,
	[location] [nvarchar](50) NULL,
	[address] [ntext] NULL,
	[introduction] [ntext] NULL,
	[avatar] [nvarchar](max) NULL,
 CONSTRAINT [PK__UngVien__B770024B1C590C0E] PRIMARY KEY CLUSTERED 
(
	[IdUV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[ChungChi] ON 

INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (3, 4, N'Chứng chỉ Quản lý sản phẩm quốc tế')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (4, 4, N'Chứng chỉ Nghiên cứu thị trường chuyên sâu')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (5, 4, N'Chứng chỉ Kỹ năng tư vấn khách hàng')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (6, 4, N'Chứng chỉ Giao tiếp chuyên nghiệp')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (7, 6, N'Chứng chỉ Thiết kế UI/UX từ Google')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (8, 6, N'Chứng chỉ Figma Design')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (9, 6, N'Chứng chỉ Quản lý nhóm và lãnh đạo')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (10, 6, N'Chứng chỉ Quản lý dự án Agile')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (11, 8, N'Chứng chỉ Lãnh đạo sáng tạo từ Harvard Business School')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (12, 8, N'Chứng chỉ Quản lý dự án sáng tạo Agile')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (13, 8, N'Chứng chỉ Viết nội dung chuyên nghiệp')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (14, 8, N'Chứng chỉ Thiết kế đồ họa Adobe Photoshop')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (15, 10, N'Chứng chỉ Quản lý chăm sóc khách hàng chuyên nghiệp')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (16, 10, N'Chứng chỉ Kỹ năng giao tiếp và giải quyết khiếu nại')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (17, 10, N'Chứng chỉ Quản lý dịch vụ khách hàng từ ISMM')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (18, 10, N'Chứng chỉ Quản lý đội ngũ chăm sóc khách hàng')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (41, 12, N'Kỹ sư CNTT')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (42, 14, N'Kỹ sư CNTT')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (44, 16, N'Kỹ sư CNTT')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (48, 1, N'Chứng chỉ Java chuyên nghiệp')
INSERT [dbo].[ChungChi] ([id], [idCV], [name]) VALUES (49, 1, N'Chứng chỉ Quản lý dự án PMP')
SET IDENTITY_INSERT [dbo].[ChungChi] OFF
GO
INSERT [dbo].[CongTy] ([IdCT], [TenCongTy], [SDT], [Email], [MaSoThue], [LinhVuc], [QuyMoNhanSu], [TinhThanh], [DiaChi], [URL], [GioiThieu], [Logo], [Background]) VALUES (2, N'Công ty TNHH A', N'0987654321', N'contact@abc.com', N'1234567890', N'Technology', N'100-200 người', N'Hà Nội', N'Số 123, phố ABC, Hà Nội', N'https://www.abc.com', N'Chúng tôi là công ty chuyên cung cấp các giải pháp công nghệ', N'assets/images/avatar/cvhublogo.png', N'assets/images/background/testBanner.jpg')
INSERT [dbo].[CongTy] ([IdCT], [TenCongTy], [SDT], [Email], [MaSoThue], [LinhVuc], [QuyMoNhanSu], [TinhThanh], [DiaChi], [URL], [GioiThieu], [Logo], [Background]) VALUES (3, N'Công ty XYZ', N'0987654321', N'contact@xyz.com', N'0987654321', N'Marketing', N'50-100 người', N'Hồ Chí Minh', N'Số 456, đường XYZ, Hồ Chí Minh', N'https://www.xyz.com', N'Chuyên cung c?p d?ch v? marketing tr?c tuy?n, t?i uu hóa công c? tìm ki?m.', N'assets/images/avatar/logo.jpg', N'background_xyz.png')
INSERT [dbo].[CongTy] ([IdCT], [TenCongTy], [SDT], [Email], [MaSoThue], [LinhVuc], [QuyMoNhanSu], [TinhThanh], [DiaChi], [URL], [GioiThieu], [Logo], [Background]) VALUES (5, N'Công ty DEF', N'0930123456', N'info@def.com', N'1122334455', N'Tài chính', N'200-500 người', N'Hà Nội', N'Số 789, khu công nghiệp DEF, Hà Nội', N'https://www.def.com', N'Cung c?p d?ch v? tài chính, d?u tu và tu v?n ngân hàng.', N'assets/images/avatar/unnamed.png', N'background_def.png')
INSERT [dbo].[CongTy] ([IdCT], [TenCongTy], [SDT], [Email], [MaSoThue], [LinhVuc], [QuyMoNhanSu], [TinhThanh], [DiaChi], [URL], [GioiThieu], [Logo], [Background]) VALUES (7, N'Công ty GHI', N'0912345678', N'hello@ghi.com', N'6677889900', N'Giáo dục', N'10-20 người', N'Đà Nẵng', N'Số 101, phố GHI, Đà Nẵng', N'https://www.ghi.com', N'M?t công ty chuyên cung c?p các khóa h?c online cho h?c sinh và sinh viên.', N'logo_ghi.png', N'background_ghi.png')
INSERT [dbo].[CongTy] ([IdCT], [TenCongTy], [SDT], [Email], [MaSoThue], [LinhVuc], [QuyMoNhanSu], [TinhThanh], [DiaChi], [URL], [GioiThieu], [Logo], [Background]) VALUES (9, N'Công ty JKL', N'0901234567', N'contact@jkl.com', N'2233445566', N'Bán lẻ', N'300-1000 người', N'Hồ Chí Minh', N'Số 102, đường JKL, Hồ Chí Minh', N'https://www.jkl.com', N'Cung c?p s?n ph?m tiêu dùng, bán l? qua m?ng.', N'logo_jkl.png', N'background_jkl.png')
INSERT [dbo].[CongTy] ([IdCT], [TenCongTy], [SDT], [Email], [MaSoThue], [LinhVuc], [QuyMoNhanSu], [TinhThanh], [DiaChi], [URL], [GioiThieu], [Logo], [Background]) VALUES (16, N'Công Ty Mới', NULL, N'sa@a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[CongTy] ([IdCT], [TenCongTy], [SDT], [Email], [MaSoThue], [LinhVuc], [QuyMoNhanSu], [TinhThanh], [DiaChi], [URL], [GioiThieu], [Logo], [Background]) VALUES (19, N'Công Ty Mới', NULL, N'vophantandat.ktc@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[CongViec] ON 

INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (1, 2, N'Tuyển Dụng Nhân Viên Marketing', N'Hồ Chí Minh', 15000000, 2, N'Marketing', CAST(N'2024-12-06T18:03:19.420' AS DateTime), CAST(N'2025-01-06T18:03:19.420' AS DateTime), N'Chịu trách nhiệm xây dựng chiến lược marketing cho công ty.', N'Có kinh nghiệm ít nhất 2 năm trong lĩnh vực marketing.', N'Cung cấp môi trường làm việc năng động và các phúc lợi đầy đủ.', 26, 2)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (2, 2, N'Tuyển Dụng Quản Lý Dự Án', N'Hồ Chí Minh', 20000000, 3, N'Quản lý', CAST(N'2024-12-06T18:03:19.420' AS DateTime), CAST(N'2025-01-06T18:03:19.420' AS DateTime), N'Quản lý các dự án marketing, đảm bảo tiến độ và chất lượng dự án.', N'Tối thiểu 3 năm kinh nghiệm quản lý dự án.', N'Chế độ lương thưởng hấp dẫn, bảo hiểm đầy đủ.', 2, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (3, 2, N'Tuyển Dụng Nhân Viên SEO', N'Hồ Chí Minh', 12000000, 1, N'Marketing', CAST(N'2024-12-06T18:03:19.420' AS DateTime), CAST(N'2025-01-06T18:03:19.420' AS DateTime), N'Tối ưu hóa website công ty cho các công cụ tìm kiếm.', N'Có kiến thức cơ bản về SEO và marketing trực tuyến.', N'Được đào tạo thêm về SEO và tiếp cận các công nghệ mới.', 9, 3)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (4, 2, N'Tuyển Dụng Nhân Viên Content', N'Hồ Chí Minh', 13000000, 1, N'Content', CAST(N'2024-12-06T18:03:19.420' AS DateTime), CAST(N'2025-01-06T18:03:19.420' AS DateTime), N'Viết bài blog, quảng cáo, nội dung cho các kênh truyền thông của công ty.', N'Có khả năng viết bài tốt, sáng tạo, và yêu thích công việc viết lách.', N'Chế độ đãi ngộ tốt, môi trường làm việc trẻ trung.', 6, 1)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (5, 2, N'Tuyển Dụng Nhân Viên Tư Vấn', N'Hồ Chí Minh', 10000000, 1, N'Kinh doanh', CAST(N'2024-12-06T18:03:19.420' AS DateTime), CAST(N'2025-01-06T18:03:19.420' AS DateTime), N'Tư vấn các giải pháp marketing cho khách hàng.', N'Tốt nghiệp đại học, giao tiếp tốt, năng động.', N'Được hỗ trợ đào tạo, cơ hội thăng tiến cao.', 2, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (6, 3, N'Tuyển Dụng Nhân Viên Tư Vấn Tài Chính', N'Hà Nội', 25000000, 3, N'Tài chính', CAST(N'2024-12-06T18:03:19.423' AS DateTime), CAST(N'2025-01-06T18:03:19.423' AS DateTime), N'Tư vấn các giải pháp tài chính, đầu tư cho khách hàng.', N'Tốt nghiệp đại học chuyên ngành tài chính, ngân hàng.', N'Chế độ bảo hiểm, lương thưởng theo hiệu suất.', 1, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (7, 3, N'Tuyển Dụng Chuyên Viên Phân Tích Tài Chính', N'Hà Nội', 30000000, 5, N'Tài chính', CAST(N'2024-12-06T18:03:19.423' AS DateTime), CAST(N'2025-01-06T18:03:19.423' AS DateTime), N'Phân tích dữ liệu tài chính, tư vấn giải pháp đầu tư.', N'Tối thiểu 5 năm kinh nghiệm trong ngành tài chính.', N'Chế độ đãi ngộ cao, cơ hội thăng tiến.', 2, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (8, 3, N'Tuyển Dụng Kế Toán Trưởng', N'Hà Nội', 18000000, 4, N'Tài chính', CAST(N'2024-12-06T18:03:19.423' AS DateTime), CAST(N'2025-01-06T18:03:19.423' AS DateTime), N'Chịu trách nhiệm quản lý tài chính, kế toán cho công ty.', N'Kinh nghiệm 4 năm trở lên trong lĩnh vực kế toán.', N'Được hưởng bảo hiểm và các quyền lợi khác.', 2, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (9, 3, N'Tuyển Dụng Nhân Viên Tài Chính', N'Hà Nội', 12000000, 2, N'Tài chính', CAST(N'2024-12-06T18:03:19.423' AS DateTime), CAST(N'2025-01-06T18:03:19.423' AS DateTime), N'Hỗ trợ công tác thu chi, làm báo cáo tài chính.', N'Có ít nhất 2 năm kinh nghiệm về tài chính, kế toán.', N'Chế độ đãi ngộ hấp dẫn, môi trường làm việc chuyên nghiệp.', 3, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (10, 3, N'Tuyển Dụng Nhân Viên Thu Hồi Nợ', N'Hà Nội', 10000000, 1, N'Tài chính', CAST(N'2024-12-06T18:03:19.423' AS DateTime), CAST(N'2025-01-06T18:03:19.423' AS DateTime), N'Thu hồi nợ, xử lý các công nợ xấu của khách hàng.', N'Có kỹ năng giao tiếp tốt và giải quyết vấn đề.', N'Được hỗ trợ công tác đào tạo, cơ hội thăng tiến.', 0, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (11, 5, N'Tuyển Dụng Nhân Viên Kinh Doanh', N'Hồ Chí Minh', 12000000, 1, N'Bán lẻ', CAST(N'2024-12-06T18:03:19.443' AS DateTime), CAST(N'2025-01-06T18:03:19.443' AS DateTime), N'Tìm kiếm và phát triển các khách hàng mới.', N'Tốt nghiệp cao đẳng trở lên, có kỹ năng giao tiếp tốt.', N'Được hỗ trợ đào tạo, chế độ đãi ngộ tốt.', 1, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (12, 5, N'Tuyển Dụng Nhân Viên Bán Hàng', N'Hồ Chí Minh', 10000000, 1, N'Bán lẻ', CAST(N'2024-12-06T18:03:19.443' AS DateTime), CAST(N'2025-01-06T18:03:19.443' AS DateTime), N'Bán hàng tại cửa hàng, hỗ trợ khách hàng lựa chọn sản phẩm.', N'Yêu thích công việc bán hàng, có kỹ năng giao tiếp tốt.', N'Được đào tạo chuyên sâu, có cơ hội thăng tiến.', 0, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (13, 5, N'Tuyển Dụng Quản Lý Kho', N'Hồ Chí Minh', 15000000, 2, N'Bán lẻ', CAST(N'2024-12-06T18:03:19.443' AS DateTime), CAST(N'2025-01-06T18:03:19.443' AS DateTime), N'Quản lý kho hàng, đảm bảo hàng hóa luôn đầy đủ và chất lượng.', N'Tối thiểu 2 năm kinh nghiệm quản lý kho.', N'Chế độ phúc lợi tốt, môi trường làm việc thân thiện.', 0, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (14, 5, N'Tuyển Dụng Nhân Viên Marketing', N'Hồ Chí Minh', 14000000, 2, N'Marketing', CAST(N'2024-12-06T18:03:19.443' AS DateTime), CAST(N'2025-01-06T18:03:19.443' AS DateTime), N'Lập kế hoạch marketing cho các sản phẩm của công ty.', N'Có ít nhất 2 năm kinh nghiệm trong ngành marketing.', N'Chế độ đãi ngộ tốt, môi trường làm việc năng động.', 0, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (15, 5, N'Tuyển Dụng Nhân Viên Kế Toán', N'Hồ Chí Minh', 13000000, 3, N'Bán lẻ', CAST(N'2024-12-06T18:03:19.443' AS DateTime), CAST(N'2025-01-06T18:03:19.443' AS DateTime), N'Tính toán và quản lý các báo cáo tài chính của công ty.', N'Có ít nhất 3 năm kinh nghiệm trong lĩnh vực kế toán.', N'Chế độ bảo hiểm đầy đủ, cơ hội phát triển sự nghiệp.', 2, 0)
INSERT [dbo].[CongViec] ([IdCongViec], [IdCT], [Ten], [DiaDiem], [Luong], [NamKinhNghiem], [LinhVuc], [ThoiGianDang], [ThoiGianHetHan], [MoTa], [YeuCau], [QuyenLoi], [LuotXem], [LuotNop]) VALUES (20, 2, N'Công việc A', N'TPHCM', 20000000, 3, N'Công nghệ thông tin', CAST(N'2024-12-07T16:09:10.657' AS DateTime), CAST(N'2024-12-20T16:09:00.000' AS DateTime), N'aaaa', N'aaaaaa', N'aaaaaaa', 0, 0)
SET IDENTITY_INSERT [dbo].[CongViec] OFF
GO
INSERT [dbo].[CongViecYeuThich] ([idUV], [idCongViec]) VALUES (1, 5)
INSERT [dbo].[CongViecYeuThich] ([idUV], [idCongViec]) VALUES (1, 9)
INSERT [dbo].[CongViecYeuThich] ([idUV], [idCongViec]) VALUES (21, 4)
INSERT [dbo].[CongViecYeuThich] ([idUV], [idCongViec]) VALUES (22, 1)
INSERT [dbo].[CongViecYeuThich] ([idUV], [idCongViec]) VALUES (23, 4)
GO
SET IDENTITY_INSERT [dbo].[CV] ON 

INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (1, 1, N'Nhân viên phát triển phần mềm', N'Mong muốn trở thành senior trong 3 tháng')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (3, 4, N'Chuyên viên phát triển sản phẩm', N'Mong mu?n du?c tham gia vào quá trình phát tri?n và c?i ti?n s?n ph?m, mang d?n giá tr? th?c cho khách hàng.')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (4, 4, N'Nhân viên tư vấn sản phẩm', N'Mong mu?n áp d?ng ki?n th?c v? s?n ph?m và kinh doanh d? tu v?n cho khách hàng nh?ng gi?i pháp t?i uu nh?t.')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (5, 6, N'Chuyên viên UI/UX', N'Mong mu?n thi?t k? nh?ng s?n ph?m có giao di?n ngu?i dùng t?i uu, d? s? d?ng và mang d?n tr?i nghi?m ngu?i dùng tuy?t v?i.')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (6, 6, N'Trưởng nhóm phát triển UI/UX', N'Mong mu?n phát tri?n k? nang lãnh d?o và d?n d?t d?i ngu UI/UX t?o ra nh?ng s?n ph?m sáng t?o.')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (7, 8, N'Trưởng nhóm sáng tạo', N'Mong mu?n lãnh d?o m?t d?i ngu sáng t?o, thúc d?y các ý tu?ng và phát tri?n nh?ng s?n ph?m d?c dáo.')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (8, 8, N'Nhân viên sáng tạo nội dung', N'Mong mu?n làm vi?c trong môi tru?ng sáng t?o, phát tri?n các s?n ph?m n?i dung có giá tr? và gây ?n tu?ng m?nh m? v?i công chúng.')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (9, 10, N'Chuyên viên chăm sóc khách hàng', N'Mong mu?n c?i thi?n ch?t lu?ng d?ch v? cham sóc khách hàng, xây d?ng m?i quan h? b?n v?ng v?i khách hàng.')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (10, 10, N'Quản lý dịch vụ khách hàng', N'Mong mu?n nâng cao ch?t lu?ng d?ch v? và d?n d?t d?i ngu cham sóc khách hàng d?t du?c s? hài lòng t?i da t? khách hàng.')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (12, 21, N'Vị trí ứng tuyển A', N'Trở thành senior trong 3 tháng')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (14, 22, N'Vị trí ứng tuyển', N'Mục tiêu 1')
INSERT [dbo].[CV] ([IdCV], [IdUV], [position], [careerGoals]) VALUES (16, 23, N'Vị trí ứng tuyển', N'Senior trong 3 tháng')
SET IDENTITY_INSERT [dbo].[CV] OFF
GO
INSERT [dbo].[HinhAnhHoatDong] ([idCongTy], [duongDan]) VALUES (2, N'assets/images/act/donsinhviec.png')
INSERT [dbo].[HinhAnhHoatDong] ([idCongTy], [duongDan]) VALUES (2, N'assets/images/act/image_e715b947-51cc-4f56-9319-450ac327e7f120211105_101458.jpg')
INSERT [dbo].[HinhAnhHoatDong] ([idCongTy], [duongDan]) VALUES (2, N'assets/images/act/images (1).jpg')
GO
SET IDENTITY_INSERT [dbo].[HocVan] ON 

INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (3, 4, CAST(N'2010-09-01' AS Date), CAST(N'2014-06-01' AS Date), N'Đại học Kinh tế Quốc dân', N'Quản trị kinh doanh', N'Học về chiến lược phát triển sản phẩm, nghiên cứu thị trường, và quản lý chuỗi cung ứng.')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (4, 4, CAST(N'2014-09-01' AS Date), CAST(N'2016-06-01' AS Date), N'Đại học Ngoại thương', N'Tiếng Anh thương mại', N'Học về giao tiếp kinh doanh quốc tế, kỹ năng tư vấn sản phẩm và thương mại điện tử.')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (5, 6, CAST(N'2009-09-01' AS Date), CAST(N'2013-06-01' AS Date), N'Đại học FPT', N'Thiết kế đồ họa', N'Học về các nguyên lý thiết kế đồ họa, UI/UX, nghiên cứu về người dùng và thẩm mỹ trong thiết kế sản phẩm.')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (6, 6, CAST(N'2013-09-01' AS Date), CAST(N'2015-06-01' AS Date), N'Đại học Mỹ thuật TP.HCM', N'Mỹ thuật ứng dụng', N'Chuyên sâu về thiết kế giao diện người dùng, nghiên cứu và phát triển sản phẩm UI/UX, trải nghiệm người dùng và tương tác giữa người và máy.')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (7, 8, CAST(N'2008-09-01' AS Date), CAST(N'2012-06-01' AS Date), N'Đại học Kiến trúc TP.HCM', N'Kiến trúc', N'Học về thiết kế sáng tạo, phát triển các ý tưởng và xây dựng các sản phẩm sáng tạo trong lĩnh vực kiến trúc và nghệ thuật.')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (8, 8, CAST(N'2012-09-01' AS Date), CAST(N'2014-06-01' AS Date), N'Đại học Văn hóa TP.HCM', N'Nghiên cứu và sáng tạo nội dung', N'Học về xây dựng nội dung sáng tạo, marketing nội dung, phát triển các chiến lược truyền thông và tương tác với công chúng.')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (9, 10, CAST(N'2010-09-01' AS Date), CAST(N'2014-06-01' AS Date), N'Đại học Kinh tế TP.HCM', N'Quản trị Kinh doanh', N'Học về các nguyên lý quản trị, xây dựng chiến lược dịch vụ khách hàng và phát triển kỹ năng giao tiếp với khách hàng.')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (10, 10, CAST(N'2014-09-01' AS Date), CAST(N'2016-06-01' AS Date), N'Đại học Ngoại thương', N'Tiếp thị và Quản trị dịch vụ', N'Học về các phương pháp quản lý và cải tiến dịch vụ khách hàng, phát triển các chiến lược giữ chân khách hàng và tăng cường mối quan hệ khách hàng.')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (33, 12, CAST(N'2024-12-07' AS Date), CAST(N'2024-12-21' AS Date), N'UTE', N'CNTT', N'Loại giỏi')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (34, 14, CAST(N'2024-12-07' AS Date), CAST(N'2024-12-21' AS Date), N'UTE', N'CNTT', N'Giỏi')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (36, 16, CAST(N'2024-12-07' AS Date), CAST(N'2024-12-14' AS Date), N'UTE', N'CNTT', N'Mô tả a')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (40, 1, CAST(N'2008-09-01' AS Date), CAST(N'2012-06-01' AS Date), N'Đại học Bách Khoa Hà Nội', N'Kỹ sư phần mềm', N'Học chuyên ngành lập trình, phát triển phần mềm, và hệ thống máy tính.')
INSERT [dbo].[HocVan] ([id], [idCV], [startDate], [endDate], [school], [major], [description]) VALUES (41, 1, CAST(N'2012-09-06' AS Date), CAST(N'2014-06-05' AS Date), N'Đại học Kinh tế Quốc dân', N'Quản trị kinh doanh', N'Học về quản lý dự án, lãnh đạo nhóm, và kỹ năng quản lý doanh nghiệp.')
SET IDENTITY_INSERT [dbo].[HocVan] OFF
GO
INSERT [dbo].[HoSo] ([IdCongViec], [IdCV], [ThoiGianGui], [TrangThai]) VALUES (1, 1, CAST(N'2024-12-07T01:18:01.543' AS DateTime), N'Phỏng vấn')
INSERT [dbo].[HoSo] ([IdCongViec], [IdCV], [ThoiGianGui], [TrangThai]) VALUES (1, 14, CAST(N'2024-12-07T13:27:03.690' AS DateTime), N'Phỏng vấn')
INSERT [dbo].[HoSo] ([IdCongViec], [IdCV], [ThoiGianGui], [TrangThai]) VALUES (3, 1, CAST(N'2024-12-07T13:07:38.677' AS DateTime), N'Phỏng vấn')
INSERT [dbo].[HoSo] ([IdCongViec], [IdCV], [ThoiGianGui], [TrangThai]) VALUES (3, 4, CAST(N'2024-12-07T01:18:29.563' AS DateTime), N'Phỏng vấn')
INSERT [dbo].[HoSo] ([IdCongViec], [IdCV], [ThoiGianGui], [TrangThai]) VALUES (3, 12, CAST(N'2024-12-07T12:58:29.487' AS DateTime), N'Phỏng vấn')
INSERT [dbo].[HoSo] ([IdCongViec], [IdCV], [ThoiGianGui], [TrangThai]) VALUES (4, 16, CAST(N'2024-12-07T13:45:26.070' AS DateTime), N'Chờ')
GO
SET IDENTITY_INSERT [dbo].[KinhNghiem] ON 

INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (3, 4, CAST(N'2015-03-01' AS Date), CAST(N'2018-06-30' AS Date), N'Công ty Sáng Tạo ABC', N'Chuyên viên phát triển sản phẩm', N'Thực hiện nghiên cứu thị trường, phát triển các tính năng mới cho sản phẩm, đảm bảo tính khả thi và chất lượng của sản phẩm.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (4, 4, CAST(N'2018-07-01' AS Date), CAST(N'2021-12-31' AS Date), N'Công ty Đổi Mới XYZ', N'Chuyên viên phát triển sản phẩm', N'Làm việc chặt chẽ với các nhóm kỹ thuật và marketing để phát triển và ra mắt sản phẩm mới thành công.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (5, 4, CAST(N'2022-01-01' AS Date), CAST(N'2023-12-31' AS Date), N'Công ty Tư Vấn PQR', N'Nhân viên tư vấn sản phẩm', N'Tư vấn giải pháp tối ưu cho khách hàng dựa trên đặc tính sản phẩm và nhu cầu thị trường, nâng cao doanh số và sự hài lòng của khách hàng.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (6, 6, CAST(N'2016-01-01' AS Date), CAST(N'2018-12-31' AS Date), N'Công ty Công Nghệ XYZ', N'Chuyên viên UI/UX', N'Chịu trách nhiệm thiết kế giao diện người dùng và tạo trải nghiệm người dùng mượt mà cho các ứng dụng web và di động.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (7, 6, CAST(N'2019-01-01' AS Date), CAST(N'2022-06-30' AS Date), N'Công ty Thiết Kế ABC', N'Chuyên viên UI/UX', N'Đảm nhiệm việc thiết kế giao diện người dùng cho các sản phẩm phần mềm, cải tiến tính năng và thẩm mỹ của sản phẩm.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (8, 6, CAST(N'2022-07-01' AS Date), CAST(N'2024-12-31' AS Date), N'Công ty Sáng Tạo DEF', N'Trưởng nhóm phát triển UI/UX', N'Lãnh đạo nhóm thiết kế UI/UX để phát triển các sản phẩm sáng tạo, hợp tác với các nhóm khác để đảm bảo sản phẩm đạt chất lượng và tối ưu người dùng.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (9, 8, CAST(N'2017-01-01' AS Date), CAST(N'2020-12-31' AS Date), N'Công ty Sáng Tạo ABC', N'Trưởng nhóm sáng tạo', N'Lãnh đạo đội ngũ sáng tạo, phát triển ý tưởng và đưa các sản phẩm sáng tạo vào thực tế. Quản lý các dự án sáng tạo và đảm bảo tiến độ hoàn thành.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (10, 8, CAST(N'2021-01-01' AS Date), CAST(N'2024-12-31' AS Date), N'Công ty Marketing XYZ', N'Trưởng nhóm sáng tạo', N'Chịu trách nhiệm cho chiến lược sáng tạo, phát triển các ý tưởng mới cho các chiến dịch quảng cáo, đồng thời lãnh đạo đội ngũ sáng tạo để hoàn thiện sản phẩm.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (11, 8, CAST(N'2015-01-01' AS Date), CAST(N'2016-12-31' AS Date), N'Công ty Truyền Thông DEF', N'Nhân viên sáng tạo nội dung', N'Phát triển các sản phẩm nội dung như bài viết, video, hình ảnh cho các chiến dịch truyền thông, quảng cáo. Đảm bảo nội dung phù hợp và thu hút công chúng.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (12, 10, CAST(N'2018-01-01' AS Date), CAST(N'2021-12-31' AS Date), N'Công ty Dịch Vụ ABC', N'Chuyên viên chăm sóc khách hàng', N'Chăm sóc khách hàng, giải quyết các thắc mắc, khiếu nại của khách hàng. Cải thiện chất lượng dịch vụ chăm sóc và duy trì mối quan hệ với khách hàng lâu dài.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (13, 10, CAST(N'2022-01-01' AS Date), CAST(N'2024-12-31' AS Date), N'Công ty Dịch Vụ XYZ', N'Chuyên viên chăm sóc khách hàng', N'Phát triển mối quan hệ bền vững với khách hàng, tư vấn và hỗ trợ giải pháp, duy trì mức độ hài lòng cao của khách hàng.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (14, 10, CAST(N'2021-01-01' AS Date), CAST(N'2024-12-31' AS Date), N'Công ty Dịch Vụ XYZ', N'Quản lý dịch vụ khách hàng', N'Quản lý đội ngũ chăm sóc khách hàng, xây dựng quy trình hỗ trợ khách hàng, cải thiện chất lượng dịch vụ và đảm bảo mức độ hài lòng của khách hàng luôn ở mức cao.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (37, 12, CAST(N'2024-12-07' AS Date), CAST(N'2024-12-07' AS Date), N'Cây cảnh HCMUTE', N'Bussiness Analyst', N'Hoàn thành tốt')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (38, 14, CAST(N'2024-12-14' AS Date), CAST(N'2024-12-21' AS Date), N'Cây cảnh HCMUTE', N'Bussiness Analyst', N'Mô tả')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (40, 16, CAST(N'2024-12-07' AS Date), CAST(N'2024-12-14' AS Date), N'Cây cảnh HCMUTE', N'Bussiness Analyst', N'Mô tả a')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (44, 1, CAST(N'2018-06-01' AS Date), CAST(N'2020-12-31' AS Date), N'Công ty ABC', N'Nhân viên phát triển phần mềm', N'Phát triển các ứng dụng web, tham gia vào các dự án phát triển phần mềm cho khách hàng, đảm bảo tiến độ và chất lượng dự án.')
INSERT [dbo].[KinhNghiem] ([id], [idCV], [startDate], [endDate], [company], [position], [description]) VALUES (45, 1, CAST(N'2021-01-01' AS Date), CAST(N'2023-12-31' AS Date), N'Công ty XYZ', N'Quản lý dự án', N'Quản lý các dự án phần mềm, điều phối đội ngũ phát triển, đảm bảo các dự án được triển khai đúng hạn và trong phạm vi ngân sách.')
SET IDENTITY_INSERT [dbo].[KinhNghiem] OFF
GO
SET IDENTITY_INSERT [dbo].[KyNang] ON 

INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (5, 4, N'Phân tích thị trường', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (6, 4, N'Quản lý sản phẩm', N'Khá')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (7, 4, N'Tiếng Anh thương mại', N'Khá')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (8, 4, N'Thuyết trình', N'Tốt')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (9, 4, N'Tư vấn khách hàng', N'Tốt')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (10, 4, N'Kỹ năng giao tiếp', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (11, 4, N'Quản lý thời gian', N'Tốt')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (12, 4, N'Kỹ năng lập kế hoạch', N'Khá')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (13, 6, N'Thiết kế giao diện người dùng (UI)', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (14, 6, N'Trải nghiệm người dùng (UX)', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (15, 6, N'Adobe XD', N'Khá')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (16, 6, N'Figma', N'Khá')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (17, 6, N'Lãnh đạo nhóm', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (18, 6, N'Quản lý dự án', N'Tốt')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (19, 6, N'Kỹ năng giao tiếp', N'Khá')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (20, 6, N'Phát triển sản phẩm sáng tạo', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (21, 8, N'Lãnh đạo nhóm sáng tạo', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (22, 8, N'Quản lý dự án sáng tạo', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (23, 8, N'Phát triển ý tưởng sáng tạo', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (24, 8, N'Sáng tạo nội dung', N'Khá')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (25, 8, N'Viết bài và biên tập nội dung', N'Khá')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (26, 8, N'Sử dụng công cụ thiết kế đồ họa (Photoshop, Illustrator)', N'Tốt')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (27, 10, N'Kỹ năng giao tiếp với khách hàng', N'Tốt')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (28, 10, N'Giải quyết khiếu nại khách hàng', N'Khá')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (29, 10, N'Quản lý mối quan hệ khách hàng', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (30, 10, N'Quản lý đội ngũ chăm sóc khách hàng', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (31, 10, N'Quản lý chất lượng dịch vụ', N'Chuyên gia')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (32, 10, N'Phân tích dữ liệu khách hàng', N'Khá')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (76, 12, N'HTML', N'5')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (77, 12, N'CSS', N'5')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (78, 14, N'HTML', N'5')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (79, 14, N'CSS', N'5')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (82, 16, N'HTML', N'5')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (83, 16, N'css', N'5')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (90, 1, N'Lập trình Java', N'5')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (91, 1, N'Quản lý dự án', N'3')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (92, 1, N'Tin học văn phòng', N'4')
INSERT [dbo].[KyNang] ([id], [idCV], [name], [level]) VALUES (93, 1, N'Giao tiếp tiếng Anh', N'5')
SET IDENTITY_INSERT [dbo].[KyNang] OFF
GO
SET IDENTITY_INSERT [dbo].[TaiKhoan] ON 

INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (1, N'uv1', N'TanDat@td123456', N'108430755903819864475', NULL, N'td13052004@gmail.com', N'UngVien', N'ya29.a0AeDClZAV_FMzBB4Dzhmo_y1268uanQ5612Ra88c_GvVLh641mjTy_PA8Wo7cqne-YcyUwWS9MoD_yqG6wiEczoAfAv7PDpK6f7unNdh4R8JugdfoOwoMO1qELH6zXBrI3zO0MTitIwPzuQqLY1aGs9stf3oy-e8b-PUaCgYKAdESARISFQHGX2MiyqViD7AzhXhGhUYc1b8elQ0170', N'1//0gio4QkMX4XnQCgYIARAAGBASNwF-L9IrZSNpzAuK7WDgjkUD5cX8SMDYOZJ-9x06kIZjXYrJqIqBKxFgcc-Gv7nngnKnOg6fGGs')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (2, N'ct1', N'1', N'108960123715063084861', NULL, N'22110309@student.hcmute.edu.vn', N'CongTy', N'ya29.a0AeDClZBwZ-mcT0Wkv5NtL5lu0VgGBNsCbLlgQ7daPlugEcs8I6YceRH4yQC1TS5PfcIW266hD6nJNAhYwW3QfEvKZ6xNVYb7XAniFBIpEdV83fCyYXAlBwcsTVMuuMoU3LZBU2cyce7PwskHjoi9yYmAUkvJ7XwWriUaCgYKAV8SARMSFQHGX2Mixm3o4g11VO4joBnPXhAGcg0170', N'1//0gvrYGL0XoWr_CgYIARAAGBASNwF-L9IriXpOie3n4MAo3lDOyeGZVcvahNCH2tbYTrGAYsBekgBVW1J_60Pe2TfV-3EsG6zrtAk')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (3, N'ct2', N'1', NULL, NULL, N'mai.hoang01@example.com', N'CongTy', N'access_token_value3', N'refresh_token_value3')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (4, N'lan.nguyen01', N'password101', NULL, NULL, N'lan.nguyen01@example.com', N'UngVien', N'access_token_value4', N'refresh_token_value4')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (5, N'ct3', N'1', NULL, NULL, N'nguyen.anh02@example.com', N'CongTy', N'access_token_value5', N'refresh_token_value5')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (6, N'hoang.tran02', N'password456', NULL, NULL, N'hoang.tran02@example.com', N'UngVien', N'access_token_value6', N'refresh_token_value6')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (7, N'mai.hoang02', N'password789', NULL, NULL, N'mai.hoang02@example.com', N'CongTy', N'access_token_value7', N'refresh_token_value7')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (8, N'lan.nguyen02', N'password101', NULL, NULL, N'lan.nguyen02@example.com', N'UngVien', N'access_token_value8', N'refresh_token_value8')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (9, N'nguyen.anh03', N'password123', NULL, NULL, N'nguyen.anh03@example.com', N'CongTy', N'access_token_value9', N'refresh_token_value9')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (10, N'hoang.tran03', N'password456', NULL, NULL, N'hoang.tran03@example.com', N'UngVien', N'access_token_value10', N'refresh_token_value10')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (14, N'sa123', N'sa@123SA', NULL, NULL, N'dat@gmail.com', N'UngVien', NULL, NULL)
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (16, N'sa1234', N'sa1234@SA', NULL, NULL, N'sa@a', N'CongTy', NULL, NULL)
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (19, N'067805B0-1B3E-4D9A-9C18-D527F11B6827', NULL, N'108103484825324657594', NULL, N'vophantandat.ktc@gmail.com', N'CongTy', N'ya29.a0AeDClZCJ1G0Kn-rIVtSIEQsVny0ZQQlHlylxjOSq_rwZ88PYMFgiMDSHQZ2H0ksaRdAqJlj9_0cXOsoEMBrtNQJtA-d9S1InzUKrxfHFISsWwODP7eK_1yq46QUjYVbReiZj2IdYVdLeWc-qK2qFc0eP855_3ko6vtEH8br_aCgYKAdwSARESFQHGX2Mi22AoxD9iwZp6GOrKl0txSg0175', N'1//0gL2K6glN-Rj_CgYIARAAGBASNwF-L9Ir9X4jc8fsYKfQKYPnoC-iGx1vALUXKl2CFOwcC5puDVl6O3QHY2olBSOevW1zhKH9Xiw')
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (21, N'sa123456', N'TanDat@td1234', NULL, NULL, N'dat@gmail.com', N'UngVien', NULL, NULL)
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (22, N'sa1234567', N'TanDat@td12345', NULL, NULL, N'5cu@gmail.com', N'UngVien', NULL, NULL)
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (23, N'sa12345678', N'TanDat@td123456', NULL, NULL, N'a@gmail.com', N'UngVien', NULL, NULL)
INSERT [dbo].[TaiKhoan] ([id], [username], [password], [id_google], [id_facebook], [email], [role], [access_token], [refresh_token]) VALUES (24, N'sa123456789', N'TanDat@td123456', NULL, NULL, N'dat@gmail.com', N'UngVien', NULL, NULL)
SET IDENTITY_INSERT [dbo].[TaiKhoan] OFF
GO
INSERT [dbo].[UngVien] ([IdUV], [fullName], [gender], [dob], [phone], [email], [location], [address], [introduction], [avatar]) VALUES (1, N'T?n Ð?t Vo Phan', N'Nam', CAST(N'1990-01-01' AS Date), N'0123456780', N'td13052004@gmail.com', N'Hà Nội', N'Số 10, đường ABC, Hà Nội', N'Ứng viên tài năng, đam mê công nghệ', N'assets/images/avatar/donsinhviec.png')
INSERT [dbo].[UngVien] ([IdUV], [fullName], [gender], [dob], [phone], [email], [location], [address], [introduction], [avatar]) VALUES (4, N'Lan Nguyễn', N'Nữ', CAST(N'1994-07-25' AS Date), N'0934567890', N'lan.nguyen01@example.com', N'Hải Phòng', N'Số 40, đường OPQ, Hải Phòng', N'Ứng viên đam mê phát triển sản phẩm', N'avatar_url_4')
INSERT [dbo].[UngVien] ([IdUV], [fullName], [gender], [dob], [phone], [email], [location], [address], [introduction], [avatar]) VALUES (6, N'Hoàng Trần', N'Nam', CAST(N'1992-05-10' AS Date), N'0987654321', N'hoang.tran02@example.com', N'Hồ Chí Minh', N'Số 60, đường RST, TP.HCM', N'Ứng viên có kinh nghiệm trong phát triển UI/UX', N'avatar_url_6')
INSERT [dbo].[UngVien] ([IdUV], [fullName], [gender], [dob], [phone], [email], [location], [address], [introduction], [avatar]) VALUES (8, N'Lan Nguyễn', N'Nữ', CAST(N'1994-07-25' AS Date), N'0934567890', N'lan.nguyen02@example.com', N'Hải Phòng', N'Số 80, đường NOP, Hải Phòng', N'Ứng viên năng động và sáng tạo', N'avatar_url_8')
INSERT [dbo].[UngVien] ([IdUV], [fullName], [gender], [dob], [phone], [email], [location], [address], [introduction], [avatar]) VALUES (10, N'Hoàng Trần', N'Nam', CAST(N'1992-05-10' AS Date), N'0987654321', N'hoang.tran03@example.com', N'Hồ Chí Minh', N'Số 100, đường GHI, TP.HCM', N'Ứng viên có kinh nghiệm làm việc với khách hàng', N'avatar_url_10')
INSERT [dbo].[UngVien] ([IdUV], [fullName], [gender], [dob], [phone], [email], [location], [address], [introduction], [avatar]) VALUES (14, N'Đạt', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[UngVien] ([IdUV], [fullName], [gender], [dob], [phone], [email], [location], [address], [introduction], [avatar]) VALUES (21, N'Ứng viên demo', N'Nam', CAST(N'2005-03-07' AS Date), N'0987654321', N'dat@gmail.com', N'TPHCM', N'Số 1 VVN', N'Hello world', N'assets/images/avatar/meo.jpg')
INSERT [dbo].[UngVien] ([IdUV], [fullName], [gender], [dob], [phone], [email], [location], [address], [introduction], [avatar]) VALUES (22, N'Ứng Viên demo', N'Nam', CAST(N'2004-06-09' AS Date), N'0987654321', N'5cu@gmail.com', N'TPHCM', N'Số 1 VVN', N'Giới thiệu 1', N'assets/images/avatar/meo.jpg')
INSERT [dbo].[UngVien] ([IdUV], [fullName], [gender], [dob], [phone], [email], [location], [address], [introduction], [avatar]) VALUES (23, N'User demo', N'Nam', CAST(N'2004-07-08' AS Date), N'0987654321', N'a@gmail.com', N'TPHCM', N'Số 1 VVN', N'Giới thiệu á', N'assets/images/avatar/images (2).jpg')
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__TaiKhoan__F3DBC57206AD290C]    Script Date: 3/2/2025 10:52:38 AM ******/
ALTER TABLE [dbo].[TaiKhoan] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__UngVien__AB6E6164A0F1CEBA]    Script Date: 3/2/2025 10:52:38 AM ******/
ALTER TABLE [dbo].[UngVien] ADD  CONSTRAINT [UQ__UngVien__AB6E6164A0F1CEBA] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CongTy] ADD  DEFAULT (N'Công Ty Mới') FOR [TenCongTy]
GO
ALTER TABLE [dbo].[CongViec] ADD  CONSTRAINT [DF__CongViec__ThoiGi__36B12243]  DEFAULT (getdate()) FOR [ThoiGianDang]
GO
ALTER TABLE [dbo].[CongViec] ADD  CONSTRAINT [DF__CongViec__LuotXe__37A5467C]  DEFAULT ((0)) FOR [LuotXem]
GO
ALTER TABLE [dbo].[CongViec] ADD  CONSTRAINT [DF__CongViec__LuotNo__38996AB5]  DEFAULT ((0)) FOR [LuotNop]
GO
ALTER TABLE [dbo].[TaiKhoan] ADD  DEFAULT (CONVERT([varchar](255),newid())) FOR [username]
GO
ALTER TABLE [dbo].[UngVien] ADD  DEFAULT (N'Ứng viên Mới') FOR [IdUV]
GO
ALTER TABLE [dbo].[ChungChi]  WITH CHECK ADD  CONSTRAINT [FK_ChungChi_CV] FOREIGN KEY([idCV])
REFERENCES [dbo].[CV] ([IdCV])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ChungChi] CHECK CONSTRAINT [FK_ChungChi_CV]
GO
ALTER TABLE [dbo].[CongTy]  WITH CHECK ADD  CONSTRAINT [FK_CongTy_TaiKhoan] FOREIGN KEY([IdCT])
REFERENCES [dbo].[TaiKhoan] ([id])
GO
ALTER TABLE [dbo].[CongTy] CHECK CONSTRAINT [FK_CongTy_TaiKhoan]
GO
ALTER TABLE [dbo].[CongViec]  WITH CHECK ADD  CONSTRAINT [FK_CongViec_CongTy] FOREIGN KEY([IdCT])
REFERENCES [dbo].[CongTy] ([IdCT])
GO
ALTER TABLE [dbo].[CongViec] CHECK CONSTRAINT [FK_CongViec_CongTy]
GO
ALTER TABLE [dbo].[CongViecYeuThich]  WITH CHECK ADD  CONSTRAINT [FK_CongViecYeuThich_CongViec] FOREIGN KEY([idCongViec])
REFERENCES [dbo].[CongViec] ([IdCongViec])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[CongViecYeuThich] CHECK CONSTRAINT [FK_CongViecYeuThich_CongViec]
GO
ALTER TABLE [dbo].[CongViecYeuThich]  WITH CHECK ADD  CONSTRAINT [FK_CongViecYeuThich_UngVien] FOREIGN KEY([idUV])
REFERENCES [dbo].[UngVien] ([IdUV])
GO
ALTER TABLE [dbo].[CongViecYeuThich] CHECK CONSTRAINT [FK_CongViecYeuThich_UngVien]
GO
ALTER TABLE [dbo].[CV]  WITH CHECK ADD  CONSTRAINT [FK_CV_UngVien] FOREIGN KEY([IdUV])
REFERENCES [dbo].[UngVien] ([IdUV])
GO
ALTER TABLE [dbo].[CV] CHECK CONSTRAINT [FK_CV_UngVien]
GO
ALTER TABLE [dbo].[HinhAnhHoatDong]  WITH CHECK ADD  CONSTRAINT [fk_HinhAnhHoatDong_CongTy] FOREIGN KEY([idCongTy])
REFERENCES [dbo].[CongTy] ([IdCT])
GO
ALTER TABLE [dbo].[HinhAnhHoatDong] CHECK CONSTRAINT [fk_HinhAnhHoatDong_CongTy]
GO
ALTER TABLE [dbo].[HocVan]  WITH CHECK ADD  CONSTRAINT [FK_HocVan_CV] FOREIGN KEY([idCV])
REFERENCES [dbo].[CV] ([IdCV])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HocVan] CHECK CONSTRAINT [FK_HocVan_CV]
GO
ALTER TABLE [dbo].[HoSo]  WITH CHECK ADD  CONSTRAINT [FK_HoSo_CongViec] FOREIGN KEY([IdCongViec])
REFERENCES [dbo].[CongViec] ([IdCongViec])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoSo] CHECK CONSTRAINT [FK_HoSo_CongViec]
GO
ALTER TABLE [dbo].[HoSo]  WITH CHECK ADD  CONSTRAINT [FK_HoSo_CV] FOREIGN KEY([IdCV])
REFERENCES [dbo].[CV] ([IdCV])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoSo] CHECK CONSTRAINT [FK_HoSo_CV]
GO
ALTER TABLE [dbo].[KinhNghiem]  WITH CHECK ADD  CONSTRAINT [FK_KinhNghiem_CV] FOREIGN KEY([idCV])
REFERENCES [dbo].[CV] ([IdCV])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[KinhNghiem] CHECK CONSTRAINT [FK_KinhNghiem_CV]
GO
ALTER TABLE [dbo].[KyNang]  WITH CHECK ADD  CONSTRAINT [FK_KyNang_CV] FOREIGN KEY([idCV])
REFERENCES [dbo].[CV] ([IdCV])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[KyNang] CHECK CONSTRAINT [FK_KyNang_CV]
GO
ALTER TABLE [dbo].[UngVien]  WITH CHECK ADD  CONSTRAINT [FK_UngVien_TaiKhoan] FOREIGN KEY([IdUV])
REFERENCES [dbo].[TaiKhoan] ([id])
GO
ALTER TABLE [dbo].[UngVien] CHECK CONSTRAINT [FK_UngVien_TaiKhoan]
GO
USE [master]
GO
ALTER DATABASE [CVHub] SET  READ_WRITE 
GO
