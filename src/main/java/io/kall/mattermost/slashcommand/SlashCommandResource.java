package io.kall.mattermost.slashcommand;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Extend this resource with a Path-annotation to process incoming Mattermost Slash Commands. Provide also a
 * {@link SlashCommandProcessor} bean.
 */
public class SlashCommandResource {
	
	@Inject
	SlashCommandProcessor processor;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public SlashCommandResponse slashCommand(@BeanParam SlashCommandParams cmdParams) {
		return processor.process(cmdParams);
	}
}
