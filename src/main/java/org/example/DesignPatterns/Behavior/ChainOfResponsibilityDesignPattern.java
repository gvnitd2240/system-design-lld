package org.example.DesignPatterns.Behavior;

/**
 * https://algomaster.io/learn/lld/chain-of-responsibility
 *
 * The Chain of Responsibility Design Pattern is a behavioral
 * pattern that lets you pass requests along a chain of handlers,
 * allowing each handler to decide whether to process the request
 * or pass it to the next handler in the chain.
 *
 * A request must be handled by one of many possible handlers,
 * and you don’t want
 * the sender to be tightly coupled to any specific one.
 *
 * You want to decouple request logic from the code that processes it.
 *
 * You want to flexibly add, remove, or reorder
 * handlers without changing the client code.
 *
 * The Chain of Responsibility Pattern solves this by turning individual
 * processing steps into standalone classes, each responsible for
 * one specific concern. These handlers are linked together to form a
 * chain, and the
 * request flows through the chain until it is handled (or dropped).
 *
 * */
public class ChainOfResponsibilityDesignPattern {
    /**
     * 1. The Problem: Handling HTTP Requests
     *
     * Authentication: Is the user properly authenticated via a token or session?
     * Authorization: Is the authenticated user allowed to perform this action?
     * Rate Limiting: Has the user exceeded their allowed number of requests?
     * Data Validation: Is the request payload well-formed and valid?
     * */

    static class Request {
        public String user;
        public String userRole;
        public int requestCount;
        public String payload;

        public Request(String user, String role, int requestCount, String payload) {
            this.user = user;
            this.userRole = role;
            this.requestCount = requestCount;
            this.payload = payload;
        }

        public String getUser() {
            return user;
        }

        public String getUserRole() {
            return userRole;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public String getPayload() {
            return payload;
        }
    }

    static class ChainOfResponsibilityDesignPatternNaive{
        static class RequestHandler {

            void handle (Request request){
                if(!authenticate(request)){
                    System.out.println("Request Rejected: Authentication failed.");
                    return;
                }

                if(!authorization(request)){
                    System.out.println("Request Rejected: Authorization failed.");
                    return;
                }

                if(!rateLimit(request)){
                    System.out.println("Request Rejected: Invalid payload.");
                    return;
                }

                if (!validate(request)) {
                    System.out.println("Request Rejected: Invalid payload.");
                    return;
                }

                System.out.println("Request passed all checks. Executing business logic...");
                // business logic.
            }

            private boolean authenticate(Request request) {
                return request.user != null;
            }

            private boolean authorization(Request request){
                return "ADMIN".equals(request.userRole);
            }

            private boolean rateLimit(Request req){
                return req.requestCount < 100;
            }

            private boolean validate(Request request){
                return request.payload != null && !request.payload.isEmpty();
            }
        }

        /**
         * Why This Approach Breaks Down?
         * 1. Violates the Open/Closed Principle
         * 2. Poor Separation of Concerns
         * 3. No Reusability
         * 4. Inflexible Configuration
         * */
    }

    /**
     * What We Really Need?
     * We need a way to:
     *
     * Break each processing step into its own isolated unit
     * Let each step decide whether to continue, pass, or stop the chain
     * Allow new handlers to be added, removed, or reordered without touching existing code
     * Keep our logic clean, testable, and extensible.
     * */

    /**
     * Chain of Responsibility Pattern - The Chain of Responsibility Pattern allows a request to be passed along a chain of handlers.
     * Each handler in the chain can either:
     * Handle the request and stop the chain
     * Pass it to the next handler in the chain
     * Handle the request and then pass it along
     *
     *
     * This pattern decouples the sender of the request from
     * the receivers, giving you the flexibility to compose chains
     * dynamically,
     * reuse logic, and avoid rigid conditional blocks.
     * */

    /**
     * Components:
     *
     * 1. Handler Interface (Abstract Base Class / Interface) -> handle(request), Holds a reference to the next handler
     * 2. ConcreteHandlers (e.g., AuthHandler, RateLimitHandler)
     *    Handle the request (e.g., reject, log, transform), or
     *    Pass the request along to the next handler in the chain.
     * 3. Client
     * Builds and connects the chain of handlers using setNext().
     * Sends the request to the first handler in the chain.
     * Is unaware of which handler will ultimately process the request.
     *
     *
     * */

    static class ChainOfResponsibilityDesignPatternImpl{
        interface RequestHandler{
            void setNext(RequestHandler handler);
            void handleRequest(Request request);
        }

        abstract static class BaseHandler implements RequestHandler {
            protected RequestHandler next;

            @Override
            public void setNext(RequestHandler handler) {
                this.next = handler;
            }

            protected void forward(Request request){
                if(next != null){
                    next.handleRequest(request);
                }
            }
        }

        static class AuthenticationHandler  extends BaseHandler{
            @Override
            public void handleRequest(Request request) {
                if(request.user == null){
                    System.out.println("Request Rejected: Authentication failed.");
                    return;
                }

                System.out.println("Authentication passed.");
                forward(request);

            }
        }

        static class AuthorizationHandler  extends BaseHandler{
            @Override
            public void handleRequest(Request request) {
                if(!"ADMIN".equals(request.userRole)){
                    System.out.println("Request Rejected: AuthorizationHandler failed.");
                    return;
                }

                System.out.println("AuthorizationHandler: ✅ Authorized.");
                forward(request);
            }
        }

        static class RateLimitHandler  extends BaseHandler{
            @Override
            public void handleRequest(Request request) {
                if(request.requestCount > 100){
                    System.out.println("Request Rejected: RateLimitHandler failed.");
                    return;
                }

                System.out.println("RateLimitHandler: ✅ Passed.");
                forward(request);
            }
        }


        static class ValidationHandler  extends BaseHandler{
            @Override
            public void handleRequest(Request request) {
                if(request.payload == null && request.payload.isEmpty()){
                    System.out.println("Request Rejected: ValidationHandler failed.");
                    return;
                }

                System.out.println("ValidationHandler: ✅ Passed.");
                forward(request);
            }
        }


        static class LoggingHandler  extends BaseHandler{
            @Override
            public void handleRequest(Request request) {
                System.out.println(request);
                System.out.println("LoggingHandler: ✅ Passed.");
                forward(request);
            }
        }

        static class BusinessLogicHandler extends BaseHandler {
            @Override
            public void handleRequest(Request request) {
                System.out.println("BusinessLogicHandler: 🚀 Processing request...");
            }
        }

        /** What We Achieved?
         * 1. Modularity: Each handler is isolated and easy to test
         * 2. Loose Coupling: Handlers don’t need to know who comes next
         * 3. Extensibility: Easily insert, remove, or reorder handlers
         * 4. Clean Client Code: Only responsible for building the chain and sending the request
         * 5.Open/Closed Compliant: You can add new functionality (e.g., LoggingHandler) without touching existing code
         * */
    }

    public static void main(String[] args) {
        Request req = new Request("john_doe", "ADMIN", 42, "{ 'data': 123 }");
        ChainOfResponsibilityDesignPatternNaive.RequestHandler handler = new ChainOfResponsibilityDesignPatternNaive.RequestHandler();
        handler.handle(req);

        ChainOfResponsibilityDesignPatternImpl.RequestHandler auth = new ChainOfResponsibilityDesignPatternImpl.AuthorizationHandler();
        ChainOfResponsibilityDesignPatternImpl.AuthorizationHandler authorizationHandler = new ChainOfResponsibilityDesignPatternImpl.AuthorizationHandler();
        ChainOfResponsibilityDesignPatternImpl.RateLimitHandler rateLimitHandler = new ChainOfResponsibilityDesignPatternImpl.RateLimitHandler();
        ChainOfResponsibilityDesignPatternImpl.ValidationHandler validationHandler = new ChainOfResponsibilityDesignPatternImpl.ValidationHandler();
        ChainOfResponsibilityDesignPatternImpl.LoggingHandler loggingHandler = new ChainOfResponsibilityDesignPatternImpl.LoggingHandler();
        ChainOfResponsibilityDesignPatternImpl.BusinessLogicHandler businessLogicHandler = new ChainOfResponsibilityDesignPatternImpl.BusinessLogicHandler();

        auth.setNext(authorizationHandler);
        authorizationHandler.setNext(rateLimitHandler);
        rateLimitHandler.setNext(validationHandler);
        validationHandler.setNext(loggingHandler);
        loggingHandler.setNext(businessLogicHandler);


        auth.handleRequest(req);

        System.out.println("\n--- Trying an invalid request ---");
        Request badRequest = new Request(null, "USER", 150, "");
        auth.handleRequest(badRequest);
    }


}
