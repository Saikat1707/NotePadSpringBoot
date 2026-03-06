-- Users
INSERT INTO user(name, email, password) VALUES ('Alice', 'alice@example.com', 'pass123');
INSERT INTO user(name, email, password) VALUES ('Bob', 'bob@example.com', 'pass123');
INSERT INTO user(name, email, password) VALUES ('Charlie', 'charlie@example.com', 'pass123');
INSERT INTO user(name, email, password) VALUES ('David', 'david@example.com', 'pass123');
INSERT INTO user(name, email, password) VALUES ('Eva', 'eva@example.com', 'pass123');

-- Notes for Alice (user_id = 1)
INSERT INTO note(title, desc, user_id) VALUES ('Shopping List', 'Milk, Bread, Eggs', 1);
INSERT INTO note(title, desc, user_id) VALUES ('Workout Plan', 'Pushups, Situps', 1);
INSERT INTO note(title, desc, user_id) VALUES ('Birthday Reminder', 'Buy gift for Bob', 1);

-- Notes for Bob (user_id = 2)
INSERT INTO note(title, desc, user_id) VALUES ('Meeting Notes', 'Project deadline: Friday', 2);
INSERT INTO note(title, desc, user_id) VALUES ('Reading List', 'Clean Code, Effective Java', 2);

-- Notes for Charlie (user_id = 3)
INSERT INTO note(title, desc, user_id) VALUES ('Vacation Plan', 'Book flights and hotels', 3);
INSERT INTO note(title, desc, user_id) VALUES ('Grocery List', 'Tomatoes, Onions, Rice', 3);

-- Notes for David (user_id = 4)
INSERT INTO note(title, desc, user_id) VALUES ('Fitness Tracker', 'Run 5km daily', 4);
INSERT INTO note(title, desc, user_id) VALUES ('Car Service', 'Book service appointment', 4);

-- Notes for Eva (user_id = 5)
INSERT INTO note(title, desc, user_id) VALUES ('Cooking Plan', 'Try new pasta recipe', 5);
INSERT INTO note(title, desc, user_id) VALUES ('Project Ideas', 'Build Spring Boot app with SQLite', 5);
INSERT INTO note(title, desc, user_id) VALUES ('Shopping List', 'Fruits, Vegetables, Milk', 5);