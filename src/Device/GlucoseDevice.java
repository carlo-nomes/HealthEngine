package Device;

import vendorY.drv.GlcDrv;


public class GlucoseDevice implements IDevice {
    private static final String DEVICE_NAME = "Glucose Monitor (TypeXYZWatchDriver)";
    private static final String DEVICE_DESCRIPTION = "Monitors glucose";

    private GlcDrv glucoseDevice;

    @Override
    public void initialize() {
        glucoseDevice = new GlcDrv();
    }

    @Override
    public double getValue() throws DeviceException {
        try {
            return glucoseDevice.get_glcXYZ_value();
        } catch (RuntimeException e) {
            throw new DeviceException("The glucose device has failed to get a reading.", e);
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
