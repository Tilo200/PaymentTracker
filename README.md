# Payment-Tracker

command for running the application:
-------------------------------------
java -jar PaymentTracker-1.0.0-jar-with-dependencies.jar --fileName=<filename.extension>

	- <filename.extension> optional parameter for defining file with data to be loaded on startup
	
In case an error occurs during file loading, the process is aborted and the application will continue without loading the data.
	
Using the application:
----------------------
User can enter a currency and an amount separated by a space (e.g.: USD 12300), can be with the positive or negative sing (+ or -)
the currency must consist from 3 letters, case in-sensitive.

User can exit the application writing the "quit" word