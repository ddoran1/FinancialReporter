/*
Add recurrence rate
Add attribute (necessary, neutral, frivilous)

<<<***NOT TESTED***>>>
*/

CREATE TABLE IF NOT EXISTS Debt(
	primary_key INTEGER PRIMARY KEY,
	name VARCHAR(25) NOT NULL, debt REAL NOT NULL,
	description VARCHAR(25), service REAL,
	interest REAL,
	datePaid TEXT, dateAcquired TEXT
	);