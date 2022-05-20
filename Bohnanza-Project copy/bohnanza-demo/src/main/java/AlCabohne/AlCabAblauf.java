package AlCabohne;

import BohnGame.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class AlCabAblauf {

    Map<Class,Class> ablaufPlan;

    public AlCabAblauf() {
        this.ablaufPlan  =  Map.of(PhaseSetup.class, PhaseAnbauen.class,
                PhaseAnbauen.class, PhaseAufdecken.class,
                PhaseAufdecken.class, PhaseNachziehen.class
                //TODO
        );
    }

    public Phase nextPhase(Phase aktPhase, GameContext game) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class phase = ablaufPlan.get(aktPhase.getClass());
        return (Phase) phase.getConstructors()[0].newInstance(game);
    }
}
