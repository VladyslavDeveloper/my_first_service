package server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));

        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
        server.createContext("/", new RootHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Сервер запущен на порту " + port);
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = "<!DOCTYPE html>" +
                    "<html lang='ru'>" +
                    "<head>" +
                    "<meta charset='UTF-8'>" +
                    "<title>Мой первый сервис</title>" +
                    "<style>" +
                    "body{margin:0;padding:0;font-family:sans-serif;background:linear-gradient(to right,#6a11cb,#2575fc);color:#fff;display:flex;justify-content:center;align-items:center;height:100vh;text-align:center;}" +
                    ".container{background:rgba(0,0,0,0.4);padding:40px 60px;border-radius:20px;box-shadow:0 8px 20px rgba(0,0,0,0.3);}" +
                    "h1{font-size:3em;margin-bottom:20px;text-shadow:2px 2px 5px rgba(0,0,0,0.5);}" +
                    "p{font-size:1.2em;line-height:1.6;}" +
                    ".btn{display:inline-block;margin-top:30px;padding:12px 25px;font-size:1em;color:#fff;background:#ff6a00;border:none;border-radius:10px;cursor:pointer;text-decoration:none;transition:background 0.3s;}" +
                    ".btn:hover{background:#ff3d00;}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<h1>Мой первый сервис, привет!</h1>" +
                    "<p>HTML прямо в Java. Всё в одном файле!</p>" +
                    "<a href='#' class='btn'>Нажми меня</a>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            byte[] bytes = html.getBytes("UTF-8");
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
                os.flush();
            }
        }
    }
}
