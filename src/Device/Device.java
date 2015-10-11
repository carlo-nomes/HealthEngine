package Device;

/**
 * Created by Carlo on 11/10/2015.
 */
public interface Device {
    Device initialize();

    double getValue(String type) throws DeviceException;

    String getDeviceName();

    String getDeviceDescription();

    String[] getValueTypes();
}
