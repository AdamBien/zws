import java.awt.Desktop;

import com.sun.net.httpserver.SimpleFileServer;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;

    void main(String... args) throws IOException, InterruptedException {
        var port = 3000;
        var loopback = new InetSocketAddress(InetAddress.getLoopbackAddress(), port);
        var path = Path.of(".").toAbsolutePath();
        var webServer = SimpleFileServer.createFileServer(loopback, path, OutputLevel.VERBOSE);
        webServer.start();
        var url = "http://%s:%d".formatted(
                webServer.getAddress().getHostString(),
                webServer.getAddress().getPort());
       IO.println( url);
        Browser.open(url);
       IO.println( "browser opened ");
    }

interface Browser {
    static void open(String uriString) throws IOException {
        var uri = URI.create(uriString);
        Desktop.getDesktop().browse(uri);
    }
}

enum OS {

    MAC,LINUX,WINDOWS;

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
