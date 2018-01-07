# TranslationApp

https://github.com/fatevil/TranslationApp


### Usage

```
git clone git@github.com:fatevil/TranslationApp.git
cd TranslationApp
```

MySQL:
```
mysql> create database translation;
mysql> use translation;
 ```

Configure db connection:
```
gedit src/main/resources/application.properties
spring-boot:run
```

Steps summarized above should lead a developer to it's quick deployment. It is a standard procedure for deploying Spring Boot app.

Example data are imported manually in DatabaseLoader class. To try out: 


Visit
```
localhost:8080
```
```
Regular user: john@ahoj.cz0
Admin: admin@admin.cz

Password: bobek
```