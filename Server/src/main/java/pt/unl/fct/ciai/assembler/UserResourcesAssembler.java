package pt.unl.fct.ciai.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import pt.unl.fct.ciai.controller.ProposalsController;
import pt.unl.fct.ciai.controller.RootController;
import pt.unl.fct.ciai.controller.UsersController;
import pt.unl.fct.ciai.model.Employee;
import pt.unl.fct.ciai.model.Proposal;
import pt.unl.fct.ciai.model.User;

@Component
public class UserResourcesAssembler implements ResourceAssembler<User, Resource<User>>,
		ResourcesAssembler<User, Resource<User>>,
		SubResourceAssembler<User, Proposal, Resource<User>>,
		SubResourcesAssembler<User, Proposal, Resource<User>> {

	@Override
	public Resource<User> toResource(User user) {
		long id = user.getId();
		return new Resource<>(user,
				linkTo(methodOn(UsersController.class).getUser(id)).withSelfRel(),
				linkTo(methodOn(UsersController.class).getUsers("")).withRel("users"),
				linkTo(methodOn(UsersController.class).getProposals(id, "")).withRel("proposals"),
				linkTo(methodOn(UsersController.class).getBids(id, "")).withRel("bids"));
	}

	@Override
	public Resources<Resource<User>> toResources(Iterable<? extends User> entities) {
		List<Resource<User>> resources = 
				StreamSupport.stream(entities.spliterator(), false)
				.map(this::toResource)
				.collect(Collectors.toList());
		return new Resources<>(resources,
				linkTo(methodOn(UsersController.class).getUsers("")).withSelfRel(),
				linkTo(methodOn(RootController.class).root()).withRel("root"));
	}

	@Override
	public Resource<User> toResource(User user, Proposal proposal) {
		long pid = proposal.getId();
		long uid = user.getId();
		Resource<User> resource = new Resource<>(user,
				linkTo(methodOn(UsersController.class).getProposals(uid, "")).withRel("proposals"),
				linkTo(methodOn(UsersController.class).getBids(uid, "")).withRel("bids"));
		return resource;
	}

	@Override
	public Resources<Resource<User>> toResources(Iterable<? extends User> entities, Proposal proposal) {
		long pid = proposal.getId();
		List<Resource<User>> users =
				StreamSupport.stream(entities.spliterator(), false)
				.map(user -> this.toResource(user, proposal))
				.collect(Collectors.toList());
		return new Resources<>(users,
				linkTo(methodOn(ProposalsController.class).getReviewBids(pid, "")).withRel("bids"),
				linkTo(methodOn(ProposalsController.class).getReviewers(pid, "")).withRel("reviewers"),
				linkTo(methodOn(RootController.class).root()).withRel("root"));
	}
	
}
