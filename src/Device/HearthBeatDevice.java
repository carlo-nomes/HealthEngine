package Device;

import vendorX.drivers.NoSignalException;
import vendorX.drivers.TypeXYZWatchDriver;

import java.io.IOException;


public class HearthbeatDevice implements IDevice {
    private static final String DEVICE_NAME = "Hearthbeat Monitor (TypeXYZWatchDriver)";
    private static final String DEVICE_DESCRIPTION = "Monitors hearthbeat";

    private   TypeXYZWatchDriver hearthBeatDevice;

    @Override
    public void initialize() {
        hearthBeatDevice = new TypeXYZWatchDriver();
    }

    @Override
    public double getValue() throws DeviceException{
        try {
            return hearthBeatDevice.getHeartbeat();
        } catch (IOException e) {
            throw new DeviceException("IO Exception thrown by TypeXYZWatchDriver: " + e.getMessage(),e);
        } catch (NoSignalException e) {
            throw new DeviceException("No signal exception thrown by TypeXYZWatchDriver: " + e.getMessage(),e);
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
