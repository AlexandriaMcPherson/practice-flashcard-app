CREATE TABLE IF NOT EXISTS cards (
    id INT NOT NULL PRIMARY KEY,
    lesson_id INT,
    card_front NVARCHAR(255) NOT NULL,
    card_back NVARCHAR(255),
    notes NVARCHAR(500),
    time_due TIMESTAMP,
    review_interval DOUBLE PRECISION NOT NULL,
    correct_in_a_row INT NOT NULL,
    ease FLOAT NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS lessons (
    lesson_id INT NOT NULL PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    `description` NVARCHAR(500)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS slides (
    slide_id NOT NULL PRIMARY KEY,
    lesson_id NOT NULL FOREIGN KEY REFERENCES lessons(lesson_id),
    `text` NVARCHAR(500),
    imageLoc VARCHAR(255),
    audioLoc VARCHAR(255)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;