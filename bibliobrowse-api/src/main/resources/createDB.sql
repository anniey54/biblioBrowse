-- If bibliobrowse_db database exist, delete it and then recreate database
DROP DATABASE IF EXISTS bibliobrowse_db;
CREATE DATABASE bibliobrowse_db;

\c bibliobrowse_db

-- Custom data types
CREATE TYPE genre AS ENUM ('Romance', 'Fantasy', 'Science fiction', 'Action', 'Adventure', 'Mystery', 'Horror', 'Comedy', 
'Drama', 'Historical', 'Urban', 'Thriller', 'Philosophical', 'Religious', 'Crime', 'Poetry', 'Short stories', 'Dystopia', 
'Contemporary', 'LGBT', 'Slice of life', 'Supernatural', 'Sport', 'Erotica', 'Coming of age', 'Folklore', 'Detective', 'Graphic novel',
'Political', 'Cyberpunk', 'Alien', 'Time travel', 'Military', 'Superhero', 'War', 'Nonfiction', 'Music', 'Academy', 'Business', 
'Cooking', 'Psychological', 'Apocalypse', 'Economics', 'Travel', 'Magic', 'Female protagonist', 'Male protagonist', 'Classic', 'Mental health');
CREATE TYPE collection_status AS ENUM ('Public', 'Private');
CREATE TYPE book_status AS ENUM ('Want to read', 'Reading', 'Finished');
CREATE TYPE age_range AS ENUM ('Middle Grade', 'Young Adult', 'Adult');

-- Create tables
CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  salt CHAR(16) NOT NULL,
  password_hash TEXT NOT NULL,
  profile_image BYTEA DEFAULT NULL,
  is_admin BOOLEAN DEFAULT FALSE
);

CREATE TABLE authors (
  author_id SERIAL PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  profile_image BYTEA DEFAULT NULL,
  about TEXT DEFAULT '', 
  date_of_birth CHAR(18) DEFAULT '',
  website VARCHAR(255) DEFAULT '',
  social_x VARCHAR(255) DEFAULT '',
  instagram VARCHAR(255) DEFAULT ''
);

CREATE TABLE books (
  book_id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  image_cover BYTEA DEFAULT NULL,
  author_id INTEGER NOT NULL,
  genres genre ARRAY DEFAULT '{}',
  summary TEXT DEFAULT '',
  audience age_range DEFAULT 'Young Adult',
  language VARCHAR(20) NOT NULL,
  number_page INTEGER DEFAULT 0,
  publisher VARCHAR(255) DEFAULT '',
  ISBN VARCHAR(255) DEFAULT '',
  series VARCHAR(255) DEFAULT '',
  volume INTEGER DEFAULT NULL,
  CONSTRAINT fk_book_author FOREIGN KEY (author_id) REFERENCES authors(author_id) ON DELETE CASCADE
);

CREATE TABLE user_book_status (
  user_id INTEGER NOT NULL,
  book_id INTEGER NOT NULL,
  status book_status DEFAULT 'Want to read',
  PRIMARY KEY (user_id, book_id),
  CONSTRAINT fk_book_status_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
  CONSTRAINT fk_book_status_book FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
);

CREATE TABLE reviews (
  review_id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL,
  book_id INTEGER NOT NULL,
  rating INTEGER DEFAULT 1,
  title VARCHAR(255) DEFAULT '',
  comment TEXT DEFAULT '',
  number_of_likes INTEGER DEFAULT 0,
  created_at DATE DEFAULT CURRENT_DATE,
  CONSTRAINT rating_range CHECK (rating >= 1 AND rating <= 5),
  CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
  CONSTRAINT fk_review_book FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
);

CREATE TABLE collections (
  collection_id SERIAL PRIMARY KEY,
  creator INTEGER NOT NULL,
  status collection_status DEFAULT 'Private',
  title VARCHAR(255) NOT NULL,
  summary TEXT DEFAULT '',
  number_of_likes INTEGER DEFAULT 0,
  genres genre ARRAY DEFAULT '{}',
  created_at DATE DEFAULT CURRENT_DATE,
  last_updated_at DATE DEFAULT CURRENT_DATE,
  CONSTRAINT fk_collection_creator FOREIGN KEY (creator) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE collection_books (
  collection_id INTEGER NOT NULL,
  book_id INTEGER NOT NULL,
  PRIMARY KEY (collection_id, book_id),
  CONSTRAINT fk_collection_id FOREIGN KEY (collection_id) REFERENCES collections(collection_id) ON DELETE CASCADE,
  CONSTRAINT fk_collection_book FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
);

CREATE TABLE user_favourite_books (
  user_id INTEGER NOT NULL,
  book_id INTEGER NOT NULL,
  PRIMARY KEY (user_id, book_id),
  CONSTRAINT fk_favourite_book_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
  CONSTRAINT fk_favourite_book_id FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
);

CREATE TABLE user_favourite_collections (
  user_id INTEGER NOT NULL,
  collection_id INTEGER NOT NULL,
  PRIMARY KEY (user_id, collection_id),
  CONSTRAINT fk_favourite_collection_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
  CONSTRAINT fk_favourite_collection_id FOREIGN KEY (collection_id) REFERENCES collections(collection_id) ON DELETE CASCADE
);

-- Populate tables
INSERT INTO users (username, email, salt, password_hash) VALUES
  ('user_one', 'user.one@email.com', 'thisissalt', 'password123'), 
  ('MarySue', 'mary.sue@email.com', 'thisissalt', 'password123123'), 
  ('John', 'john@email.com', 'thisissalt', 'larrygary');

INSERT INTO authors (full_name, about, date_of_birth, website, social_x, instagram) VALUES
  ('Suzanne Collins', 'Suzanne Collins is an American author acclaimed for her significant contributions to young adult literature, most notably the globally successful Hunger Games series. Born in Hartford, Connecticut, in 1962, Collins''s early life was profoundly shaped by her father''s military career and his experiences in the Vietnam War. This background instilled in her a deep understanding of conflict and survival, themes that are central to her most famous works. Before becoming a celebrated novelist, Collins honed her storytelling skills as a television writer for children''s shows, including Clarissa Explains It All, which prepared her to craft narratives that resonate with young audiences.', 
    'August 11, 1962', 'http://suzannecollinsbooks.com/', '', ''), 
  ('singNsong', 'singNsong (also known as SingShong or sing-syong) is the married couple pseudonym for the authors of the popular webtoons Omniscient Reader''s Viewpoint and The World After the Fall. Sing is the husband and Shong is the wife. They are known for their meta-fiction, where they break the fourth wall and incorporate themes of readers becoming part of the story.', 
    '', '', '', ''), 
  ('Stephen Chbosky', 'Stephen Chbosky is a writer, director, and producer known for his young adult novel The Perks of Being a Wallflower, which he also adapted into a film. He has also written and directed films like Wonder, written screenplays for Rent and Beauty and the Beast, and authored the horror novel Imaginary Friend. Chbosky''s work often explores themes relevant to young people and incorporates his experiences growing up in Pittsburgh, Pennsylvania.', 
    'January 25, 1970', '', 'https://x.com/StephenChbosky', 'https://www.instagram.com/chboskys/?hl=en');

INSERT INTO books (title, author_id, genres, summary, audience, language, number_page, publisher, ISBN, series, volume) VALUES
  ('Omniscient Reader''s Viewpoint, Vol. 1', 2,
    ARRAY[('Action')::genre, ('Apocalypse')::genre, ('Fantasy')::genre, ('Urban')::genre, ('Science fiction')::genre, ('Horror')::genre, ('Psychological')::genre, ('Dystopia')::genre, ('Mystery')::genre, ('Male protagonist')::genre], 
    'IF YOU ARE READING THIS, YOU WILL SURVIVE. Kill each other within the time limit or die. It''s just another evening commute on the train, until the passengers are given an order they can''t disobey. Utter chaos ensues, but ordinary office worker Dokja Kim only feels an unsettling calm. He knows exactly how this will play out! The subway car, the passengers'' reactions, even the bizarre creature that suddenly appears to oversee this sadistic scenario...everything is straight out of his favorite story, an online novel so obscure he is its sole reader. And as the only one who knows where the plot is headed, Dokja must use his knowledge to survive the oncoming apocalypse!',
    ('Young Adult')::age_range, 'English', 260, 'Ize Press', '9798400903526', 'Omniscient Reader''s Viewpoint', 1), 
  ('The Hunger Games', 1,
    ARRAY[('Action')::genre, ('Adventure')::genre, ('Fantasy')::genre, ('Thriller')::genre, ('Science fiction')::genre, ('Horror')::genre, ('Psychological')::genre, ('Dystopia')::genre, ('Female protagonist')::genre], 
    'Winning means fame and fortune. Losing means certain death. The Hunger Games have begun. . . .
    In the ruins of a place once known as North America lies the nation of Panem, a shining Capitol surrounded by twelve outlying districts. The Capitol is harsh and cruel and keeps the districts in line by forcing them all to send one boy and one girl between the ages of twelve and eighteen to participate in the annual Hunger Games, a fight to the death on live TV.
    Sixteen-year-old Katniss Everdeen regards it as a death sentence when she steps forward to take her sister''s place in the Games. But Katniss has been close to dead before-and survival, for her, is second nature. Without really meaning to, she becomes a contender. But if she is to win, she will have to start making choices that weigh survival against humanity and life against love.', 
    ('Young Adult')::age_range, 'English', 374, 'Scholastic Press', '9780439023481 (ISBN10: 0439023483)', 'The Hunger Games', 1), 
  ('The Perks of Being a Wallflower', 3,
    ARRAY[('Classic')::genre, ('Coming of age')::genre, ('Romance')::genre, ('Mental health')::genre, ('LGBT')::genre, ('Slice of life')::genre, ('Psychological')::genre, ('Male protagonist')::genre], 
    'standing on the fringes of life...
    offers a unique perspective. But there comes a time to see
    what it looks like from the dance floor.
    This haunting novel about the dilemma of passivity vs. passion marks the stunning debut of a provocative new voice in contemporary fiction: The Perks of Being A WALLFLOWER
    This is the story of what it''s like to grow up in high school. More intimate than a diary, Charlie''s letters are singular and unique, hilarious and devastating. We may not know where he lives. We may not know to whom he is writing. All we know is the world he shares. Caught between trying to live his life and trying to run from it puts him on a strange course through uncharted territory. The world of first dates and mixed tapes, family dramas and new friends. The world of sex, drugs, and The Rocky Horror Picture Show, when all one requires is that the perfect song on that perfect drive to feel infinite.
    Through Charlie, Stephen Chbosky has created a deeply affecting coming-of-age story, a powerful novel that will spirit you back to those wild and poignant roller coaster days known as growing up.',
    ('Young Adult')::age_range, 'English', 213, 'MTV Books/Pocket Books', '9781451696196', '', NULL);

INSERT INTO user_book_status (user_id, book_id, status) VALUES
  (1, 3, 'Want to read'),
  (2, 2, 'Reading'),
  (3, 3, 'Finished');

INSERT INTO reviews (user_id, book_id, rating, title, comment, number_of_likes) VALUES
  (1, 2, 5, 'Wow, this book is the best dystopian fiction I have read so far!', 
    'The Hunger Games is an absolutely captivating and thought-provoking read! Suzanne Collins masterfully crafts a dystopian world that pulls you in from the very first page. Katniss Everdeen is an unforgettable protagonist -- her strength, resourcefulness, and unwavering love for her family make her incredibly compelling. The tension is palpable throughout, keeping you on the edge of your seat, and the underlying themes of survival, sacrifice, and the corrupting influence of power are explored with incredible depth. A truly brilliant and unputdownable start to an iconic series!',
    1280),
  (2, 1, 5, 'A Masterpiece of a Survival Story Begins!',
    'This novel is an absolute thrill ride! The premise of a man who knows the future of the world-ending scenario the world has become is incredibly engaging. You''ll be on the edge of your seat as he navigates brutal challenges and builds a formidable team, and the depth of the characters is truly impressive. A must-read for anyone who loves action-packed, thought-provoking, and emotionally resonant stories.',
    203),
  (3, 2, 3, 'An Engaging Concept, But Lacking Some Depth',
    '"The Hunger Games" presents an incredibly intriguing premise, and the high-stakes survival aspect definitely keeps you turning pages. Katniss is a strong protagonist, and her journey through the arena is undeniably exciting. However, while the action is engaging, I found some of the world-building to be a bit thin, and certain character developments felt rushed. It''s a solid, quick read that delivers on its thrilling concept, but it didn''t quite resonate with me on a deeper emotional or intellectual level as much as I''d hoped. A good read for a dystopian action fix, but not a standout for me personally.',
    1),
  (3, 1, 4,'','', 0);

INSERT INTO collections (creator, status, title, summary, number_of_likes, genres) VALUES
  (1, ('Public')::collection_status, 'My Personal Book Collection: A Handpicked Collection of Beloved Reads', 
    'Welcome to a deeply personal exploration of my favourite literary works. 
    My taste tends to gravitate towards thought-provoking literary fiction, insightful historical accounts, and thrilling mysteries that keep me guessing. You''ll also find a fair share of character-driven sagas and well-crafted science fiction that explore complex ideas. 
    Each book here holds a special significance, representing moments of revelation, joy, solace, or pure escapism. I often revisit these cherished volumes, finding new insights with every read, and they are the first ones I recommend when asked for a truly great book. Dive in and discover the stories that have shaped my journey as a reader, and perhaps find a new favourite to add to your own collection.',
    5, ARRAY[('Adventure')::genre, ('Romance')::genre, ('Action')::genre, ('Dystopia')::genre, ('Science fiction')::genre]),
  (2, ('Private')::collection_status, 'Dystopian fiction novels', '', 0, ARRAY[('Action')::genre, ('Dystopia')::genre, ('Science fiction')::genre]);

INSERT INTO collection_books (collection_id, book_id) VALUES
  (1, 1), (1, 2), (1, 3),
  (2, 1), (2, 2);

INSERT INTO user_favourite_books (user_id, book_id) VALUES
  (2, 1), (3, 2);

INSERT INTO user_favourite_collections (user_id, collection_id) VALUES
  (1, 2), (2, 1), (3, 1);