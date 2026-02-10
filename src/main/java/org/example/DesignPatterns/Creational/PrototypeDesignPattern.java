package org.example.DesignPatterns.Creational;

import java.util.*;

/**
 * The Prototype Design Pattern is a creational
 * design pattern that lets you create new objects by
 * cloning existing ones,
 * instead of instantiating them from scratch.
 * ---
 * The Prototype Pattern allows you to create new
 * instances by cloning a pre-configured prototype object,
 * ensuring consistency
 * while reducing boilerplate and complexity.
 * ---
 *
 * */
public class PrototypeDesignPattern {

    /**
     * The Challenge of Cloning Objects
     * 1. Problem 1: Encapsulation Gets in the Way
     * 2. Problem 2:
     *          Class-Level Dependency - It violates the Open/Closed Principle.
     * 3. Problem 3: Interface-Only Contexts - In many cases, your code doesn’t work with concrete classes at all—it works with interfaces.
     * */

    /***
     * The Better Way: Let the Object Clone Itself
     * Instead of having external code copy or recreate the object,
     * the object itself knows how to create its clone
     *
     * Preserves encapsulation
     * Eliminates the need to know the concrete class
     * Makes the system more flexible and extensible
     */

    /**
     * The Problem: Spawning Enemies in a Game
     * ----
     * Let’s say you’re developing a 2D shooting game where enemies appear frequently throughout the gameplay.
     * BasicEnemy: Low health, slow speed — used in early levels.
     * ArmoredEnemy: High health, medium speed — harder to defeat, appears later.
     * FlyingEnemy: Medium health, fast speed — harder to hit, used for surprise attacks.
     *
     * Each enemy type comes with predefined properties such as:
     * Health (how much damage they can take)
     * Speed (how quickly they move across the screen)
     * Armor (whether they take reduced damage)
     * Weapon type (e.g., laser, cannon, missile)
     * Sprite or appearance (the visual representation)
     * */


    /**
     * The Prototype Design Pattern
     * Define a Prototype InterfaceThis declares the clone() method, which every cloneable object must implement.
     * Implement Concrete Prototypes
     * Client Requests a Clone
     * */

    public static class PrototypeDesignPatternImpl{
        interface EnemyPrototype{
            EnemyPrototype clone();
            EnemyPrototype cloneShallow() throws CloneNotSupportedException;
            EnemyPrototype cloneDeep();
        }


        public static class Enemy implements EnemyPrototype{
            private String type;
            private int health;
            private double speed;
            private boolean armored;
            private String weapon;

            public Enemy(String type, int health, double speed, boolean armored, String weapon) {
                this.type = type;
                this.health = health;
                this.speed = speed;
                this.armored = armored;
                this.weapon = weapon;
            }

            @Override
            public Enemy clone() {
                return new Enemy(type, health, speed, armored, weapon);
            }

            @Override
            public EnemyPrototype cloneShallow() throws CloneNotSupportedException {
                return (EnemyPrototype) super.clone();
            }

            @Override
            public EnemyPrototype cloneDeep() {
                return new Enemy(type, health, speed, armored, weapon);
            }

            @Override
            public String toString() {
                return "Enemy{" +
                        "type='" + type + '\'' +
                        ", health=" + health +
                        ", speed=" + speed +
                        ", armored=" + armored +
                        ", weapon='" + weapon + '\'' +
                        '}';
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setHealth(int health) {
                this.health = health;
            }

            public void setSpeed(double speed) {
                this.speed = speed;
            }

            public void setArmored(boolean armored) {
                this.armored = armored;
            }

            public void setWeapon(String weapon) {
                this.weapon = weapon;
            }
        }

        static class EnemyRegistry{
            private Map<String, Enemy> prototypes = new HashMap<>();


            public void register(String key, Enemy prototype) {
                prototypes.put(key, prototype);
            }

            public Enemy get(String key){
                Enemy prototype = prototypes.get(key);
                if (prototype != null) {
                    return prototype.clone();
                }
                throw new IllegalArgumentException("No prototype registered for: " + key);
            }

        }
    }

    public static void main(String[] args) {
        PrototypeDesignPatternImpl.Enemy  basicEnemy = new PrototypeDesignPatternImpl.Enemy("Basic",  10, 120.0, true, "Sword");
        PrototypeDesignPatternImpl.Enemy  armouredEnemy = new PrototypeDesignPatternImpl.Enemy("Armoured",  10, 120.0, true, "Sword");
        PrototypeDesignPatternImpl.Enemy  clonedBasicEnemy = basicEnemy.clone();
        System.out.println(basicEnemy);
        System.out.println(clonedBasicEnemy);

        List< PrototypeDesignPatternImpl.EnemyPrototype> list = new ArrayList<>();
        list.add(basicEnemy);
        list.add(armouredEnemy);

        List< PrototypeDesignPatternImpl.EnemyPrototype> inventory = new ArrayList<>();

        for(PrototypeDesignPatternImpl.EnemyPrototype enemyPrototype: list){
            inventory.add(enemyPrototype.clone());
        }


        for(PrototypeDesignPatternImpl.EnemyPrototype item: inventory){
            System.out.println(item);
        }

        PrototypeDesignPatternImpl.EnemyRegistry registry = new PrototypeDesignPatternImpl.EnemyRegistry();
        registry.register("Basic", basicEnemy);
        System.out.println("Item returned from Registry:::: " + registry.get("Basic"));
    }

    /**
     * Shallow Copy: This implementation performs a shallow copy.
     * It’s fine if all fields are primitives or immutable (like String).
     * But if Enemy had a field like a List, both the original and cloned
     * enemies would share the same list object, which can cause subtle bugs.
     * ----
     * Deep Copy: If your object contains mutable reference types,
     * you should create a deep copy in the copy constructor. For example:
     * this.inventory = new ArrayList<>();
     * for (Item item : source.inventory) {
     *     this.inventory.add(item.clone()); // Assuming Item also implements clone()
     * }
     * */

    /**
     * Shallow Copy ->
     * What it does
     *
     * Copies primitive values
     * Copies object references
     * Does not copy nested objects
     *
     * Result
     *      Two objects share the same referenced objects
     *
     * Deep Copy
     *
     * What it does
     * Copies primitives
     * Also copies all referenced mutable objects
     * Result
     * Completely independent object graph
     * */

}
