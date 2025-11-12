# Используем Java 17 JDK
FROM eclipse-temurin:17-jdk

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем исходники
COPY src/server/*.java .

# Компилируем
RUN javac SimpleService.java MyHandler.java

# Создаём исполняемый JAR с точкой входа SimpleService
RUN jar cfe SimpleServer.jar SimpleService *.class

# Обязательно: поддержка UTF-8
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF8"

# Запуск сервера
CMD ["java","-jar","SimpleServer.jar"]
