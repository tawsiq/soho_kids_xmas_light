CREATE DATABASE IF NOT EXISTS team7_soho_kids_database;
USE team7_soho_kids_database;

DROP TABLE IF EXISTS Lights;
DROP TABLE IF EXISTS Drawings;


CREATE TABLE IF NOT EXISTS Drawings (

    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    filename VARCHAR(128) NOT NULL,
    filepath VARCHAR(255) NOT NULL,
    mime_type ENUM('image/jpeg', 'image/png', 'image/jpg') NOT NULL,
    submission_year YEAR NOT NULL,
    year_group VARCHAR(50) NOT NULL,
    `name` VARCHAR(128)

) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Lights (

    drawing_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    filename VARCHAR(128) NOT NULL,
    filepath VARCHAR(255) NOT NULL,
    mime_type ENUM('image/jpeg', 'image/png', 'image/jpg') NOT NULL,

    FOREIGN KEY (drawing_id) REFERENCES Drawings(id)

) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Ratings (

    rating_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    submission_id BIGINT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `comment` VARCHAR(10000) NULL, -- Should be allowed to be NULL because the user may just want to submit a like.
--     liked BOOLEAN DEFAULT FALSE, -- The like button will act as a check box, so null values cannot be entered, even though they're allowed.
    `date_time` VARCHAR(64) NOT NULL
    -- COME BACK HERE IF YOU FACE ISSUES. THIS WAS COMMENTED OUT FOR SOME REASON
    -- FOREIGN KEY (submission_id) REFERENCES Drawings(id)

) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS LikeCounts (

   submission_id BIGINT PRIMARY KEY NOT NULL,
   like_count BIGINT NOT NULL
   -- Every time a like is submitted, the entry corresponding to the submission id will be updated.
   -- No duplicates can be entered due to the way implementation will work.

   -- FOREIGN KEY (submission_id) REFERENCES Drawings(id)

) ENGINE=InnoDB;



DROP TABLE IF EXISTS MarketplaceProducts;

CREATE TABLE IF NOT EXISTS MarketplaceProducts (
   product_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
   filename VARCHAR(128) NOT NULL,
   filepath VARCHAR(255) NOT NULL,
   product_name VARCHAR(255) NOT NULL,
   price DECIMAL(10, 2) NOT NULL
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS SponsorInfo (

    sponsor_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    contact_person VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL




) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ContactInfo (

    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    `subject` VARCHAR(255) NOT NULL,
    `message` VARCHAR(10000) NOT NULL



) ENGINE=InnoDB;


