# mattermost-services-support Project

## Use on other Quarkus projects

Include as dependency:

```xml
<dependency>
	<groupId>io.kall</groupId>
	<artifactId>mattermost-services-support</artifactId>
	<version>1.0.0-SNAPSHOT</version>
</dependency>
```

Implement the slash command endpoint:

```java
@Path("command")
public class CommandResource extends io.kall.mattermost.slashcommand.SlashCommandResource {
	// Override methods to customize
}
```

Provide a slash command processor:

```java
@ApplicationScoped
public class MyProcessor extends io.kall.mattermost.slashcommand.SlashCommandProcessor {
	
	@ConfigProperty(name = "slashcommand.token")
	String configuredToken;
	
	@Override
	protected String getConfiguredToken() {
		return configuredToken;
	}
	
	@Override
	protected SlashCommandResponse runCommand(SlashCommandParams params) {
		// Implement
	}
}
```
