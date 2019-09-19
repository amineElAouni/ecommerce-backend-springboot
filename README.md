# ecommerce-backend-springboot
# Installation de l'application:
Se positionner dans la racine du projet et exécuter les commandes suivantes:
   1) mvn package,
   2) mvn install,
   3) java -jar target/ecommerce-0.0.1-SNAPSHOT.jar pour lancer l'application
   
#l'api est documenté avec Swagger:
Lancer http://localhost:8080/swagger-ui.html pour avoir la documentation sur l'api ainsi pour le tester, 
sinon vous pouvez utiliser postman ou ARC Developer pour tester les API's.

#L'application est sécurisée avec Spring Security
Login : http://localhost:8080/login (Method Post)
Json de connexion {"username":"ADMIN", "password":"ADMIN"} (x-www-form-urlencoded)

Logout : http://localhost:8080/logout (Method Post)
