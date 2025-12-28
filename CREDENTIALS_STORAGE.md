# Where Credentials Are Stored

## Summary

Your Gmail API credentials (refresh token, client ID, client secret) are stored in different places depending on the environment:

## 1. **Local Development** (Your Computer)

**Location:** `src/main/resources/application-local.properties`

This file:
- ✅ Contains your actual credentials
- ✅ Is **gitignored** (not committed to GitHub)
- ✅ Only exists on your local machine
- ✅ Automatically loaded by Spring Boot if it exists

**File contents:**
```properties
gmail.client.id=89909255216-qk0te5fakm5l0svs40hcau4o4bpo1q86.apps.googleusercontent.com
gmail.client.secret=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
GMAIL_REFRESH_TOKEN=1//04L8gaFu7zL99CgYIARAAGAQSNwF-L9IrcmWhOzm1JbfmAP-508k8NspMzDrafJnlS05LI-7bW3QfMsC0UWUC3I3P40ntbENE1qE
```

## 2. **Render Deployment** (Production)

**Location:** Render Dashboard → Environment Variables

Set these 3 environment variables in Render:

| Variable Name | Value |
|--------------|-------|
| `GMAIL_CLIENT_ID` | `89909255216-qk0te5fakm5l0svs40hcau4o4bpo1q86.apps.googleusercontent.com` |
| `GMAIL_CLIENT_SECRET` | `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` |
| `GMAIL_REFRESH_TOKEN` | `1//04L8gaFu7zL99CgYIARAAGAQSNwF-L9IrcmWhOzm1JbfmAP-508k8NspMzDrafJnlS05LI-7bW3QfMsC0UWUC3I3P40ntbENE1qE` |

**How to set in Render:**
1. Go to your Render dashboard
2. Select your service
3. Go to "Environment" tab
4. Click "Add Environment Variable"
5. Add each variable above

## 3. **Code Reference** (How It's Used)

**Location:** `src/main/java/com/example/demo/service/GmailService.java`

The code reads these values using Spring's `@Value` annotation:

```java
@Value("${GMAIL_REFRESH_TOKEN}")
private String refreshToken;

@Value("${gmail.client.id}")
private String clientId;

@Value("${gmail.client.secret}")
private String clientSecret;
```

## 4. **Configuration File** (Template)

**Location:** `src/main/resources/application.properties`

This file:
- ✅ Is committed to GitHub (safe, no secrets)
- ✅ Contains placeholders that reference environment variables
- ✅ Works for both local and production

**File contents:**
```properties
gmail.client.id=${GMAIL_CLIENT_ID}
gmail.client.secret=${GMAIL_CLIENT_SECRET}
GMAIL_REFRESH_TOKEN=${GMAIL_REFRESH_TOKEN}
```

## Security Notes

✅ **Safe:**
- `application.properties` (only has placeholders, no real values)
- Environment variables in Render (server-side only)
- `application-local.properties` (gitignored, local only)

❌ **Never commit:**
- Files with actual credentials
- `application-local.properties` (already gitignored)

## How It Works

1. **Local:** Spring Boot loads `application-local.properties` which overrides the placeholders
2. **Render:** Environment variables are injected at runtime, Spring Boot resolves the placeholders
3. **Code:** `@Value` annotations inject the resolved values into `GmailService`

