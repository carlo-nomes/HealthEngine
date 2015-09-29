package Device;

public interface IDevice {

    void initialize();

    double getValue() throws DeviceException;

    String getName();

    String getDescription();
}
