DROP TABLE IF EXISTS people;

CREATE TABLE people  (
    person_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);
