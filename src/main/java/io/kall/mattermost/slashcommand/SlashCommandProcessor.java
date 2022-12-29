package io.kall.mattermost.slashcommand;

import java.util.Objects;
import java.util.Optional;

public abstract class SlashCommandProcessor {
	
	public SlashCommandProcessor() {
	}
	
	/**
	 * Provides pre-configured slash command token. Processed parameters must contain an identical token.
	 * 
	 * @return
	 */
	protected abstract String getConfiguredToken();
	
	public SlashCommandResponse process(SlashCommandParams cmdParams) {
		try {
			validateToken(cmdParams.getToken());
			return runCommand(cmdParams);
		} catch (SlashCommandResponseException e) {
			return e.getResponse();
		}
	}
	
	protected void validateToken(String token) throws SlashCommandResponseException {
		// Check token matches configured token
		if (!Objects.equals(getConfiguredToken(), token)) {
			throw new SlashCommandResponseException(incorrectTokenResponse());
		}
	}
	
	protected SlashCommandResponse incorrectTokenResponse() {
		return SlashCommandResponse.builder()
				.text("Error: Slash command's token does not match configured token.")
				.build();
	}
	
	/** Exception which contains response (which probably explains error to slash command sender). */
	private static class SlashCommandResponseException extends Exception {
		private final SlashCommandResponse response;
		
		public SlashCommandResponseException(SlashCommandResponse response) {
			this.response = response;
		}
		
		public SlashCommandResponse getResponse() {
			return response;
		}
	}
	
	protected abstract SlashCommandResponse runCommand(SlashCommandParams params);
	
	/**
	 * Finds Nth word from command's text.
	 * 
	 * @param params Slash command parameters
	 * @param index 0-based index of word.
	 * @return Found word or empty optional.
	 */
	protected Optional<String> findCommandWord(SlashCommandParams params, int index) {
		return Optional.ofNullable(params.getText())
				.filter(Objects::nonNull)
				.map(s -> s.split(" "))
				.filter(array -> array.length > index)
				.map(array -> array[index]);
	}
}
