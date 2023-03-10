create database PS10576_QLSV
go

use PS10576_QLSV
go

create table SINHVIEN
(
	MASV VARCHAR(10) PRIMARY KEY NOT NULL,
	HOTEN NVARCHAR(50),
	EMAIL VARCHAR(50),
	SDT VARCHAR(10),
	GIOITINH BIT,
	DIACHI NVARCHAR(50)
)
go

INSERT INTO SINHVIEN VALUES('SV01', N'Ngô Tuấn Đức', 'ducntps10576@fpt.edu.vn', '0334255377', 1, N'123 Lê Duẩn Q1 TPHCM')
INSERT INTO SINHVIEN VALUES('SV02', N'Nguyễn Thị Hồng', 'hongnt6@fpt.edu.vn', '0741852963', 0, N'45 Hàn Hải Nguyên Q11 TPHCM')
INSERT INTO SINHVIEN VALUES('SV03', N'Đoàn Nguyên Phú', 'phudn123@gmail.com', '0789456123', 1, N'3 Lê Đại Hành Q10 TPHCM')
INSERT INTO SINHVIEN VALUES('SV04', N'Đinh Hoàng Hiếu', 'hieudh741@gmail.com', '0951753456', 1, N'25 Út Tịch Q. Tân Bình TPHCM')
INSERT INTO SINHVIEN VALUES('SV05', N'Lê Hoàng Phúc', 'phuclt@fpt.edu.vn', '0123654789', 1, N'1050 3 Tháng 2 Q11 TPHCM')
go
