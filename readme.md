# java design pattern demos

This is a study note for the course [Design Patterns in Java](https://www.udemy.com/course/design-patterns-java)


## Types of Deisgn Patterns

### Creational Deisgn Patterns

- Builder
- Factories
  - Abstract Factory
  - Factory Method
- Prototype
- Singleton

### Structural Design Patterns

- Adapter
- Bridge
- Composite
- Decorator
- Facade
- Flyweight
- Proxy

### Behavior Design Patterns

- Chain of Responsibility
- Command
- Interpreter
- Iterator
- Mediator
- Memenro
- Null Object
- State
- Strategy
- Template Method
- Visitor

## Solid Design Principles

### 01. Single Responsibility Principle(SRP)

- A class should have just a single reason to change
- One primary responsibility

### 02. Open Closed Principle (OCP)
- Open for extension
- Close for modification

class should not be written for aggregating a new feature

### 03. Liskov Substitution Principle (LSP)
You should be able to substitute a subclass for a base class

if you have some API, which takes a base class, you should be able to stick a subclass in there without things break in anyway

> a superclass should be replaceable with objects of its subclasses without breaking the application.

(https://blog.knoldus.com/what-is-liskov-substitution-principle-lsp-with-real-world-examples/#:~:text=Simply%20put%2C%20the%20Liskov%20Substitution,the%20objects%20of%20our%20superclass.)

### 04. Interface Segregation Principle (ISP)
>  Segregation: the action or state of setting someone or something apart from others

a recommendation on how to split interfaces into smaller interfaces

put absolute minimum code in an interface
 
