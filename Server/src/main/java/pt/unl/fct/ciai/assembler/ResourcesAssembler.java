package pt.unl.fct.ciai.assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

public interface ResourcesAssembler<T, D extends ResourceSupport> {

		Resources<D> toResources(Iterable<? extends T> entities);

}
