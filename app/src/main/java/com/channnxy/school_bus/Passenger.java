package com.channnxy.school_bus;

import java.util.ArrayList;

public class Passenger {
    public String name;
    // 0teacher 1student
    public int identity;
    // 0 未签到 1已签到
    public int state;

    private Passenger(String name,int identity,int state){
        this.name = name;
        this.identity = identity;
        this.state = state;
    }

    private static String[] nameArray={"王老师","王老师","王老师","王老师","陈同学","陈同学","陈同学","陈同学","陈同学","陈同学"};
    private static int[] identityArray={0,0,0,0,1,1,1,1,1,1};
    private static int[] stateArray={1,0,0,1,1,0,1,0,1,1};

    public static ArrayList<Passenger> defaultData(){
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int i = 0;i<nameArray.length;i++){
            passengers.add(new Passenger(nameArray[i],identityArray[i],stateArray[i]));
        }
        return passengers;
    }
}
