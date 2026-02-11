package org.example.DesignPatterns.Questions.LoggingFramework;

import org.example.DesignPatterns.Questions.LoggingFramework.processor.AsyncLogProcessor;
import org.example.DesignPatterns.Questions.LoggingFramework.strategies.appender.LogAppender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {
    private static LogManager instance;
    private Logger rootLogger;
    private Map<String, Logger> loggers = new ConcurrentHashMap<>();;
    private AsyncLogProcessor logProcessor;

    private LogManager(){
        this.rootLogger = new Logger("root", null);
        this.loggers.put("root", rootLogger);
    }

    public void setLogProcessor(AsyncLogProcessor logProcessor) {
        this.logProcessor = logProcessor;
    }

    public static LogManager getInstance(){
        if(instance == null){
            synchronized (LogManager.class){
                if(instance == null){
                    instance = new LogManager();
                }
            }
        }

        return instance;
    }

    public Logger getRootLogger() {
        return rootLogger;
    }

    public Map<String, Logger> getLoggers() {
        return loggers;
    }

    public AsyncLogProcessor getLogProcessor() {
        return logProcessor;
    }

    public Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, this::createLogger);
    }

    public Logger createLogger(String name){
        if(name.equals("root")){
            return rootLogger;
        }

        int lastDot = name.lastIndexOf('.');
        String parentName = (lastDot == -1) ? "root" : name.substring(0, lastDot);
        Logger parent = getLogger(parentName);
        return new Logger(name, parent);
    }

    public void shutdown() {
        // Stop the processor first to ensure all logs are written.
        logProcessor.stop();

        // Then, close all appenders.
        loggers.values().stream()
                .flatMap(logger -> logger.getAppenders().stream())
                .distinct()
                .forEach(LogAppender::close);
        System.out.println("Logging framework shut down gracefully.");
    }
}
