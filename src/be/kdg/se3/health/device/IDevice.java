package be.kdg.se3.health.device;

/**
 * Created by NicolasH on 25/09/2015.
 */
public interface IDevice {
    void initialize();

    double getValue() throws DeviceException;

    String getName();
}
