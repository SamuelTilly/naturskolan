CREATE TABLE Bookings (
    id SERIAL PRIMARY KEY,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    claimed_by INT REFERENCES Users(id),
    CHECK (start_time < end_time) -- Ensures start_time is before end_time
);
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    firstname VARCHAR(50),
    lastname VARCHAR(50)
);