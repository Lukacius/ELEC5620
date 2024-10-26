package com.elec5620.portal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: OllamaService
 * Package: com.elec5620.portal.service
 * Description:
 *
 * @Author Benjamin-Huang
 * @Create 26/10/2024 AM11:25
 * @Version 1.0
 */
@Service
public class OllamaService {
    public String callOllamaAPI(String userInput, String model) {
        // 使用 RestTemplate 发送 HTTP 请求
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:11434/api/chat";

        // 构建请求体，包含 model 和 messages
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);

        // 构建 messages 数组
        List<Map<String, String>> messages = new ArrayList<>();

        // 添加 system 消息
        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a helpful assistant."); // 你可以根据需要修改

        // 添加 user 消息
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", userInput);

        // 将消息添加到 messages 列表中
        messages.add(systemMessage);
        messages.add(userMessage);

        // 将 messages 列表加入请求体
        requestBody.put("messages", messages);
        requestBody.put("stream", false);  // 设置为非流式响应

        // 发送 POST 请求
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class);

        // 返回响应结果
        return response.getBody();
    }
}
