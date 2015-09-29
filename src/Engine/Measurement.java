package Engine;

import java.util.Date;

public class Measurement {
    private final Date measurementDateTime;
    private final double value;
    private final String type;

    public Measurement(Date measurementDateTime, double value, String type) {
        this.measurementDateTime = measurementDateTime;
        this.value = value;
        this.type = type;
    }

    public Date getMeasurementDateTime() {
        return measurementDateTime;
    }

    public double getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return measurementDateTime + ": " + type + " = " + value;
    }
}
