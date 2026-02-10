package org.example.DesignPatterns.Structural;

/**
 * https://algomaster.io/learn/lld/proxy
 * The Proxy Design Pattern is a structural pattern that provides a placeholder or
 * surrogate for another object, allowing you to control access to it.
 * ----
 * In real-world applications, you often work with resource-intensive,
 * remote, or sensitive components such as database connections,
 * third-party APIs, file systems, or large in-memory datasets.
 * ----
 * - Defer or control access to the actual implementation
 * - Add extra functionality (e.g., logging, authentication) without modifying existing code.
 * ----
 * A proxy sits between the client and the real object,
 * intercepting calls and optionally altering the behavior.
 *
 * */
public class ProxyDesignPattern {

    /**
     * 1. The Problem: Eager Loading
     * image gallery application
     *  Users can scroll through a list of image thumbnails,
     *  and when they click on one, the full high-resolution image is displayed.
     *
     * */

    static class ProxyDesignPatternNaive{
        interface Image{
            void display();
            String getFileName();
        }

        static class HighResImage implements Image{
            private String fileName;
            private byte[] imageData;

            public HighResImage(String fileName){
                this.fileName = fileName;
                loadImageFromDisk(); // Expensive operation!
            }

            private void loadImageFromDisk(){
                System.out.println("Loading image: " + fileName + " from disk (Expensive Operation)...");

                try{
                    Thread.sleep(2000); // Simulate disk I/O delay
                    this.imageData = new byte[10 * 1024 * 1024]; // Simulate 10MB memory usage
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Image " + fileName + " loaded successfully.");
            }

            @Override
            public void display() {
                System.out.println("Displaying image: " + fileName);
            }

            @Override
            public String getFileName() {
                return fileName;
            }
        }

        public static class ImageGalleryAppV1{
            public static void main(String[] args) {
                System.out.println("Application Started. Initializing images for gallery...");

                // Images are created eagerly – loaded even if not viewed!
                Image image1 = new HighResImage("photo1.jpg");
                Image image2 = new HighResImage("photo2.png");
                Image image3 = new HighResImage("photo3.gif");

                System.out.println("\nGallery initialized. User might view an image now.");

                System.out.println("User requests to display " + image1.getFileName());
                image1.display();

                System.out.println("\nUser requests to display " + image3.getFileName());
                image3.display();

                System.out.println("\nApplication finished.");

            }
        }

        /** What's Wrong With This Approach?
         1. Resource-Intensive Initialization
         2. No Control Over Access
             Log every time an image is actually displayed?
             Add permission checks before loading a sensitive image?
             Cache previously loaded images for reuse?
         * */
    }

    /** What We Really Need?
     * Defer the expensive loading of image data until it's actually needed.
     * Add extra behaviors like logging, access control, or caching without changing the existing HighResolutionImage class.
     * Maintain the same interface so that the client code doesn’t need to change.
     * */

    /**
     * The Proxy Design Pattern provides a stand-in or placeholder
     * for another object to control access to it.
     * Instead of the client interacting directly
     * with the “real” object (e.g., HighResolutionImage),
     * it interacts with a Proxy that implements the same interface.
     *
     * Proxy will manage:
     * 1.  lazy initialization,
     * 2.  access control
     * 3.  logging,
     * 4.  caching without changing the original class or the client code.
     *
     *
     *
     * 1. Subject (e.g., Image) - An interface or abstract class that defines the common operations shared by both the RealSubject and the Proxy.
     * 2. RealSubject (e.g., HighResolutionImage) -> Concrete implementation of Subject.
     * 3. Proxy (e.g., ImageProxy):
     *      3.1 Implements the same interface as the RealSubject (i.e., Image), allowing it to stand in seamlessly.
     *      3.2. Holds a reference to the real object and controls when and how it is created or accessed.
     *      3.3. Acts as a gatekeeper, delegating calls to the real object only when appropriate.
     *
     * 4. Client - Gallery App - Works with objects through the Subject interface.
     *
     *
     * Proxy's Different Form:
     *
     * 1. Virtual Proxy: Defers creation of the real object until it’s actually needed (lazy loading).
     * 2. Protection Proxy: Performs permission checks before allowing access to certain operations.
     * 3. Remote Proxy: Handles communication between local and remote objects over a network.
     * 4. Caching Proxy: Caches expensive results and avoids repeated calls to the real subject.
     * 5. Smart Proxy: Adds logging, reference counting, or monitoring before/after method calls.
     * */


    static class ProxyDesignPatternImpl {
        interface Image {
            void display(String userRole);

            String getFileName();
        }

        static class HighResImage implements Image {
            private String fileName;
            private byte[] imageData;

            public HighResImage(String fileName) {
                this.fileName = fileName;
                loadImageFromDisk(); // Expensive operation!
            }

            private void loadImageFromDisk() {
                System.out.println("Loading image: " + fileName + " from disk (Expensive Operation)...");

                try {
                    Thread.sleep(2000); // Simulate disk I/O delay
                    this.imageData = new byte[10 * 1024 * 1024]; // Simulate 10MB memory usage
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Image " + fileName + " loaded successfully.");
            }

            @Override
            public void display(String userRole) {
                System.out.println("Displaying image: " + fileName);
            }

            @Override
            public String getFileName() {
                return fileName;
            }
        }

        static class HighResImageProxy implements Image {
            private String fileName;
            private HighResImage realImage;

            public HighResImageProxy(String fileName) {
                this.fileName = fileName;
                System.out.println("ImageProxy: Created for " + fileName + ". Real image not loaded yet.");
            }

            @Override
            public void display(String userRole) {
                if (realImage == null) {
                    System.out.println("ImageProxy: display() requested for " + fileName + ". Loading high-resolution image...");
                    realImage = new HighResImage(fileName);
                } else {
                    System.out.println("ImageProxy: Using cached high-resolution image for " + fileName);
                }
                // Delegate the display call to the real image
                realImage.display(null);

            }

            @Override
            public String getFileName() {
                return fileName;
            }
        }

        static class ImageGalleryAppV2 {
            public static void main(String[] args) {
                System.out.println("Application Started. Initializing image proxies for gallery...");

                // Create lightweight proxies instead of full image objects
                Image image1 = new HighResImageProxy("photo1.jpg");
                Image image2 = new HighResImageProxy("photo2.png"); // Never displayed
                Image image3 = new HighResImageProxy("photo3.gif");


                System.out.println("\nGallery initialized. No images actually loaded yet.");
                System.out.println("Image 1 Filename: " + image1.getFileName()); // Does not trigger image load

                System.out.println("\nUser requests to display " + image1.getFileName());
                image1.display(null); // Lazy loading happens here

                System.out.println("\nUser requests to display " + image3.getFileName());
                image3.display(null); // Triggers loading for image3


                System.out.println("\nApplication finished. Note: photo2.png was never loaded.");
            }

        }

        /** What Did We Gain?
         * 1. Lazy Loading:
         * 2. Clean Interface:
         * 3. No Code Changes to Real Object: The original HighResolutionImage remains untouched.
         *      We didn’t have to modify it to support lazy loading.
         * 4.Reusability
         *
         *
         * Virtual Proxy — a proxy that stands in for a
         * costly object and defers its creation until
         * absolutely necessary.
         * */

        /** Protection Proxy – restricts access based on user roles or permissions.
         * Only the user with role ADMIN, will be able to see confidential images.
         */

        static class HighResImageProtectionProxy implements Image {
            private String fileName;
            private HighResImage realImage;

            public HighResImageProtectionProxy(String fileName) {
                this.fileName = fileName;
                System.out.println("ImageProxy: Created for " + fileName + ". Real image not loaded yet.");
            }

            private boolean checkAccess(String userRole){
                System.out.println("ProtectionProxy: Checking access for role: " + userRole + " on file: " + fileName);
                return "ADMIN".equals(userRole) || !fileName.contains("secret");
            }

            @Override
            public void display(String userRole) {
                if(!checkAccess(userRole)) {
                    System.out.println("ProtectionProxy: Access denied for " + fileName);
                    return;
                }
                if (realImage == null) {
                    System.out.println("ImageProxy: display() requested for " + fileName + ". Loading high-resolution image...");
                    realImage = new HighResImage(fileName);
                } else {
                    System.out.println("ImageProxy: Using cached high-resolution image for " + fileName);
                }
                // Delegate the display call to the real image
                realImage.display(userRole);

            }

            @Override
            public String getFileName() {
                return fileName;
            }
        }

        /** Logging Proxy: A Logging Proxy intercepts
         * method calls and logs them
         * before or after forwarding them to
         * the real object.
         * This is especially useful for auditing, debugging, or usage analytics.
         * */

        static class HighResImageLoggingProxy implements Image {
            private String fileName;
            private HighResImage realImage;

            public HighResImageLoggingProxy(String fileName) {
                this.fileName = fileName;
                System.out.println("ImageProxy: Created for " + fileName + ". Real image not loaded yet.");
            }
            @Override
            public void display(String userRole) {
                System.out.println("LoggingProxy: Attempting to display " + fileName + " at " + new java.util.Date());
                if (realImage == null) {
                    System.out.println("ImageProxy: display() requested for " + fileName + ". Loading high-resolution image...");
                    realImage = new HighResImage(fileName);
                } else {
                    System.out.println("ImageProxy: Using cached high-resolution image for " + fileName);
                }
                // Delegate the display call to the real image
                realImage.display(userRole);
                System.out.println("LoggingProxy: Finished displaying " + fileName + " at " + new java.util.Date());
            }

            @Override
            public String getFileName() {
                return fileName;
            }
        }
    }

    /**
     * Acts as a stand-in for an expensive object (Virtual Proxy)
     * Delays initialization until needed (Lazy loading)
     * Restricts access to sensitive content (Protection Proxy)
     * Logs image access and usage patterns (Logging Proxy)
     * */



}
