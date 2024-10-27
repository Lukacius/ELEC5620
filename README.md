# ELEC5620
This project is a backend system for the AI education system, and uses Springboot as the framework. It connects to AI models (openAI and ollama) and database (postgresDB)

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
