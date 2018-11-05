package com.example.tj.qq;

public class SampleModel {
    private String sampleText;

    public SampleModel(String sampleText) {
        this.sampleText = sampleText;
    }

    public void setSampleText(String sampleText) {
        this.sampleText = sampleText;
    }

    public String getSampleText() {
        return sampleText;
    }

    @Override
    public String toString() {
        return "SampleModel{" + "sampleText='" + sampleText + '\'' + '}';
    }
}
