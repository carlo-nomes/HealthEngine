package Device;

import vendorX.drivers.NoSignalException;
import vendorX.drivers.TypeXYZWatchDriver;

import java.io.IOException;

public class StressLevelDevice implements IDevice {
    private static final String DEVICE_NAME = "Stresslevel Monitor (TypeXYZWatchDriver)";
    private static final String DEVICE_DESCRIPTION = "Monitors stresslevel";

    private TypeXYZWatchDriver stressLevelDevice;

    @Override
    public void initialize() {
        stressLevelDevice = new TypeXYZWatchDriver();
    }

    @Override
    public double getValue() throws DeviceException {
        try {
           return stressLevelDevice.getStressLevel();
        } catch (NoSignalException e) {
            throw new DeviceException("No signal exception thrown by TypeXYZWatchDriver: " + e.getMessage(), e);
        }
    }

    @Override
    public String getName() {
        return DEVICE_NAME;
    }

    @Override
    public String getDescription() {
        return DEVICE_DESCRIPTION;
    }
}
