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
                    "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                    "<title>Мой первый сервис</title>" +
                    "<style>" +
                    "body{" +
                    "margin:0;padding:0;" +
                    "font-family:sans-serif;" +
                    "background:linear-gradient(to bottom, #6a11cb, #2575fc);" +
                    "color:#fff;" +
                    "display:flex;" +
                    "justify-content:center;" +
                    "align-items:center;" +
                    "height:100vh;" +
                    "text-align:center;" +
                    "}" +
                    ".container{" +
                    "background:rgba(0,0,0,0.5);" +
                    "padding:20px;" +
                    "border-radius:15px;" +
                    "width:90%;" +
                    "max-width:400px;" +
                    "box-shadow:0 5px 15px rgba(0,0,0,0.3);" +
                    "}" +
                    "h1{font-size:2em;margin-bottom:15px;}" +
                    "p{font-size:1em;line-height:1.4;}" +
                    ".btn{" +
                    "display:inline-block;margin-top:20px;padding:10px 20px;" +
                    "font-size:1em;color:#fff;background:#ff6a00;" +
                    "border:none;border-radius:8px;text-decoration:none;" +
                    "transition:0.3s;cursor:pointer;" +
                    "}" +
                    ".btn:hover{background:#ff3d00;}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<h1>Привет! Это мой первый сервис</h1>" +
                    "<p>HTML прямо в Java. Всё в одном файле!</p>" +
                    "<p>Создан Владиславом Жигановым</p>" +
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

