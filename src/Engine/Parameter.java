package Engine;

import Device.*;

import java.util.*;

public class Parameter implements Runnable {
    public static final long CHECK_FREQUENCY_EACH_SECOND = 1000;
    public static final long CHECK_FREQUENCY_EACH_TEN_SECONDS = 10000;
    public static final long CHECK_FREQUENCY_EACH_MINUTE = 60000;
    public static final long CHECK_FREQUENCY_EACH_TEN_MINUTES = 600000;
    public static final long CHECK_FREQUENCY_EACH_HALF_HOUR = 1800000;
    public static final long CHECK_FREQUENCY_EACH_HOUR = 1800000 * 2;
    public static final long CHECK_FREQUENCY_EACH_DAY = 1800000 * 2 * 24;

    Collection<ParameterListener> listeners;

    private long frequency;

    private Device device;
    private String valueType;

    private Measurement lastSuccesfullMeasurement;

    public Parameter(Device device, String type, long frequency) {
        this.device = device;
        device.initialize();
        listeners = new ArrayList<ParameterListener>();
        this.frequency = frequency;
    }

    public void addListener(ParameterListener listener) {
        listeners.add(listener);
    }

    private void getMeasurement() {
        try {
            lastSuccesfullMeasurement = new Measurement(new Date(), device.getValue(valueType), device.getDeviceName());
            for (ParameterListener listener : listeners) {
                listener.OnMeasured(lastSuccesfullMeasurement);
            }
        } catch (DeviceException e) {
            for (ParameterListener listener : listeners) {
                listener.OnError(new ParameterException("A Device Exception has occurred within " + device.getDeviceName() + ", with the message: \"" + e.getMessage() + "\"", e));
            }
        }
    }

    public Measurement getLastSuccesfullMeasurement() {
        return lastSuccesfullMeasurement;
    }

    @Override
    public void run() {
        while (true) {
            getMeasurement();
            try {
                Thread.sleep(frequency);
            } catch (InterruptedException e) {
                System.out.printf("Thread Interrupted. Exiting thread..");
                return;
            }
        }
    }
}
