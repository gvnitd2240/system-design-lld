package org.example.DesignPatterns.Questions.LoggingFramework.strategies.appender;

import org.example.DesignPatterns.Questions.LoggingFramework.entities.LogMessage;
import org.example.DesignPatterns.Questions.LoggingFramework.strategies.formatter.SimpleTextFormatter;
import org.example.DesignPatterns.Questions.LoggingFramework.strategies.formatter.LogFormatter;

public class ConsoleAppender implements LogAppender{
    private LogFormatter logFormatter;
    public ConsoleAppender() {
        this.logFormatter = new SimpleTextFormatter();
    }


    @Override
    public void setFormatter(LogFormatter formatter) {
        this.logFormatter = formatter;
    }

    @Override
    public LogFormatter getFormatter() {
        return logFormatter;
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println("ConsoleAppender" + logMessage);
        System.out.println(logFormatter.format(logMessage));
    }

    @Override
    public void close() {

    }
}
