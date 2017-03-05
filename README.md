# F-SecureCyberSecurityAcademy
Work done for the CyberSecurityAcademy MOOC

The repo will contain work done for the course projects set by the CyberSecurityAcademy MOOC, created by the University of Helsinki and F-Secure. All applications are constructed using the Java Spring framework.

Course materials: https://cybersecuritybase.github.io/

### Course Project 1:
Course Project 1 asked us to create a vulnerable web application containing at least 5 flaws from the OWASP top 10 list. My version includes:
* __A2: Broken Authentication and Session Management:__ The SecurityConfiguration class includes a custom configure method which disables the _csrf attribute being added by the app on a POST request. Malicious external sites are now free to make any POST request they like on this app, and the user has no way of signing out or discontinuing their session! Fantastic.
