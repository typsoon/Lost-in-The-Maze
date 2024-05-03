package com.bksgames.game.core;

import java.util.Objects;

public class SourceOfDamage {
    final Parameters parameters;
    final DamageType damageType;

    int getDamageValue() {
        switch (damageType) {
            case LASER: return parameters.laserDamage;

            case SWORD: return parameters.swordDamage;
        }

        throw new IllegalArgumentException("Unknown type of damage");
    }

    public DamageType getSource() { return damageType;}

    public enum DamageType {
        LASER, SWORD
    }

    SourceOfDamage(Parameters parameters, DamageType damageType) {
        this.parameters = Objects.requireNonNull(parameters);
        this.damageType = Objects.requireNonNull(damageType);
    }
}