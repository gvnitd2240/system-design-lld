package org.example.DesignPatterns.Questions.LoggingFramework.processor;

import org.example.DesignPatterns.Questions.LoggingFramework.entities.LogMessage;
import org.example.DesignPatterns.Questions.LoggingFramework.strategies.appender.LogAppender;

import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutionService implements AsyncLogProcessor{
    private final ThreadPoolExecutor executor;

    public ThreadPoolExecutionService(){
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 60L;

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(1000);

        RejectedExecutionHandler rejectionHandler = (task, exec) -> {
            // Fallback strategy when queue is full
            System.err.println("Log queue full. Dropping log message.");
        };

        ThreadFactory threadFactory = runnable -> {
            Thread thread = new Thread(runnable);
            thread.setName("async-log-worker");
            thread.setDaemon(false); // JVM can exit even if logger running
            return thread;
        };

        this.executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, queue, threadFactory, rejectionHandler);
    }



    @Override
    public void process(LogMessage logMessage, List<LogAppender> appenders) {
        if(executor.isShutdown()){
            System.err.println("Logger is shut down. Cannot process log message.");
            return;
        }

        executor.execute(()->{
            for (LogAppender appender : appenders) {
                try {
                    appender.append(logMessage);
                } catch (Exception e) {
                    System.err.println("Failed to write log: " + e.getMessage());
                }
            }
        });

    }

    @Override
    public void stop() {
        executor.shutdown();

        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("Logger executor did not terminate. Forcing shutdown...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

    }
}
