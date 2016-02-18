package com.amaterasu12.raidenredux.Components;

import com.amaterasu12.raidenredux.Tools.Entities;
import com.badlogic.ashley.core.Component;

/**
 * Created by Carl on 2/18/2016.
 */
public class ProjectileComponent implements Component {
    public Entities.PROJECTILE_TYPE type;

    public ProjectileComponent(Entities.PROJECTILE_TYPE type) {
        this.type = type;
    }
}
