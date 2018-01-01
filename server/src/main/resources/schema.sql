-- Cool kids store this in Vault and recycle their passwords :)
CREATE USER teuser PASSWORD 'abc123';

CREATE ALIAS IF NOT EXISTS FT_INIT FOR "org.h2.fulltext.FullText.init";
CALL FT_INIT();

DROP TABLE IF EXISTS people;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS followers;

-- Feel free to augment or modify these schemas (and the corresponding data) as you see fit!
CREATE TABLE people (
    id IDENTITY AUTO_INCREMENT,
    handle VARCHAR,
    name VARCHAR
);

GRANT SELECT ON people TO teuser;

CREATE TABLE messages (
    id IDENTITY AUTO_INCREMENT,
    person_id NUMBER REFERENCES people (id),
    content VARCHAR
);

GRANT SELECT ON messages TO teuser;

CALL FT_CREATE_INDEX('PUBLIC', 'MESSAGES', NULL);

CREATE TABLE followers (
    id IDENTITY AUTO_INCREMENT,
    person_id NUMBER REFERENCES people (id),
    follower_person_id NUMBER REFERENCES people (id)
);

GRANT SELECT ON followers TO teuser;
GRANT INSERT ON followers TO teuser;
GRANT DELETE ON followers TO teuser;

-- Don't allow people to follow themselves
ALTER TABLE followers ADD CONSTRAINT distinct_follower CHECK(follower_person_id <> person_id);