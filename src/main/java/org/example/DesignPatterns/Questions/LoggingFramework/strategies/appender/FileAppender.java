package org.example.DesignPatterns.Questions.LoggingFramework.strategies.appender;

import org.example.DesignPatterns.Questions.LoggingFramework.entities.LogMessage;
import org.example.DesignPatterns.Questions.LoggingFramework.strategies.formatter.SimpleTextFormatter;
import org.example.DesignPatterns.Questions.LoggingFramework.strategies.formatter.LogFormatter;

import java.io.FileWriter;
import java.io.IOException;


public class FileAppender implements LogAppender {
    private LogFormatter logFormatter;
    private FileWriter fileWriter;

    public FileAppender(String filePath){
        this.logFormatter = new SimpleTextFormatter();
        try{
            this.fileWriter = new FileWriter(filePath, true);
        } catch (IOException e) {
            System.out.println("Failed to create writer for file logs, exception: " + e.getMessage());
        }
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
        try{
            fileWriter.write(logFormatter.format(logMessage) +  "\n");
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Failed to write logs to file, exception: " + e.getMessage());
        }

    }

    @Override
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to close logs file, exception: " + e.getMessage());
        }
    }
}
