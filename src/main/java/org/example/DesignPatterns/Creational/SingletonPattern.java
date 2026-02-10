package org.example.DesignPatterns.Creational;


/**
 * https://algomaster.io/learn/lld/singleton - Singleton Pattern is a creational design pattern that
 * guarantees a class has only
 * one instance and provides a global point of access to it.
* */
public class SingletonPattern {

    static class LazySingleton{
        public static LazySingleton instance;

        private LazySingleton(){};

        public static LazySingleton getInstance(){
            if(instance== null) instance = new LazySingleton();
            return instance;
        }
    }


    static class ThreadSafeSingleton{
        public static ThreadSafeSingleton instance;

        private ThreadSafeSingleton(){};

        public static synchronized  ThreadSafeSingleton getInstance(){
            if(instance == null) instance = new ThreadSafeSingleton();
            return instance;
        }
    }

    static class DoubleLockedSingleton{
        public static volatile DoubleLockedSingleton instance;

        private DoubleLockedSingleton(){};

        public static  DoubleLockedSingleton getInstance(){
            //first check not synchronized.
            if(instance  == null){
                synchronized (DoubleLockedSingleton.class){
                    //second check - multi thread environment - it may happen that
                    // many thread would have passed the first check.
                    if(instance == null) instance = new DoubleLockedSingleton();
                }
            }

            return instance;

        }
    }

    static class EagerSingleton{
        public static EagerSingleton instance = new EagerSingleton();

        private EagerSingleton(){};

        public static  EagerSingleton getInstance(){
            return instance;

        }
    }

    static class BillPughSingleton{

        private BillPughSingleton(){};

        /**
         * we have a static helper class which will be triggered the first time
         * get instance is called.
        **/
        private static class SingletonHelper{
            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        }

        public static  BillPughSingleton getInstance(){
            return SingletonHelper.INSTANCE;

        }
    }

    static class StaticBlockSingleton{
        public static StaticBlockSingleton instance;

        private StaticBlockSingleton(){};

        /**
         * Static block will be executed when the class is loaded by the JVM.
         * */
        static {
            try{
                if(instance == null) instance = new StaticBlockSingleton();
            } catch (Exception e){
                throw new RuntimeException("Exception occured.");
            }
        }

        public static  StaticBlockSingleton getInstance(){
            return instance;
        }
    }

    enum EnumSingleton {
        /**
         * Best method to create singleton class. Because Java always create one single
         * instance of Enum Classes.
         * */
        INSTANCE;

        public void doSomething(){
            // add any singleton logic.
        }
    }
}
