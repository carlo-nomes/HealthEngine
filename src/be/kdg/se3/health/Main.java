package be.kdg.se3.health;

import be.kdg.se3.health.device.*;
import be.kdg.se3.health.engine.*;

import java.util.Scanner;

/**
 * Created by NicolasH on 25/09/2015.
 */
public class Main implements ParameterListener {
    private HealthController controller;
    private Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
        controller = new HealthController();
        boolean stopped = false;
        while (!stopped) {
            Parameter parameter = null;
            System.out.println("What value do you want to measure?");
            System.out.println("\t\t1) hearthbeat");
            System.out.println("\t\t2) stress");
            System.out.println("\t\t3) glucose");
            System.out.println("\t\t4) steps since midnight");
            int choice = scanner.nextInt();
            System.out.println("How often do you want to measure? Enter in seconds please: ");
            int often = scanner.nextInt() * 1000;
            System.out.println("What is the bottom(minimum) value level you don't want to cross: ");
            int minimum = scanner.nextInt();
            System.out.println("What is the top(maximum) value level you don't want to cross; ");
            int maximum = scanner.nextInt();
            System.out.println("After how many failed readings do you want to stop reading: ");
            int maxAmountErrors = scanner.nextInt();
            switch (choice) {
                case 1:
                    parameter = new Parameter(new HeartbeatDevice(), often,minimum,maximum,maxAmountErrors);
                    break;
                case 2:
                    parameter = new Parameter(new StressDevice(), often,minimum,maximum,maxAmountErrors);
                    break;
                case 3:
                    parameter = new Parameter(new GlucoseDevice(), often,minimum,maximum,maxAmountErrors);
                    break;
                case 4:
                    parameter = new Parameter(new StepsDevice(),often,minimum,maximum,maxAmountErrors);
                    break;
                default:
                    System.out.println("Please enter a number between 1 and 4.");
                    break;
            }
            if (parameter != null) {
                controller.addParameter(parameter);
                System.out.println("Do you want to measure other value(s)? y/n");
                if (scanner.next().equalsIgnoreCase("n")) {
                    stopped = true;
                }
            }
            controller.deleteListeners();
            controller.addListener(this);
        }
        controller.start();
    }


    @Override
    public void onMeasured(Measurement measurement) {
        System.out.println(measurement.toString());
    }

    @Override
    public void onAlert(Measurement measurement,Bound bound,double valueCrossed) {
        System.err.println(measurement.getType() + " " + bound + " value has been crossed with: " + valueCrossed);
    }

    @Override
    public void onError(Throwable t) {
        System.err.println("An error has occured: " + t.getMessage());
    }

    public static void main(String[] args) throws DeviceException {
        Main main = new Main();
    }
}
