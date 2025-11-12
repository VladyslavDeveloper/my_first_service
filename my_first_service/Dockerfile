FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY src/server/*.java .

# Компилируем с учётом пакета
RUN javac -d . SimpleServer.java MyHandler.java

# Создаём JAR с точкой входа
RUN jar cfe SimpleServer.jar server.SimpleServer server/*.class

# UTF-8
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF8"

# Запуск
CMD ["java","-jar","SimpleServer.jar"]
