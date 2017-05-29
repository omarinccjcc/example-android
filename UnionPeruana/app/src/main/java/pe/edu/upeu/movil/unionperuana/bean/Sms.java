package pe.edu.upeu.movil.unionperuana.bean;

/**
 * Created by omar on 28/05/17.
 */

public class Sms {

    private Long id;
    private String numPhone;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumPhone() {
        return numPhone;
    }

    public void setNumPhone(String numPhone) {
        this.numPhone = numPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
