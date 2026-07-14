# AGENTS.md

Guidance for AI coding agents working in this repository or using zws in other projects.

## What zws is

Zero Dependencies Web Server: a development web server for static sites, implemented in a single Java source file (`zws`) on top of the JDK's built-in `jdk.httpserver`. No external dependencies, no build system — the source file is the executable.

## Using zws

```bash
./zws [root-directory] [--single] [--live]
```

- Serves static files from the given directory (default: current directory)
- Listens on `http://localhost:3000`, loopback only — the port is fixed
- Opens the default browser on start (fails in headless environments; ignore the error or run where a desktop is available)
- Sends no-cache headers on every response, so edits are always visible on refresh

Flags (order-independent, combinable):

- `--single` — SPA fallback: GET requests for extension-less paths that don't exist on disk (client-side routes like `/add`) return `index.html`. Paths with a file extension still 404.
- `--live` — live reload: the site root is watched recursively and served HTML pages get a script injected before `</body>` that listens on the `/reload` server-sent-events endpoint and calls `location.reload()` on every file change. Composes with `--single`.

Launching: the file has no `.java` extension, so either run it directly (`./zws`, shebang uses `java --source 25`), via `./zws.sh`, or with `java --source 25 zws [args]`. Requires Java 25+.

For agents driving a browser against a zws-served site: the server prints the URL on stdout and blocks; run it in the background. `/reload` is an SSE endpoint — connecting to it with a plain HTTP GET will hang by design.

## Working on this repository

- **Single file, zero dependencies.** All server code lives in `zws`. Do not split it into multiple files, add a build system (Maven/Gradle/zb), or introduce any dependency beyond the JDK.
- **Java 25 compact source file style**: instance `main`, `IO.println`, module imports, records for handlers. Keep new code in this idiom.
- **Version**: the `VERSION` constant at the top of `zws` uses the `YYYY.MM.DD.NN` scheme (date + per-day sequence). Bump it with every functional change before committing.
- **Portability**: the shebang uses `env -S` for Linux compatibility — don't change it. Behavior must work on macOS, Linux, and Windows.
- **No tests exist.** Verify changes by serving a sample directory and exercising the behavior (curl the endpoints, check headers, watch the reload SSE stream).
- Keep `README.md` in sync when flags or behavior change.
