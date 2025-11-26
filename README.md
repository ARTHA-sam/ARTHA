ARTHAJava Backend Made Simple.📖 OverviewARTHA is a beginner-friendly, ultra-lightweight Java backend framework designed to lower the barrier to entry for server-side Java development.While Java is powerful, frameworks like Spring Boot can feel heavy and overwhelming for students or quick prototypes. ARTHA bridges this gap. Inspired by the simplicity of Python's Flask and FastAPI, ARTHA brings that same "zero-boilerplate" experience to the Java ecosystem.Why ARTHA?For Students: Learn backend concepts without fighting with Maven/Gradle configurations.For Hackathons: Spin up a working API in seconds, not hours.For Educators: Teach logic, not configuration.✨ Key FeaturesSingle-File Backend: Write your entire application in one file if you want to.No main() Required: ARTHA handles the entry point so you can focus on logic.Blazing Fast Startup: Boots up in < 3 seconds.Zero Configuration: No XML, no complex dependency injection setup. Just code.Invisible Build Tools: No exposure to pom.xml or build.gradle unless you want it.Hot Reload: Changes apply instantly via the artha dev CLI.Intuitive Annotations: Use @Get, @Post and more right out of the box.JSON Configuration: Simple artha.json file for settings.IDE Friendly: Works seamlessly in VS Code and IntelliJ IDEA.📦 InstallationTo get started, install the ARTHA CLI tool globally via npm.npm install -g artha
Prerequisites:Java 17 or higher installed on your machine.🚀 Create Your First AppARTHA removes the noise. Here is a complete, working backend application in a single file.App.java@Get("/")
String home() {
    return "Hello from ARTHA!";
}

@Post("/sum")
int sum(@Body Numbers n) {
    return n.a + n.b;
}

class Numbers {
    int a;
    int b;
}
No imports, no class wrappers, no public static void main. It just works.🏃 Run the ApplicationInitialize a new project and start the server using the CLI.# 1. Create a new project
artha new myapp

# 2. Navigate to the directory
cd myapp

# 3. Start the dev server
artha dev
What happens next?ARTHA automatically detects your Java files.It compiles the code in the background.It scans for annotations (like @Get and @Post).The server starts at http://localhost:8080.📂 Project StructureARTHA is flexible. Start small and grow as needed.The Minimalist (Perfect for learning)myapp/
├── App.java        # Your logic
└── artha.json      # Config
The Scalable (Perfect for larger projects)myapp/
├── artha.json
└── src/
    ├── controllers/
    │   ├── UserController.java
    │   └── ProductController.java
    ├── models/
    │   └── User.java
    └── services/
        └── AuthService.java
⚙️ ConfigurationConfiguration is managed via a simple artha.json file in the root directory.{
  "port": 8080,
  "env": "dev",
  "dependencies": [
    "lombok",
    "postgresql"
  ]
}
port: The HTTP port to listen on.env: Run mode (dev enables hot-reload).dependencies: A list of shorthand libraries ARTHA will auto-load for you.📡 Routing API ReferenceDefine routes effortlessly using standard HTTP verb annotations.AnnotationDescriptionExample@Get(path)Handles HTTP GET requests.@Get("/users")@Post(path)Handles HTTP POST requests.@Post("/users")@Put(path)Handles HTTP PUT requests.@Put("/users/:id")@Delete(path)Handles HTTP DELETE requests.@Delete("/users/:id")@Route(verb, path)Generic handler for custom verbs.@Route("PATCH", "/users")🗺️ RoadmapWe are constantly improving ARTHA. Here is what is coming next:[ ] Dependency Auto-installer (Search and add libs via CLI)[ ] Web Postman UI (Built-in API tester at /docs)[ ] VS Code Extension (Snippets and run buttons)[ ] IntelliJ Plugin (Syntax highlighting support)[ ] Database Helpers (Simple ORM wrappers)[ ] Auth Middleware (JWT support out of the box)🆚 ComparisonHow does ARTHA stack up against the giants?FeatureARTHASpring BootJavalinFastAPI (Python)Target AudienceBeginners / StudentsEnterpriseIntermediateBeginners / DataSetup Time< 1 min5-10 mins2-5 mins< 1 minBoilerplateNoneHighLowLowMain() MethodNot RequiredRequiredRequiredRequiredBuild ToolsHidden (CLI)Maven/GradleMaven/Gradlepip/poetryStartup Speed🚀 Very Fast🐢 Slow⚡ Fast🚀 Very Fast🤝 ContributingWe welcome contributions from everyone, especially students looking to make their first open-source contribution!Fork the repository.Create a new Branch (git checkout -b feature/AmazingFeature).Commit your changes (git commit -m 'Add some AmazingFeature').Push to the branch (git push origin feature/AmazingFeature).Open a Pull Request.Please read our Contributing Guidelines for more details.📄 LicenseDistributed under the MIT License. See LICENSE for more information.📬 Contact & CommunityGitHub Issues: Report a bugDiscussions: Join the conversationTwitter/X: @ArthaFrameworkEmail: hello@arthaframework.org<p align="center">Made with ❤️ for the Java Community</p>
