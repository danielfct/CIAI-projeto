package pt.unl.fct.ciai.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(CanReadSection.Condition)
public @interface CanReadSection {
    String Condition = "@securityService.isProposalApproved(#id) or "
    		+ "@securityService.isPartOfProposal(principal, #id) or "
    		+ "@securityService.isAdminOfAuthorOfProposal(principal, #id) or "
    		+ "hasRole(T(pt.unl.fct.ciai.model.User.Role).ROLE_SYS_ADMIN.name())";
}
