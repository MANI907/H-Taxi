package htaxi;


public class GrabRequestConfirmed extends AbstractEvent {

    private Long id;

    public GrabRequestConfirmed(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }
}
