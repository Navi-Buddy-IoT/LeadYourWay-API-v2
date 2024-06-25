CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    id VARCHAR(255) NOT NULL,
    created_date DATETIME(6) NOT NULL,
    deleted BIT,
    email VARCHAR(255),
    google_name VARCHAR(255),
    google_photo_url VARCHAR(255),
    role_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE cards (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_date DATETIME(6) NOT NULL,
    deleted BIT,
    updated_date DATETIME(6),
    card_amount FLOAT NOT NULL,
    card_cvv VARCHAR(255) NOT NULL,
    card_expiration_date DATE NOT NULL,
    card_holder VARCHAR(255) NOT NULL,
    card_main BIT NOT NULL,
    card_number VARCHAR(255) NOT NULL,
    card_type VARCHAR(255) NOT NULL,
    user_id VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE temperatures (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_date DATETIME(6) NOT NULL,
    deleted BIT,
    updated_date DATETIME(6),
    temperature float(53) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE velocities (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_date DATETIME(6) NOT NULL,
    deleted BIT,
    updated_date DATETIME(6),
    velocity float(53) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE gpses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_date DATETIME(6) NOT NULL,
    deleted BIT,
    updated_date DATETIME(6),
    latitude float(53) NOT NULL,
    longitude float(53) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE bicycles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_date DATETIME(6) NOT NULL,
    deleted BIT,
    updated_date DATETIME(6),
    bicycle_description VARCHAR(255) NOT NULL,
    bicycle_model VARCHAR(255),
    bicycle_name VARCHAR(255) NOT NULL,
    bicycle_price FLOAT(53) NOT NULL,
    bicycle_size VARCHAR(255) NOT NULL,
    image_data VARCHAR(255),
    user_id VARCHAR(255),
    temperature_id BIGINT,
    velocity_id BIGINT,
    gps_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE availabilities (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_date DATETIME(6) NOT NULL,
    deleted BIT,
    updated_date DATETIME(6),
    availability_end_date DATE NOT NULL,
    availability_start_date DATE NOT NULL,
    bicycle_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE rents (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_date DATETIME(6) NOT NULL,
    deleted BIT,
    updated_date DATETIME(6),
    rent_end_date DATE NOT NULL,
    rent_price FLOAT(53) NOT NULL,
    rent_start_date DATE NOT NULL,
    bicycle_id BIGINT,
    card_id BIGINT,
    PRIMARY KEY (id)
);
    
ALTER TABLE users
    ADD CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles(id);

ALTER TABLE cards
    ADD CONSTRAINT fk_cards_users FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE bicycles
    ADD CONSTRAINT fk_bicycles_users FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE bicycles
    ADD CONSTRAINT fk_bicycles_temperatures FOREIGN KEY (temperature_id) REFERENCES temperatures(id);

ALTER TABLE bicycles
    ADD CONSTRAINT uk_bicycles_temperature UNIQUE (temperature_id);

ALTER TABLE bicycles
    ADD CONSTRAINT fk_bicycles_velocities FOREIGN KEY (velocity_id) REFERENCES velocities(id);

ALTER TABLE bicycles
    ADD CONSTRAINT uk_bicycles_velocity UNIQUE (velocity_id);

ALTER TABLE bicycles
    ADD CONSTRAINT fk_bicycles_gpses FOREIGN KEY (gps_id) REFERENCES gpses(id);

ALTER TABLE bicycles
    ADD CONSTRAINT uk_bicycles_gps UNIQUE (gps_id);

ALTER TABLE availabilities
    ADD CONSTRAINT fk_availabilities_bicycles FOREIGN KEY (bicycle_id) REFERENCES bicycles(id);

ALTER TABLE rents
    ADD CONSTRAINT fk_rents_bicycles FOREIGN KEY (bicycle_id) REFERENCES bicycles (id);

ALTER TABLE rents
    ADD CONSTRAINT fk_rents_cards FOREIGN KEY (card_id) REFERENCES cards (id);
