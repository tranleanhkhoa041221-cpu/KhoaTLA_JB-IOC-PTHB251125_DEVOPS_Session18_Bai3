package com.quickbite.devopssession16bai1.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DiscordAppender extends AppenderBase<ILoggingEvent> {

    private String webhookUrl;

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        // Chỉ xử lý khi log ở mức ERROR
        if (eventObject.getLevel().levelInt == Level.ERROR_INT) {
            if (webhookUrl == null || webhookUrl.isBlank()) {
                return;
            }
            try {
                String message = eventObject.getFormattedMessage();
                String loggerName = eventObject.getLoggerName();

                // Chuẩn hóa chuỗi tránh lỗi JSON Syntax
                String safeMessage = message.replace("\\", "\\\\").replace("\"", "\\\"");

                // Payload JSON gửi lên Discord Webhook
                String jsonPayload = String.format(
                        "{\"content\": \" **[ERROR ALERT]**\\n**Logger:** `%s`\\n**Message:** `%s`\"}",
                        loggerName, safeMessage
                );

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(webhookUrl))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                        .build();

                // Gửi HTTP Request bất đồng bộ
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            } catch (Exception e) {
                addError("Failed to send log to Discord Webhook", e);
            }
        }
    }
}
