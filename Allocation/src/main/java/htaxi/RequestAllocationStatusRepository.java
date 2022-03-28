package htaxi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestAllocationStatusRepository extends CrudRepository<RequestAllocationStatus, Long> {


}