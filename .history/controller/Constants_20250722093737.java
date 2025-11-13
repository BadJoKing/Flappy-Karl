package controller;

public class Constants {
    public static final int FRAMERATE = 60;
    public static final String workerPath = "Assets/Arbeiter-scaled.png";
    public static final String workerFlapPath = "Assets/Arbeiterhüpf-scaled.png";
    public static final String capitalistPath = "Assets/Stacheldrahtbessa-scaled.png";
    public static final String backgroundPath = "Assets/background.gif";
    public static final String barricadePath = "Assets/Barrikaden-scaled.png";
    public static final String bulletPath = "Assets/Fellball.png";

    //---player actions---
    //-- NOTE: changing these doesn't actually do anything, because everything that uses these just refers to the variable and not to the actual value
    //-- NOTE 2: actually if you just set some of them to the same value you might break a lot of stuff, but you wouldn't do that... right?
    public static final int JUMP = 0; // the action code for jumping
    public static final int ATK_01 = 1; // the action code for attacking
    public static final int LOSE = 2; //the action code for 


    //---capitalist movement constraints---
    public static final int min_height = 64;                        //how much capitalist should be visible at least?
    public static final int cap_gap = 200;                          //how far should top and bottom stay apart?
    public static final int min_top_cap_y = -800+min_height;        //the minimum y coordinate for the top capitalist
    public static final int max_top_cap_y = -min_height-cap_gap;    //the maximum y coordinate for the top capitalist
    public static final int cap_distance = 500;                     //how far should two pairs of capitalists stay apart?
    public static final int cap_speed = 5;
}
