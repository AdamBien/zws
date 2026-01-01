# Zero Dependencies Web Server (ZWS)

A zero-dependency development web server that serves files and opens the browser automatically.

## Prerequisites

Java 25 or later

## Quick Start

```bash
curl -O https://raw.githubusercontent.com/AdamBien/zws/main/ZWS.java && java ZWS.java
```

Or clone this repository and copy `ZWS.java` to a directory in your PATH.

## Usage

```bash
java ZWS.java [root-directory]
```

- Serves files from the current directory (or specified root)
- Runs on port 3000
- Opens Firefox at `http://localhost:3000`
- Disables caching for development

## Example Projects

[bce.design](https://github.com/AdamBien/bce.design) — vanilla WebComponents

## Demo

[![ZWS Demo](https://i.ytimg.com/vi/pkpaUHuT9Rg/mqdefault.jpg)](https://www.youtube.com/watch?v=pkpaUHuT9Rg)
