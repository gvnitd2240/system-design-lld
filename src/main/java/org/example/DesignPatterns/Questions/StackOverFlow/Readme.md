# Designing Stack Overflow System

This project demonstrates the **Low Level Design (LLD)** of a simplified **Stack Overflow–like Question & Answer platform**.  
The system is designed using **object-oriented principles**, supports **concurrent access**, and models real-world features such as voting, tagging, reputation, and search.

---

## 📌 Requirements

### Functional Requirements
- Users can:
    - Post questions
    - Answer questions
    - Comment on questions and answers
- Users can **vote** on questions and answers (upvote / downvote).
- Questions can have **multiple tags**.
- Users can **search questions** based on:
    - Keywords
    - Tags
    - User profiles
- The system assigns a **reputation score** to users based on:
    - Votes received
    - Quality of contributions

### Non-Functional Requirements
- Handle **concurrent access** safely.
- Ensure **data consistency** during voting and posting.
- Follow **object-oriented design principles**.
- Be modular and easily extensible.

---

## 🧩 UML Class Diagram

The following UML diagram illustrates the relationships between core entities such as users, questions, answers, comments, votes, and tags.

<p align="center">
  <a href="https://github.com/ashishps1/awesome-low-level-design/blob/main/class-diagrams/stackoverflow-class-diagram.png" target="_blank">
    <img src="https://raw.githubusercontent.com/ashishps1/awesome-low-level-design/main/class-diagrams/stackoverflow-class-diagram.png" width="850"/>
  </a>
</p>

---

## 🏗️ Core Classes, Interfaces, and Enumerations

### User
Represents a registered user in the system.

**Key Properties**
- `id`
- `username`
- `email`
- `reputation`

**Responsibilities**
- Post questions and answers
- Comment on content
- Vote on questions and answers

---

### Question
Represents a question posted by a user.

**Key Properties**
- `id`
- `title`
- `content`
- `author`
- `answers`
- `comments`
- `tags`
- `votes`
- `creationDate`

---

### Answer
Represents an answer to a question.

**Key Properties**
- `id`
- `content`
- `author`
- `question`
- `comments`
- `votes`
- `creationDate`

---

### Comment
Represents a comment on a question or an answer.

**Key Properties**
- `id`
- `content`
- `author`
- `creationDate`

---

### Tag
Represents a tag associated with a question.

**Key Properties**
- `id`
- `name`

---

### Vote
Represents a vote on a question or an answer.

**Key Properties**
- `id`
- `voteType` (UPVOTE / DOWNVOTE)
- `voter`
- `creationDate`

---

### VoteType (Enum)
Defines the type of vote:
- `UPVOTE`
- `DOWNVOTE`

Each vote type maps to a numeric value used for scoring.

---

## 🧠 StackOverflow (Core Manager Class)

The `StackOverflow` class acts as the **central orchestrator** of the system.

### Responsibilities
- Create and manage users
- Post questions, answers, and comments
- Handle voting logic
- Update user reputation
- Search questions by:
    - Keywords
    - Tags
    - Users

---

## 🧪 Demo / Driver Class

### StackOverflowDemo
Demonstrates system usage by:
- Creating users
- Posting questions and answers
- Adding comments
- Voting on content
- Searching questions
- Retrieving questions by tags and users

This class helps simulate real-world workflows without user input handling.

---

## 🔒 Concurrency Handling

- Critical operations such as:
    - Posting content
    - Voting
    - Updating reputation  
      are synchronized to ensure **thread safety**.
- Prevents race conditions in concurrent environments.

---

## 🎯 Design Highlights

- Clean separation of concerns
- Extensible architecture
- Real-world feature modeling
- Interview-friendly LLD structure

---

## 🚀 Possible Extensions

- Badge system for users
- Accepted answers
- Pagination in search
- Distributed cache for hot questions
- Event-driven reputation updates

---

## 📎 Conclusion

This design models the core functionality of Stack Overflow while keeping the system modular, scalable, and easy to extend. It is suitable for **LLD interviews**, **system design practice**, and **portfolio projects**.
