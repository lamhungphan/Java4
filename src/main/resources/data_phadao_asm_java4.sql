-- Xóa database nếu đã tồn tại và tạo database mới
DROP DATABASE IF EXISTS java4_asm;
CREATE DATABASE java4_asm;
USE java4_asm;

-- Tạo bảng `user`
CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  isAdmin TINYINT(1) NOT NULL DEFAULT 0,
  isActive TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
);

-- Tạo bảng `video`
CREATE TABLE video (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  href VARCHAR(255) NOT NULL,
  poster VARCHAR(255) NULL,
  views INT NOT NULL DEFAULT 0,
  shares INT NOT NULL DEFAULT 0,
  description VARCHAR(255) NOT NULL,
  isActive TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
);

-- Tạo bảng `history`
CREATE TABLE history (
  id INT NOT NULL AUTO_INCREMENT,
  userId INT,
  videoId INT,
  viewedDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  isLiked TINYINT(1) NOT NULL DEFAULT 0,
  likedDate DATETIME NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (userId) REFERENCES user(id),
  FOREIGN KEY (videoId) REFERENCES video(id)
);

-- Thêm dữ liệu mẫu vào bảng `user`
INSERT INTO user (username, password, email, isAdmin)
VALUES
  ('lamhungphan', '123', 'hungpl@gmail.com', 1),
  ('phanlamhung', '12345', 'hungpl@fpt.edu.vn', 0);

-- Thêm dữ liệu mẫu vào bảng `video`
INSERT INTO video (title, href, description)
VALUES
  ('Shape of You', 'JGwWNGJdvx8', 'Ed Sheeran'),
  ('Just the Way You Are', 'KYgrXWf2zUc', 'Bruno Mars'),
  ('See You Again', 'RgKAFK5djSk', 'Wiz Khalifa ft. Charlie Puth');

-- Thêm dữ liệu mẫu vào bảng `history`
INSERT INTO history (userId, videoId, isLiked, likedDate) VALUES
  (2, 2, 1, CURRENT_TIMESTAMP),
  (2, 3, 0, NULL);
