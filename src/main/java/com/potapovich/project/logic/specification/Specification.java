package com.potapovich.project.logic.specification;

import com.potapovich.project.entity.Taxi;

public interface Specification {

    boolean specified(Taxi taxi);
}
