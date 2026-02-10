package org.example.DesignPatterns.Structural;

/**
 * https://algomaster.io/learn/lld/facade
 * The Facade Design Pattern is a structural design pattern
 * that provides a unified, simplified interface to a complex
 * subsystem making it easier for clients
 * to interact with multiple
 * components without getting overwhelmed by their intricacies.
 *  ----
 *  1. Your system contains many interdependent classes or low-level APIs.
 *  2. The client doesn’t need to know how those parts work internally.
 *  3. You want to reduce the learning curve or coupling between clients and complex systems.
 * */
public class FacadeDesignPattern {

    /**
     * The Problem: Deployment Complexity
     * Let's say you're building a deployment automation tool for your development team.
     * On the surface, deploying an application may seem like a straightforward task, but in reality,
     * it involves a sequence of coordinated, error-prone steps.
     *
     * 1. Pull the latest code from a Git repository
     * 2. Build the project using a tool like Maven or Gradle
     * 3. Run automated tests (unit, integration, maybe end-to-end)
     * 4. Deploy the build artifact to a production environment
     * */

    static class VersionControlSystem {
        public void pullLatestChanges(String branch) {
            System.out.println("VCS: Pulling latest changes from '" + branch + "'...");
            simulateDelay();
            System.out.println("VCS: Pull complete.");
        }

        private void simulateDelay() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class BuildSystem {
        public boolean compileProject() {
            System.out.println("BuildSystem: Compiling project...");
            simulateDelay(2000);
            System.out.println("BuildSystem: Build successful.");
            return true;
        }

        public String getArtifactPath() {
            String path = "target/myapplication-1.0.jar";
            System.out.println("BuildSystem: Artifact located at " + path);
            return path;
        }

        private void simulateDelay(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** 3. Testing Framework **/
    static class TestingFramework {
        public boolean runUnitTests() {
            System.out.println("Testing: Running unit tests...");
            simulateDelay(1500);
            System.out.println("Testing: Unit tests passed.");
            return true;
        }

        public boolean runIntegrationTests() {
            System.out.println("Testing: Running integration tests...");
            simulateDelay(3000);
            System.out.println("Testing: Integration tests passed.");
            return true;
        }

        private void simulateDelay(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class DeploymentTarget {
        public void transferArtifact(String artifactPath, String server) {
            System.out.println("Deployment: Transferring " + artifactPath + " to " + server + "...");
            simulateDelay(1000);
            System.out.println("Deployment: Transfer complete.");
        }

        public void activateNewVersion(String server) {
            System.out.println("Deployment: Activating new version on " + server + "...");
            simulateDelay(500);
            System.out.println("Deployment: Now live on " + server + "!");
        }

        private void simulateDelay(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class FacadeDesignPatternNaiveApproach {

        public static class DeploymentClient{
            public static void main(String[] args) {
                String branch = "main";
                String prodServer = "prod.server.example.com";

                VersionControlSystem vcs = new VersionControlSystem();
                BuildSystem buildSystem = new BuildSystem();
                TestingFramework testFramework = new TestingFramework();
                DeploymentTarget deployTarget = new DeploymentTarget();

                System.out.println("\n[Client] Starting deployment for branch: " + branch);

                vcs.pullLatestChanges(branch);

                if(!buildSystem.compileProject()){
                    System.err.println("[Client] Build failed. Deployment aborted.");
                    return;
                }

                String artifact = buildSystem.getArtifactPath();

                if(!testFramework.runUnitTests()){
                    System.err.println("[Client] Unit tests failed. Deployment aborted.");
                    return;
                }

                if (!testFramework.runIntegrationTests()) {
                    System.err.println("[Client] Integration tests failed. Deployment aborted.");
                    return;
                }

                // Deployment;
                deployTarget.transferArtifact(artifact, prodServer);
                deployTarget.activateNewVersion(prodServer);

                System.out.println("[Client] Deployment successful!");

            }
        }

        /**
         * What’s Wrong with This Design?
         * 1. High Client Complexity -
             The client must be aware of every subsystem:

             What classes to instantiate
             What methods to call
             In what sequence
             What to do on success or failure

         * 2. Tight Coupling Between Subsystems
                The client directly depends on VersionControlSystem, BuildSystem, TestingFramework, and DeploymentTarget.
         * 3. Poor Maintainability
                 Want to:

                 Add a code quality scan before deployment?
                 Send Slack notifications after deployment?
                 Integrate a rollback mechanism?
                 Add caching for faster builds?

         * 4. 4. Scattered Workflow Logic
         * 5. Difficult Testing
         * */
    }

    /**
     * What We Need
     * Hide the complexity of the underlying subsystems
     * Expose a simple and unified interface to perform deployments
     * Decouple the client code from the internal workflow
     * Make the system easier to maintain, test, and evolve
     * */

    /**
     * The Facade Pattern introduces a high-level interface that hides
     * the complexities of one or more
     * subsystems and exposes only the functionality needed by the client.
     *
     * Facade (e.g., DeploymentFacade)
     * Subsystem Classes (e.g., VersionControlSystem, BuildSystem)
     * Client (e.g., our main application or a script)
     */

    static  class FacadeDesignPatternImpl{
        static class DeploymentFacade {
            private VersionControlSystem vcs = new VersionControlSystem();
            private BuildSystem buildSystem = new BuildSystem();
            private TestingFramework testingFramework = new TestingFramework();
            private DeploymentTarget deploymentTarget = new DeploymentTarget();

            public boolean deployApplication(String branch, String serverAddress) {
                System.out.println("\nFACADE: --- Initiating FULL DEPLOYMENT for branch: " + branch + " to " + serverAddress + " ---");
                boolean success = true;

                try {
                    vcs.pullLatestChanges(branch);

                    if (!buildSystem.compileProject()) {
                        System.err.println("FACADE: DEPLOYMENT FAILED - Build compilation failed.");
                        return false;
                    }

                    String artifactPath = buildSystem.getArtifactPath();

                    if (!testingFramework.runUnitTests()) {
                        System.err.println("FACADE: DEPLOYMENT FAILED - Unit tests failed.");
                        return false;
                    }

                    if (!testingFramework.runIntegrationTests()) {
                        System.err.println("FACADE: DEPLOYMENT FAILED - Integration tests failed.");
                        return false;
                    }

                    deploymentTarget.transferArtifact(artifactPath, serverAddress);
                    deploymentTarget.activateNewVersion(serverAddress);

                    System.out.println("FACADE: APPLICATION DEPLOYED SUCCESSFULLY to " + serverAddress + "!");

                } catch (Exception e){
                    System.err.println("FACADE: DEPLOYMENT FAILED - An unexpected error occurred: " + e.getMessage());
                    e.printStackTrace();
                    success = false;
                }

                return success;
            }

            public boolean deployHotfix(String branch, String serverAddress){
                // Deploy with expedited testing
                return true;
            }

            static class DeploymentAppFacade{
                public static void main(String[] args) {
                    DeploymentFacade deploymentFacade = new DeploymentFacade();

                    deploymentFacade.deployApplication("main", "prod.server.example.com");

                    System.out.println("\n--- Deploying feature branch to staging ---");
                    deploymentFacade.deployApplication("feature/new-ui", "staging.server.example.com");
                }
            }

            /**
             * DeploymentFacade ->
             * Knows which subsystem classes to use and in what order.
             * Delegates requests to appropriate subsystem
             * methods without exposing internal details to the client.
             *
             * It will have subsystems. It will know in which order it need to trigger different
             * subsystems.
             *
             * Benefits:
             * 1. Client no longer needs to worry about the internal complexity of internal
             * subsystems.
             * 2. Client is no longer tightly coupled to sub systems.
             * */

        }
    }
}
