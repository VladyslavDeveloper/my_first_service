FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY SimpleServer.java .
RUN javac SimpleServer.java
RUN jar cfe SimpleServer.jar server.SimpleServer server/*.class
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF8"
CMD ["java", "-jar", "SimpleServer.jar"]
