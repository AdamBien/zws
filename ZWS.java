import java.awt.Desktop;

import module jdk.httpserver;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;


void main(String... args) throws IOException, InterruptedException {
    var port = 3000;
    var loopback = new InetSocketAddress(InetAddress.getLoopbackAddress(), port);
    var directory = args.length > 0 ? args[0] : ".";
    var path = Path.of(directory).normalize().toAbsolutePath();

    var server = HttpServer.create(loopback, 0);
    var handler = SimpleFileServer.createFileHandler(path);
    server.createContext("/", new NoCacheHandler(handler));
    server.start();

    var url = "http://%s:%d".formatted(
            server.getAddress().getHostString(),
            server.getAddress().getPort());
    IO.println("Serving files from: " + path);
    IO.println(url);
    Browser.open(url);
    IO.println("browser opened ");
}

record NoCacheHandler(HttpHandler handler) implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var headers = exchange.getResponseHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        handler.handle(exchange);
    }
}

interface Browser {
    static void open(String uriString) throws IOException {
        var uri = URI.create(uriString);
        Desktop.getDesktop().browse(uri);
    }
}

enum OS {

    MAC, LINUX, WINDOWS;

    static OS detect() {
        var os = System.getProperty("os.name")
                .toLowerCase();
        if (os.contains("mac")) {
            return MAC;
        }
        if (os.contains("nix")) {
            return LINUX;
        }
        if (os.contains("win")) {
            return WINDOWS;
        }
        throw new IllegalArgumentException("Unknown OS: " + os);
    }

}
