package be.kdg.se3.health.engine;

import java.util.Date;

/**
 * Created by NicolasH on 02/10/2015.
 */
public class Measurement {
    private final Date measurementDateTime;
    private final double value;
    private final String type;

    protected Measurement(Date measurementDateTime,double value,String type){
        this.measurementDateTime = measurementDateTime;
        this.value = value;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return measurementDateTime+": "+type+" = "+value;
    }
}
