# Zero Dependencies Web Server (ZWS)

Development web server in a single Java source file on top of the JDK's built-in `jdk.httpserver`: serves static files, disables caching, and opens the browser automatically.

## Prerequisites

Java 25 or later

## Quick Start

```bash
curl -O https://raw.githubusercontent.com/AdamBien/zws/main/zws
chmod +x zws
./zws [root-directory]
```

The file has no `.java` extension, so launching it with `java` directly requires the source flag: `java --source 25 zws [root-directory]` — or use `zws.sh`, which does exactly that. Copy `zws` and `zws.sh` to a directory in your PATH for system-wide use.

- Serves files from the current directory (or the specified root)
- Listens on `http://localhost:3000` (loopback only)
- Opens the default browser
- Sends no-cache headers on every response

## Single Page Applications

Pass `--single` to serve SPAs with client-side routing:

```bash
./zws [root-directory] --single
```

GET requests for paths that do not exist on disk and have no file extension (client-side routes like `/add`) are answered with `index.html`, so deep links and reloads reach the application. Requests with a file extension (assets) still return 404, keeping typos visible.

## Example Projects

[bce.design](https://github.com/AdamBien/bce.design) — vanilla WebComponents

## Demo

[![ZWS Demo](https://i.ytimg.com/vi/pkpaUHuT9Rg/mqdefault.jpg)](https://www.youtube.com/watch?v=pkpaUHuT9Rg)
