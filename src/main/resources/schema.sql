DROP TABLE IF EXISTS people;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS followers;

-- Feel free to augment or modify these schemas (and the corresponding data) as you see fit!
CREATE TABLE people (
    id IDENTITY AUTO_INCREMENT,
    handle VARCHAR,
    name VARCHAR
);

CREATE TABLE messages (
    id IDENTITY AUTO_INCREMENT,
    person_id NUMBER REFERENCES people (id),
    content VARCHAR
);

CREATE TABLE followers (
    id IDENTITY AUTO_INCREMENT,
    person_id NUMBER REFERENCES people (id),
    follower_person_id NUMBER REFERENCES people (id)
);

-- Don't allow people to follow themselves
ALTER TABLE followers ADD CONSTRAINT distinct_follower CHECK(follower_person_id <> person_id);