package CLI;

import Engine.*;
import Device.*;

import java.util.Scanner;

public class View implements ParameterListener {
    private Engine engine;
    private Scanner scanner;

    public static void main(String[] args) {
        View view = new View();
    }

    public View() {
        scanner = new Scanner(System.in);
        boolean cont = false;
        engine = new Engine();
        while (!cont) {
            Parameter parameter = null;

            System.out.println("Which value would you like to follow? \n");
            for (String key : Devices.DEVICES.keySet()) {
                System.out.print(key + ":\n");
                int i = 0;
                for (String valueType : Devices.DEVICES.get(key).getValueTypes()) {
                    System.out.printf("\t%d: %s\n", i, valueType);
                    i++;
                }
            }

            int choice = scanner.nextInt();
            System.out.printf("At what frequency do you want to measure? (Seconds): ");
            int freq = scanner.nextInt() * 1000;

            switch (choice) {
                case 1:
                    parameter = new Parameter(Devices.DEVICES.get(0).initialize(), Devices.DEVICES.get(0).getValueTypes()[0], freq);
                    break;
                case 2:
                    parameter = new Parameter(Devices.DEVICES.get(0).initialize(), Devices.DEVICES.get(0).getValueTypes()[1], freq);
                    break;
                case 3:
                    parameter = new Parameter(Devices.DEVICES.get(0).initialize(), Devices.DEVICES.get(0).getValueTypes()[2], freq);
                    break;
                case 4:
                    parameter = new Parameter(Devices.DEVICES.get(1).initialize(), Devices.DEVICES.get(0).getValueTypes()[0], freq);
                    break;
                default:
                    System.out.println("Please select a valid value.");
                    break;
            }

            if (parameter != null)
                engine.addParameter(parameter);

            System.out.printf("Add another value to follow? [Y/N] : ");
            if (scanner.next().toUpperCase().equals("N")) cont = true;
        }

        engine.addListener(this);
        engine.start();

    }

    @Override
    public void OnMeasured(Measurement measurement) {
        System.out.println(measurement.toString());
    }

    @Override
    public void OnAlert(Measurement measurement, double valueCrossed) {
        System.out.printf("WARNING: the value of " + valueCrossed + " has been crossed.");
    }

    @Override
    public void OnError(Throwable e) {
        System.out.println("An error occurred: " + e.getMessage());
    }
}
