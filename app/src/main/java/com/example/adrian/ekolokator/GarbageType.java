package com.example.adrian.ekolokator;

/**
 * Created by ADRIAN on 21-Jun-17.
 */

public enum GarbageType {


    Noise("Buke"),
    AbbandonedVehicle("Napuštenog vozila"),
    IllegalConstrucion("Bespravne gradnje"),
    IllegalWoodchopping("Bespravne sječe stabala"),
    IllegalSepticDumping("Ispuštanja sadržaja septičke jame"),
    WaterPollution("Onečišćenja vodenih površina"),
    AnimalAbuse("Zlostavljanja životinja"),
    GarbageBurning("Paljenja smeća"),
    CrumblingBuilding("Urušavajuće zgrade"),
    LightPollution("Svjetlosnog onečišćenja");

    private final String FriendlyName;

    GarbageType(final String friendlyName)
    {
        FriendlyName = friendlyName;
    }

    @Override
    public String toString()
    {
        return FriendlyName;
    }

}
