# ELEC5620
This project is a backend system for the AI education system, and uses Springboot as the framework. It connects to AI models (openAI and ollama) and database (postgreSql)

# Bacnk-end(Port:8080)
# Technology Stack
    Java 21
    PostgreSQL
    Springboot 3.3.4
    Gradle
    Spring Data JPA
# Back-end Setup
  1.Clone the repository
    git clone <repository-url>
    cd backend
    update application.properties(use your own postgreSql port and change your 'username','password')
# Backend Structure

```bash
backend/
└── src/main/java/com.elec5620.portal/
    ├── config/         # handle crossOrigin
    ├── controller/     # define API
    ├── dto/            # data transfer object
    ├── model/          # DB table structure
    ├── repository/     # JpaRepository
    └── util/           # util
    
    
## API for frontend
    @PostMapping("/LanguageTutor/{email}")
    public String LanguageTutor(@PathVariable String email, @RequestBody UserRequest request)

    @PostMapping("/FurtherReadings/{email}")
    public String FurtherReadings(@PathVariable String email, @RequestBody UserRequest request)

    @PostMapping("/AfterClassExercise/{email}")
    public String AfterClassExercise(@PathVariable String email, @RequestBody UserRequest request)

    @PostMapping("/AssessAssignmentStudent/{email}")
    public String AssessAssignmentStudent(@PathVariable String email, @RequestBody UserRequest request)

    @PostMapping("/LearningFeedback/{email}")
    public String LearningFeedback(@PathVariable String email, @RequestBody UserRequest request)

    @PostMapping("/GenerateSyllabus/{email}")
    public String GenerateSyllabus(@PathVariable String email, @RequestBody UserRequest request)

    @PostMapping("/DetectingPlagiarism/{email}")
    public String DetectingPlagiarism(@PathVariable String email, @RequestBody UserRequest request)

    @PostMapping("/AssessAssignmentTeacher/{email}")
    public String AssessAssignmentTeacher(@PathVariable String email, @RequestBody UserRequest request)

    @PostMapping("/MockExam/{email}")
    public String MockExam(@PathVariable String email, @RequestBody UserRequest request)

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)

## Request body
    public static class UserRequest {
        public String userInput;
        public String model;
        public String session;
    }

## Backend Development
./gradlew build    # Build project
./gradlew test     # Run tests

## Contributing
1. Fork the repository
2. Create feature branch
3. Commit changes
4. Push to branch
5. Create Pull Request
