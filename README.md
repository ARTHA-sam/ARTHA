# 🚀 ARTHA - Java Backend Made Simple

> A zero-boilerplate, ultra-lightweight Java backend framework that brings Flask-like simplicity to the Java ecosystem.

**Status**: ✨ Early Stage | **Contributors**: 2 | **License**: MIT

---

## 📖 What is ARTHA?

ARTHA is a **beginner-friendly, lightweight Java backend framework** designed to remove the complexity that makes Java overwhelming for newcomers.

While Python developers enjoy Flask and FastAPI's "just write code" philosophy, Java developers face:
- Maven/Gradle configuration nightmares
- Heavy frameworks like Spring Boot (5+ minute startup times)
- Complex dependency injection setups  
- Boilerplate everywhere

**ARTHA changes this paradigm.**

---

## 🤯 Why ARTHA is "Mad" (Insanely Cool)

### 1. **Zero-Boilerplate Code with @Step Annotation**
Write complete API endpoints with just the `@Step` annotation:

```java
@Step(path = "/namaste", method = "GET")
public class Hello {
    public String handle(Request req, Response res) {
        return "Namaste, World!";
    }
}
```

**No imports needed. No main() method. No class wrappers. It just works.**

### 2. **Blazingly Fast Setup**
- ⚡ **< 3 seconds startup** (vs. Spring Boot's 30+ seconds)
- 📦 **One command**: `artha new myapp`
- 🎯 **Instant hot-reload**: Changes apply immediately

### 3. **No Build Tool Exposure**
- No `pom.xml` or `build.gradle` cluttering your workspace
- No Maven/Gradle learning curve for beginners
- Build system completely abstracted away by the CLI

### 4. **Intelligent Annotation Scanning**
The framework automatically:
- Detects your Java files
- Compiles code in the background
- Scans for @Step annotations
- Starts your server at http://localhost:8080

### 5. **Flexible Architecture**
Scale from minimalist to enterprise

### 6. **Hybrid Language Stack**
- **CLI**: Node.js/JavaScript (universal, fast)
- **Runtime**: Java (robust, performant)  
- **Best of both**: Easy installation + powerful execution

---

## ✨ Key Features

- **Single-File Backend** - Start with just one `.java` file
- **No main() Required** - ARTHA handles the entry point
- **Hot Reload** - Dev server reloads on file changes
- **Zero Configuration** - No XML, no complex setup
- **IDE Friendly** - Works in VS Code & IntelliJ IDEA
- **JSON Config** - Simple `artha.json` for settings
- **@Step Annotation** - Simple route definition

---

## 🏗️ Repository Structure

```
ARTHA/
├── cli/              # Node.js CLI tool
├── runtime/          # Java runtime engine
├── examples/         # Example projects
├── test-api/         # Test suite
└── README.md
```

**CLI** (`/cli`): Project scaffolding, dev server, hot-reload
**Runtime** (`/runtime`): HTTP server, annotation scanning, routing
**Examples** (`/examples`): 01-hello-world and more

---

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Node.js 14+

### Installation

```bash
npm install -g artha
artha --version
```

### Create Your First App

```bash
artha new myapp
cd myapp
artha dev
```

Open http://localhost:8080 in your browser!

---

## ⚙️ Configuration

Manage your app via **artha.json**:

```json
{
  "port": 8080,
  "env": "dev"
}
```

---

## 📡 @Step Annotation Reference

### Basic Usage

The `@Step` annotation marks a class as an API endpoint:

```java
@Step(path = "/hello", method = "GET")
public class HelloEndpoint {
    public String handle(Request req, Response res) {
        return "Hello, World!";
    }
}
```

### Annotation Parameters

- `path` (required): The URL path for the endpoint (e.g., "/users", "/api/products")
- `method` (optional): HTTP method (default: "GET") - supports GET, POST, PUT, DELETE, etc.

### Method Signature

Your handler method receives:
- `Request req`: Contains query parameters, headers, request data
- `Response res`: Used to set response headers or status codes
- Returns: Response body (String, JSON, etc.)

### Examples

```java
// GET endpoint
@Step(path = "/greet", method = "GET")
public class GreetHandler {
    public String handle(Request req, Response res) {
        String name = req.query("name", "Guest");
        return "Hello, " + name + "!";
    }
}

// POST endpoint
@Step(path = "/users", method = "POST")
public class CreateUserHandler {
    public String handle(Request req, Response res) {
        // Process POST data
        return "{\"id\": 1, \"created\": true}";
    }
}

// Dynamic path
@Step(path = "/users/:id", method = "GET")
public class GetUserHandler {
    public String handle(Request req, Response res) {
        String userId = req.pathParam("id");
        return "{\"id\": " + userId + "}";
    }
}
```

---

## 🎯 Why Choose ARTHA?

**For Students**: Learn backend without fighting configuration
**For Hackathons**: Spin up APIs in seconds
**For Educators**: Teach logic, not configuration

---

## 🛣️ Roadmap

- [ ] Request/Response body auto-parsing
- [ ] Error handling middleware
- [ ] Database helpers
- [ ] JWT Auth support
- [ ] WebSocket support
- [ ] Docker integration
- [ ] OpenAPI generation
- [ ] Built-in testing framework

---

## 🤝 Contributing

We welcome contributions! Especially from students making their first open-source contribution.

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/YourFeature`
3. Commit: `git commit -m 'Add YourFeature'`
4. Push: `git push origin feature/YourFeature`
5. Open a Pull Request

---

## 📄 License

MIT License. See LICENSE for details.

---

## 📬 Support & Community

- **GitHub Issues**: Report bugs or request features
- **GitHub Discussions**: Join the conversation
- **Email**: hello@arthaframework.org
- **Twitter/X**: @ArthaFramework

---

## 💡 Why We Built ARTHA

Java's power shouldn't require a steep learning curve. We believe beginners should focus on logic, not configuration.

Inspired by Flask and FastAPI, ARTHA brings that philosophy to Java.

---

## 🎯 Made with ❤️ for the Java Community

**Let's make Java fun again.** 🚀

If ARTHA helped you, please give us a star! ⭐
