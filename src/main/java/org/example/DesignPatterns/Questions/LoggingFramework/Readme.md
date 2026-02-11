Designing Logging Framework

This project demonstrates the Low Level Design (LLD) of a scalable and extensible Logging Framework.
The system is designed using object-oriented principles, supports multiple output destinations, and ensures thread-safe logging across concurrent environments.

The framework allows configurable log levels, flexible output handling, and easy extensibility for future enhancements.

📌 Requirements
Functional Requirements

Support multiple log levels:

DEBUG

INFO

WARNING

ERROR

FATAL

Each log entry should contain:

Timestamp

Log level

Message content

Support multiple output destinations:

Console

File

Database

Allow configuration of:

Minimum log level threshold

Output destination

Provide convenient methods for logging at each level.

Non-Functional Requirements

Ensure thread-safe logging in concurrent environments.

Follow object-oriented design principles.

Support runtime configuration changes.

Be extensible to support new log levels and appenders.

Maintain high performance with minimal logging overhead.

🧩 UML Class Diagram

The following UML diagram illustrates the structure of the logging framework, including the logger, configuration, appenders, and message representation.

<p align="center"> <a href="https://github.com/ashishps1/awesome-low-level-design/blob/main/class-diagrams/loggingframework-class-diagram.png" target="_blank"> <img src="https://raw.githubusercontent.com/ashishps1/awesome-low-level-design/main/class-diagrams/loggingframework-class-diagram.png" width="850"/> </a> </p>
🏗️ Core Classes, Interfaces, and Enumerations
LogLevel (Enum)

Defines supported log severity levels.

Values

DEBUG

INFO

WARNING

ERROR

FATAL

Used to filter messages based on configured threshold.

LogMessage

Represents a single log entry.

Key Properties

timestamp

logLevel

message

Encapsulates all information required to log an event.

LogAppender (Interface)

Defines how log messages are written to an output destination.

Method

append(LogMessage message)

Allows adding new output destinations without modifying existing logger logic.

ConsoleAppender

Concrete implementation of LogAppender that writes logs to the system console.

FileAppender

Concrete implementation of LogAppender that writes logs to a file for persistent storage.

DatabaseAppender

Concrete implementation of LogAppender that stores logs in a database for querying and monitoring.

LoggerConfig

Stores logging configuration.

Key Properties

Minimum log level

Selected log appender

Allows runtime changes in logging behavior.

🧠 Logger (Core Manager Class)

The Logger class acts as the central logging engine of the system.

Responsibilities

Maintain global logging configuration

Filter messages based on log level threshold

Forward messages to configured appender

Provide logging APIs:

debug()

info()

warning()

error()

fatal()

Ensure thread-safe logging

Provide global access using Singleton pattern

🧪 Demo / Driver Class
LoggingExample

Demonstrates system usage by:

Logging messages at different levels

Changing logger configuration at runtime

Switching output destinations

Logging concurrently from multiple threads

This class simulates real-world logging scenarios.

🔒 Concurrency Handling

Logger is implemented as a thread-safe Singleton.

Logging operations are synchronized to prevent race conditions.

Multiple threads can safely write logs simultaneously.

Ensures consistency of log messages across destinations.

🎯 Design Highlights

Strategy pattern for pluggable appenders

Singleton logger instance

Configurable log filtering

Thread-safe operations

Extensible architecture

Separation of concerns

Interview-friendly LLD structure

🚀 Possible Extensions

Asynchronous logging using queues

Log file rotation and archiving

Structured logging (JSON format)

Remote log streaming

Distributed logging support

Log format customization

Multiple appenders simultaneously

Log batching for performance

📎 Conclusion

This design models a production-ready logging framework that is modular, scalable, and extensible. It demonstrates clean object-oriented architecture and concurrency-safe logging, making it ideal for LLD interviews, system design practice, and real-world applications.