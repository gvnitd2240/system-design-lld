package org.example.DesignPatterns.Questions.LoggingFramework.strategies.formatter;

import org.example.DesignPatterns.Questions.LoggingFramework.entities.LogMessage;

public interface LogFormatter {
    public String format(LogMessage logMessage);
}
