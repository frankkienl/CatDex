package nl.frankkie.catdex.data

// In the ideal world, this would not be inside the APK, but on our own backend.
// And in that case;
// We could route all our calls through our backend, so only our backend would know this external API key,
// or we would provide this key to validated clients only (Google Play Integritey Verification).
// It would make it easier to rotate the key if needed.
// TODO: Hard-coded key
const val CAT_API_KEY = "live_7wfUrO4FBg8cKIZ2LPfakqre0oIc0pyuMvY4IRTCo42fF9di4iQ7mMKt2P9yKP1X"