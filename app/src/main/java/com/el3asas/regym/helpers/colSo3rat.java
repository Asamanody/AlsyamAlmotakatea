package com.el3asas.regym.helpers;

public class colSo3rat {
    public int setBodyStatusForMens(int i, float weight, int height, int age){
        int BB=0,BMR;
        BMR= (int) (88.362+(13.397*weight)+(4.799*height)-(5.677*age));
        switch (i){
            case 1:
                BB= (int) (1.2*BMR);
                break;
            case 2:
                BB= (int) (1.375*BMR);
                break;
            case 3:
                BB= (int) (1.5*BMR);
                break;
        }
        return BB;
    }
    public int setBodyStatusForWomans(int i, float weight, int height, int age){

        int BB=0,BMR;
        BMR= (int) (447.593 + (9.247*weight) + (3.098*height) - (4.330*age));

        switch (i){
            case 1:
                BB= (int) (1.2*BMR);
                break;
            case 2:
                BB= (int) (1.375*BMR);
                break;
            case 3:
                BB= (int) (1.5*BMR);
                break;
        }
        return BB;
    }
}
