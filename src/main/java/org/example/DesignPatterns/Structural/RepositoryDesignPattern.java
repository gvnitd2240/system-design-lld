package org.example.DesignPatterns.Structural;

import java.util.List;

/**
 * https://algomaster.io/learn/lld/repository
 * The Repository Pattern is a structural design pattern that provides a
 * centralized abstraction layer for data access.
 *
 * Instead of services querying the database directly,
 * they use a repository interface that defines CRUD and query operations.
 *
 * 1. Separation of Concerns
 * 2. Testability
 * 3. Flexibility
 *
 * Service → Repository Interface → Repository Implementation → Database / API
 * */
public class RepositoryDesignPattern {

    static class User{
        String id;
        String name;
        String email;

        public User(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    public interface UserRepo{
        User findById(String id);
        List<User> findAll();
        void save(User user);
        void deleteById(String id);
    }

    static class UserRepoImpl implements UserRepo{

        List<User> users = List.of(
            new User(
                    "1","VIVEK","vgarg7900@gmail.com"
            )
        );

        @Override
        public User findById(String id) {
            return new User(
                    "1","VIVEK","vgarg7900@gmail.com"
            );
        }

        @Override
        public List<User> findAll() {
            return users;
        }

        @Override
        public void save(User user) {
            System.out.println("User saved" + user.toString());
        }

        @Override
        public void deleteById(String id) {
            System.out.println("User saved" + id);
        }
    }

    static class UserService {
        private final UserRepo userRepo;


        UserService(UserRepo userRepo) {
            this.userRepo = userRepo;
        }

        public User findById(String id){
            return userRepo.findById(id);
        }
    }

    /**
     * The Repository Pattern keeps your business logic clean,
     * abstracted, and test-ready by providing a structured
     * interface to data operations
     * */

}
