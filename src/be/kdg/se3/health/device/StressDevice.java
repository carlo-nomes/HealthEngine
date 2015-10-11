package be.kdg.se3.health.device;

import vendorX.drivers.TypeXYZWatchDriver;

/**
 * Created by NicolasH on 25/09/2015.
 */
public class StressDevice implements IDevice {
    private TypeXYZWatchDriver stress;
    private static final String NAME = "Stress monitor";

    @Override
    public void initialize() {
        stress = new TypeXYZWatchDriver();
    }

    @Override
    public double getValue() throws DeviceException {
        try {
            return stress.getStressLevel();
        } catch (Exception e) {
            throw new DeviceException("Unabel to get a stresslevel reading",e);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
