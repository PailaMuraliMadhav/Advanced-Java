package org.ex;

import java.util.Scanner;

import org.entity.Car;
import org.entity.DieselEngine;
import org.entity.Engine;
import org.entity.PetrolEngine;


public class main {
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("1. Diesel Engine");
	System.out.println("2. Petrol Engine");
	System.out.print("Enter choice: ");
	Byte choiceByte = sc.nextByte();
	//eager instantiation
//	Car car = new Car();
	Engine engine = null;
	switch (choiceByte) {
	case 1:
		//lazy instantiation
		engine = new DieselEngine();
		break;
	case 2:
		engine = new PetrolEngine();
		break;
	}
	//field injection
//	car.engine=engine;
//	car.engine.run();
//	System.out.println(car.engine.getClass());
	//setter injection
//	car.setEngine(engine);
//	car.getEngine().run();
//	System.out.println(car.getEngine().getClass());
	//Constructor engine
	Car car = new Car(engine);
	car.getEngine().run();
	System.out.println(car.getEngine().getClass());
	}	
}
