package com.example.adrian.ekolokator;

import android.provider.ContactsContract;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ADRIAN on 21-Jun-17.
 */

public class ContactInfo
{
    static boolean initialized = false;
    static ContactData Noise = new ContactData();
    static ContactData Vehicle = new ContactData();
    static ContactData Construction = new ContactData();
    static ContactData Woodchopping = new ContactData();
    static ContactData Sepctic = new ContactData();
    static ContactData Water = new ContactData();
    static ContactData Animal = new ContactData();
    static ContactData Burning = new ContactData();
    static ContactData OldBuilding = new ContactData();
    static ContactData Light = new ContactData();

    static Map<GarbageType, ContactData> Info = new HashMap<>();

    public static Map<GarbageType, ContactData> getDictionary() {
        Init();
        return Info;
    }

    public static ContactData GetContactInfo(GarbageType TypeOfGarbage)
    {
        Init();
        return Info.get(TypeOfGarbage);
    }


    private static void InitNoise()
    {
        Noise.PolutionName = GarbageType.Noise.toString();
        Noise.Address = "Ministarstvo zdravlja, Sanitarna inspekcija, Ksaver 200a, 10000 Zagreb";
        Noise.Mail = "pitajtenas@miz.hr";
        Noise.PhoneNumber = "014607553";
    }

    private static void InitVehicle()
    {
        Vehicle.PolutionName = GarbageType.AbbandonedVehicle.toString();
        Vehicle.Address = "Komunalno redarstvo u vašem gradu ili prometna policija";
        Vehicle.Mail = "";
        Vehicle.PhoneNumber = "";
    }

    private static void InitConstruction()
    {
        Construction.PolutionName = GarbageType.IllegalConstrucion.toString();
        Construction.Address = "Ministarstvo graditeljstva i prostornog uređenja, Ulica Republike Austrije 20, 10000 Zagreb";
        Construction.Mail = "gradjevinska.inspekcija@mgipu.hr";
        Construction.PhoneNumber = "013712700";
    }

    private static void InitWoodchopping()
    {
        Woodchopping.PolutionName = GarbageType.IllegalWoodchopping.toString();
        Woodchopping.Address = "Ministarstvo poljoprivrede, Šumarska inspekcija, Ulica grada Vukovara 78, 10000 Zagreb";
        Woodchopping.Mail = "prijava@mps.hr";
        Woodchopping.PhoneNumber = "016106111";
    }

    private static void InitSeptic()
    {
        Sepctic.PolutionName = GarbageType.IllegalSepticDumping.toString();
        Sepctic.Address = "Ministarstvo zdravlja, Sanitarna inspekcija, Ksaver 200a, 10000 Zagreb";
        Sepctic.Mail = "pitajtenas@miz.hr";
        Sepctic.PhoneNumber = "014607553";
    }

    private static void InitWater()
    {
        Water.PolutionName = GarbageType.WaterPollution.toString();
        Water.Address = "Ministarstvo poljoprivrede, Vodopravna inspekcija, Ulica grada Vuovara 78, 10000 Zagreb";
        Water.Mail = "voda@voda.hr";
        Water.PhoneNumber = "016307333";
    }

    private static void InitAnimal()
    {
        Animal.PolutionName = GarbageType.AnimalAbuse.toString();
        Animal.Address = "Ministarstvo zaštite okoliša i prirode, Radnička cesta 80/3, 10000 Zagreb";
        Animal.Mail = "kresimir.ilic@mzop.hr";
        Animal.PhoneNumber = "014866193";
    }

    private static void InitBurning()
    {
        Burning.PolutionName = GarbageType.GarbageBurning.toString();
        Burning.Address = "Ministarstvo zaštite okoliša i prirode, Radnička cesta 80, 10000 Zagreb";
        Burning.Mail = "okolis.inspekcija@mzopu.hr";
        Burning.PhoneNumber = "013717111";
    }

    private static void InitOldBuilding()
    {
        OldBuilding.PolutionName = GarbageType.CrumblingBuilding.toString();
        OldBuilding.Address = "Ministarstvo graditeljstva i prostornog uređenja, Ulica Republike Austrije 20, 10000 Zagreb";
        OldBuilding.Mail = "gradjevinska.ubsoekcija@mgipu.hr";
        OldBuilding.PhoneNumber = "013712700";
    }

    private static void InitLight()
    {
        Light.PolutionName = GarbageType.LightPollution.toString();
        Light.Address = "Ministarstvo zaštite okoliša i prirode, Radnička cesta 80/3, 10000 Tagreb";
        Light.Mail = "kresimir.ilic@mzoip.hr";
        Light.PhoneNumber = "014866193";
    }

    private static void InitDictionary()
    {
        Info.put(GarbageType.Noise, Noise);
        Info.put(GarbageType.AbbandonedVehicle, Vehicle);
        Info.put(GarbageType.IllegalConstrucion, Construction);
        Info.put(GarbageType.IllegalWoodchopping, Woodchopping);
        Info.put(GarbageType.IllegalSepticDumping, Sepctic);
        Info.put(GarbageType.WaterPollution, Water);
        Info.put(GarbageType.AnimalAbuse, Animal);
        Info.put(GarbageType.GarbageBurning, Burning);
        Info.put(GarbageType.CrumblingBuilding, OldBuilding);
        Info.put(GarbageType.LightPollution, Light);
    }

    private static void Init()
    {
        if (initialized) return;
        initialized = true;

        InitNoise();
        InitVehicle();
        InitConstruction();
        InitWoodchopping();
        InitSeptic();
        InitWater();
        InitAnimal();
        InitBurning();
        InitOldBuilding();
        InitLight();
        InitDictionary();
    }



}
