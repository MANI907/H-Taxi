package htaxi;


public class GrabCancelled extends AbstractEvent {

    private Long id;

    public GrabCancelled(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }
}
