package reyst.gsihome.rates.data.remote.retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false, name = "currency")
public class CurrencyDTO {

    @Element(name = "r030")
    private int id;
    @Element(name = "txt")
    private String name;
    @Element(name = "rate")
    private double rate;
    @Element(name = "cc")
    private String code;
    @Element(name = "exchangedate")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
