# F-SecureCyberSecurityAcademy
Work done for the CyberSecurityAcademy MOOC

The repo will contain work done for the course projects set by the CyberSecurityAcademy MOOC, created by the University of Helsinki and F-Secure. All applications are constructed using the Java Spring framework.

Course materials: https://cybersecuritybase.github.io/

### Course Project 1:
Course Project 1 asked us to create a vulnerable web application containing at least 5 flaws from the OWASP top 10 list. My version includes:
* __A5: Security Misconfiguration:__ The SecurityConfiguration class includes a custom configure method which disables the _csrf attribute being added by the app on a POST request. Malicious external sites are now free to make any POST request they like on this app, and the user has no way of signing out or discontinuing their session! Fantastic. 
  *__Solution:__ Spring seems to automatically add _csrf to POST requests by default in the unmodified version of this project. Repairing this is easy: don't actively disable _csrf being added.


* __A6: Sensitive Data Exposure:__ Added 'Date of birth' and 'Password' fields in the form. This data is unencrypted and is sent in plain text in the form's POST request. Name, address and date of birth are very interesting to an attacker wanting to commit identity fraud - having a password in plain text is just handing them the keys. Bonus feature: password field isn't even obscured by asterisks, so anyone looking over the user's shoulder also has their details. 
  * __Solution:__ Use encrypt sensitive data using hashing and salt before it's sent over POST. Jasypt is a library which allows passwords to be encrypted and securely validated, figuring out how to implement this would be an urgent next step for any developer in charge of an app with this problem. Also don't go publishing details in plain text on user profile pages!


* __A7: Missing Function Level Access Control:__ The application doesn't check the user page being accessed belongs to the person who created the user entity. Fraudulent requests are extremely easy to do, just enter any valid username in the url's 'name' variable.
 * __Solution:__ Users should only be able to see their own information, so the username currently signed in should be checked against the url. Alternatively, the url could just not contain any queries, so the application handles which information should be displayed on the page without allowing the user to enter any information into url variables themselves.


* __A2: Broken Authentication and Session Management:__ Any user can edit anyone else's information if they know a valid username to enter as part of the URL for `/loginpage`.
 * __Solution:__ Enable _csrf tokens as mentioned in vulnerability A5 and create a login system which allows a user to change only their own information if they've signed in successfully. This login should expire after a set time has elapsed (eg. automatically sign a user out after 2 hours of inactivity).


* __[UNSUCCESSFUL] A1: Injection:__ Attempted to allow SQL queries to be executed using input from the 'name' variable in the url for `/loginpage`. See the 'Persistence' branch of this project for the attempted implementation - haven't been able to fix the error 'Unrecognised persistence.xml version []' despite changing persistence versions to 1.0, 2.0 and 2.1.
 * __Solution:__ The current implementation already fixes this by using `signupRepository.findByName(userName);`, which does not allow the native execution of SQL queries. An input of `name=' OR '1'='1` currently searches for a user with the exact name "' OR '1'='1" rather than executing a query with an OR statement 
 