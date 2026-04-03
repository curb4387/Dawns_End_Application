package edu.dccc.dawnsend;

public class Actions {
    private String attack;
    private int minDamage;
    private int maxDamage;

    public Actions(String attack, int minDamage, int maxDamage) {
        this.attack = attack;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString() {
        if (minDamage == maxDamage) {
            return "Actions\n{" +
                    attack + " (" + maxDamage + ")\n}";
        }
        return "Actions\n{" +
                attack + " (" + minDamage + " - " + maxDamage + ")\n}";
    }

    public String getAttack() {
        return attack;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }
}