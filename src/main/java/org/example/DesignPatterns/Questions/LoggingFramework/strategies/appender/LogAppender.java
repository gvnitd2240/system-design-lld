package org.example.DesignPatterns.Questions.LoggingFramework.strategies.appender;

import org.example.DesignPatterns.Questions.LoggingFramework.entities.LogMessage;
import org.example.DesignPatterns.Questions.LoggingFramework.strategies.formatter.LogFormatter;

public interface LogAppender {
    public void setFormatter(LogFormatter formatter);
    public LogFormatter getFormatter();
    public void append(LogMessage logMessage);
    public void close();
}
