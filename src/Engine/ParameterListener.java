package Engine;

import java.util.Observable;
import java.util.Observer;

public interface ParameterListener {

    void OnMeasured(Measurement measurement);

    void OnAlert(Measurement measurement, double valueCrossed);

    void OnError(Throwable e);
}
