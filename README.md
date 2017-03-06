# F-SecureCyberSecurityAcademy
Work done for the CyberSecurityAcademy MOOC

The repo will contain work done for the course projects set by the CyberSecurityAcademy MOOC, created by the University of Helsinki and F-Secure. All applications are constructed using the Java Spring framework.

Course materials: https://cybersecuritybase.github.io/

### Course Project 1:
Course Project 1 asked us to create a vulnerable web application containing at least 5 flaws from the OWASP top 10 list. My version includes:
* __A2: Broken Authentication and Session Management:__ The SecurityConfiguration class includes a custom configure method which disables the _csrf attribute being added by the app on a POST request. Malicious external sites are now free to make any POST request they like on this app, and the user has no way of signing out or discontinuing their session! Fantastic. * __Solution:__ Spring seems to automatically add _csrf to POST requests by default in the unmodified version of this project. Repairing this is easy: don't actively disable _csrf being added.
* __A6: Sensitive Data Exposure:__ Added 'Date of birth' and 'Password' fields in the form. This data is unencrypted and is sent in plain text in the form's POST request. Name, address and date of birth are very interesting to an attacker wanting to commit identity fraud - having a password in plain text is just handing them the keys. Bonus feature: password field isn't even obscured by asterisks, so anyone looking over the user's shoulder also has their details. * __Solution:__ Use encryption to hash and salt sensitive data before it's sent over POST. Jasypt is a library which allows passwords to be encrypted and securely validated, figuring out how to implement this would be an urgent next step for any developer in charge of an app with this problem.
