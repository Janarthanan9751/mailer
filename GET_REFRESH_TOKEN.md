# How to Get Gmail API Refresh Token

Follow these steps to get your refresh token:

## Step 1: Go to OAuth 2.0 Playground
Open your browser and go to: **https://developers.google.com/oauthplayground/**

## Step 2: Configure OAuth Credentials
1. Click the **gear icon (⚙️)** in the top right corner
2. Check the box: **"Use your own OAuth credentials"**
3. Enter your credentials:
   - **OAuth Client ID**: `89909255216-qk0te5fakm5l0svs40hcau4o4bpo1q86.apps.googleusercontent.com`
   - **OAuth Client secret**: `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`
4. Click **Close**

## Step 3: Select Gmail API Scope
1. In the left panel, scroll down and find **"Gmail API v1"**
2. Expand it and check: **`https://www.googleapis.com/auth/gmail.send`**
3. Click **"Authorize APIs"** button

## Step 4: Authorize the Application
1. You'll be redirected to Google's authorization page
2. Select your Google account (the one you want to send emails from)
3. Click **"Allow"** to grant permissions
4. You'll be redirected back to the OAuth Playground

## Step 5: Exchange for Tokens
1. Click the **"Exchange authorization code for tokens"** button
2. You'll see a JSON response with tokens

## Step 6: Copy the Refresh Token
1. In the response, find the **"refresh_token"** field
2. Copy the entire value (it's a long string)
3. Example format: `1//0gXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX`

## Step 7: Update application.properties
1. Open `src/main/resources/application.properties`
2. Replace `your-refresh-token-here` with your actual refresh token:
   ```
   GMAIL_REFRESH_TOKEN=1//0gXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   ```
3. Save the file

## Step 8: Restart Your Application
Restart your Spring Boot application for the changes to take effect.

---

**Note**: If you don't see a refresh_token in the response, it might be because:
- You've already authorized this app before (revoke access and try again)
- The OAuth client is configured as "Internal" (refresh tokens are only provided on first authorization)

If this happens, you may need to revoke access first:
1. Go to https://myaccount.google.com/permissions
2. Find and revoke access for your OAuth client
3. Then repeat the steps above

