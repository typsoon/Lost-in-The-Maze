package com.bksgames.game.core;

public class SourceOfDamage {
    final Parameters parameters;
    final Source source;

    int getDamageValue() {
        switch (source) {
            case LASER: return parameters.laserDamage;

            case SWORD: return parameters.swordDamage;
        }

        throw new IllegalArgumentException("Unknown source of damage");
    }

    private enum Source {
        LASER, SWORD
    }

    SourceOfDamage(Parameters parameters, Source source) {
        this.parameters = parameters;
        this.source = source;
    }
}