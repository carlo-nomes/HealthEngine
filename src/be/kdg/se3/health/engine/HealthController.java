package be.kdg.se3.health.engine;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by NicolasH on 25/09/2015.
 */
public class HealthController {
    private Collection<Parameter> parameters;

    public HealthController() {
        parameters = new ArrayList<>();
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public void addListener(ParameterListener listener) {
        for (Parameter p : parameters) {
            p.addListener(listener);
        }
    }
    public void deleteListeners(){
        for(Parameter p : parameters){
            p.deleteListeners();
        }
    }
    public void start() {
        for (Parameter p : parameters) {
           new Thread(p).start();
        }
    }
}
