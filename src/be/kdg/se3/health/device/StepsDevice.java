package be.kdg.se3.health.device;

import vendorX.drivers.TypeXYZWatchDriver;

/**
 * Created by NicolasH on 08/10/2015.
 */
public class StepsDevice implements IDevice {
    private TypeXYZWatchDriver steps;
    private static final String NAME = "Steps since midnight monitor";

    @Override
    public void initialize() {
        steps = new TypeXYZWatchDriver();
    }

    @Override
    public double getValue() throws DeviceException {
        try {
            return steps.getStepsSinceMidnight();
        } catch (Exception e) {
            throw new DeviceException("Unabel to get the requested steps since midnight reading",e);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
