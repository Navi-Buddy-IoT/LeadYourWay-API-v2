-- Insertar Temperaturas
INSERT INTO temperatures (created_date, deleted, temperature)
VALUES
    (NOW(), 0, 20.0),  
    (NOW(), 0, 22.5),  
    (NOW(), 0, 18.0), 
    (NOW(), 0, 25.0), 
    (NOW(), 0, 21.0), 
    (NOW(), 0, 19.5); 

UPDATE bicycles SET temperature_id = 1 WHERE id = 1;
UPDATE bicycles SET temperature_id = 2 WHERE id = 2;
UPDATE bicycles SET temperature_id = 3 WHERE id = 3;

UPDATE bicycles SET temperature_id = 4 WHERE id = 4;
UPDATE bicycles SET temperature_id = 5 WHERE id = 5;
UPDATE bicycles SET temperature_id = 6 WHERE id = 6;
