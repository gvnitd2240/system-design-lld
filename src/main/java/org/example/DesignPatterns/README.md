# Design Patterns

> **“Design patterns are the best practices you didn’t know you needed until you hit the same design problem for the third time.”**

Design patterns are **reusable solutions to commonly occurring problems** in software design.  
They represent **proven approaches**, not finished code, that help you structure your application in a clean and maintainable way.

Think of them as **blueprints** you can adapt to your own system.

---

## 🪑 Real-World Analogy: Furniture Assembly

Imagine assembling furniture from IKEA:

- You don’t invent a new way to connect every screw or panel
- You follow a **repeatable set of instructions**
- The result is **predictable, reliable, and efficient**

Design patterns work the same way in software — they give you a **tested plan** to reduce uncertainty and mistakes.

---

## 🤔 Why Use Design Patterns?

### 🔁 Reusability
Design patterns allow you to **reuse proven design ideas** instead of reinventing solutions every time.

- Single instance needed → **Singleton**
- Swappable behavior → **Strategy**

---

### 🔧 Maintainability
Patterns enforce separation of concerns and modularity, making systems:
- Easier to change
- Easier to test
- Easier to debug

---

### 📖 Readability
Patterns provide a **shared vocabulary**.  
Developers immediately understand intent when they see familiar pattern names.

---

### ⚙️ Flexibility
Patterns help systems adapt to change with minimal disruption by favoring abstraction and loose coupling.

---

## 📚 Categories of Design Patterns

---

## 🏗️ Creational Patterns
> **Concerned with object creation mechanisms**

Creational patterns abstract the instantiation process and help make systems independent of how objects are created.

### 🔹 Singleton
Ensures that a class has **only one instance** and provides a global access point.

**Use case:** Configuration manager, logger, database connection.

---

### 🔹 Factory Method
Defines an interface for creating an object, but lets subclasses decide which class to instantiate.

**Use case:** Creating different notification types (Email, SMS, Push).

---

### 🔹 Abstract Factory
Provides an interface to create **families of related objects** without specifying their concrete classes.

**Use case:** UI components for different platforms (Windows, Mac).

---

### 🔹 Builder
Separates the construction of a complex object from its representation.

**Use case:** Building complex objects step-by-step (House, Meal, HTTP request).

---

### 🔹 Prototype
Creates new objects by cloning existing ones instead of creating from scratch.

**Use case:** Object copying when creation is expensive.

---

## 🧱 Structural Patterns
> **Concerned with object composition**

Structural patterns focus on how classes and objects are composed to form larger structures.

### 🔹 Adapter
Allows incompatible interfaces to work together.

**Use case:** Integrating a legacy system with a new API.

---

### 🔹 Decorator
Adds new behavior to an object **dynamically** without modifying its structure.

**Use case:** Adding logging, caching, or encryption.

---

### 🔹 Facade
Provides a simplified interface to a complex subsystem.

**Use case:** One API to interact with multiple backend services.

---

### 🔹 Composite
Treats individual objects and compositions of objects uniformly.

**Use case:** File system (files and folders).

---

### 🔹 Proxy
Acts as a placeholder or controller for another object.

**Use case:** Lazy loading, access control, caching.

---

## 🧠 Behavioral Patterns
> **Concerned with communication and responsibility between objects**

Behavioral patterns define how objects interact and communicate.

### 🔹 Strategy
Defines a family of algorithms and allows them to be interchangeable at runtime.

**Use case:** Payment methods, sorting strategies.

---

### 🔹 Observer
Defines a one-to-many dependency where observers are notified of state changes.

**Use case:** Event systems, notification services.

---

### 🔹 Command
Encapsulates a request as an object.

**Use case:** Undo/redo functionality, task queues.

---

### 🔹 State
Allows an object to change its behavior when its internal state changes.

**Use case:** Order lifecycle, vending machine.

---

### 🔹 Template Method
Defines the skeleton of an algorithm in a base class while allowing subclasses to override steps.

**Use case:** Report generation, workflow processing.

---

## 🔗 Learning Resource

This repository follows concepts explained clearly in:

👉 **https://algomaster.io/learn/lld/design-patterns**

A highly recommended resource for:
- Low-Level Design
- Interview preparation
- Real-world design thinking

---

## 🚀 Goal of This Repository

- Explain patterns in **simple language**
- Use **real-world analogies**
- Provide **clean implementations**
- Focus on **when and why** to use each pattern

---

Happy coding & designing! 🎯  
