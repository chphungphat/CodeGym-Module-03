CREATE DATABASE QuanLyDiemThi;
USE QuanLyDiemThi;

CREATE TABLE HocSinh (
    MaHS tinyint,
    TenHS nvarchar(255),
    NgaySinh date,
    Lop varchar(20),
    GT varchar(20),
    PRIMARY KEY (MaHS)
);

ALTER TABLE HocSinh DROP PRIMARY KEY;

ALTER TABLE HocSinh DROP COLUMN MaHS;

ALTER TABLE HocSinh ADD COLUMN MaHS varchar(20) PRIMARY KEY FIRST;

CREATE TABLE MonHoc (
    MaMH varchar(20),
    TenMH varchar(50),
    PRIMARY KEY (MaMH)
);

CREATE TABLE BangDiem (
    MaHS varchar(20),
    MaMH varchar(20),
    DiemThi tinyint,
    NgayKT datetime,
    PRIMARY KEY (MaHS, MaMH),
    FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS),
    FOREIGN KEY (MaMH) REFERENCES MonHoc(MaMH)
);

CREATE TABLE GiaoVien (
    MaGV varchar(20) PRIMARY KEY,
    TenGV varchar(20),
    SDT varchar(10)
);

ALTER TABLE MonHoc ADD COLUMN MaGV varchar(20);

ALTER TABLE MonHoc ADD CONSTRAINT FK_MaGV FOREIGN KEY (MaGV) REFERENCES GiaoVien(MaGV);

