package cn.edu.usst.cognitive.model;

import java.io.Serializable;

public class EvalContext implements Serializable {

    private String nums; // 生成的随机nums
    private String inputNums; // 患者输入的nums

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getInputNums() {
        return inputNums;
    }

    public void setInputNums(String inputNums) {
        this.inputNums = inputNums;
    }
}
