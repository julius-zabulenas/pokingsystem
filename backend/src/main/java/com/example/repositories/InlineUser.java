package com.example.repositories;

import com.example.models.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineUser", types = { User.class })
public interface InlineUser {

	Long getId();

	String getEmail();

	String getFirstName();

	String getLastName();

	String getCity();

	String getImage();
}
