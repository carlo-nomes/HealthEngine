package be.kdg.se3.health.device;

import vendorY.drv.GlcDrv;

/**
 * Created by NicolasH on 25/09/2015.
 */
public class GlucoseDevice implements IDevice {
    private GlcDrv glucose;
    private static final String NAME = "Glucose monitor";

    @Override
    public void initialize() {
        glucose = new GlcDrv();
    }

    @Override
    public double getValue() throws DeviceException {
        try {
            return glucose.get_glcXYZ_value();
        } catch (RuntimeException e) {
            throw new DeviceException("Unable to get a glucose reading",e);
        }

    }

    @Override
    public String getName() {
        return NAME;
    }
}
