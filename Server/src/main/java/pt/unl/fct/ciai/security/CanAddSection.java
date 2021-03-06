package pt.unl.fct.ciai.security;

import java.lang.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(CanAddSection.Condition)
public @interface CanAddSection {
    String Condition = "@securityService.isProposalNotRejected(#id) and "
    		+ "@securityService.isAuthorOfProposal(principal,#id)";
}
