package BohnGame;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class Ablauf {

    Map<Class,Class> ablaufPlan;

    public Phase nextPhase(Phase aktPhase, GameContext game) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class phase = ablaufPlan.get(aktPhase.getClass());
        return (Phase) phase.getConstructors()[0].newInstance(game);
    }

}
