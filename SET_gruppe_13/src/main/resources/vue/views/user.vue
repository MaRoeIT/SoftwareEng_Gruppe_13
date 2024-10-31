<template id="template">
  <div>
    <h1>User Profile</h1>
    <p v-if="user">Name: {{ user.fornavn }} {{ user.etternavn}}</p>
    <p v-if="user">Email: {{ user.epost }}</p>
    <p v-if="user">Mobil: {{ user.mobil }}</p>
    <p v-if="user">Adresse: {{ user.address.adresse }}</p>
    <p v-if="user">Postnummer: {{ user.address.postnummer }}</p>
    <p v-if="error">{{ error }}</p>
    <p v-if="loading">Loading user data...</p>
  </div>
</template>

<script>
app.component("user", {
  template: "#template",
  data() {
    return {
      user: null,      // Holds the fetched user data
      loading: true,   // Shows loading state
      error: null      // Shows error message if the fetch fails
    };
  },
  mounted() {
    // Get user ID from the URL
    const userId = this.getUserIdFromUrl();
    console.log(userId);
    if (userId) {
      this.fetchUserData(userId); // Fetch user data with the extracted ID
    } else {
      this.error = "User ID not found in URL.";
      this.loading = false;
    }
  },
  methods: {
    // Function to extract user ID from the URL
    getUserIdFromUrl() {
      const url = new URL(window.location.href);
      const id = url.pathname.split("/").pop(); // Assuming URL format is `/api/user/1`
      return id ? id : null;
    },

    // Function to fetch user data based on ID
    async fetchUserData(userId) {
      try {
        const response = await fetch(`/api/user/${userId}`); // Use `userId` in the fetch URL
        if (!response.ok) {
          throw new Error('Failed to fetch user data');
        }
        this.user = await response.json(); // Parse and store the JSON response
      } catch (error) {
        this.error = error.message; // Set error message if something goes wrong
      } finally {
        this.loading = false; // Stop the loading spinner
      }
    }
  }
});
</script>
