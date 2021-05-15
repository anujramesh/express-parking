# Express Parking

An interactive parking booking and payment management system based on a software requirements specification (SRS) provided by the instructor.</br>
Some of the main features:
- User login/registration
- Book Space
- View Bookings
- Cancel Bookings
- Payment

Additional functionality/features added (not specified by the SRS):
- PBKDF2 (a slow hashing function) Salted hashing to store and verify password information
- Luhn's Algorithm to validate credit card number provided for payment
- Java threading with blocking queues to solve the concurrency problem of multiple customers potentially booking the same space at once
