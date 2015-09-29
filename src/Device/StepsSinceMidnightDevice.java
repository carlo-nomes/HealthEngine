package Device;

import vendorX.drivers.NoSignalException;
import vendorX.drivers.TypeXYZWatchDriver;

public class StepsSinceMidnightDevice implements IDevice {
    private static final String DEVICE_NAME = "Step Monitor (TypeXYZWatchDriver)";
    private static final String DEVICE_DESCRIPTION = "Monitors Steps taken since midnight";

    private TypeXYZWatchDriver stepsSinceMidnightDevice;

    @Override
    public void initialize() {
        stepsSinceMidnightDevice = new TypeXYZWatchDriver();
    }

    @Override
    public double getValue() throws DeviceException {
        try {
            return stepsSinceMidnightDevice.getStepsSinceMidnight();
        } catch (NoSignalException e) {
            throw new DeviceException("The XYZWatchdriver has no signal.: "+e.getMessage(), e);
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