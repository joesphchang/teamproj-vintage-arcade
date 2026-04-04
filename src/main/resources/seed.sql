INSERT INTO GameCondition (status) VALUES
('Excellent'),
('Good'),
('Fair'),
('Out of Order');

INSERT INTO Manufacturer (name, country, foundedYear) VALUES
('Atari', 'USA', 1972),
('Nintendo', 'Japan', 1889);

INSERT INTO Venue (name, location, openFrom, openTo) VALUES
('IO Arcade Bar', 'Madison, WI', '10:00:00', '22:00:00'),
('Player 2 Arcade', 'Appleton, WI', '11:00:00', '23:00:00');

INSERT INTO Cabinet (gameName, year, pricePerPlay, conditionId, manufacturerId)
VALUES
('Ms. Pac-Man', 1981, 0.50, 1, 1),
('Donkey Kong', 1981, 0.75, 2, 2);

INSERT INTO VenueCabinet (venueId, gameId) VALUES
(1, 1),
(2, 1);

INSERT INTO VenueCabinet (venueId, gameId) VALUES
(1, 2);