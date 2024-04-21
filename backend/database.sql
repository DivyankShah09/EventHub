CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(10) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_picture_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `event_organizer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `business_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(10) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_picture_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_organizer_id` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_et1_idx` (`event_organizer_id`),
  CONSTRAINT `fk1_et1` FOREIGN KEY (`event_organizer_id`) REFERENCES `event_organizer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `event_id` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_booking1_idx` (`user_id`),
  KEY `fk2_bt2_idx` (`event_id`),
  CONSTRAINT `fk1_bt1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk2_bt2` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `booking_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqewrl4xrv9eiad6eab3aoja65` (`booking_id`),
  CONSTRAINT `FKqewrl4xrv9eiad6eab3aoja65` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
