CREATE TABLE IF NOT EXISTS Participants (

    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    name VARCHAR(128) NOT NULL ,
    year_group VARCHAR(128) NOT NULL

) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Submissions (

    id BIGINT NOT NULL,
    submission_year YEAR NOT NULL,
    drawing LONGBLOB NOT NULL,
    finished_light LONGBLOB NULL, -- Allows null inputs. Be careful with data retrieval.

    FOREIGN KEY (id) REFERENCES Participants(id),
    PRIMARY KEY (id, submission_year)

) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS Ratings (

   id BIGINT NOT NULL,
   name VARCHAR(128) NOT NULL,
   comment VARCHAR(128) NULL, -- Should be allowed to be NULL because the user may just want to submit a like.
   liked BOOLEAN, -- The like button will act as a check box, so null values cannot be entered, even though they're allowed.

   FOREIGN KEY (id) REFERENCES Participants(id)

) ENGINE=InnoDB;