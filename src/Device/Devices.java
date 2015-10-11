package Device;

import vendorX.drivers.NoSignalException;
import vendorX.drivers.TypeXYZWatchDriver;
import vendorY.drv.GlcDrv;

import java.io.IOException;
import java.util.*;

/**
 * Created by Carlo on 11/10/2015.
 */
public abstract class Devices {
    public final static Map<String, Device> DEVICES;

    static {
        DEVICES = new HashMap<String, Device>();
        DEVICES.put("XYZWatchDriver", new XYZDevice());
        DEVICES.put("GlcDriver", new GlcDevice());
    }

}

class XYZDevice implements Device {
    private final static String DEVICE_NAME = "XYZWatchDriver";
    private final static String DEVICE_DESCRIPTION = "Device that monitors the wearers stress level, heartbeat and the number of steps taken since midnight";

    private final static String[] VALUE_TYPES = {"Stresslevel", "Heartbeat", "StepsSinceMidnight"};

    private TypeXYZWatchDriver driver;

    @Override
    public Device initialize() {
        driver = new TypeXYZWatchDriver();
        return this;
    }

    @Override
    public String getDeviceName() {
        return DEVICE_NAME;
    }

    @Override
    public String getDeviceDescription() {
        return DEVICE_DESCRIPTION;
    }

    @Override
    public String[] getValueTypes() {
        return VALUE_TYPES;
    }

    @Override
    public double getValue(String type) throws DeviceException {
        if (!Arrays.asList(VALUE_TYPES).contains(type))
            throw new DeviceException("Name \"" + type + "\" not contained within the value type list of class " + this.getClass() + ".");

        try {

            if (type.equals(VALUE_TYPES[0]))
                return driver.getStressLevel();
            else if (type.equals(VALUE_TYPES[1]))
                return driver.getHeartbeat();
            else if (type.equals(VALUE_TYPES[2]))
                return driver.getStepsSinceMidnight();
            else
                throw new DeviceException("The given value \"" + type + "\" was not retrieved  from the list of types.");
        } catch (NoSignalException e) {
            throw new DeviceException("A no signal exception occurred within the device (" + DEVICE_NAME + ") with the message: \"" + e.getMessage() + "\"", e);
        } catch (IOException e) {
            throw new DeviceException("An  IO exception occurred within the device (" + DEVICE_NAME + ") with the message: \"" + e.getMessage() + "\"", e);
        }
    }
}

class GlcDevice implements Device {
    private final static String DEVICE_NAME = "GlcDriver";
    private final static String DEVICE_DESCRIPTION = "Device that monitors the wearers glucose level.";

    private final static String[] VALUE_TYPES = {"Glucose"};

    private GlcDrv driver;

    @Override
    public Device initialize() {
        driver = new GlcDrv();
        return this;
    }

    @Override
    public String getDeviceName() {
        return DEVICE_NAME;
    }

    @Override
    public String getDeviceDescription() {
        return DEVICE_DESCRIPTION;
    }

    @Override
    public String[] getValueTypes() {
        return VALUE_TYPES;
    }

    @Override
    public double getValue(String type) throws DeviceException {
        if (!Arrays.asList(VALUE_TYPES).contains(type))
            throw new DeviceException("Name \"" + type + "\" not contained within the value type list of class " + this.getClass() + ".");

        try {

            if (type.equals(VALUE_TYPES[0]))
                return driver.get_glcXYZ_value();
            else
                throw new DeviceException("The given value \"" + type + "\" was not retrieved  from the list of types.");
        } catch (RuntimeException e) {
            throw new DeviceException("A runtime exception occurred within the device (" + DEVICE_NAME + ") with the message: \"" + e.getMessage() + "\"", e);
        }
    }
}