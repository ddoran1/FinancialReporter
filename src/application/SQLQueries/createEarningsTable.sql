/*
recurrance rate (daily, weekly, monthly, yearly)
need to define this set in later addition
*/

CREATE TABLE IF NOT EXISTS Earnings(
	primary_key INTEGER PRIMARY KEY,
	name VARCHAR(25) NOT NULL, earning REAL NOT NULL,
	description VARCHAR(25), 
	recurring BOOLEAN NOT NULL, recurrance_rate VARCHAR(6) 
	datePaid TEXT, dateAcquired TEXT
	);