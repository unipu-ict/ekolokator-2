package com.example.adrian.ekolokator;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ADRIAN on 22-Jun-17.
 */

public class ReportData
{
    public Uri Image;
    public GarbageType GType;
    public String FirstName;
    public String LastName;
    public String Mail;
    public String PhoneNumber;
    public String Comments;
    public LatLng Location;
    public  String LocationName;

    public ReportData()
    {
        GType = GarbageType.Noise;
    }
}
