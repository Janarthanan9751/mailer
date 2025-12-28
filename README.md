# Java API Service Starter

This is a minimal Java API service starter based on [Google Cloud Run Quickstart](https://cloud.google.com/run/docs/quickstarts/build-and-deploy/deploy-java-service).

## Getting Started

Server should run automatically when starting a workspace. To run manually, run:
```sh
mvn spring-boot:run
```

Or if Maven is not in your PATH:
```sh
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## Gmail API Setup

This application uses Gmail API to send emails. You need to configure OAuth credentials:

### Step 1: Get OAuth Refresh Token

You have two options:

#### Option A: Using TokenGenerator Utility (Recommended)
1. Run the `TokenGenerator` class:
   ```sh
   java -cp target/classes com.example.demo.util.TokenGenerator
   ```
2. A browser window will open asking you to authorize the application
3. After authorization, copy the refresh token displayed in the console
4. Update `src/main/resources/application.properties` with the refresh token

#### Option B: Using Google OAuth 2.0 Playground
1. Go to https://developers.google.com/oauthplayground/
2. Click the gear icon (⚙️) in the top right
3. Check "Use your own OAuth credentials"
4. Enter your Client ID and Client Secret from `application.properties`
5. In the left panel, find "Gmail API v1" and select `https://www.googleapis.com/auth/gmail.send`
6. Click "Authorize APIs" and complete the authorization
7. Click "Exchange authorization code for tokens"
8. Copy the "Refresh token" value
9. Update `src/main/resources/application.properties` with the refresh token

### Step 2: Update application.properties

Replace the placeholder in `src/main/resources/application.properties`:
```
GMAIL_REFRESH_TOKEN=your-actual-refresh-token-here
```

### Step 3: Restart the Application

After updating the refresh token, restart the application for changes to take effect.

## API Endpoints

- `GET /` - Hello world endpoint
- `POST /api/send-resume` - Send resume email
  - Request body: `{"email": "recipient@example.com"}`