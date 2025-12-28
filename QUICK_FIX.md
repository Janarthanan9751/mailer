# 🚀 Quick Fix: Get Your Refresh Token in 2 Minutes

## Step-by-Step Instructions

### 1. Open OAuth Playground
👉 **Go to:** https://developers.google.com/oauthplayground/

### 2. Configure Your Credentials
1. Click the **⚙️ gear icon** (top right)
2. ✅ Check **"Use your own OAuth credentials"**
3. Fill in:
   - **OAuth Client ID:** `89909255216-qk0te5fakm5l0svs40hcau4o4bpo1q86.apps.googleusercontent.com`
   - **OAuth Client secret:** `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`
4. Click **Close**

### 3. Select Gmail Scope
1. Scroll down in the left panel
2. Find **"Gmail API v1"** → Expand it
3. ✅ Check: **`https://www.googleapis.com/auth/gmail.send`**
4. Click **"Authorize APIs"** button

### 4. Authorize
1. Sign in with your Google account (the one you want to send emails from)
2. Click **"Allow"**

### 5. Get Your Token
1. Click **"Exchange authorization code for tokens"**
2. Look for **"refresh_token"** in the response
3. **Copy the entire refresh_token value** (it's a long string starting with `1//`)

### 6. Update Your Config
1. Open: `src/main/resources/application.properties`
2. Replace this line:
   ```
   GMAIL_REFRESH_TOKEN=your-refresh-token-here
   ```
   With:
   ```
   GMAIL_REFRESH_TOKEN=PASTE_YOUR_REFRESH_TOKEN_HERE
   ```
3. **Save the file**

### 7. Restart Your App
Restart your Spring Boot application and try again!

---

## ⚠️ Troubleshooting

**If you don't see a refresh_token:**
- You might have already authorized this app before
- Solution: Go to https://myaccount.google.com/permissions → Revoke access → Try again

**If you get "invalid_grant" error:**
- Make sure you copied the ENTIRE refresh_token (it's very long)
- Make sure there are no extra spaces before/after the token
- The token should start with `1//` or similar

