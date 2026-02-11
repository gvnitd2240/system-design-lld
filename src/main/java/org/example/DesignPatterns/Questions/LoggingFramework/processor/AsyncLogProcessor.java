package org.example.DesignPatterns.Questions.LoggingFramework.processor;

import org.example.DesignPatterns.Questions.LoggingFramework.entities.LogMessage;
import org.example.DesignPatterns.Questions.LoggingFramework.strategies.appender.LogAppender;

import java.util.List;

public interface AsyncLogProcessor {
    public void process(LogMessage logMessage, List<LogAppender> appenders);

    public void stop();
}
