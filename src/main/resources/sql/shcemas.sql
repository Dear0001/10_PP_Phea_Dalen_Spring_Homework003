CREATE DATABASE event_db;

CREATE TABLE attendees (
        id SERIAL PRIMARY KEY,
        attendees_name VARCHAR(100),
        email VARCHAR(225)
);

CREATE TABLE events (
        id SERIAL PRIMARY KEY,
        event_name VARCHAR(100),
        event_date TIMESTAMP,
        venue_id INT,
        FOREIGN KEY (venue_id) REFERENCES venues(venue_id) ON DELETE CASCADE
);
-- query to create new foreign key constraint
ALTER TABLE events
    DROP CONSTRAINT events_venue_id_fkey,
    ADD CONSTRAINT events_venue_id_fkey FOREIGN KEY (venue_id) REFERENCES venues(venue_id) ON DELETE CASCADE;

CREATE TABLE attendees_events (
        id SERIAL PRIMARY KEY,
        attendee_id INT,
        event_id INT,
        FOREIGN KEY (attendee_id) REFERENCES attendees(id) ON DELETE CASCADE,
        FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
);

CREATE TABLE venues (
        venue_id SERIAL PRIMARY KEY,
        venue_name VARCHAR(100),
        venue_location VARCHAR(225)
);











SELECT * FROM attendees
LIMIT 1 OFFSET 0;


SELECT * FROM events
LIMIT 1
OFFSET 0;


SELECT a.id, a.attendees_name, a.email, e.event_name, e.event_date
FROM attendees a
JOIN attendees_events ae ON a.id = ae.attendee_id
JOIN events e ON ae.event_id = e.id
WHERE ae.event_id= 1;

UPDATE events
SET event_name = 'Khmer News Year',
    event_date = '2024-04-06T03:08:58.075',
    venue_id = 2
WHERE id = 2;

UPDATE attendees_events
SET event_id = 2
WHERE attendee_id IN (1);

UPDATE events
SET event_name = 'Bun Pchum', event_date = '2024-04-07T06:39:38.536Z', venue_id = 2
WHERE id = 4
RETURNING *;

DELETE FROM attendees_events
WHERE event_id = 4;