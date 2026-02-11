package org.example.DesignPatterns.Questions.LoggingFramework.entities;

import org.example.DesignPatterns.Questions.LoggingFramework.Logger;
import org.example.DesignPatterns.Questions.LoggingFramework.enums.LogLevel;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LogMessage {
    private String content;
    private LogLevel logLevel;
    private LocalDateTime timestamp;
    private String threadName;
    private String loggerName;

    public LogMessage(String content, LogLevel logLevel, String loggerName) {
        this.content = content;
        this.logLevel = logLevel;
        this.timestamp = LocalDateTime.now();
        this.threadName = Thread.currentThread().getName();
        this.loggerName = loggerName;
    }

    public String getContent() {
        return content;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}
