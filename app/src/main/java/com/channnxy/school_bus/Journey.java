package com.channnxy.school_bus;

import java.util.ArrayList;

public class Journey {
    public String origin; //出发点
    public String destination; //目的地
    public String start_place; //上车地点
    public String start_time; //上车时间

    public Journey(String origin,String destination,String start_place,String start_time){
        this.origin = origin;
        this.destination = destination;
        this.start_place = start_place;
        this.start_time = start_time;
    }

    private static String[] originArray = {"小和山","小和山","小和山","小和山"};
    private static String[] destinationArray = {"安吉校区","安吉校区","安吉校区","安吉校区"};
    private static String[] start_placeArray = {"西和食堂门口","西和食堂门口","西和食堂门口","西和食堂门口"};
    private static String[] start_timeArray = {"2019年7月7日18：30","2019年7月7日18：30","2019年7月7日18：30","2019年7月7日18：30"};

    public static ArrayList<Journey> getDefaultData(){
        ArrayList<Journey> journeysList = new ArrayList<>();
        for(int i = 0;i<originArray.length;i++){
            journeysList.add(new Journey(originArray[i],destinationArray[i],start_placeArray[i],start_timeArray[i]));
        }
        return journeysList;
    }
}
