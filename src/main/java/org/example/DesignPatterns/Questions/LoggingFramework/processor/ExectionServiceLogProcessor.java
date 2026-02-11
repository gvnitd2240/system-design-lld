package org.example.DesignPatterns.Questions.LoggingFramework.processor;

import org.example.DesignPatterns.Questions.LoggingFramework.entities.LogMessage;
import org.example.DesignPatterns.Questions.LoggingFramework.strategies.appender.LogAppender;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExectionServiceLogProcessor implements AsyncLogProcessor{
    private final ExecutorService executorService;

    public ExectionServiceLogProcessor(){
        this.executorService = Executors.newSingleThreadExecutor(runnable ->{
            Thread thread = new Thread(runnable, "AsyncLogProcessor");
            thread.setDaemon(false); // Don't prevent JVM exit
            return thread;
        });
    }

    @Override
    public void process(LogMessage logMessage, List<LogAppender> appenders) {
        if (executorService.isShutdown()) {
            System.err.println("Logger is shut down. Cannot process log message.");
            return;
        }

        executorService.submit(()->{
            for(LogAppender logAppender: appenders){
                logAppender.append(logMessage);
            }
        });

    }

    @Override
    public void stop(){
        // Disable new tasks from being submitted

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                System.err.println("Logger executor did not terminate in the specified time.");
                // Forcibly shut down any still-running tasks.
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

}
