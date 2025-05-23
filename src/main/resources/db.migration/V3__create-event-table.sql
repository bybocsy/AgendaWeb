CREATE TABLE event(
    id UUID get random uuid() PRIMARY KEY,
    place VARCHAR(100) NOT NULL,
    date_time TIMESTAMP NOT NULL,
    isFixed BOOLEAN NOT NULL,
    description VARCHAR(400),
    responsible VARCHAR(100) NOT NULL
);