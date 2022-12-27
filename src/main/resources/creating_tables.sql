CREATE DATABASE weather_sensor;

CREATE TABLE sensor (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name varchar(30) NOT NULL UNIQUE
);

CREATE TABLE measurement (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             value double NOT NULL,
                             raining boolean NOT NULL,
                             sensor varchar(30),
                             created_at DATETIME not null ,
                             FOREIGN KEY (sensor) REFERENCES sensor (name)
);


