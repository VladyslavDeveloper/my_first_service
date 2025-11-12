package server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        // Получаем порт из переменной окружения PORT (для Render) или используем 8080 по умолчанию
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        
        // Создаём сервер один раз
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Сервер запущен на порту " + server.getAddress().getPort());
    }
}
