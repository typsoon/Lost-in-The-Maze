package com.bksgames.game.core.utils;

import java.util.Objects;

public class SourceOfDamage {
    final Parameters parameters;
    final DamageType damageType;

    int getDamageValue() {
        return switch (damageType) {
            case LASER -> parameters.laserDamage;
            case SWORD -> parameters.swordDamage;
        };

    }

    public DamageType getSource() { return damageType;}

    public enum DamageType {
        LASER, SWORD
    }

    public SourceOfDamage(Parameters parameters, DamageType damageType) {
        this.parameters = Objects.requireNonNull(parameters);
        this.damageType = Objects.requireNonNull(damageType);
    }
}