CREATE TABLE IF NOT EXISTS cards (
    id INT NOT NULL PRIMARY KEY,
    card_front NVARCHAR(255) NOT NULL,
    card_back NVARCHAR(255),
    notes NVARCHAR(500),
    time_due TIMESTAMP NOT NULL,
    review_interval DOUBLE PRECISION NOT NULL,
    correct_in_a_row INT NOT NULL,
    ease FLOAT NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;