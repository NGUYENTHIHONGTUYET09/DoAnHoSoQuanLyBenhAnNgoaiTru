CREATE DATABASE QuanLyThongTinBenhAn
go

select * from NHANVIEN

-- Sài Id cho việc tác động lên DB
-- Sài Mã bệnh nhân cho việc xuất lên UI

use QuanLyThongTinBenhAn

CREATE TABLE TINH(
 id INT IDENTITY(1,1) PRIMARY KEY ,
 tenTinh NVARCHAR(50)
 );

go

CREATE TABLE BenhNhan (
    id INT IDENTITY(1,1) PRIMARY KEY , -- Trường tự động tăng
    maBN AS 'BN' + RIGHT('00000' + CAST(id AS VARCHAR(10)), 5), -- Trường computed column cho mã định dạng
    tenBN NVARCHAR(50),
    sdt VARCHAR(10),
    ngaySinh SMALLDATETIME,
    diaChi NVARCHAR(50),
    gioiTinh NVARCHAR(5),
    queQuan int,
    ghichu NVARCHAR(50) NULL
);

go
alter table BenhNhan add constraint FK_QueQuan 
Foreign key (queQuan) references Tinh(id);


go
CREATE TABLE TaiKhoan(
id int IDENTITY(1,1) primary key,
hoTen nvarchar(50),
maSoNV int,  --tham chiếu qua nhân viên ( Id) 
soDienThoai VARCHAR(10),
email varchar(50),
matKhau varchar(50),
matkhauxacnhan  varchar(50),
vaiTro nvarchar(20),
trangThai NVARCHAR(20),
);

CREATE TABLE NHANVIEN(
id INT IDENTITY(1,1) PRIMARY KEY , -- Trường tự động tăng
MANV  AS 'NV' + RIGHT('00000' + CAST(id AS VARCHAR(10)), 5),
HOTEN NVARCHAR(50),
NGAYSINH SMALLDATETIME,
DIACHI NVARCHAR(50),
GIOITINH NVARCHAR(5),
NGAYVL SMALLDATETIME,
VAITRO NVARCHAR(20),
TRANGTHAI NVARCHAR(20),
);


CREATE TABLE PHONGKHAM(
id INT IDENTITY(1,1) PRIMARY KEY , -- Trường tự động tăng
MAPK  AS 'PK' + RIGHT('00000' + CAST(id AS VARCHAR(10)), 5),
TENPK NVARCHAR(50),
TENKHOA NVARCHAR(50),
THOIGIANBD TIME,
THOIGIANKT TIME
);


CREATE TABLE PHIEUKHAMBENH(
id INT IDENTITY(1,1) PRIMARY KEY , -- Trường tự động tăng
MAPKB  AS 'PKB' + RIGHT('00000' + CAST(id AS VARCHAR(10)), 5),
MABS int,
MABN int,
MAPK int,
NGAYTAO SMALLDATETIME,
CHANDOAN NVARCHAR(50)
);


CREATE TABLE BIENLAI(
id INT IDENTITY(1,1) PRIMARY KEY , -- Trường tự động tăng
MABL  AS 'BL' + RIGHT('00000' + CAST(id AS VARCHAR(10)), 5),
MANV_TT int,
MATOA int,
NGAYTAO  DATETIME DEFAULT GETDATE(),
TONGTIENKHAM MONEY
);

CREATE TABLE TOATHUOC(
    id INT IDENTITY(1,1) PRIMARY KEY,
    MATOA AS 'TOA' + RIGHT('00000' + CAST(id AS VARCHAR(10)), 5),
    MABS INT,
    maBN INT,
    TONGTIEN MONEY,
    NGAYLAP DATETIME DEFAULT GETDATE(),
	maPKB INT
);


CREATE TABLE CTDONTHUOC(
MATHUOC int NOT NULL,
MATOA int NOT NULL,
SOLUONG INT,
GHICHU NVARCHAR(200)
);
go

CREATE TABLE THUOC(
id INT IDENTITY(1,1) PRIMARY KEY , -- Trường tự động tăng
TENTHUOC NVARCHAR(50),
NUOCSX NVARCHAR(50),
DONGIA MONEY,
HSD SMALLDATETIME,
SOLUONGTON int,
TRANGTHAI NVARCHAR(20),
);


ALTER TABLE TaiKhoan ADD CONSTRAINT FK_TK_MANV
FOREIGN KEY(maSoNV) REFERENCES NHANVIEN(id);

alter table PHIEUKHAMBENH ADD CONSTRAINT FK_PKB_MAPK
FOREIGN KEY(MAPK) REFERENCES PHONGKHAM(ID);

ALTER TABLE PHIEUKHAMBENH ADD CONSTRAINT FK_PKB_MABS
FOREIGN KEY(MABS) REFERENCES NHANVIEN(id);

ALTER TABLE PHIEUKHAMBENH ADD CONSTRAINT FK_PKB_MABN
FOREIGN KEY(MABN) REFERENCES BENHNHAN(id);

ALTER TABLE BIENLAI ADD CONSTRAINT FK_BL_MANV
FOREIGN KEY(MANV_TT) REFERENCES NHANVIEN(id);

ALTER TABLE CTDONTHUOC ADD CONSTRAINT PK_CTDT 
PRIMARY KEY(MATHUOC, MATOA);

ALTER TABLE CTDONTHUOC ADD CONSTRAINT FK_CTDT_MATHUOC
FOREIGN KEY(MATHUOC) REFERENCES THUOC(id);

ALTER TABLE CTDONTHUOC ADD CONSTRAINT FK_CTDT_MATOA
FOREIGN KEY(MATOA) REFERENCES TOATHUOC(id);

ALTER TABLE TOATHUOC ADD CONSTRAINT FK_TT_MABS
FOREIGN KEY(MABS) REFERENCES NHANVIEN(id);

ALTER TABLE TOATHUOC ADD CONSTRAINT FK_TT_MAPKB
FOREIGN KEY(MAPKB) REFERENCES PHIEUKHAMBENH(id);


ALTER TABLE TOATHUOC
ADD CONSTRAINT FK_TT_MABN
FOREIGN KEY(maBN)
REFERENCES BENHNHAN(id);

ALTER TABLE BIENLAI ADD CONSTRAINT FK_BL_MABN
FOREIGN KEY(MATOA) REFERENCES TOATHUOC(id);


INSERT INTO TINH (tenTinh) VALUES
(N'An Giang'),
(N'Bà Rịa - Vũng Tàu'),
(N'Bắc Giang'),
(N'Bắc Kạn'),
(N'Bạc Liêu'),
(N'Bắc Ninh'),
(N'Bến Tre'),
(N'Bình Định'),
(N'Bình Dương'),
(N'Bình Phước'),
(N'Bình Thuận'),
(N'Cà Mau'),
(N'Cần Thơ'),
(N'Cao Bằng'),
(N'Đà Nẵng'),
(N'Đắk Lắk'),
(N'Đắk Nông'),
(N'Điện Biên'),
(N'Đồng Nai'),
(N'Đồng Tháp'),
(N'Gia Lai'),
(N'Hà Giang'),
(N'Hà Nam'),
(N'Hà Nội'),
(N'Hà Tĩnh'),
(N'Hải Dương'),
(N'Hải Phòng'),
(N'Hậu Giang'),
(N'Hòa Bình'),
(N'Hưng Yên'),
(N'Khánh Hòa'),
(N'Kiên Giang'),
(N'Kon Tum'),
(N'Lai Châu'),
(N'Lâm Đồng'),
(N'Lạng Sơn'),
(N'Lào Cai'),
(N'Long An'),
(N'Nam Định'),
(N'Nghệ An'),
(N'Ninh Bình'),
(N'Ninh Thuận'),
(N'Phú Thọ'),
(N'Phú Yên'),
(N'Quảng Bình'),
(N'Quảng Nam'),
(N'Quảng Ngãi'),
(N'Quảng Ninh'),
(N'Quảng Trị'),
(N'Sóc Trăng'),
(N'Sơn La'),
(N'Tây Ninh'),
(N'Thái Bình'),
(N'Thái Nguyên'),
(N'Thanh Hóa'),
(N'Thừa Thiên Huế'),
(N'Tiền Giang'),
(N'TP Hồ Chí Minh'),
(N'Trà Vinh'),
(N'Tuyên Quang'),
(N'Vĩnh Long'),
(N'Vĩnh Phúc'),
(N'Yên Bái');


INSERT INTO BenhNhan (tenBN, sdt, ngaySinh, diaChi, gioiTinh, queQuan, ghichu)
VALUES
    (N'Nguyễn Văn A', '0987654321', '1990-05-15', N'916 Hậu Giang', N'Nam', 1, NULL),
    (N'Trần Thị B', '0976543210', '1992-10-20', N'55 Quang Trung ', N'Nữ', 2, NULL),
    (N'Lê Văn C', '0965432109', '1995-03-25', N'93 Bình Tây', N'Nam', 1, NULL),
	(N'Tường Vi', '0764003108', '2004-02-21', N'93 Bình Tây', N'Nữ', 3, NULL);

	select * from taikhoan

INSERT INTO NhanVien (HOTEN, NGAYSINH, DIACHI, GIOITINH, NGAYVL, VAITRO,TRANGTHAI)
VALUES
    (N'Nguyễn Thị X', '1990-05-15', N'Hà Nội', N'Nữ', '2010-01-01', N'Bác sĩ',N'Đang tồn tại'),
	(N'Trần Văn Bưởi', '1990-05-15', N'Hà Nội', N'Nam', '2010-01-15', N'Bác sĩ',N'Đang tồn tại'),
	(N'Nguyễn Thanh Thức', '1990-05-15', N'Hà Nội', N'Nam', '2011-01-01', N'Bác sĩ',N'Đang tồn tại'),
    (N'Trần Văn Y', '1992-10-20', N'Hải Phòng', N'Nam', '2012-03-15', N'NV Tiếp nhận',N'Đang tồn tại'),
    (N'Lê Thị Z', '1995-03-25', N'Đà Nẵng', N'Nữ', '2015-07-20', N'NV Thanh toán',N'Đang tồn tại'),
    (N'Lê Ba Lê', '1990-05-15', N'Hà Nội', N'Nữ', '2010-01-01', N'Admin',N'Đang tồn tại'),
	(N'Trần Bảo Ngọc', '1990-05-15', N'Hà Nội', N'Nam', '2010-01-15', N'Quản lý kho',N'Đang tồn tại');


INSERT INTO TaiKhoan (hoTen, maSoNV, soDienThoai, email, matKhau, matKhauXacNhan, vaiTro, trangThai)
VALUES
    (N'Nguyễn Thị X',1, '0954321098', 'nguyenthix@example.com', 'nhanvien123', 'nhanvien123', N'Bác sĩ',N'Hiệu lực'),
	 (N'Trần Văn Bưởi',2, '0954321098', 'tranvanbuoi@example.com', 'nhanvien123', 'nhanvien123', N'Bác sĩ',N'Hiệu lực'),
	  (N'Nguyễn Thanh Thức',3, '0954321099', 'nguyenthanhthuc@example.com', 'nhanvien123', 'nhanvien123', N'Bác sĩ',N'Hiệu lực'),
	  
    (N'Trần Văn Y', 4, '0954321099', 'tranvany@example.com', 'nhanvien123', 'nhanvien123', N'NV Tiếp Nhận',N'Hiệu lực'),
	(N'Lê Thị Z', 5, '0954321010', 'lethiz@example.com', 'nhanvien123', 'nhanvien123', N'NV Thanh Toán',N'Hiệu lực'),

    (N'Lê Ba Lê',6, '0954321098', 'lebela@example.com', 'nhanvien123', 'nhanvien123', N'Admin',N'Hiệu lực'),
	 (N'Trần Bảo Ngọc',7, '0954321098', 'tranbaongoc@example.com', 'nhanvien123', 'nhanvien123', N'Quản lý kho',N'Hiệu lực');

	INSERT INTO PHONGKHAM (TENPK, TENKHOA, THOIGIANBD, THOIGIANKT)
VALUES
    (N'Phòng điều trị tiểu đường', N'Khoa Ngoại trú', '08:00', '17:30'),
    (N'Phòng da liễu', N'Khoa Ngoại trú', '08:30', '17:30'),
    (N'Phòng mắt', N'Khoa Ngoại trú', '09:00', '17:30'),
    (N'Phòng tai mũi họng', N'Khoa Ngoại trú', '09:30', '17:30'),
	(N'Phòng tiêu hóa', N'Khoa Ngoại trú', '09:30', '17:30'),
	(N'Phòng điều trị thấp khớp', N'Khoa Ngoại trú', '09:30', '17:30'),
	(N'Phòng cao huyết áp', N'Khoa Ngoại trú', '09:30', '17:30');

	

	INSERT INTO PHIEUKHAMBENH (MABS, MABN, NGAYTAO, CHANDOAN,MAPK)
VALUES
    (1, 1, '2024-05-13', N'Tiểu đường',1),
    (1, 2, '2024-05-13', N'Thấp khớp',6),
	(2, 3, '2024-05-13', N'Viêm mắt',3),
	(3, 4, '2024-05-13', N'Dạ dày',5);
   
 
		INSERT INTO THUOC (TENTHUOC, NUOCSX, DONGIA, HSD,SOLUONGTON,TRANGTHAI)
VALUES
    (N'Paracetamol', N'Việt Nam', 10000, '2025-05-10',50,N'Còn thuốc'),--giảm đau
    (N'Ibuprofen', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--thấp khớp
	(N'Hydroxychloroquine', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--thấp khớp

	(N'Metformin', N'Hoa Kỳ', 20000, '2025-06-15',50,N'Còn thuốc'),--tiểu đường
	(N'Gliptins ', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--tiểu đường
	(N'Sulfonylureas', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--tiểu đường
	(N'Insulin', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--tiểu đường

	(N'Dexamethasone', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--nhỏ mắt
	(N'Antibiotics', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--kháng khuẩn

	(N'Amoxicillin', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--Bệnh Viêm amidan 
	(N'Loratadine', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--Bệnh Viêm mũi 

	(N'Antibiotics', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--kháng khuẩn
	(N'Antibiotics', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--kháng khuẩn


	(N'Sucralfate', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc'),--viêm dạ dày
	(N'Famotidine', N'Mỹ', 20000, '2025-06-15',50,N'Còn thuốc');--viêm dạ dày

		INSERT INTO TOATHUOC (MABS, MABN,TONGTIEN,MaPKB)
VALUES
    (1, 1,600000,1),
    (1, 2,500000,2),
    (2, 3,400000,3),
	(3, 4,400000,4);


		INSERT INTO BIENLAI (MANV_TT, MATOA, TONGTIENKHAM)
VALUES
    (5,1,900000),
	(5,2,800000),
	(5,3,700000),
	(5,4,700000);

		INSERT INTO BIENLAI (MANV_TT, MATOA,NGAYTAO,TONGTIENKHAM)
VALUES
    (5,1,'2023-12-21',900000),
	(5,2,'2023-1-21',800000),
	(5,3,'2022-12-21',700000),
	(5,4,'2021-12-21',700000);

	select * from bienlai
	select * from taikhoan
	
		INSERT INTO CTDONTHUOC (MATHUOC, MATOA,SOLUONG,GHICHU)
VALUES
    (4, 1,10,N'Sáng 1 viên'),
	(5, 1,10,N'Sáng 1 viên'),
	(6, 1,10,N'Sáng 1 viên'),

    (1, 2,10,N'Trưa 1 viên'),
	(2, 2,10,N'Trưa 1 viên'),
	(3, 2,10,N'Trưa 1 viên'),


    (8, 3,10,N'Chiều 1 viên'),
	(9, 3,10,N'Chiều 1 viên'),

	(12, 4,10,N'Chiều 1 viên'),
	(13, 4,10,N'Chiều 1 viên');



		
		
	/***************************************TRIGGER****************************************/


CREATE TRIGGER trg_XoaNhanVien
ON NHANVIEN
INSTEAD OF DELETE
AS 
BEGIN
	BEGIN TRY
		BEGIN TRANSACTION;
			DECLARE @ID INT;
			SELECT @ID = ID FROM DELETED;

			IF EXISTS (SELECT * FROM NHANVIEN WHERE ID = @ID)
			BEGIN
				UPDATE TAIKHOAN
				SET TRANGTHAI = N'Ngưng hoạt động'
				WHERE MASONV = @ID;

				UPDATE NHANVIEN
				SET TRANGTHAI = N'Đã nghỉ việc'
				WHERE ID = @ID;
			END
			ELSE
			BEGIN
				THROW 51000, N'Không tìm thấy nhân viên cần xóa', 1;
			END
		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRANSACTION
		PRINT N'Lỗi xảy ra: ' + ERROR_MESSAGE();
	END CATCH
END;
GO



CREATE TRIGGER trg_XoaThuoc
ON THUOC
INSTEAD OF DELETE
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;
            DECLARE @ID INT;
            SELECT @ID = ID FROM DELETED;

            -- Check if the record exists in the THUOC table
            IF EXISTS (SELECT * FROM THUOC WHERE ID = @ID)
            BEGIN
                -- Update the TRANGTHAI field
                UPDATE THUOC
                SET TRANGTHAI = N'Thuốc không có sẵn'
                WHERE ID = @ID;
            END
            ELSE
            BEGIN
                -- If the record does not exist, throw an error
                THROW 51001, N'Không tìm thấy thuốc', 1;
            END
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        -- Rollback the transaction in case of an error
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        -- Print the error message
        PRINT N'Lỗi xảy ra: ' + ERROR_MESSAGE();
    END CATCH
END;
GO



CREATE TRIGGER trg_UpdateTrangThaiOnInsertOrUpdate
ON THUOC
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Cập nhật trạng thái 'Còn thuốc' khi SOLUONGTON > 0
    UPDATE THUOC
    SET TRANGTHAI = N'Còn thuốc'
    FROM THUOC t
    INNER JOIN inserted i ON t.id = i.id
    WHERE i.SOLUONGTON > 0;

    -- Cập nhật trạng thái 'Đã hết thuốc' khi SOLUONGTON = 0
    UPDATE THUOC
    SET TRANGTHAI = N'Đã hết thuốc'
    FROM THUOC t
    INNER JOIN inserted i ON t.id = i.id
    WHERE i.SOLUONGTON = 0;
END;
GO



CREATE TRIGGER trg_AfterInsert_TaiKhoan
ON TaiKhoan
AFTER INSERT
AS
BEGIN
    UPDATE TaiKhoan
    SET trangThai = N'Hiệu lực'
    WHERE id IN (SELECT id FROM inserted);
END;

go

CREATE TRIGGER TRG_SET_TRANGTHAI
ON NHANVIEN
AFTER INSERT
AS
BEGIN
    -- Set NOCOUNT to ON to prevent extra result sets from interfering with SELECT statements.
    SET NOCOUNT ON;

    -- Update the TRANGTHAI column to 'Đang tồn tại' for newly inserted rows
    UPDATE NHANVIEN
    SET TRANGTHAI = N'Đang tồn tại'
    WHERE id IN (SELECT id FROM INSERTED);
END;
GO


CREATE TRIGGER trg_UpdateThuocOnInsert
ON CTDONTHUOC
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @MATHUOC INT, @SOLUONG INT; 
  
    SELECT @MATHUOC = i.MATHUOC, @SOLUONG = i.SOLUONG
    FROM inserted i;
  
    UPDATE THUOC
    SET SOLUONGTON = SOLUONGTON - @SOLUONG
    WHERE id = @MATHUOC;
   
    IF EXISTS (
        SELECT 1
        FROM THUOC
        WHERE id = @MATHUOC AND SOLUONGTON <= 0
    )
    BEGIN
        UPDATE THUOC
        SET TRANGTHAI = N'Đã hết thuốc'
        WHERE id = @MATHUOC;
    END
END;
GO


IF OBJECT_ID ('trg_UpdateTrangThaiInsteadOfDelete', 'TR') IS NOT NULL
BEGIN
    DROP TRIGGER trg_UpdateTrangThaiInsteadOfDelete;
END;

delete from thuoc where id = 18
select * from thuoc


-- Xóa trigger nếu đã tồn tại
IF OBJECT_ID ('trg_UpdateTrangThaiInsteadOfDelete', 'TR') IS NOT NULL
BEGIN
    DROP TRIGGER trg_UpdateSoLuongTonInsteadOfDelete;
END;
GO

-- Tạo lại trigger để cập nhật trạng thái thay vì xóa
CREATE TRIGGER trg_UpdateSoLuongTonInsteadOfDelete
ON THUOC
INSTEAD OF DELETE
AS 
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;

        -- Cập nhật số lượng tồn của các thuốc bị yêu cầu xóa thành 0
        UPDATE THUOC
        SET SOLUONGTON = 0
        WHERE id IN (SELECT id FROM DELETED);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        PRINT N'Lỗi xảy ra: ' + ERROR_MESSAGE();
    END CATCH
END;
GO




	--tính tổng tiền thuốc của một toa thuốc.
	
CREATE TRIGGER trg_CalculateTotalAmount
ON CTDONTHUOC
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    -- Khai báo biến
    DECLARE @MATOA INT;

    -- Lấy mã toa từ bảng inserted hoặc deleted
    SELECT @MATOA = i.MATOA FROM inserted i;

    IF @MATOA IS NULL
    BEGIN
        SELECT @MATOA = d.MATOA FROM deleted d;
    END

    -- Tính tổng tiền của các thuốc trong toa thuốc
    UPDATE TOATHUOC
    SET TONGTIEN = (
        SELECT SUM(c.SOLUONG * t.DONGIA)
        FROM CTDONTHUOC c
        INNER JOIN THUOC t ON c.MATHUOC = t.id
        WHERE c.MATOA = @MATOA
    )
    WHERE id = @MATOA;
END;

	go
	--1. Tổng tiền khám = tổng tiền thuốc + 300 
CREATE TRIGGER Trigger_TongTienKham
ON BIENLAI
AFTER INSERT, UPDATE
AS
BEGIN
    DECLARE @TongTienThuoc MONEY;
	
    --  tổng tiền thuốc (
    SELECT @TongTienThuoc = TT.TONGTIEN
    FROM TOATHUOC TT , INSERTED I
	WHERE TT.ID = I.MATOA
   
    UPDATE BIENLAI
    SET TONGTIENKHAM = @TongTienThuoc + 300000
    WHERE MATOA IN (SELECT MATOA FROM inserted);
END;
GO
----------------------************************-----------------------
select * from taikhoan

--3. Vaitro trong nhân viên chỉ nhận 5 giá trị 
ALTER TABLE NHANVIEN ADD CONSTRAINT CHECK_VAITRO CHECK (VAITRO IN (N'Bác Sĩ', N'NV Thanh Toán', N'NV Tiếp Nhận',N'Admin',N'Quản lý kho'));
GO
--4. Biên lai chỉ được tạo bởi NV_TT 
--(Tức là manvtt = maSoNV trong nhân viên nhưng phải có role NV_Thanh Toán)
--cách 2 TRIGGER
CREATE TRIGGER T_QuyenTaoBienLai
ON BIENLAI
AFTER INSERT, UPDATE
AS
BEGIN
    -- Kiểm tra xem các giá trị mới thêm vào hoặc cập nhật có phù hợp không
    IF EXISTS ( SELECT 1
			FROM inserted i
			JOIN NHANVIEN nv ON i.MANV_TT = nv.ID
			 WHERE nv.VAITRO != N'NV Thanh Toán'
    )
    BEGIN
        PRINT (N'Biên lai chỉ được tạo bởi nhân viên có vai trò NV Thanh Toán');
        ROLLBACK TRANSACTION;
        RETURN
    END
END

GO

drop trigger TRIG_TONGTIENTHUOC 
drop trigger TRG_MABS 
--Kiem tra tongtienthuoc > 0
CREATE TRIGGER TRIG_TONGTIENTHUOC
ON TOATHUOC
FOR INSERT, UPDATE
AS
BEGIN
    DECLARE @TONGTIENTHUOC MONEY;
    
    -- Cursor to iterate through inserted rows
    DECLARE cur CURSOR FOR
    SELECT TT.TONGTIEN
    FROM TOATHUOC TT
    JOIN INSERTED I ON TT.ID = I.ID;
    
    OPEN cur;
    FETCH NEXT FROM cur INTO @TONGTIENTHUOC;
    
    WHILE @@FETCH_STATUS = 0
    BEGIN
        IF @TONGTIENTHUOC <= 0
        BEGIN
            PRINT N'Tổng tiền thuốc phải lớn hơn 0';
            ROLLBACK TRANSACTION;
            RETURN; -- Stop processing further
        END
        
        FETCH NEXT FROM cur INTO @TONGTIENTHUOC;
    END
    
    CLOSE cur;
    DEALLOCATE cur;
    
    PRINT 'Thành công';
END;

GO
--kiem tra tong tien kham > 0

CREATE TRIGGER TRIG_TONGTIENKHAM
ON BIENLAI
FOR INSERT, UPDATE
AS
BEGIN
    DECLARE @TONGTIENKHAM MONEY;
    
    -- Cursor to iterate through inserted rows
    DECLARE cur CURSOR FOR
    SELECT BL.TONGTIENKHAM
    FROM BIENLAI BL
    JOIN INSERTED I ON BL.ID = I.ID;
    
    OPEN cur;
    FETCH NEXT FROM cur INTO @TONGTIENKHAM;
    
    WHILE @@FETCH_STATUS = 0
    BEGIN
        IF @TONGTIENKHAM <= 0
        BEGIN
            PRINT N'Tổng tiền khám phải lớn hơn 0';
            ROLLBACK TRANSACTION;
            RETURN; -- Stop processing further
        END
        
        FETCH NEXT FROM cur INTO @TONGTIENKHAM;
    END
    
    CLOSE cur;
    DEALLOCATE cur;
    
    PRINT 'Thành công';
END;

GO
--kiem tra so luong (ctdonthuoc)> 0

CREATE TRIGGER TRIG_SOLUONGTHUOC
ON CTDONTHUOC
FOR INSERT, UPDATE
AS
BEGIN
    DECLARE @SOLUONGTHUOC INT;
    
    -- Cursor to iterate through inserted rows
    DECLARE cur CURSOR FOR
    SELECT CT.SOLUONG
    FROM CTDONTHUOC CT
    JOIN INSERTED I ON CT.MATOA = I.MATOA
    
    OPEN cur;
    FETCH NEXT FROM cur INTO @SOLUONGTHUOC;
    
    WHILE @@FETCH_STATUS = 0
    BEGIN
        IF @SOLUONGTHUOC <= 0
        BEGIN
            PRINT N'Số lượng thuốc phải lớn hơn 0';
            ROLLBACK TRANSACTION;
            RETURN; -- Stop processing further
        END
        
        FETCH NEXT FROM cur INTO @SOLUONGTHUOC;
    END
    
    CLOSE cur;
    DEALLOCATE cur;
    
    PRINT 'Thành công';
END;

GO
--kiem tra so luong ton > soluong(ctdonthuoc)
CREATE TRIGGER TRIG_SOLUONGTON
ON CTDONTHUOC
FOR INSERT, UPDATE
AS
BEGIN
    -- Declare variables to store current values
    DECLARE @SOLUONGTON INT, @SOLUONG INT, @MATHUOC INT;
    
    -- Cursor to iterate through inserted rows
    DECLARE cur CURSOR FOR
    SELECT I.MATHUOC, I.SOLUONG, T.SOLUONGTON
    FROM INSERTED I
    JOIN THUOC T ON I.MATHUOC = T.ID;
    
    OPEN cur;
    FETCH NEXT FROM cur INTO @MATHUOC, @SOLUONG, @SOLUONGTON;
    
    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Check if the current stock is less than the required quantity
        IF @SOLUONGTON < @SOLUONG
        BEGIN
            PRINT N'Số lượng tồn không đủ';
            ROLLBACK TRANSACTION;
            CLOSE cur;
            DEALLOCATE cur;
            RETURN; -- Exit the trigger immediately
        END
        ELSE
        BEGIN
            -- Update the stock quantity in THUOC table
            UPDATE THUOC
            SET SOLUONGTON = SOLUONGTON - @SOLUONG
            WHERE ID = @MATHUOC;

        END
        
        -- Fetch the next row
        FETCH NEXT FROM cur INTO @MATHUOC, @SOLUONG, @SOLUONGTON;
    END
    
    CLOSE cur;
    DEALLOCATE cur;
    
    PRINT 'Thành công';
END;



GO
--hsd của thuoc > ngay toathuoc
CREATE TRIGGER TRIG_HSD
ON CTDONTHUOC
FOR INSERT, UPDATE
AS
BEGIN
    -- Declare variables to store current values
    DECLARE @MATHUOC INT, @NGAYTOATHUOC DATE, @HSD DATE;
    
    -- Cursor to iterate through inserted rows
    DECLARE cur CURSOR FOR
    SELECT I.MATHUOC, T.HSD, TT.NGAYLAP
    FROM INSERTED I
    JOIN THUOC T ON I.MATHUOC = T.ID
    JOIN TOATHUOC TT ON I.MATOA = TT.ID;
    
    OPEN cur;
    FETCH NEXT FROM cur INTO @MATHUOC, @HSD, @NGAYTOATHUOC;
    
    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Check if the expiration date is less than or equal to the prescription date
        IF @HSD <= @NGAYTOATHUOC
        BEGIN
            PRINT N'Hạn sử dụng của thuốc không hợp lệ';
            ROLLBACK TRANSACTION;
            CLOSE cur;
            DEALLOCATE cur;
            RETURN; -- Exit the trigger immediately
        END
        
        -- Fetch the next row
        FETCH NEXT FROM cur INTO @MATHUOC, @HSD, @NGAYTOATHUOC;
    END
    
    CLOSE cur;
    DEALLOCATE cur;
    
    PRINT 'Thành công';
END;

GO


--3. Trigger cập nhật thông tin nhân viên khi thông tin tài khoản được cập nhật
drop TRIGGER trg_TaiKhoan_Update_NhanVien
CREATE TRIGGER trg_TaiKhoan_Update_NhanVien
ON TaiKhoan
AFTER UPDATE
AS
BEGIN
  
    DISABLE TRIGGER trg_NhanVien_Update_TaiKhoan ON NhanVien;
    
    BEGIN TRY
     
        UPDATE NHANVIEN
        SET HOTEN = i.hoTen
        FROM NHANVIEN nv
        INNER JOIN inserted i ON nv.id = i.maSoNV;
        
    END TRY
    BEGIN CATCH
        -- Xử lý lỗi nếu cần thiết
        PRINT 'Error occurred in trg_TaiKhoan_Update_NhanVien';
		ROLLBACK TRANSACTION
    END CATCH;

    -- Bật lại trigger trg_NhanVien_Update_TaiKhoan
    ENABLE TRIGGER trg_NhanVien_Update_TaiKhoan ON NhanVien;
END;

GO
--1. Trigger cập nhật tài khoản khi thông tin nhân viên tương ứng được cập nhật
DROP TRIGGER IF EXISTS trg_NhanVien_Update_TaiKhoan;
CREATE TRIGGER trg_NhanVien_Update_TaiKhoan
ON NhanVien
AFTER UPDATE
AS
BEGIN
    -- Tắt trigger trg_TaiKhoan_Update_NhanVien để tránh vòng lặp
    DISABLE TRIGGER trg_TaiKhoan_Update_NhanVien ON TaiKhoan;

    BEGIN TRY
        -- Chỉ cập nhật những tài khoản có maSoBN khớp với id của bệnh nhân
        UPDATE tk
        SET tk.hoTen = i.HOTEN
        FROM TaiKhoan tk
        INNER JOIN inserted i ON tk.maSoNV= i.id
    END TRY
    BEGIN CATCH
        -- Xử lý lỗi nếu cần thiết
        PRINT 'Error occurred in trg_NhanVien_Update_TaiKhoan';
		ROLLBACK TRANSACTION;
    END CATCH;

	 -- Bật lại trigger trg_TaiKhoan_Update_NhanVien
    ENABLE TRIGGER trg_TaiKhoan_Update_NhanVien ON TaiKhoan;
END;
go

select * from TaiKhoan
--NGAY LAP TOA THUOC > HSD CỦA THUỐC 
CREATE OR ALTER TRIGGER TRG_HANSD_THUOC
ON CTDONTHUOC
FOR INSERT, UPDATE
AS
BEGIN
	BEGIN TRY
		BEGIN TRANSACTION;

		DECLARE 
		@MATHUOC INT,
		@MATOA INT,
		@HSD SMALLDATETIME,
		@NGAYLAP SMALLDATETIME;

		SELECT @MATOA = MATOA, @MATHUOC = MATHUOC
		FROM INSERTED;

		SELECT @HSD = HSD
		FROM THUOC
		WHERE ID = @MATHUOC;

		SELECT @NGAYLAP = NGAYLAP
		FROM TOATHUOC
		WHERE ID = @MATOA;

		IF (@NGAYLAP > @HSD)
		BEGIN
			ROLLBACK TRANSACTION;
			THROW 51001, N'Thuốc đã hết hạn sử dụng, chon thuốc khác', 1;
		END;

		COMMIT TRANSACTION;	
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRANSACTION;
		PRINT N'Lỗi xảy ra: ' + ERROR_MESSAGE();
	END CATCH
END
GO
--TOA THUỐC CHỈ ĐƯỢC TẠO BỞI BÁC SĨ


CREATE OR ALTER TRIGGER TRG_MABS
ON TOATHUOC
FOR INSERT, UPDATE
AS
BEGIN
	BEGIN TRY
		BEGIN TRANSACTION;
			DECLARE
			@MABS INT,
			@VAITRO NVARCHAR(20);

			SELECT @MABS = MABS
			FROM INSERTED;

			SELECT @VAITRO = VAITRO
			FROM NHANVIEN
			WHERE ID = @MABS;

			IF (@VAITRO <> N'Bác sĩ')
			BEGIN 
				ROLLBACK TRANSACTION;
				THROW 51002, N'Toa thuốc chỉ được tạo bởi bác sĩ', 1;
			END;

		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRANSACTION;
		PRINT N'Lỗi xảy ra: ' + ERROR_MESSAGE();
	END CATCH
END
GO

drop trigger TRG_NGAYTAO_PKB 

go

--NGAYTAO phải lớn hơn NGAYVL của bác sĩ
CREATE OR ALTER TRIGGER TRG_NGAYTAO_PKB 
ON PHIEUKHAMBENH
FOR INSERT, UPDATE
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;

        DECLARE @ngaytao smalldatetime,
                @mabs int,
                @ngayvl smalldatetime;

        SELECT @ngaytao = NGAYTAO,
               @mabs = MABS
        FROM inserted;

        SELECT @ngayvl = NGAYVL
        FROM NHANVIEN 
        WHERE id = @mabs;

        IF (@ngaytao < @ngayvl)
        BEGIN
            ROLLBACK TRANSACTION;
            THROW 51000, N'NGAYTAO phải lớn hơn NGAYVL của bác sĩ', 1;
        END;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        PRINT N'Lỗi xảy ra: ' + ERROR_MESSAGE();
    END CATCH;
END;
GO

drop trigger T_NGTAO_NGSINH 
--11 ngaytao (phieu kham benh) > ngaysinh benh nhanh
CREATE OR ALTER TRIGGER T_NGTAO_NGSINH
ON PHIEUKHAMBENH
FOR INSERT, UPDATE
AS
BEGIN
	SET NOCOUNT ON
 
	DECLARE @NGAYTAO SMALLDATETIME;
    DECLARE @MABN INT;
    DECLARE @NGAYSINH SMALLDATETIME;

     SELECT @NGAYTAO = NGAYTAO, @MABN = MABN
    FROM INSERTED;

     SELECT @NGAYSINH = NGAYSINH
    FROM BENHNHAN
    WHERE id = @MABN;

	IF(@NGAYTAO < @NGAYSINH)
	BEGIN
		RAISERROR(N'Ngay sinh phai nho hon ngay tao cua phieu kham benh ', 16, 1)
		ROLLBACK TRANSACTION
	END	
END;
GO
drop trigger T_NGTAO_NGSINH_bsi 
--12 ngaytao (phieu kham benh) > ngaysinh bac si
CREATE OR ALTER TRIGGER T_NGTAO_NGSINH_bsi
ON PHIEUKHAMBENH
FOR INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @NGAYTAO SMALLDATETIME;
    DECLARE @MABS INT;
    DECLARE @NGAYSINH SMALLDATETIME;

    BEGIN TRANSACTION;

    BEGIN TRY
        SELECT @NGAYTAO = NGAYTAO, @MABS = MABS
        FROM INSERTED;

        SELECT @NGAYSINH = NGAYSINH
        FROM NHANVIEN
        WHERE id = @MABS;

        IF(@NGAYTAO < @NGAYSINH)
        BEGIN
            RAISERROR(N'Ngay tao phai lon hon ngay sinh cua bac si', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
    END CATCH;
END;
GO
drop trigger T_NGTAO_TRONG_KHOANG_TG_PK 
--13 tg bat dau < gio tao (pkb) < tg ket thuc
CREATE OR ALTER TRIGGER T_NGTAO_TRONG_KHOANG_TG_PK
ON PHIEUKHAMBENH
FOR INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @NGAYTAO SMALLDATETIME;
    DECLARE @MAPK INT;
    DECLARE @THOIGIANBD TIME;
    DECLARE @THOIGIANKT TIME;

    BEGIN TRANSACTION;

    BEGIN TRY
        SELECT @NGAYTAO = NGAYTAO, @MAPK = MAPK
        FROM INSERTED;

        SELECT @THOIGIANBD = THOIGIANBD, @THOIGIANKT = THOIGIANKT
        FROM PHONGKHAM
        WHERE id = @MAPK;

        DECLARE @GIO_TAO TIME;
        SET @GIO_TAO = CAST(@NGAYTAO AS TIME);

        IF (@GIO_TAO < @THOIGIANBD OR @GIO_TAO > @THOIGIANKT)
        BEGIN
            RAISERROR(N'Gio tao cua phieu kham benh phai trong khoang thoi gian bat dau va ket thuc cua phong kham', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
    END CATCH;
END;
GO

--ngay tao bien lai > ngay vao lam cua NV có role thanh toan(KIỂM TRA LẠI NHA DO LA MÌNH GỘP 1 BẢNG NHÂN VIÊN => CHECK ROLE)
CREATE OR ALTER TRIGGER TRG_NGAYTAO_BIENLAI
ON BIENLAI
FOR INSERT, UPDATE
AS
BEGIN
	BEGIN TRY
		BEGIN TRANSACTION;
			DECLARE 
			@MANV_TT INT,
			@NGAYTAO DATETIME,
			@NGAYVL SMALLDATETIME;

			SELECT @MANV_TT = MANV_TT, @NGAYTAO = NGAYTAO  
			FROM INSERTED
			
			IF NOT EXISTS (SELECT * FROM NHANVIEN WHERE ID = @MANV_TT AND VAITRO = N'NV Thanh toán')
			BEGIN
				ROLLBACK TRANSACTION;
				THROW 51007, N'Không tồn tại nhân viên với ID trên', 1;
			END
			ELSE
			BEGIN
				SELECT @NGAYVL = NGAYVL
				FROM NHANVIEN
				WHERE ID = @MANV_TT;

				IF(@NGAYTAO < @NGAYVL)
				BEGIN
					ROLLBACK TRANSACTION;
					THROW 51008, N'NGAYTAO biên lai phải lớn hơn NGAYVL của NV_TT', 1;
				END
			END
		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH 
		IF @@TRANCOUNT > 0
			ROLLBACK TRANSACTION;
		PRINT N'Lỗi xảy ra: ' + ERROR_MESSAGE();
	END CATCH
END
GO

/***************************************PROC*******************************/
CREATE PROCEDURE PRO_DANGNHAP
    @EMAIL VARCHAR(50),
    @MATKHAU VARCHAR(50),
    @VAITRO NVARCHAR(20)
AS
BEGIN
    -- Bật NOCOUNT để ngăn việc trả về thông tin đếm số dòng bị ảnh hưởng
    SET NOCOUNT ON;

    DECLARE @UserExists INT;
    DECLARE @RoleMatches INT;
    DECLARE @TrangThai NVARCHAR(20);

    -- Kiểm tra xem email và mật khẩu có tồn tại trong bảng TaiKhoan không
    SELECT @UserExists = COUNT(*)
    FROM TaiKhoan
    WHERE email = @EMAIL AND matKhau = @MATKHAU;

    IF @UserExists = 1
    BEGIN
        -- Kiểm tra trạng thái của tài khoản
        SELECT @TrangThai = trangThai
        FROM TaiKhoan
        WHERE email = @EMAIL AND matKhau = @MATKHAU;

        IF @TrangThai = N'Hiệu lực'
        BEGIN
            -- Kiểm tra vai trò của tài khoản có khớp với vai trò được cung cấp không
            SELECT @RoleMatches = COUNT(*)
            FROM TaiKhoan
            WHERE email = @EMAIL AND matKhau = @MATKHAU AND vaiTro = @VAITRO;

            IF @RoleMatches = 1
            BEGIN
                -- Đăng nhập thành công
                SELECT N'Đăng nhập thành công' AS Message, @VAITRO AS Role;
            END
            ELSE
            BEGIN
                -- Vai trò không khớp
                SELECT N'Vai trò không đúng' AS Message;
            END
        END
        ELSE
        BEGIN
            -- Trạng thái không hợp lệ
            SELECT N'Tài khoản không hiệu lực' AS Message;
        END
    END
    ELSE
    BEGIN
        -- Email hoặc mật khẩu không đúng
        SELECT N'Email hoặc mật khẩu không đúng' AS Message;
    END
END;
GO

--dang ky
CREATE PROCEDURE PRO_DANGKY
    @HOTEN NVARCHAR(50),
    @MASONV VARCHAR(10),
    @SDT VARCHAR(10),
    @EMAIL VARCHAR(50),
    @MATKHAU VARCHAR(50),
    @MATKHAUXACNHAN VARCHAR(50),
    @VAITRO NVARCHAR(20)
AS
BEGIN
    -- Disable row count messages
    SET NOCOUNT ON;

    -- Check if passwords match
    IF @MATKHAU != @MATKHAUXACNHAN
    BEGIN
        SELECT N'MẬT KHẨU KHÔNG KHỚP NHAU' AS Message;
        RETURN;
    END

    -- Variables to store existence checks and IDs
    DECLARE @UserExists INT, @EmployeeExists INT, @ID INT, @Role NVARCHAR(20), @EmployeeStatus NVARCHAR(20);

    -- Validate roles
    IF @VAITRO IN (N'Bác sĩ', N'NV Tiếp Nhận', N'NV Thanh Toán', N'Admin', N'Quản lý kho')
    BEGIN
        SELECT @EmployeeExists = COUNT(*)
        FROM NHANVIEN
        WHERE MANV = @MASONV;

        IF @EmployeeExists > 0
        BEGIN
            SELECT @ID = ID, @Role = vaiTro, @EmployeeStatus = trangThai
            FROM NHANVIEN
            WHERE MANV = @MASONV;

            -- Check if the role matches
            IF @Role != @VAITRO
            BEGIN
                SELECT N'Vai trò không khớp với hồ sơ nhân viên' AS Message;
                RETURN;
            END

            -- Check if the employee status is 'đang còn làm'
            IF @EmployeeStatus != N'đang còn làm'
            BEGIN
                SELECT N'Trạng thái nhân viên không hợp lệ' AS Message;
                RETURN;
            END

            -- Insert the new account
            INSERT INTO TAIKHOAN (hoTen, maSoNV, soDienThoai, email, matKhau, matKhauxacnhan, vaiTro, TRANGTHAI)
            VALUES (@HOTEN, @ID, @SDT, @EMAIL, @MATKHAU, @MATKHAUXACNHAN, @VAITRO, N'Hiệu lực');

            SELECT N'Tài khoản nhân viên tạo thành công' AS Message;
        END
        ELSE
        BEGIN
            SELECT N'Nhân viên không tồn tại' AS Message;
        END
    END
    ELSE
    BEGIN
        SELECT N'Vai trò không hợp lệ' AS Message;
    END
END;
GO


--1. Tạo procedure thêm thông tin bệnh nhân
CREATE PROCEDURE InsertBenhNhan
    @tenBN NVARCHAR(50),
    @sdt VARCHAR(10),
    @ngaySinh SMALLDATETIME,
    @diaChi NVARCHAR(50),
    @gioiTinh NVARCHAR(5),
    @queQuan NVARCHAR(20),
    @ghichu NVARCHAR(50) = NULL
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRANSACTION;
        
        INSERT INTO BenhNhan (tenBN, sdt, ngaySinh, diaChi, gioiTinh, queQuan, ghichu)
        VALUES (@tenBN, @sdt, @ngaySinh, @diaChi, @gioiTinh, @queQuan, @ghichu);
		PRINT N'Thêm bệnh nhân thành công';
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        PRINT N'Thêm bệnh nhhân không thành công';
    END CATCH;
END;

GO
--2. Tạo procedure thêm thông tin nhân viên 

CREATE PROCEDURE InsertNhanVien
	@HOTEN NVARCHAR(50),
	@NGAYSINH SMALLDATETIME,
	@DIACHI NVARCHAR(50),
	@GIOITINH NVARCHAR(5),
	@NGAYVL SMALLDATETIME,
	@VAITRO NVARCHAR(20),
	@TRANGTHAI NVARCHAR(20)
AS
BEGIN
	SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRANSACTION;
        
        INSERT INTO NHANVIEN (HOTEN, NGAYSINH, DIACHI, GIOITINH, NGAYVL, VAITRO,TRANGTHAI)
        VALUES (@HOTEN, @NGAYSINH, @DIACHI, @GIOITINH, @NGAYVL, @VAITRO,N'ĐANG TỒN TẠI');
		PRINT N'Thêm nhân viên thành công';
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        PRINT N'Thêm nhân viên không thành công';
    END CATCH;
END;

GO
--3.Stored procedure để tạo một phiếu khám bệnh mới cho một bệnh nhân (thêm phiếu khám bệnh)
CREATE PROCEDURE InsertPhieuKhamBenh
    @MABS INT,
    @MABN INT,
	@MAPK INT,
    @NGAYTAO SMALLDATETIME,
    @CHANDOAN NVARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRANSACTION;

    BEGIN TRY
		DECLARE @ValidMABS INT
		DECLARE @ValidMABN INT 

        SELECT @MABS = id
		FROM NHANVIEN
		WHERE id = @MABS
		AND VAITRO = 'Bác sỹ';

		SELECT @MABN = id 
		FROM BenhNhan
		WHERE id = @MABN;

		IF @MABS IS NULL OR @MABN IS NULL
		BEGIN
			THROW 51000, N'Mã bác sỹ và mã bệnh nhân không tồn tại', 1;
		END;

        INSERT INTO PHIEUKHAMBENH (MABS, MABN, MAPK, NGAYTAO, CHANDOAN)
        VALUES (@MABS, @MABN, @MAPK, @NGAYTAO, @CHANDOAN);

        COMMIT TRANSACTION;
        PRINT N'Thêm phiếu khám bệnh thành công';
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        PRINT N'Thêm phiếu khám bệnh thất bại';
    END CATCH;
END;
GO

select * from taikhoan
--4. procedure thêm một phòng khám
CREATE PROCEDURE ThemPhongKham
	@TENPK NVARCHAR(50),
	@TENKHOA NVARCHAR(50),
	@THOIGIANBD TIME,
	@THOIGIANKT TIME
AS
BEGIN
	SET NOCOUNT ON;
	BEGIN TRANSACTION;

	BEGIN TRY
		IF @THOIGIANBD >= @THOIGIANKT
		BEGIN 
			RAISERROR ('Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc', 16,1)
			RETURN;
		END

		INSERT INTO PHONGKHAM (TENPK, TENKHOA, THOIGIANBD, THOIGIANKT)
		VALUES (@TENPK, @TENKHOA, @THOIGIANBD, @THOIGIANKT)
		COMMIT TRANSACTION
		PRINT N'Thêm phòng khám thành công';
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT >0
			ROLLBACK TRANSACTION
		PRINT N'Thêm phòng khám thất bại. ' + ERROR_MESSAGE();
	END CATCH
END;

GO

--6. stored procedure để thêm một toa thuốc
CREATE PROCEDURE ThemToaThuoc
    @MABS INT,
    @maBN INT,
    @TONGTIEN MONEY,
    @NGAYLAP DATETIME = NULL
AS
BEGIN
	SET NOCOUNT ON;
	IF @NGAYLAP IS NULL
	BEGIN
		SET @NGAYLAP = GETDATE()
	END

	BEGIN TRANSACTION ThemToaThuocTransaction
	BEGIN TRY
		IF  NOT EXISTS (SELECT 1 FROM NHANVIEN WHERE @MABS = id AND VAITRO = N'Bác sĩ')
		BEGIN
			RAISERROR ('Mã bác sỹ không tồn tại', 16,1);
			RETURN;
		END;
		IF  NOT EXISTS (SELECT 1 FROM BENHNHAN WHERE @MABN = id )
		BEGIN
			RAISERROR ('Mã bệnh nhân không tồn tại', 16,1);
			RETURN;
		END;

		IF EXISTS (SELECT 1 FROM TOATHUOC WHERE MABS = @MABS AND maBN = @maBN)
        BEGIN
            RAISERROR ('Toa thuốc đã tồn tại.', 16, 1);
            RETURN;
        END;

		INSERT INTO TOATHUOC (MABS, maBN, TONGTIEN, NGAYLAP)
        VALUES (@MABS, @maBN, @TONGTIEN, @NGAYLAP);
		COMMIT TRANSACTION ThemToaThuoc
		PRINT N'Thêm toa thuốc thành công';
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRANSACTION ThemToaThuocTransaction
			PRINT N'Thêm toa thuốc thất bại. ' + ERROR_MESSAGE();
	END CATCH
END;

GO

--7. procedure thêm một chi tiết đơn thuốc
CREATE PROCEDURE ThemCTDonThuoc
    @MATHUOC INT,
    @MATOA INT,
    @SOLUONG INT
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRANSACTION;

    BEGIN TRY
        IF NOT EXISTS (SELECT 1 FROM THUOC WHERE id = @MATHUOC)
        BEGIN
            RAISERROR('Mã thuốc không tồn tại trong bảng THUOC.', 16, 1);
            RETURN;
        END;
        IF NOT EXISTS (SELECT 1 FROM TOATHUOC WHERE id = @MATOA)
        BEGIN
            RAISERROR('Mã toa thuốc không tồn tại trong bảng TOATHUOC.', 16, 1);
            RETURN;
        END;
		IF EXISTS (SELECT 1 FROM CTDONTHUOC WHERE MATHUOC = @MATHUOC AND MATOA = @MATOA AND SOLUONG = @SOLUONG)
        BEGIN
            RAISERROR ('Chi tiết đơn thuốc đã tồn tại.', 16, 1);
            RETURN;
        END;
        -- Chèn dữ liệu vào bảng CTDONTHUOC
        INSERT INTO CTDONTHUOC (MATHUOC, MATOA, SOLUONG)
        VALUES (@MATHUOC, @MATOA, @SOLUONG);

        -- Commit transaction nếu mọi thứ thành công
        COMMIT TRANSACTION;
        PRINT N'Thêm chi tiết đơn thuốc thành công.';
    END TRY
    BEGIN CATCH
        -- Rollback transaction nếu có lỗi
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;

        -- In thông báo lỗi chi tiết
        PRINT N'Thêm chi tiết đơn thuốc thất bại. ' + ERROR_MESSAGE();
    END CATCH;
END;

GO

--8. procedure thêm thông tin thuốc

CREATE PROCEDURE ThemThuoc
    @TENTHUOC NVARCHAR(50),
    @NUOCSX NVARCHAR(50),
    @DONGIA MONEY,
    @HSD SMALLDATETIME NULL,
    @SOLUONGTON INT,
    @TRANGTHAI NVARCHAR(20) NULL
AS 
BEGIN
    SET NOCOUNT ON;
    BEGIN TRANSACTION ThemThuocTransaction;
    BEGIN TRY
		SET @HSD = COALESCE(@HSD, GETDATE()); -- Sử dụng COALESCE để cung cấp giá trị mặc định cho HSD nếu nó là null

        INSERT INTO THUOC (TENTHUOC, NUOCSX, DONGIA, HSD, SOLUONGTON,TRANGTHAI)
        VALUES (@TENTHUOC, @NUOCSX, @DONGIA, @HSD, @SOLUONGTON,N'Còn thuốc');
        COMMIT TRANSACTION ThemThuocTransaction;
        PRINT N'Thêm thuốc thành công';
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION ThemThuocTransaction;
        PRINT N'Thêm thuốc thất bại. ' + ERROR_MESSAGE();
    END CATCH;
END;

GO
--9. Cập nhật phiếu khám bệnh

CREATE PROCEDURE CapNhatPhieuKhamBenh
    @MAPKB VARCHAR(10),
    @MABS INT = NULL,
    @MABN INT = NULL,
    @MAPK INT = NULL,
    @NGAYTAO SMALLDATETIME = NULL,
    @CHANDOAN NVARCHAR(50) = NULL
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRANSACTION CapNhatPhieuKhamBenhTransaction;

    BEGIN TRY
        -- Kiểm tra xem phiếu khám bệnh có tồn tại không
        IF NOT EXISTS (SELECT 1 FROM PHIEUKHAMBENH WHERE MAPKB = @MAPKB)
        BEGIN
            RAISERROR('Phiếu khám bệnh không tồn tại.', 16, 1);
            RETURN;
        END;

        -- Lấy giá trị hiện tại của các trường trong bảng
        DECLARE @MABS_OLD INT, @MABN_OLD INT, @MAPK_OLD INT, @NGAYTAO_OLD SMALLDATETIME, @CHANDOAN_OLD NVARCHAR(50);
        SELECT @MABS_OLD = MABS, @MABN_OLD = MABN, @MAPK_OLD = MAPK, @NGAYTAO_OLD = NGAYTAO, @CHANDOAN_OLD = CHANDOAN
        FROM PHIEUKHAMBENH
        WHERE MAPKB = @MAPKB;

        -- Gán giá trị cũ cho các biến nếu các tham số truyền vào là null
        SET @MABS = COALESCE(@MABS, @MABS_OLD);
        SET @MABN = COALESCE(@MABN, @MABN_OLD);
        SET @MAPK = COALESCE(@MAPK, @MAPK_OLD);
        SET @NGAYTAO = COALESCE(@NGAYTAO, @NGAYTAO_OLD);
        SET @CHANDOAN = COALESCE(@CHANDOAN, @CHANDOAN_OLD);

        -- Cập nhật các trường
        UPDATE PHIEUKHAMBENH
        SET
            MABS = @MABS,
            MABN = @MABN,
            MAPK = @MAPK,
            NGAYTAO = @NGAYTAO,
            CHANDOAN = @CHANDOAN
        WHERE MAPKB = @MAPKB;

        -- Commit giao dịch nếu mọi thứ thành công
        COMMIT TRANSACTION CapNhatPhieuKhamBenhTransaction;
        PRINT N'Cập nhật phiếu khám bệnh thành công';
    END TRY
    BEGIN CATCH
        -- Rollback giao dịch nếu có lỗi
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION CapNhatPhieuKhamBenhTransaction;
        PRINT N'Cập nhật phiếu khám bệnh thất bại. ' + ERROR_MESSAGE();
    END CATCH;
END;

GO
--10.Cập nhật giá thuốc
CREATE PROCEDURE CapNhatGiaThuoc
    @TENTHUOC NVARCHAR(50),
    @NUOCSX NVARCHAR(50),
    @DONGIA MONEY
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRANSACTION CapNhatGiaThuocTransaction;

    BEGIN TRY
        IF NOT EXISTS (SELECT 1 FROM THUOC WHERE TENTHUOC = @TENTHUOC AND NUOCSX = @NUOCSX)
        BEGIN
            RAISERROR('Loại thuốc không tồn tại.', 16, 1);
            RETURN;
        END;

        UPDATE THUOC
        SET DONGIA = @DONGIA
        WHERE TENTHUOC = @TENTHUOC AND NUOCSX = @NUOCSX;

        COMMIT TRANSACTION CapNhatGiaThuocTransaction;
        PRINT N'Cập nhật giá thuốc thành công';
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION CapNhatGiaThuocTransaction;
        PRINT N'Cập nhật giá thuốc thất bại. ' + ERROR_MESSAGE();
    END CATCH;
END;

GO
--11. Cập nhật thông tin bệnh nhân
GO
CREATE PROCEDURE CapNhatBenhNhan
    @maBN VARCHAR(10) = NULL,
    @tenBN NVARCHAR(50) = NULL,
    @sdt VARCHAR(10) = NULL,
    @ngaySinh SMALLDATETIME = NULL,
    @diaChi NVARCHAR(50) = NULL,
    @gioiTinh NVARCHAR(5) = NULL,
    @queQuan NVARCHAR(20) = NULL,
    @ghichu NVARCHAR(50) = NULL
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRANSACTION CapNhatBenhNhanTransaction;
    BEGIN TRY
        -- Kiểm tra xem bệnh nhân có tồn tại không
        IF NOT EXISTS (SELECT 1 FROM BenhNhan WHERE maBN = @maBN)
        BEGIN
            RAISERROR('Bệnh nhân không tồn tại.', 16, 1);
            RETURN;
        END;

        -- Lấy giá trị hiện tại của các trường trong bảng
        DECLARE @tenBN_OLD NVARCHAR(50), @sdt_OLD VARCHAR(10), @ngaySinh_OLD SMALLDATETIME, @diaChi_OLD NVARCHAR(50), @gioiTinh_OLD NVARCHAR(5), @queQuan_OLD NVARCHAR(20), @ghichu_OLD NVARCHAR(50);
        SELECT @tenBN_OLD = tenBN, @sdt_OLD = sdt, @ngaySinh_OLD = ngaySinh, @diaChi_OLD = diaChi, @gioiTinh_OLD = gioiTinh, @queQuan_OLD = queQuan, @ghichu_OLD = ghichu
        FROM BenhNhan
        WHERE maBN = @maBN;

        -- Gán giá trị cũ cho các tham số nếu chúng là null
        SET @tenBN = COALESCE(@tenBN, @tenBN_OLD);
        SET @sdt = COALESCE(@sdt, @sdt_OLD);
        SET @ngaySinh = COALESCE(@ngaySinh, @ngaySinh_OLD);
        SET @diaChi = COALESCE(@diaChi, @diaChi_OLD);
        SET @gioiTinh = COALESCE(@gioiTinh, @gioiTinh_OLD);
        SET @queQuan = COALESCE(@queQuan, @queQuan_OLD);
        SET @ghichu = COALESCE(@ghichu, @ghichu_OLD);

        UPDATE BenhNhan
        SET
            tenBN = @tenBN,
            sdt = @sdt,
            ngaySinh = @ngaySinh,
            diaChi = @diaChi,
            gioiTinh = @gioiTinh,
            queQuan = @queQuan,
            ghichu = @ghichu
        WHERE maBN = @maBN;

        COMMIT TRANSACTION CapNhatBenhNhanTransaction;
        PRINT N'Cập nhật thông tin bệnh nhân thành công';
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION CapNhatBenhNhanTransaction;
        PRINT N'Cập nhật thông tin bệnh nhân thất bại. ' + ERROR_MESSAGE();
    END CATCH;
END;

/**********************PROC XOA************************/

CREATE PROCEDURE XOABENHNHAN
	@MABN INT
AS
BEGIN
	SET NOCOUNT ON;

	BEGIN TRY
		BEGIN TRANSACTION;
		IF NOT EXISTS (SELECT * FROM BENHNHAN WHERE ID = @MABN)
		BEGIN
			ROLLBACK TRANSACTION;
			THROW 51004, N'Bệnh nhân không tồn tại', 1;
		END
		ELSE 
		BEGIN
			IF EXISTS (SELECT * FROM PHIEUKHAMBENH WHERE MABN = @MABN)
				DELETE FROM PHIEUKHAMBENH WHERE MABN = @MABN;
			IF EXISTS (SELECT * FROM TOATHUOC WHERE MABN = @MABN)
			BEGIN
				DECLARE @MATOA INT;
				DECLARE CURSOR_TOATHUOC CURSOR FOR
				SELECT ID FROM TOATHUOC WHERE MABN = @MABN;
				OPEN CURSOR_TOATHUOC;
				FETCH NEXT FROM CURSOR_TOATHUOC INTO @MATOA;
				WHILE @@FETCH_STATUS = 0
				BEGIN
					EXEC XOATOATHUOC @MATOA;
					FETCH NEXT FROM CURSOR_TOATHUOC INTO @MATOA;
				END
				CLOSE CURSOR_TOATHUOC;
				DEALLOCATE CURSOR_TOATHUOC;
			END
			DELETE FROM BENHNHAN WHERE ID = @MABN;
		END
		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRANSACTION;
		PRINT N'Lỗi xảy ra: ' + ERROR_MESSAGE();
	END CATCH
END
GO

CREATE PROCEDURE XOANHANVIEN
	@ID INT
AS 
BEGIN
	BEGIN TRY
		BEGIN TRANSACTION;
			IF EXISTS (SELECT * FROM NHANVIEN WHERE ID = @ID)
			BEGIN
				UPDATE TAIKHOAN
				SET TRANGTHAI = N'Ngưng hoạt động'
				WHERE MASONV = @ID;

				UPDATE NHANVIEN
				SET TRANGTHAI = N'Đã nghỉ việc'
				WHERE ID = @ID;
			END
			ELSE
			BEGIN
				THROW 51000, N'Không tìm thấy nhân viên cần xóa', 1;
			END
		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRANSACTION
		PRINT N'Lỗi xảy ra: ' + ERROR_MESSAGE();
	END CATCH
END
GO


CREATE PROCEDURE XOATHUOC 
	@ID INT
AS
BEGIN
	BEGIN TRY
		BEGIN TRANSACTION;
			IF EXISTS (SELECT * FROM THUOC)
			BEGIN
				UPDATE THUOC
				SET TRANGTHAI = N'Thuốc không có sẵn'
				WHERE ID = @ID;
			END
			BEGIN
				THROW 51001, N'Không tìm thấy thuốc', 1;
			END
		COMMIT TRANSACTION;
	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
			ROLLBACK TRANSACTION
		PRINT N'Lỗi xảy ra: ' + ERROR_MESSAGE();
	END CATCH
END
GO


/**************************************FUNCTION***********************************/
--Tinh Doanh thu theo ngay ( Tong cac tien kham)
GO
CREATE FUNCTION dbo.DoanhThu (@ngay DATE)
RETURNS MONEY
AS
BEGIN
    DECLARE @tongTienKham MONEY;
    
    SELECT @tongTienKham = SUM(tongTienKham)
    FROM BienLai
    WHERE CAST(ngayTao AS DATE) = @ngay;
    
    RETURN ISNULL(@tongTienKham, 0); -- Trả về 0 nếu không có bản ghi nào
END;
--So luong benh nhan kham trong ngay
GO

CREATE FUNCTION dbo.SoLuongKhamTrongNgay (@ngay DATE)
RETURNS int
AS
BEGIN
    DECLARE @SoLuongBenhNhan  int;
    
    SELECT @SoLuongBenhNhan = count(*) 
    FROM BienLai
    WHERE CAST(ngayTao AS DATE) = @ngay;
    
    RETURN ISNULL(@SoLuongBenhNhan, 0); -- Trả về 0 nếu không có bản ghi nào
END;

--Tinh tong so lan kham cua 1 benh nhan
CREATE OR ALTER FUNCTION TONGLANKHAM (@MABN INT)
RETURNS INT
AS
BEGIN
    DECLARE @TONGLANKHAM INT = 0;

        SELECT @TONGLANKHAM = COUNT(*)
        FROM PHIEUKHAMBENH
        WHERE MABN = @MABN;

    RETURN @TONGLANKHAM;
END
GO

--Tinh tong so bien lai duoc xuat ra trong ngay ( thong ke theo ngay)
CREATE OR ALTER FUNCTION TONGBIENLAI (@NGAY SMALLDATETIME)
RETURNS INT
AS
BEGIN
	DECLARE @TONGBIENLAI INT;

	SELECT @TONGBIENLAI = COUNT(*)
	FROM PHIEUKHAMBENH
	WHERE NGAYTAO = @NGAY;

	RETURN @TONGBIENLAI;
END
GO

---------------DEADLOCK----------------

--3. Trigger cập nhật thông tin nhân viên khi thông tin tài khoản được cập nhật
drop TRIGGER trg_TaiKhoan_Update_NhanVien
CREATE TRIGGER trg_TaiKhoan_Update_NhanVien
ON TaiKhoan
AFTER UPDATE
AS
BEGIN
  
    DISABLE TRIGGER trg_NhanVien_Update_TaiKhoan ON NhanVien;
    
    BEGIN TRY
     
        UPDATE NHANVIEN
        SET HOTEN = i.hoTen
        FROM NHANVIEN nv
        INNER JOIN inserted i ON nv.id = i.maSoNV;
        
    END TRY
    BEGIN CATCH
        -- Xử lý lỗi nếu cần thiết
        PRINT 'Error occurred in trg_TaiKhoan_Update_NhanVien';
		ROLLBACK TRANSACTION
    END CATCH;
	-- WAITFOR DELAY '00:00:05';
    -- Bật lại trigger trg_NhanVien_Update_TaiKhoan
    ENABLE TRIGGER trg_NhanVien_Update_TaiKhoan ON NhanVien;
END;


--1. Trigger cập nhật tài khoản khi thông tin nhân viên tương ứng được cập nhật
DROP TRIGGER IF EXISTS trg_NhanVien_Update_TaiKhoan;
CREATE TRIGGER trg_NhanVien_Update_TaiKhoan
ON NhanVien
AFTER UPDATE
AS
BEGIN
    -- Tắt trigger trg_TaiKhoan_Update_NhanVien để tránh vòng lặp
    DISABLE TRIGGER trg_TaiKhoan_Update_NhanVien ON TaiKhoan;

    BEGIN TRY
        -- Chỉ cập nhật những tài khoản có maSoBN khớp với id của bệnh nhân
        UPDATE tk
        SET tk.hoTen = i.HOTEN
        FROM TaiKhoan tk
        INNER JOIN inserted i ON tk.maSoNV= i.id
    END TRY
    BEGIN CATCH
        -- Xử lý lỗi nếu cần thiết
        PRINT 'Error occurred in trg_NhanVien_Update_TaiKhoan';
		ROLLBACK TRANSACTION;
    END CATCH;

	 -- Bật lại trigger trg_TaiKhoan_Update_NhanVien
    ENABLE TRIGGER trg_TaiKhoan_Update_NhanVien ON TaiKhoan;
END;
go

GO
select * from nhanvien
select * from taikhoan
select * from THUOC



/*****************Lost update************************/

create PROCEDURE sp_ThemThuocChiTietDonThuocV1
    @MaThuoc INT,
    @SoLuongCanLay INT,
    @MaToa INT
AS
BEGIN
    SET NOCOUNT ON;
 
    DECLARE @SoLuongTon INT;
 
   
    SELECT @SoLuongTon = SOLUONGTON FROM THUOC WHERE id = @MaThuoc;
PRINT N'Số lượng tồn hiện tại: ' + CAST(@SoLuongTon AS NVARCHAR(20));
   
    WAITFOR DELAY '00:00:05';
 
    IF @SoLuongTon >= @SoLuongCanLay
    BEGIN
        UPDATE THUOC
        SET SOLUONGTON = @SoLuongTon - @SoLuongCanLay
        WHERE id = @MaThuoc;
 
DECLARE @SoLuong INT;
SELECT @SoLuong = SOLUONGTON FROM THUOC WHERE id = @MaThuoc;

SET @SoLuongTon = @SoLuong;
PRINT N'Số lượng tồn sau khi cập nhật: ' + CAST(@SoLuong AS NVARCHAR(20));
 
            IF NOT EXISTS (SELECT 1 FROM CTDONTHUOC WHERE MATHUOC = @MaThuoc AND MATOA = @MaToa)
            BEGIN
                INSERT INTO CTDONTHUOC (MATHUOC, MATOA, SOLUONG)
                VALUES (@MaThuoc, @MaToa, @SoLuongCanLay);
            END
            ELSE
            BEGIN
                               UPDATE CTDONTHUOC
                SET SOLUONG = SOLUONG + @SoLuongCanLay
                WHERE MATHUOC = @MaThuoc AND MATOA = @MaToa;
            END
 
    END
    ELSE
    BEGIN
               PRINT N'Số lượng tồn không đủ để thêm vào đơn thuốc.';
    END
END;

-----------------------------------------------------------------------------------------

create PROCEDURE sp_ThemThuocChiTietDonThuocV2 
    @MaThuoc INT,
    @SoLuongCanLay INT,
    @MaToa INT
AS
BEGIN
    SET NOCOUNT ON;
 
    DECLARE @SoLuongTon INT;
 
   
    SELECT @SoLuongTon = SOLUONGTON FROM THUOC WHERE id = @MaThuoc;
PRINT N'Số lượng tồn hiện tại: ' + CAST(@SoLuongTon AS NVARCHAR(20));
   
    WAITFOR DELAY '00:00:12';
 
    IF @SoLuongTon >= @SoLuongCanLay
    BEGIN
        UPDATE THUOC
        SET SOLUONGTON = @SoLuongTon - @SoLuongCanLay
        WHERE id = @MaThuoc;
 
DECLARE @SoLuong INT;
SELECT @SoLuong = SOLUONGTON FROM THUOC WHERE id = @MaThuoc;

SET @SoLuongTon = @SoLuong;
PRINT N'Số lượng tồn sau khi cập nhật: ' + CAST(@SoLuong AS NVARCHAR(20));
 
            IF NOT EXISTS (SELECT 1 FROM CTDONTHUOC WHERE MATHUOC = @MaThuoc AND MATOA = @MaToa)
            BEGIN
                INSERT INTO CTDONTHUOC (MATHUOC, MATOA, SOLUONG)
                VALUES (@MaThuoc, @MaToa, @SoLuongCanLay);
            END
            ELSE
            BEGIN
                               UPDATE CTDONTHUOC
                SET SOLUONG = SOLUONG + @SoLuongCanLay
                WHERE MATHUOC = @MaThuoc AND MATOA = @MaToa;
            END
 
    END
    ELSE
    BEGIN
               PRINT N'Số lượng tồn không đủ để thêm vào đơn thuốc.';
    END
END;

----------------------------------------------------

/****************dirty read*******************/
CREATE PROCEDURE sp_CapNhatGiaThuoc
    @TENTHUOC NVARCHAR(50),
    @NUOCSX NVARCHAR(50),
    @DONGIA MONEY
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRANSACTION CapNhatGiaThuocTransaction;

    BEGIN TRY
        IF NOT EXISTS (SELECT 1 FROM THUOC WHERE TENTHUOC = @TENTHUOC AND NUOCSX = @NUOCSX)
        BEGIN
            RAISERROR('Loại thuốc không tồn tại.', 16, 1);
            RETURN;
        END;

        UPDATE THUOC
        SET DONGIA = @DONGIA
        WHERE TENTHUOC = @TENTHUOC AND NUOCSX = @NUOCSX;

        COMMIT TRANSACTION CapNhatGiaThuocTransaction;
        PRINT N'Cập nhật giá thuốc thành công';
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION CapNhatGiaThuocTransaction;
        PRINT N'Cập nhật giá thuốc thất bại. ' + ERROR_MESSAGE();
    END CATCH;
END;




-----------------NON----------------
CREATE PROCEDURE ThemCTDonThuoc
    @MATHUOC INT,
    @MATOA INT,
    @SOLUONG INT
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRANSACTION;

    BEGIN TRY
        IF NOT EXISTS (SELECT 1 FROM THUOC WHERE id = @MATHUOC)
        BEGIN
            RAISERROR('Mã thuốc không tồn tại trong bảng THUOC.', 16, 1);
            RETURN;
        END;
        IF NOT EXISTS (SELECT 1 FROM TOATHUOC WHERE id = @MATOA)
        BEGIN
            RAISERROR('Mã toa thuốc không tồn tại trong bảng TOATHUOC.', 16, 1);
            RETURN;
        END;
	IF EXISTS (SELECT 1 FROM CTDONTHUOC WHERE MATHUOC = @MATHUOC AND MATOA = @MATOA AND SOLUONG = @SOLUONG)
        BEGIN
            RAISERROR ('Chi tiết đơn thuốc đã tồn tại.', 16, 1);
            RETURN;
        END;
        -- Chèn dữ liệu vào bảng CTDONTHUOC
        INSERT INTO CTDONTHUOC (MATHUOC, MATOA, SOLUONG)
        VALUES (@MATHUOC, @MATOA, @SOLUONG);
        -- Commit transaction nếu mọi thứ thành công
        COMMIT TRANSACTION;
        PRINT N'Thêm chi tiết đơn thuốc thành công.';
    END TRY
    BEGIN CATCH
        -- Rollback transaction nếu có lỗi
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION;
        -- In thông báo lỗi chi tiết
        PRINT N'Thêm chi tiết đơn thuốc thất bại. ' + ERROR_MESSAGE();
    END CATCH;
END;


CREATE TRIGGER TRIG_UPDATE_THUOC
ON CTDONTHUOC
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @MATHUOC INT, @SOLUONG INT; 
  
    SELECT @MATHUOC = i.MATHUOC, @SOLUONG = i.SOLUONG
    FROM inserted i;
  
    UPDATE THUOC
    SET SOLUONGTON = SOLUONGTON - @SOLUONG
    WHERE id = @MATHUOC;
   
    IF EXISTS (
        SELECT 1
        FROM THUOC
        WHERE id = @MATHUOC AND SOLUONGTON <= 0
    )
    BEGIN
        UPDATE THUOC
        SET TRANGTHAI = N'Đã hết thuốc'
        WHERE id = @MATHUOC;
    END
END;


	CREATE PROCEDURE sp_CapNhatSLT
	@id int,
	@SOLUONGTON int
AS
BEGIN
	DECLARE @SoLuong INT;

    SELECT @SoLuong = SOLUONGTON FROM THUOC WHERE id = @id;
	PRINT N'Số lượng tồn hiện tại: ' + CAST(@SoLuong AS NVARCHAR(20)); 
	
	--WAITFOR DELAY '00:00:10'

	UPDATE THUOC
	SET SOLUONGTON = SOLUONGTON + @SOLUONGTON
	WHERE id = @id

	SELECT @SoLuong = SOLUONGTON FROM THUOC WHERE id = @id;
	PRINT N'Số lượng tồn hiện tại: ' + CAST(@SoLuong AS NVARCHAR(20)); 
END;

update thuoc set soluongton = 50 where id = 7

select * from thuoc

select * from ctdonthuoc

drop trigger TRIG_UPDATE_THUOC
CREATE TRIGGER TRIG_UPDATE_THUOC
ON CTDONTHUOC
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @MATHUOC INT, @SOLUONG INT; 
  
    SELECT @MATHUOC = i.MATHUOC, @SOLUONG = i.SOLUONG
    FROM inserted i;
  
    UPDATE THUOC
    SET SOLUONGTON = SOLUONGTON - @SOLUONG
    WHERE id = @MATHUOC;
   
    IF EXISTS (
        SELECT 1
        FROM THUOC
        WHERE id = @MATHUOC AND SOLUONGTON <= 0
    )
    BEGIN
        UPDATE THUOC
        SET trangthai = N'Đã hết thuốc'
        WHERE id = @MATHUOC;
    END
END;

select * from ctdonthuoc