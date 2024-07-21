# MAEL Scann - Kotlin 2.0

## What is MAEL?
"Multi-application d'Aide à l'Enseignement des Langues"
Multi-application for Language Teaching Support
MAEL Scan is an application for reading the contents of QR codes generated by MAEL Gen.
The aim of MAEL is to put sound on educational paper documents to facilitate language teaching.

## Porting to Kotlin
MAEL Scan was originally designed with MIT App Invertor. But restrictions forced me to port the application to another language:

* No compilation possible for iOS
* Language too limited compared to Python (need for encryption/decryption symmetry with MAEL Gen): I had to use JavaScript in an App Inventor web element to compensate.
* Visual block system inappropriate to long codes
* No possibility of using GIT

Kotlin Multiplatform solves these problems.
Possible uses :

* Port MAEL Gen to Kotlin MP as well?
* Use Kotlin / Ktor to secure access from MAEL Phrase to mySQL?
