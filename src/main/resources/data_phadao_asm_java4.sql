-- MY SQL
DROP DATABASE IF EXISTS java4_asm;
CREATE DATABASE java4_asm;
USE java4_asm;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
                      id INT NOT NULL AUTO_INCREMENT,
                      username VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      isAdmin TINYINT(1) NOT NULL DEFAULT 0,
                      isActive TINYINT(1) NOT NULL DEFAULT 1,
                      PRIMARY KEY (id)
);

DROP TABLE IF EXISTS video;
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

DROP TABLE IF EXISTS history;
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

DROP TABLE IF EXISTS logs;
CREATE TABLE logs (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      url VARCHAR(255) NOT NULL,
                      time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      username VARCHAR(255)
);

INSERT INTO user (username, password, email, isAdmin)
VALUES
    ('admin', '123', 'hungnplps39152@fpt.edu.vn', 1),
    ('user', '1', 'hungpl@gmail.com', 0),
    ('user1', '1', 'hungpl@example.com', 0),
    ('user2', '1', 'hungpl@gmail.com', 0),
    ('user3', '1', 'hungpl@gmail.com', 0);

INSERT INTO video (title, href, poster, views, shares, description, isActive)
VALUES
    ('Shape of You', 'JGwWNGJdvx8', 'https://img.youtube.com/vi/JGwWNGJdvx8/sddefault.jpg', 0, 0, 'Ed Sheeran', 1),
    ('Just the Way You Are', 'KYgrXWf2zUc', 'https://img.youtube.com/vi/KYgrXWf2zUc/sddefault.jpg', 0, 0, 'Bruno Mars', 1),
    ('See You Again', 'RgKAFK5djSk', 'https://img.youtube.com/vi/RgKAFK5djSk/sddefault.jpg', 0, 0, 'Wiz Khalifa ft. Charlie Puth', 1),
    ('Blinding Lights', '4NRXx6U8ABQ', 'https://img.youtube.com/vi/4NRXx6U8ABQ/sddefault.jpg', 0, 0, 'The Weeknd', 1),
    ('Levitating', 'TUVcZfQe-Kw', 'https://img.youtube.com/vi/TUVcZfQe-Kw/sddefault.jpg', 0, 0, 'Dua Lipa ft. DaBaby', 1),
    ('Dance Monkey', 'q0hyYWKXF0Q', 'https://img.youtube.com/vi/q0hyYWKXF0Q/sddefault.jpg', 0, 0, 'Tones and I', 1),
    ('Old Town Road', 'r7qovpFAGrQ', 'https://img.youtube.com/vi/r7qovpFAGrQ/sddefault.jpg', 0, 0, 'Lil Nas X ft. Billy Ray Cyrus', 1),
    ('Peaches', 'tQ0yjYUFKAE', 'https://img.youtube.com/vi/tQ0yjYUFKAE/sddefault.jpg', 0, 0, 'Justin Bieber ft. Daniel Caesar, Giveon', 1),
    ('Drivers License', 'ZmDBbnmKpqQ', 'https://img.youtube.com/vi/ZmDBbnmKpqQ/sddefault.jpg', 0, 0, 'Olivia Rodrigo', 1),
    ('Bad Guy', 'DyDfgMOUjCI', 'https://img.youtube.com/vi/DyDfgMOUjCI/sddefault.jpg', 0, 0, 'Billie Eilish', 1),
    ('Shallow', 'bo_efYhYU2A', 'https://img.youtube.com/vi/bo_efYhYU2A/sddefault.jpg', 0, 0, 'Lady Gaga & Bradley Cooper', 1),
    ('Senorita', 'Pkh8UtuejGw', 'https://img.youtube.com/vi/Pkh8UtuejGw/sddefault.jpg', 0, 0, 'Shawn Mendes & Camila Cabello', 1),
    ('Havana', 'BQ0mxQXmLsk', 'https://img.youtube.com/vi/BQ0mxQXmLsk/sddefault.jpg', 0, 0, 'Camila Cabello ft. Young Thug', 1),
    ('God\'s Plan', 'xpVfcZ0ZcFM', 'https://img.youtube.com/vi/xpVfcZ0ZcFM/sddefault.jpg', 0, 0, 'Drake', 1),
    ('Shake It Off', 'nfWlot6h_JM', 'https://img.youtube.com/vi/nfWlot6h_JM/sddefault.jpg', 0, 0, 'Taylor Swift', 1),
    ('Hello', 'YQHsXMglC9A', 'https://img.youtube.com/vi/YQHsXMglC9A/sddefault.jpg', 0, 0, 'Adele', 1),
    ('Faded', '60ItHLz5WEA', 'https://img.youtube.com/vi/60ItHLz5WEA/sddefault.jpg', 0, 0, 'Alan Walker', 1),
    ('Thinking Out Loud', 'lp-EO5I60KA', 'https://img.youtube.com/vi/lp-EO5I60KA/sddefault.jpg', 0, 0, 'Ed Sheeran', 1),
    ('Despacito', 'kJQP7kiw5Fk', 'https://img.youtube.com/vi/kJQP7kiw5Fk/sddefault.jpg', 0, 0, 'Luis Fonsi ft. Daddy Yankee', 1),
    ('Gangnam Style', '9bZkp7q19f0', 'https://img.youtube.com/vi/9bZkp7q19f0/sddefault.jpg', 0, 0, 'PSY', 1);

INSERT INTO history (userId, videoId, isLiked, likedDate) VALUES
	(2, 2, 1, CURRENT_TIMESTAMP),
	(2, 3, 0, NULL),
	(1, 1, 1, '2024-11-01 12:00:00'), -- admin thích video "Shape of You"
	(2, 3, 1, '2024-11-02 14:30:00'), -- user thích video "See You Again"
	(3, 5, 0, NULL),                  -- user1 xem video "Levitating" nhưng không thích
	(4, 7, 1, '2024-11-03 16:45:00'), -- user2 thích video "Old Town Road"
	(5, 9, 0, NULL),                  -- user3 xem video "Drivers License" nhưng không thích
	(2, 6, 1, '2024-11-04 10:15:00'), -- user thích video "Dance Monkey"
	(1, 8, 1, '2024-11-05 18:00:00'), -- admin thích video "Peaches"
	(4, 10, 0, NULL),                 -- user2 xem video "Bad Guy" nhưng không thích
	(3, 11, 1, '2024-11-06 19:30:00'), -- user1 thích video "Shallow"
	(5, 12, 0, NULL),                 -- user3 xem video "Senorita" nhưng không thích
	(1, 14, 1, '2024-11-07 20:00:00'), -- admin thích video "Shake It Off"
	(2, 15, 1, '2024-11-08 21:15:00'), -- user thích video "Hello"
	(3, 16, 1, '2024-11-09 22:45:00'), -- user1 thích video "Faded"
	(4, 17, 1, '2024-11-10 23:30:00'), -- user2 thích video "Thinking Out Loud"
	(5, 18, 1, '2024-11-11 11:00:00'), -- user3 thích video "Despacito"
	(2, 19, 0, NULL),                  -- user xem video "Gangnam Style" nhưng không thích
	(1, 13, 1, '2024-11-12 13:15:00'); -- admin thích video "God's Plan"

-- Store Procedure
DELIMITER $$

CREATE PROCEDURE sp_selectUsersLikedVideoByVideoHref(IN videoHref VARCHAR(50))
BEGIN
    SELECT
        u.username,
     u.email
         FROM
        video v
        LEFT JOIN history h ON v.id = h.videoId
        LEFT JOIN `user` u ON u.id = h.userId
    WHERE
        v.href = videoHref
        AND u.isActive = 1
        AND v.isActive = 1
        AND h.isLiked = 1;
END$$

DELIMITER ;
