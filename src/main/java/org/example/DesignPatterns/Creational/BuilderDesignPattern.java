package org.example.DesignPatterns.Creational;

import java.util.Map;

/**
 * https://algomaster.io/learn/lld/builder
 * The Builder Design Pattern is a creational pattern
 * that lets you construct complex objects step-by-step,
 * separating the construction
 * logic from the final representation.
 *
 * introducing a separate builder class that handles the object creation proces.
 *
 *
 * */
public class BuilderDesignPattern {

    /**
     * The Naive Approach: Telescoping Constructors
     * constructor overloading often referred to as the
     * telescoping constructor anti-pattern.
     * */

    static class HttpRequestTelescoping{
        private String url;
        private String method;
        private Map<String, String> headers;
        private Map<String, String> queryParams;
        private String body;
        private int timeout;

        public HttpRequestTelescoping(String url){
            this(url,"GET",  null, null, null, 0);
        }

        public HttpRequestTelescoping(String url, String method) {
            this(url, method, null, null, null, 0);
        }

        public HttpRequestTelescoping(String url, String method, Map<String, String> headers) {
            this(url, method, headers, null, null, 0);
        }

        public HttpRequestTelescoping(String url, String method, Map<String, String> headers, Map<String, String> queryParams) {
            this(url, method, headers, queryParams, null, 0);
        }

        public HttpRequestTelescoping(String url, String method, Map<String, String> headers, Map<String, String> queryParams, String body) {
            this(url, method, headers, queryParams, body, 0);
        }

        public HttpRequestTelescoping(String url, String method, Map<String, String> headers, Map<String, String> queryParams, String body, int timeout) {
            this.url = url;
            this.method = method;
            this.headers = headers;
            this.queryParams = queryParams;
            this.body = body;
            this.timeout = timeout;

            System.out.println("HttpRequest Created: URL=" + url +
                    ", Method=" + method +
                    ", Headers=" + this.headers.size() +
                    ", Params=" + this.queryParams.size() +
                    ", Body=" + (body != null) +
                    ", Timeout=" + timeout);
        }

        public static void main(String[] args) {
            HttpRequestTelescoping req = new HttpRequestTelescoping(
                    "https://api.example.com/submit",
                    "POST",
                    null,
                    null,
                    "{\"key\":\"value\"}"
            );

            HttpRequestTelescoping req1 = new HttpRequestTelescoping(
                    "https://api.example.com/config",
                    "PUT",
                    Map.of("X-API-Key", "secret"),
                    null,
                    "config_data",
                    5000
            );

        }
    }


    /**
     * What We Need Instead?
     * We need a more flexible, readable, and maintainable
     * way to construct HttpRequest objects
     * especially when many optional values are involved
     * and different combinations are needed.
     *
     * The Builder pattern separates the construction
     * of a complex object from its representation.
     * */

    /**
     * The construction logic is encapsulated in a Builder.
     * The final object (the "Product") is created by
     * calling a build() method.
     * The object itself typically has a private
     * or package-private constructor,
     * forcing construction through the builder.
     * */

    /**
     * Builder - Defines methods to configure or set up the product.
     * ConcreteBuilder - Implements the Builder interface or defines the fluent methods directly.
     * Product (e.g., HttpRequest) - The final object being constructed. May be immutable and built only via the Builder. Has a private constructor that takes in the builder’s internal state.
     * Director (Optional) (e.g., HttpRequestDirector) - Orchestrates the building process using the builder.
     */

    static class HttpRequest {
        private String url;
        private String method;
        private Map<String, String> headers;
        private Map<String, String> queryParams;
        private String body;
        private int timeout;

        private HttpRequest(Builder builder) {
            this.url = builder.url;
            this.method = builder.method;
            this.headers = builder.headers;
            this.queryParams = builder.queryParams;
            this.body = builder.body;
            this.timeout = builder.timeout;
        }

        public String getUrl() {
            return url;
        }

        public String getMethod() {
            return method;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public Map<String, String> getQueryParams() {
            return queryParams;
        }

        public String getBody() {
            return body;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }

        public void setQueryParams(Map<String, String> queryParams) {
            this.queryParams = queryParams;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        @Override
        public String toString() {
            return "HttpRequest{" +
                    "url='" + url + '\'' +
                    ", method='" + method + '\'' +
                    ", headers=" + headers +
                    ", queryParams=" + queryParams +
                    ", body='" + body + '\'' +
                    ", timeout=" + timeout +
                    '}';
        }


        static class Builder {
            private final String url; //required.
            private String method;
            private Map<String, String> headers;
            private Map<String, String> queryParams;
            private String body;
            private int timeout;

            public Builder(String url) {
                this.url = url;
            }

            public Builder method(String method) {
                this.method = method;
                return this;
            }

            public Builder addHeader(String key, String value) {
                this.headers.put(key, value);
                return this;
            }

            public Builder addQueryParam(String key, String value) {
                this.queryParams.put(key, value);
                return this;
            }

            public Builder body(String body) {
                this.body = body;
                return this;
            }

            public Builder timeout(int timeout) {
                this.timeout = timeout;
                return this;
            }

            public HttpRequest build() {
                return new HttpRequest(this);
            }

        }
    }

    public static void main(String[] args) {
        HttpRequest request = new HttpRequest.Builder("https://api.example.com/data").build();

        HttpRequest request1 = new HttpRequest.Builder(
                "https://api.example.com/data"
        ).method("GET").addHeader("name", "vivek").
                build();

        System.out.println(request);
        System.out.println(request1);

        /**
         * What We Achieved
         *  No need for long constructors or null arguments.
         *  Optional values are clearly named and easy to set.
         *  The final object is immutable and fully initialized.
         * */
    }

}
