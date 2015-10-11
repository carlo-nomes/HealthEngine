package be.kdg.se3.health.engine;

import be.kdg.se3.health.device.DeviceException;
import be.kdg.se3.health.device.IDevice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by NicolasH on 02/10/2015.
 */
public class Parameter implements Runnable {
    public static final long FREQ_EACH_SECOND = 1;
    public static final long FREQ_EACH_MINUTE = 60;
    public static final long FREQ_EACH_HOUR = 3600;

    private Collection<ParameterListener> listenerCollection;
    private long freq;
    private IDevice device;
    private Measurement lastMeasurement;

    private int min;
    private int max;
    private double value;
    private double valueCrossed;

    private int countErrors;
    private int maxAmountErrors;
    private boolean running= true;
    public Parameter(IDevice device, long freq, int min, int max, int maxAmountErrors) {
        this.device = device;
        this.freq = freq;
        this.min = min;
        this.max = max;
        this.maxAmountErrors = maxAmountErrors;
        device.initialize();
        countErrors = 0;
        listenerCollection = new ArrayList<>();

    }

    public Measurement getLastMeasurement() {
        return lastMeasurement;
    }

    public void addListener(ParameterListener l) {
        listenerCollection.add(l);
    }

    public void deleteListeners() {
        listenerCollection.clear();
    }

    private void getMeasurement() {
        try {
            value = device.getValue();
            lastMeasurement = new Measurement(new Date(), value, device.getName());
            countErrors=0;
            for (ParameterListener listener : listenerCollection) {
                listener.onMeasured(lastMeasurement);
                if (value < min) {
                    valueCrossed = min - value;
                    listener.onAlert(lastMeasurement, Bound.BOTTOM, valueCrossed);
                } else if (value > max) {
                    valueCrossed = value-max;
                    listener.onAlert(lastMeasurement,Bound.TOP,valueCrossed);
                }
            }
        } catch (DeviceException d) {
            for (ParameterListener listener : listenerCollection) {
                listener.onError(new MeasurementException("There was an error measuring the device", d));
                countErrors++;
            }
        }
    }
    @Override
    public void run() {
        while (running) {
            getMeasurement();
            try {
                if(countErrors==maxAmountErrors){
                    running = false;
                }
                Thread.sleep(freq);
            } catch (InterruptedException e) {
                System.err.println("Interruption of the thread has occurred.");
                return;
            }
        }

    }
}
