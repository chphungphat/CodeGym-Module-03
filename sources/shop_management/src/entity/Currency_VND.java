package entity;

import java.text.DecimalFormat;

public class Currency_VND {
    private static final DecimalFormat formatter = new DecimalFormat("#,###");

    private String value;

    public Currency_VND(long number) {
        this.value = formatter.format(number);
    }

    public Currency_VND() {}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(long number) {
        this.value = formatter.format(number);
    }

    public String toVND(long number) {
        return formatter.format(number);
    }

    @Override
    public String toString() {
        return value + " VND";
    }
}
