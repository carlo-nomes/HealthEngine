package be.kdg.se3.health.engine;

/**
 * Created by NicolasH on 02/10/2015.
 */
public interface ParameterListener {
    void onMeasured(Measurement measurement);
    void onAlert(Measurement measurement,Bound bound,double valueCrossed);
    void onError(Throwable t);
}
