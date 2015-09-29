package CLI;

import Device.GlucoseDevice;
import Device.HearthbeatDevice;
import Device.StepsSinceMidnightDevice;
import Device.StressLevelDevice;
import Engine.*;

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
            System.out.println("Which value would you like to follow? \n" +
                    "\t1: Glucose\n" +
                    "\t2: Hearthbeat\n" +
                    "\t3: Stress\n" +
                    "\t4: Steps");
            int choice = scanner.nextInt();
            System.out.printf("At what frequency do you want to measure? (Seconds): ");
            int freq = scanner.nextInt() * 1000;

            switch (choice) {
                case 1:
                    parameter = new Parameter(new GlucoseDevice(), freq);
                    break;
                case 2:
                    parameter = new Parameter(new HearthbeatDevice(), freq);
                    break;
                case 3:
                    parameter = new Parameter(new StressLevelDevice(), freq);
                    break;
                case 4:
                    parameter = new Parameter(new StepsSinceMidnightDevice(), freq);
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
        System.out.println("An error occured: " + e.getMessage());
    }
}
