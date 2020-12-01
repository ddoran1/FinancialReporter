CREATE TABLE IF NOT EXISTS Expenses(
	primary_key INTEGER PRIMARY KEY,
	name VARCHAR(25) NOT NULL, expense REAL NOT NULL,
	description VARCHAR(25) NOT NULL, 
	recurring BOOLEAN NOT NULL, recurrance_rate VARCHAR(6) 
	datePaid TEXT, dateAcquired TEXT
	);