package BohnGame;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BaseGameAblauf extends Ablauf{

    Map<Class,Class> ablaufPlan;

    public BaseGameAblauf() {
        this.ablaufPlan  =  Map.of( PhaseSetup.class, PhaseAnbauen.class,
                                    PhaseAnbauen.class, PhaseHandeln.class,
                                    PhaseHandeln.class, PhaseOffeneKarten.class,
                                    PhaseOffeneKarten.class, PhaseNachziehen.class,
                                    PhaseNachziehen.class, PhaseNichtAktiv.class,
                                    PhaseNichtAktiv.class, PhaseAnbauen.class
        );
    }

    public Phase nextPhase(Phase aktPhase, GameContext game) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class phase = ablaufPlan.get(aktPhase.getClass());
        return (Phase) phase.getConstructors()[0].newInstance(game);
    }
}
