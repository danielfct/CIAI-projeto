package pt.unl.fct.ciai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonSubTypes({
	@JsonSubTypes.Type(value = Employee.class)
})
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String first_name;
	private String last_name;
	private String username;
	private String email;
	private String role;
	@JsonIgnore
	private String password;

	@OneToMany(mappedBy="approver") // cascade?
	private Set<Proposal> proposalsToApprove;

	public User(){}

	public User(String first_name, String last_name, String username, String email, String role, String password) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.email = email;
		this.role = role;
		this.password = password;
	}

	public User addProposalToApprove(Proposal proposalToApprove) {
		if (this.proposalsToApprove == null) {
			this.proposalsToApprove = new HashSet<Proposal>();
		}
		this.proposalsToApprove.add(proposalToApprove);
		return this;
	}

	public User removeProposalToApprove(Proposal proposalToApprove) {
		this.proposalsToApprove.remove(proposalToApprove);
		return this;
	}

	public Set<Proposal> getProposalsToApprove(){
		return this.proposalsToApprove;
	}





	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}