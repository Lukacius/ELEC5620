package com.elec5620.portal.controller;

import com.elec5620.portal.model.User;
import com.elec5620.portal.repository.UserRepository;
import com.elec5620.portal.service.AIService;
import com.elec5620.portal.service.OllamaService;
import com.elec5620.portal.service.PortalService;
import com.elec5620.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("/api/portal")
public class PortalController {
    private final PortalService portalService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OllamaService ollamaService;

    @Autowired
    private final AIService aiService;

    private static final String UPLOAD_DIRECTORY = "./";

    @Autowired
    public PortalController(PortalService portalService,AIService aiService) {
        this.portalService = portalService;
        this.aiService = aiService;
    }

    @PostMapping("/LanguageTutor/{email}")
    public String LanguageTutor(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);

        //complete prompt setting
        String userPrompt = user.getUserPrompt().trim();

        String difficultySettings = switch(user.getDifficultyLevel()) {
            case EASY -> {
                yield  "Act as a beginner-friendly language tutor focused on creating simple conversation exercises and basic grammar correction. Start with short, everyday dialogues on familiar topics (e.g., introductions, ordering food), using simple vocabulary and straightforward sentence structures. Offer gentle corrections for grammar and vocabulary, explaining errors in clear, non-technical terms. Encourage the user to respond or ask questions with short sentences and provide examples when needed. Adapt to the user's chosen language (e.g., English, Spanish) and adjust the pace based on their understanding, ensuring a comfortable and supportive learning environment.";
            }
            case MEDIUM -> {
                yield "Act as an intermediate language tutor, providing conversation practice and grammar correction aimed at enhancing fluency and accuracy. Initiate dialogues on a range of topics (e.g., discussing plans, describing experiences) and encourage more detailed responses using a wider vocabulary. Give constructive feedback on grammar and sentence structure, offering explanations for errors and suggesting more natural phrasing. Challenge the user to form compound sentences and use language structures like past and future tenses. Adapt to the user's chosen language (e.g., French, German), and guide them to apply learned grammar rules while engaging in conversation.";
            }
            case HARD -> {
                yield "Act as an advanced language tutor focused on nuanced conversation practice and in-depth grammar correction. Engage the user in complex dialogues on abstract or specialized topics (e.g., current events, cultural debates), encouraging them to express detailed opinions and arguments. Provide comprehensive feedback on grammar, idiomatic expressions, and advanced structures (e.g., subjunctive mood, hypothetical clauses), explaining corrections in technical terms if needed. Challenge the user to refine their phrasing for clarity and sophistication, helping them develop precision in vocabulary and syntax. Adapt to the user’s chosen language (e.g., Mandarin, Italian), pushing them to achieve fluency and polish in both spoken and written forms.";
            }
        };

        //prompt concat
        request.userInput =  userPrompt + " and " +difficultySettings + " and " + request.userInput;

        System.out.println(request.userInput);
        System.out.println(request.model);

        //judge model, select AI service
        if ("ollama".equalsIgnoreCase(request.model)) {
            //invoke local Ollama model API
            return ollamaService.callOllamaAPI(request.userInput,"llama3.2:latest");
        } else if ("openai".equalsIgnoreCase(request.model)) {
            return portalService.LanguageTutor(request.userInput);
        }

        return "model doesn't exist";

    }

    @PostMapping("/FurtherReadings/{email}")
    public String FurtherReadings(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);

        //complete prompt setting
        String userPrompt = user.getUserPrompt().trim();

        String difficultySettings = switch(user.getDifficultyLevel()) {
            case EASY -> {
                yield "Recommend beginner-level reading materials to help build foundational understanding of the subject. Focus on accessible sources such as introductory textbooks, articles, or reputable websites that provide clear explanations of core concepts. The reading should use simple language and cover the basics, helping students gain a solid grasp of the fundamentals. Ensure each source is engaging and well-suited for those new to the topic, emphasizing clarity and essential knowledge.";
            }
            case MEDIUM -> {
                yield "Recommend intermediate-level reading materials that go beyond the basics, aimed at students with some familiarity with the subject. Include books, articles, or research papers that introduce more complex concepts and encourage critical thinking. The readings should cover a wider range of topics and provide deeper insights, helping students connect foundational knowledge with more nuanced ideas. Suggest sources that strike a balance between accessibility and academic rigor, offering opportunities for more in-depth exploration.";
            }
            case HARD -> {
                yield "Recommend advanced-level reading materials intended for students with a strong foundation in the subject, seeking a challenging and in-depth understanding. Include specialized books, peer-reviewed research papers, or case studies that delve into complex theories, current debates, and recent advancements. The recommended readings should require analytical skills and encourage critical engagement with the material. Prioritize sources that provide comprehensive insights, support expert-level mastery, and challenge students to think independently and critically.";
            }
        };

        //prompt concat
        request.userInput =  userPrompt + " and " +difficultySettings + " and " + request.userInput;

        System.out.println(request.userInput);
        System.out.println(request.model);

        //judge model, select AI service
        if ("ollama".equalsIgnoreCase(request.model)) {
            //invoke local Ollama model API
            return ollamaService.callOllamaAPI(request.userInput,"llama3.2:latest");
        } else if ("openai".equalsIgnoreCase(request.model)) {
            return portalService.LanguageTutor(request.userInput);
        }

        return "model doesn't exist";

    }

    @PostMapping("/AfterClassExercise/{email}")
    public String AfterClassExercise(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);

        //complete prompt setting
        String userPrompt = user.getUserPrompt().trim();

        String difficultySettings = switch(user.getDifficultyLevel()) {
            case EASY -> {
                yield "Generate easy exercises designed for beginners or users seeking light practice. Each exercise should be written in clear, simple language, covering foundational concepts and skills that can be completed quickly. Use straightforward formats such as multiple-choice, fill-in-the-blank, and matching exercises. Ensure that instructions are simple, avoiding complex terminology or open-ended questions, and provide clear answers. Each set should take no longer than 10 minutes to complete and require minimal problem-solving. Include an example answer if needed for clarity. Allow the user to specify the subject or skill area (e.g., basic math, reading comprehension), and generate exercises accordingly.";
            }
            case MEDIUM -> {
                yield "Generate medium-level exercises aimed at users with a foundational understanding of the subject, seeking a moderate challenge. Exercises should involve more analytical thinking and may combine multiple skills, yet remain manageable within 20 minutes. Use formats like short-answer, multi-step problems, and scenario-based questions to engage users in deeper thinking. Instructions should be clear but may include more specific terminology relevant to the subject. Each exercise should be designed to promote understanding of concepts, provide opportunities for applied learning, and include brief hints or steps if needed. Allow users to specify the topic or skill area (e.g., intermediate math, critical reading), and create exercises that match this level.";
            }
            case HARD -> {
                yield "Generate advanced-level exercises intended for users with strong knowledge of the subject, aiming to provide a high level of challenge. These exercises should incorporate complex, multi-step problems or advanced analytical tasks that require careful reasoning and may take 30 minutes or more to complete. Utilize formats such as open-ended questions, case studies, complex problem-solving, or detailed scenario analysis that encourage critical thinking and the application of deep knowledge. Instructions should be precise and may use technical language, depending on the subject. Aim to test users' mastery and critical skills, allowing for multiple approaches or solutions where applicable. Let users specify the subject area (e.g., advanced math, literary analysis), and tailor exercises to be rigorous and comprehensive.";
            }
        };

        //prompt concat
        request.userInput =  userPrompt + " and " +difficultySettings + " and " + request.userInput;

        System.out.println(request.userInput);
        System.out.println(request.model);

        //judge model, select AI service
        if ("ollama".equalsIgnoreCase(request.model)) {
            //invoke local Ollama model API
            return ollamaService.callOllamaAPI(request.userInput,"llama3.2:latest");
        } else if ("openai".equalsIgnoreCase(request.model)) {
            return portalService.LanguageTutor(request.userInput);
        }

        return "model doesn't exist";
    }

    @PostMapping("/AssessAssignment/{email}")
    public String AssessAssignmentStudent(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);

        //complete prompt setting
        String userPrompt = user.getUserPrompt().trim();

        String difficultySettings = switch(user.getDifficultyLevel()) {
            case EASY -> {
                yield "Provide constructive, beginner-friendly feedback on my assignment. Focus on basic elements such as clarity, grammar, and structure, and give simple suggestions to improve readability and coherence. Identify any obvious grammar or spelling errors, explain them in plain language, and offer corrections where appropriate. Also, offer encouraging comments on what I did well to help build confidence. Tailor the feedback to my level, emphasizing accessible, easy-to-apply tips to strengthen the work.";
            }
            case MEDIUM -> {
                yield "Provide intermediate-level feedback on my assignment, focusing on both content and clarity. Assess the organization, main arguments, and grammar, and suggest ways to make the writing more compelling and coherent. Highlight areas where more detail, examples, or analysis might strengthen the arguments, and offer suggestions on grammar and vocabulary to improve precision. Include feedback on how well my ideas are presented and guide me in enhancing both the logical flow and depth of my work. Offer actionable steps to address any specific issues noted.";
            }
            case HARD -> {
                yield "Provide advanced-level, in-depth feedback on my assignment, critiquing content quality, argument depth, and style. Examine the effectiveness of my thesis and main arguments, looking at logical consistency, evidence support, and the structure of complex ideas. Identify any advanced grammar or syntax issues that might affect clarity, and suggest improvements in tone, phrasing, and word choice to increase sophistication. Offer detailed, constructive feedback on how to make the arguments more persuasive and cohesive, and suggest nuanced adjustments that would elevate the quality of my work to a professional level.";
            }
        };

        //prompt concat
        request.userInput =  userPrompt + " and " +difficultySettings + " and " + request.userInput;

        System.out.println(request.userInput);
        System.out.println(request.model);

        //judge model, select AI service
        if ("ollama".equalsIgnoreCase(request.model)) {
            //invoke local Ollama model API
            return ollamaService.callOllamaAPI(request.userInput,"llama3.2:latest");
        } else if ("openai".equalsIgnoreCase(request.model)) {
            return portalService.LanguageTutor(request.userInput);
        }

        return "model doesn't exist";
    }

    @PostMapping("/LearningFeedback/{email}")
    public String LearningFeedback(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);

        //complete prompt setting
        String userPrompt = user.getUserPrompt().trim();

        String difficultySettings = "Analyze my historical study data and provide comprehensive feedback on my learning progress. Based on my study dates, duration, topics, scores, and self-reported challenges, identify key areas where I’m improving, as well as specific concepts that may need further review. Summarize trends in my study habits, such as consistency in time spent or progress in understanding. Offer practical suggestions for areas needing improvement and suggest study strategies that could help me address recurring difficulties. Provide feedback that helps me better structure my study sessions and improve my overall learning effectiveness.";

        //prompt concat
        request.userInput =  userPrompt + " and " +difficultySettings + " and " + request.userInput;

        System.out.println(request.userInput);
        System.out.println(request.model);

        //judge model, select AI service
        if ("ollama".equalsIgnoreCase(request.model)) {
            //invoke local Ollama model API
            return ollamaService.callOllamaAPI(request.userInput,"llama3.2:latest");
        } else if ("openai".equalsIgnoreCase(request.model)) {
            return portalService.LanguageTutor(request.userInput);
        }

        return "model doesn't exist";
    }


    @PostMapping("/GenerateSyllabus/{email}")
    public String GenerateSyllabus(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);

        //complete prompt setting
        String userPrompt = user.getUserPrompt().trim();

        String difficultySettings = switch(user.getDifficultyLevel()) {
            case EASY -> {
                yield "Create a beginner-friendly syllabus outline for an introductory course. The syllabus should cover foundational concepts in the subject, broken down into simple, manageable weekly topics. Include basic learning objectives, recommended readings, and key activities or assessments that reinforce understanding of each topic. Keep the syllabus structure straightforward, with a focus on building a solid understanding of core ideas. Ensure that the outline is accessible and engaging for learners new to the subject, helping them gradually gain confidence and knowledge in a supportive learning environment.";
            }
            case MEDIUM -> {
                yield "Create a syllabus outline for an intermediate course, designed for students with some prior knowledge of the subject. Include weekly or unit-based topics that build on foundational concepts and introduce more complex ideas. Define specific learning objectives, list required readings, and suggest activities or assessments that encourage deeper engagement, such as group projects or analytical assignments. Ensure that each topic is structured to challenge students and expand their understanding, with an emphasis on critical thinking and application. Provide a timeline that guides progression and prepares students for more advanced study in the subject.";
            }
            case HARD -> {
                yield "Create a detailed syllabus outline for an advanced-level course intended for students with strong foundational knowledge in the subject. Organize the syllabus around in-depth, complex topics and specialized areas, outlining comprehensive weekly or module-based learning objectives. Include advanced readings, research articles, or case studies, and design rigorous assessments such as research projects, presentations, or critical essays. Each week’s topics should challenge students to critically analyze, synthesize, and apply concepts, preparing them for expert-level understanding. Provide a well-structured timeline and clear criteria for each component to help students manage the course demands and deepen their mastery.";
            }
        };

        //prompt concat
        request.userInput =  userPrompt + " and " +difficultySettings + " and " + request.userInput;

        System.out.println(request.userInput);
        System.out.println(request.model);

        //judge model, select AI service
        if ("ollama".equalsIgnoreCase(request.model)) {
            //invoke local Ollama model API
            return ollamaService.callOllamaAPI(request.userInput,"llama3.2:latest");
        } else if ("openai".equalsIgnoreCase(request.model)) {
            return portalService.GenerateSyllabus(request.userInput);
        }

        return "model doesn't exist";
    }

    @PostMapping("/DetectingPlagiarism/{email}")
    public String DetectingPlagiarism(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);

        //complete prompt setting
        String userPrompt = user.getUserPrompt().trim();

        String difficultySettings = "Analyze the input content to detect potential signs of AI generation or plagiarism. Look for common indicators such as repetitive phrasing, overly consistent structure, lack of context-specific examples, or unnatural syntax patterns. Compare the text against known AI generation styles and check for uniqueness by cross-referencing with accessible databases or sources if possible. Provide a summary of detected AI-generated characteristics, if any, and indicate the likelihood of originality. Offer feedback on specific areas of the text that may need human revision for improved authenticity and originality.";

        //prompt concat
        request.userInput =  userPrompt + " and " +difficultySettings + " and " + request.userInput;

        System.out.println(request.userInput);
        System.out.println(request.model);

        //judge model, select AI service
        if ("ollama".equalsIgnoreCase(request.model)) {
            //invoke local Ollama model API
            return ollamaService.callOllamaAPI(request.userInput,"llama3.2:latest");
        } else if ("openai".equalsIgnoreCase(request.model)) {
            return portalService.DetectingPlagiarism(request.userInput);
        }

        return "model doesn't exist";
    }

    @PostMapping("/AssessAssignment/{email}")
    public String AssessAssignment(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);

        //complete prompt setting
        String userPrompt = user.getUserPrompt().trim();

        String difficultySettings = switch(user.getDifficultyLevel()) {
            case EASY -> {
                yield "Assist in assessing beginner-level assignments, focusing on basic comprehension, clarity, and correctness. Review the student’s work for essential understanding of core concepts and identify any simple errors in grammar, spelling, or formatting. Provide positive, encouraging feedback and suggest one or two small improvements to enhance clarity and presentation. Keep feedback constructive and beginner-friendly, offering clear guidance to help students reinforce foundational skills.";
            }
            case MEDIUM -> {
                yield "Assist in evaluating intermediate-level assignments, focusing on both content accuracy and presentation. Review the work for clarity of ideas, organization, and relevance to the assignment prompt, noting any areas where reasoning or evidence could be strengthened. Offer constructive criticism on grammar, structure, and vocabulary to improve flow and coherence. Suggest practical improvements for content depth and presentation, helping students transition from foundational skills to more developed, coherent expression. Ensure feedback is actionable and specific, with examples or brief explanations where useful.";
            }
            case HARD -> {
                yield "Provide detailed assessment support for advanced-level assignments, focusing on the depth, originality, and critical thinking displayed in the work. Evaluate the quality of arguments, supporting evidence, and logical structure, highlighting strengths as well as areas for refinement. Offer in-depth, constructive feedback on advanced grammar, style, and precision of language, suggesting ways to improve clarity and persuasiveness. Identify areas where analysis, synthesis, or originality could be deepened, helping students achieve a higher level of academic rigor. Ensure that feedback is thorough, nuanced, and tailored to support advanced learning outcomes.";
            }
        };

        //prompt concat
        request.userInput =  userPrompt + " and " +difficultySettings + " and " + request.userInput;

        System.out.println(request.userInput);
        System.out.println(request.model);

        //judge model, select AI service
        if ("ollama".equalsIgnoreCase(request.model)) {
            //invoke local Ollama model API
            return ollamaService.callOllamaAPI(request.userInput,"llama3.2:latest");
        } else if ("openai".equalsIgnoreCase(request.model)) {
            return portalService.AssessAssignment(request.userInput);
        }

        return "model doesn't exist";
    }

    @PostMapping("/MockExam/{email}")
    public String MockExam(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);

        //complete prompt setting
        String userPrompt = user.getUserPrompt().trim();

        String difficultySettings = switch(user.getDifficultyLevel()) {
            case EASY -> {
                yield "Generate a beginner-level mock exam designed to test fundamental concepts and basic understanding. Focus on simple, clear questions that assess core knowledge, using multiple-choice, true/false, and short-answer formats to ensure accessibility. Questions should be direct and straightforward, with minimal complexity, covering the essential topics of the course. Provide a balanced mix of question types that reinforce foundational skills, and ensure that each question is aligned with the primary learning objectives of the introductory material.";
            }
            case MEDIUM -> {
                yield "Generate an intermediate-level mock exam aimed at assessing both foundational knowledge and application of concepts. Include a variety of question types, such as multiple-choice, short-answer, and scenario-based questions, that encourage students to think critically and apply their knowledge. Questions should cover a range of topics within the course, with some requiring multi-step reasoning or brief analysis. Ensure each question tests understanding of key concepts while challenging students to demonstrate comprehension beyond memorization, and provide clear instructions for each question type.";
            }
            case HARD -> {
                yield "Generate an advanced-level mock exam intended to evaluate deep understanding, critical analysis, and synthesis of complex topics. Design questions that require in-depth responses, including essay prompts, case analyses, and multi-part problems that test students’ mastery of the subject. The exam should include challenging, open-ended questions that encourage students to integrate knowledge, provide evidence-based reasoning, and demonstrate advanced analytical skills. Ensure that each question is structured to assess nuanced understanding and critical thinking, suitable for students with strong command over the subject matter.";
            }
        };

        //prompt concat
        request.userInput =  userPrompt + " and " +difficultySettings + " and " + request.userInput;

        System.out.println(request.userInput);
        System.out.println(request.model);

        //judge model, select AI service
        if ("ollama".equalsIgnoreCase(request.model)) {
            //invoke local Ollama model API
            return ollamaService.callOllamaAPI(request.userInput,"llama3.2:latest");
        } else if ("openai".equalsIgnoreCase(request.model)) {
            return portalService.MockExam(request.userInput);
        }

        return "model doesn't exist";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }

        try {
            File directory = new File(UPLOAD_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Path path = Paths.get(UPLOAD_DIRECTORY + file.getOriginalFilename());
            Files.write(path, file.getBytes());

            return ResponseEntity.ok("File upload successful: " + path.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }

    public static class UserRequest {
        public String userInput;
        public String model;
    }
}
