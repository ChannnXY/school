package com.channnxy.school_bus;

import java.util.ArrayList;

public class Train {
    public String originTime;
    public String originPlace;
    public String destinationTime;
    public String destinationPlace;
    public String license;
    public String seatForStudent;
    public String seatForTeacher;

    public Train(String originTime,String originPlace,String destinationPlace,String destinationTime,
                 String license,String seatForStudent,String seatForTeacher){
        this.originTime = originTime;
        this.originPlace = originPlace;
        this.destinationTime = destinationTime;
        this.destinationPlace = destinationPlace;
        this.license = license;
        this.seatForStudent = seatForStudent;
        this.seatForTeacher = seatForTeacher;
    }

    private static String[] originTimeArray = {"18:30","18:30","18:30","18:30","18:30"};
    private static String[] originPlaceArray = {"西和食堂","西和食堂","西和食堂","西和食堂"};
    private static String[] destinationTimeArray = {"20:30","20:30","20:30","20:30"};
    private static String[] destinationPlaceArray = {"科创楼北","科创楼北","科创楼北","科创楼北"};
    private static String[] licenseArray = {"浙A·28988","浙A·28988","浙A·28988","浙A·28988"};
    private static String[] seatForStudentArray = {"0","0","0","0"};
    private static String[] seatForTeacherArray = {"10","10","10","10"};

    public static ArrayList<Train> getDefaultData(){
        ArrayList<Train> trainArrayList = new ArrayList<>();
        for(int i = 0;i<originPlaceArray.length;i++){
            trainArrayList.add(new Train(originTimeArray[i],originPlaceArray[i],destinationPlaceArray[i],destinationTimeArray[i],
                    licenseArray[i],seatForStudentArray[i],seatForTeacherArray[i]));
        }
        for(int i = 0;i<originPlaceArray.length;i++){
            trainArrayList.add(new Train(originTimeArray[i],originPlaceArray[i],destinationPlaceArray[i],destinationTimeArray[i],
                    licenseArray[i],seatForStudentArray[i],seatForTeacherArray[i]));
        }
        return trainArrayList;
    }
}
