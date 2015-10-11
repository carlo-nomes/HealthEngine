package be.kdg.se3.health.device;

import vendorX.drivers.TypeXYZWatchDriver;

/**
 * Created by NicolasH on 25/09/2015.
 */
public class HeartbeatDevice implements IDevice {
    private TypeXYZWatchDriver hearthbeat;
    private static final String NAME = "Hearthbeat monitor";

    @Override
    public void initialize() {
        hearthbeat = new TypeXYZWatchDriver();
    }

    @Override
    public double getValue() throws DeviceException {
        try {
            return hearthbeat.getHeartbeat();
        } catch (Exception e) {
            throw new DeviceException("Unable to get a hearthbeat reading",e);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
