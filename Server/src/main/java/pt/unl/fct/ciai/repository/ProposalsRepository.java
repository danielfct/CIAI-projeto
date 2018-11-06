package pt.unl.fct.ciai.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.unl.fct.ciai.model.Proposal;
import pt.unl.fct.ciai.model.Section;

public interface ProposalsRepository extends CrudRepository<Proposal, Long> {

    @Query("SELECT p "
            + "FROM Proposal p "
            + "WHERE p.id LIKE CONCAT('%',:search,'%') "
            + "OR p.isApproved LIKE CONCAT('%',:search,'%') "
            + "OR p.date LIKE CONCAT('%',:search,'%') "
    )
    Iterable<Proposal> searchProposals(@Param(value = "search") String search);

    @Query("Select s "
            + "FROM Proposal p JOIN p.sections s "
            + "WHERE s.id LIKE CONCAT('%',:search,'%')"
            + "OR s.goals LIKE CONCAT('%',:search,'%')"
            + "OR s.material LIKE CONCAT('%',:search,'%')"
            + "OR s.workPlan LIKE CONCAT('%',:search,'%')"
            + "OR s.budget LIKE CONCAT('%',:search,'%')"
    )
    Iterable<Section> searchSections(@Param(value = "search") String search);
}
