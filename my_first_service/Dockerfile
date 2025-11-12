# Используем JDK 17
FROM eclipse-temurin:17-jdk

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем Java-файлы из src/server
COPY src/server/*.java ./server/

# Компилируем в ту же структуру пакетов
RUN javac -d . server/*.java

# Собираем исполняемый JAR с точкой входа
RUN jar cfe SimpleServer.jar server.SimpleServer server/*.class

# Команда запуска
CMD ["java", "-jar", "SimpleServer.jar"]
