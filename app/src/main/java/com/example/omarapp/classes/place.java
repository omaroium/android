package com.example.omarapp.classes;

public class place
{
    private String Name;

    private double TimeOfTour;
     private String Date;
     private String HourOfStart;
    private int VisitNum;

    private int Pid;
    private int MaxVisit;
    private int CurrentVisit;
    private String Place;

    private double Price;
    private String [] Tools;


    protected byte[] imageByte;
    public place(int visitnum ,String Name,int Pid,double TimeOfTour,String Date,String HourOfStart,int MaxVisit,int CurrentVisit,String Place,double Price,String[] Tools
    ,byte[]imageByte) {
        this.VisitNum = visitnum;
        this.Name=Name;
        this.Pid=Pid;
        this.TimeOfTour=TimeOfTour;
        this.Date=Date;
        this.HourOfStart=HourOfStart;
        this.MaxVisit=MaxVisit;
        this.CurrentVisit=CurrentVisit;
        this.Place=Place;
        this.Price=Price;
        this.Tools=Tools;
        this.imageByte=imageByte;


    }


}
