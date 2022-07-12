# currency_converter
Application "Currency converter with conversion history"

Рекомендации по сборке и запуску проекта:
1.	Создать на сервере БД(PostgreSQL) пользователя с именем: “demo_user” и паролем: “demo_pass” или заменить 
в файле application.properties данные в spring.datasource.username= и spring.datasource.password= на свои.
    Команда для создания пользователя:
CREATE ROLE demo_user WITH LOGIN NOSUPERUSER INHERIT CREATEDB NOCREATEROLE REPLICATION CONNECTION LIMIT -1 
PASSWORD 'demo_pass';

2.	Создать на сервере БД(PostgreSQL) пустую базу данных с именем currency_converter.
    Команда для создания базы данных:
CREATE DATABASE currency_converter WITH OWNER = demo_user ENCODING = 'UTF8' CONNECTION LIMIT = -1;

3.	Для запуска приложения из командной строки без создания архива необходимо перейти в корневую папку проекта и 
ввести команду: “mvnw spring-boot:run”.
Для создания jar-файла необходимо перейти в корневую папку проекта и ввести команду: “mvnw clean package”. 
Затем для запуска jar-файла из командной строки необходимо перейти в папку с jar файлом и набрать: 
“java -jar currencyconverter-0.1.jar”.

4.	Для входа на страницу приложения необходимо после запуска jar-файла или старта приложения без создания архива, 
в браузере открыть адрес http://localhost:8084/ . На странице ввода логина и пароля, необходимо ввести данные 
тестового пользователя: Login – demo, Password – demopass, и нажать “Enter”. Также можно сменить язык отображения.

Примечание: данные тестового пользователя можно поменять в классе WebSecurityConfig.


