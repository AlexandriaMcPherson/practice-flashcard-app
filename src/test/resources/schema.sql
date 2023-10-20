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
    slide_id INT NOT NULL,
    lesson_id INT NOT NULL,
    `text` NVARCHAR(500),
    image_loc VARCHAR(255),
    audio_loc VARCHAR(255),
    PRIMARY KEY (slide_id),
    CONSTRAINT fk_lesson_id
    FOREIGN KEY (lesson_id) 
    REFERENCES lessons(lesson_id) 
    ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE table IF NOT EXISTS users (
    user_id INT NOT NULL,
    username VARCHAR(64) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
)

CREATE table IF NOT EXISTS preferences (
    user_id INT NOT NULL,
    interface_language VARCHAR(10) DEFAULT "English",
    CONSTRAINT chk_interface_language CHECK (interface_language IN ("English", "Japanese")),
    volume TINYINT NOT NULL DEFAULT 5,
    --CONSTRAINT chk_volume CHECK (volume > 0 AND volume < 10),
    audio_on BOOLEAN NOT NULL DEFAULT TRUE,
    hints_on BOOLEAN NOT NULL DEFAULT TRUE,
    romaji_on BOOLEAN NOT NULL DEFAULT TRUE,
    dark_mode BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_user_id
    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
    ON DELETE CASCADE
)