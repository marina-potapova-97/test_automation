# Запуск автотестов

**1. Запуск Docker**
docker-compose up -d
**Важно:** Команда выполняется из корневой папки проекта, где находится файл

**2. Запуск приложения aqa-shop.jar**
java -jar aqa-shop.jar

**3. Запуск автотестов**
./gradlew clean test
