package htaxi;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="grabs", path="grabs")
public interface GrabRepository extends PagingAndSortingRepository<Grab, Long>{


}
