package com.hashini.medicare.dto;

public class PrescriptionMedicineCreationDTO {

    private int medicineId;
    private String dose;
    private int frequency;
    private String frequencyText;
    private int duration;
    private String additionalInfo;
    private int quantity;

    public PrescriptionMedicineCreationDTO() {
    }

    public PrescriptionMedicineCreationDTO(int medicineId,
                                           String dose,
                                           int frequency,
                                           String frequencyText,
                                           int duration,
                                           String additionalInfo,
                                           int quantity) {
        this.medicineId = medicineId;
        this.dose = dose;
        this.frequency = frequency;
        this.frequencyText = frequencyText;
        this.duration = duration;
        this.additionalInfo = additionalInfo;
        this.quantity = quantity;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFrequencyText() {
        return frequencyText;
    }

    public void setFrequencyText(String frequencyText) {
        this.frequencyText = frequencyText;
    }
}
