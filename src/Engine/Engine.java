package Engine;

import java.util.*;

public class Engine {

    private Collection<Parameter> parameters;

    public Engine() {
        parameters = new ArrayList<Parameter>();
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public void addListener(ParameterListener listener) {
        for (Parameter para : parameters) {
            para.addListener(listener);
        }
    }

    public void start() {
        for (Parameter para : parameters) {
            new Thread(para).start();
        }
    }

}
