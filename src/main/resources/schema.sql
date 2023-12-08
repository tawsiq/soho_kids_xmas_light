DROP TABLE IF EXISTS Ratings;
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

   id BIGINT NOT NULL,
   name VARCHAR(128) NOT NULL,
   comment VARCHAR(128) NULL, -- Should be allowed to be NULL because the user may just want to submit a like.
   liked BOOLEAN, -- The like button will act as a check box, so null values cannot be entered, even though they're allowed.

   FOREIGN KEY (id) REFERENCES Drawings(id)

) ENGINE=InnoDB;