-- Insertar Gpses
INSERT INTO gpses (created_date, deleted, latitude,longitude)
VALUES
    (NOW(), 0, -12.10405120471276, -76.96290648634394),
    (NOW(), 0, -12.087444088128292, -77.04993581500588),
    (NOW(), 0, -12.197822542108275, -77.00760328405869),
    (NOW(), 0, -12.085245729713211, -76.97724872691896),
    (NOW(), 0, -12.121777078574345, -77.03045334694788),
    (NOW(), 0, -12.129122713355832, -77.0016320980801);

UPDATE bicycles SET gps_id = 1 WHERE id = 1;
UPDATE bicycles SET gps_id = 2 WHERE id = 2;
UPDATE bicycles SET gps_id = 3 WHERE id = 3;

UPDATE bicycles SET gps_id = 4 WHERE id = 4;
UPDATE bicycles SET gps_id = 5 WHERE id = 5;
UPDATE bicycles SET gps_id = 6 WHERE id = 6;
