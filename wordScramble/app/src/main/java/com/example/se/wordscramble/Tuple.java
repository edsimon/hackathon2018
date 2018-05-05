package com.example.se.wordscramble;

public class Tuple<String,Integer> {
    private String blockString;
    private Integer blockID;

    public Tuple(String s, Integer i){
        blockString = s;
        blockID = i;
    }


    public static boolean compareTuples(Tuple p1, Tuple p2){
        return p1.getBlockID() == p2.getBlockID();
    }

    public static boolean compareNameTouple(Tuple p1, Tuple p2){
        return p1.getBlockString().equals(p2.blockString);
    }

    public String getBlockString() {
        return blockString;
    }

    public void setBlockString(String blockString) {
        this.blockString = blockString;
    }

    public Integer getBlockID() {
        return blockID;
    }

    public void setBlockID(Integer blockID) {
        this.blockID = blockID;
    }
}
