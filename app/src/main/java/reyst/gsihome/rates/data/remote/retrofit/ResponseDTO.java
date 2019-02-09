package reyst.gsihome.rates.data.remote.retrofit;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false, name = "exchange")
public class ResponseDTO {

    @ElementList(inline = true)
    private List<CurrencyDTO> currency;

    public List<CurrencyDTO> getCurrency() {
        return currency;
    }

    public void setCurrency(List<CurrencyDTO> currency) {
        this.currency = currency;
    }
}
