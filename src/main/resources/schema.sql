CREATE TABLE address(
    id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    cep VARCHAR(8) NOT NULL,
    street VARCHAR(32) NOT NULL,
    neighborhood VARCHAR(20) NOT NULL,
    city VARCHAR(20) NOT NULL,
    state VARCHAR(2) NOT NULL,
    INDEX (cep)
);