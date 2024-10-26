package com.elec5620.portal.controller;

import com.elec5620.portal.model.User;
import com.elec5620.portal.repository.UserRepository;
import com.elec5620.portal.service.AIService;
import com.elec5620.portal.service.OllamaService;
import com.elec5620.portal.service.PortalService;
import com.elec5620.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public PortalController(PortalService portalService,AIService aiService) {
        this.portalService = portalService;
        this.aiService = aiService;
    }

    @PostMapping("/LanguageTutor/{email}")
    public String LanguageTutor(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);

        //complete prompt setting
        String userPrompt = "";
        if (user.getUserPrompt() != null) {
            userPrompt = user.getUserPrompt().trim();
        }

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
        String userPrompt = "";
        if (user.getUserPrompt() != null) {
            userPrompt = user.getUserPrompt().trim();
        }

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
        String userPrompt = "";
        if (user.getUserPrompt() != null) {
            userPrompt = user.getUserPrompt().trim();
        }

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
        String userPrompt = "";
        if (user.getUserPrompt() != null) {
            userPrompt = user.getUserPrompt().trim();
        }

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
        String userPrompt = "";
        if (user.getUserPrompt() != null) {
            userPrompt = user.getUserPrompt().trim();
        }

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


    @PostMapping("/GenerateSyllabus")
    public String GenerateSyllabus(@RequestBody UserRequest request) {
        return portalService.GenerateSyllabus(request.userInput);
    }

    @PostMapping("/DetectingPlagiarism")
    public String DetectingPlagiarism(@RequestBody UserRequest request) {
        return portalService.DetectingPlagiarism(request.userInput);
    }

    @PostMapping("/AssessAssignment")
    public String AssessAssignment(@RequestBody UserRequest request) {
        return portalService.AssessAssignment(request.userInput);
    }

    @PostMapping("/MockExam")
    public String MockExam(@RequestBody UserRequest request) {
        return portalService.MockExam(request.userInput);
    }

    @PostMapping("/UpdatingLearningResource")
    public String UpdatingLearningResource(@RequestBody UserRequest request) {
        return portalService.UpdatingLearningResource(request.userInput);
    }

    @PostMapping("/OptimizeAlgorithm")
    public String OptimizeAlgorithm(@RequestBody UserRequest request) {
        return portalService.OptimizeAlgorithm(request.userInput);
    }

    public static class UserRequest {
        public String userInput;
        public String model;
    }
}
