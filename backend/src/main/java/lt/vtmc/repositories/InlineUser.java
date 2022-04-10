package lt.vtmc.repositories;

import org.springframework.data.rest.core.config.Projection;

import lt.vtmc.models.User;

@Projection(name = "inlineUser", types = { User.class })
public interface InlineUser {

	Long getId();

	String getEmail();

	String getFirstName();

	String getLastName();

	String getCity();

	String getImage();
}
