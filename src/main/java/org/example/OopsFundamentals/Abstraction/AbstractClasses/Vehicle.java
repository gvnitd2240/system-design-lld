package org.example.OopsFundamentals.Abstraction.AbstractClasses;

public abstract class Vehicle {
    String brand;

    Vehicle(String brand){
        this.brand = brand;
    }

    abstract void start();

    void displayBrand(){
        System.out.println(brand);
    }
}
