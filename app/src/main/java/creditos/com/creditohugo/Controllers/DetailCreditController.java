package creditos.com.creditohugo.Controllers;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */

public class DetailCreditController {

    private DetailCreditIterface mListener;

    public interface DetailCreditIterface {
    }

    public DetailCreditController() {
    }

    public DetailCreditController(DetailCreditIterface listener) {
        this.mListener = listener;
    }
}