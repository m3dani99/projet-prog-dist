CREATE TABLE clients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    reservation_date DATETIME NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);
CREATE TABLE restaurants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    capacity INT NOT NULL
);

CREATE TABLE availabilities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id INT NOT NULL,
    date DATE NOT NULL,
    slots JSON NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

-- Create Clients Table with Random Data
INSERT INTO clients (name, email) VALUES
('Khalil Maadani', 'khalil.maadani@example.com'),
('Alice Johnson', 'alice.johnson@example.com'),
('Bob Smith', 'bob.smith@example.com'),
('Charlie Brown', 'charlie.brown@example.com'),
('David Lee', 'david.lee@example.com'),
('Emma White', 'emma.white@example.com');

-- Create Restaurants Table with Random Data
INSERT INTO restaurants (name, location, capacity) VALUES
('Le Petit Bistro', 'Paris, France', 50),
('The Great Grill', 'New York, USA', 80),
('Sushi Zen', 'Tokyo, Japan', 40),
('Pasta Palace', 'Rome, Italy', 60),
('Taco Haven', 'Mexico City, Mexico', 70);

-- Create Reservations Table with Random Data
INSERT INTO reservations (client_id, restaurant_id, reservation_date) VALUES
(1, 1, '2025-03-20 19:00:00'),
(2, 2, '2025-03-21 20:30:00'),
(3, 3, '2025-03-22 18:45:00'),
(4, 4, '2025-03-23 21:15:00'),
(5, 5, '2025-03-24 19:30:00');

-- Create Availabilities Table with Random Data
INSERT INTO availabilities (restaurant_id, date, slots) VALUES
(1, '2025-03-20', '[{"time": "12:00", "available": true}, {"time": "19:00", "available": false}]'),
(2, '2025-03-21', '[{"time": "13:00", "available": true}, {"time": "20:30", "available": true}]'),
(3, '2025-03-22', '[{"time": "11:30", "available": true}, {"time": "18:45", "available": false}]'),
(4, '2025-03-23', '[{"time": "12:45", "available": true}, {"time": "21:15", "available": false}]'),
(5, '2025-03-24', '[{"time": "14:00", "available": true}, {"time": "19:30", "available": true}]');
