##  Profile and Active

```yaml
spring:
  profiles:
    # spring.profiles.active property is used to specify which profiles are active.
    # If multiple active profiles, last profile has precedence.
		active: 
		# If no profile, Use this profile
		default: 
```


## How to decide which profile is used on SpringBoot?
In Spring Boot, the process of deciding which profile to use is determined by the following factors:

### 1. Default Profile
If no profiles are specifically activated, then the `default` profile will be used.

### 2. Active Profiles
You can specify active profiles in several ways:

- In your `application.yaml` 
- As a command-line argument when starting your application.
- As an environment variable.
- Programmatically in your application’s code.

### 3. Profile Precedence
If multiple profiles are active, all of them are considered “active”.  \
The properties from all active profiles are loaded. If there are any conflicts, the profile listed **last will take precedence**.  \
This is because Spring Boot follows the general principle that later property sources override earlier ones.

### 4. Profile-specific Properties Files
Spring Boot allows you to have profile-specific properties files. \
For example, you can have application-dev.yaml for the dev profile and application-prod.yaml for the prod profile. \
When a specific profile is active, Spring Boot will load both the application.yaml (or .properties) and the profile-specific file (like application-dev.yaml). \
If there are any conflicts, the values from the profile-specific file will take precedence.


## Priority
CLI arguments > environment variables > @ActiveProfiles or application properties packaged inside jar  > default properties.