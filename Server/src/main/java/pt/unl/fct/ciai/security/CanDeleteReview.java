package pt.unl.fct.ciai.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(CanDeleteReview.Condition)
public @interface CanDeleteReview {
    String Condition = "@securityService.isAuthorOfReview(principal, #rid) or "
    		+ "@securityService.isAdminOfAuthorOfReview(principal, #rid) or "
    		+ "hasRole(T(pt.unl.fct.ciai.model.User.Role).ROLE_SYS_ADMIN.name())";
}
