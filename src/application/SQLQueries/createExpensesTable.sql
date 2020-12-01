/*
Add recurrence rate
Add attribute (necessary, neutral, frivilous)

<<<***NOT TESTED***>>>
*/

CREATE TABLE IF NOT EXISTS Expenses(
	primary_key INTEGER PRIMARY KEY,
	name VARCHAR(25) NOT NULL, expense REAL NOT NULL,
	description VARCHAR(25), datePaid TEXT, dateAcquired TEXT
	);