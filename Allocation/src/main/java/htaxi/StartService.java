package htaxi;


public class StartService extends AbstractEvent {

    private Long id;

    public StartService(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }
}
