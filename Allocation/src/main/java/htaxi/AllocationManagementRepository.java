package htaxi;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="allocationManagements", path="allocationManagements")
public interface AllocationManagementRepository extends PagingAndSortingRepository<AllocationManagement, Long>{


}
