<template id="template">
  <div class="container mt-5">
    <h2>Login</h2>
    <form @submit.prevent="submitLogin">
      <div class="mb-3">
        <label for="epost" class="form-label">Email:</label>
        <input type="email" v-model="epost" id="epost" name="epost" required class="form-control" />
      </div>
      <div class="mb-3">
        <label for="passord" class="form-label">Password:</label>
        <input type="password" v-model="passord" id="passord" name="passord" required class="form-control" />
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <div v-if="loginMessage" class="mt-3">{{ loginMessage }}</div>

    <div>
      <h2>Login detaljer</h2>
      <h3>Epost: john.doe@example.com</h3>
      <h3>Passord: password123</h3>
      <h3>Epost: jane.smith@example.com</h3>
      <h3>Passord: password123</h3>
    </div>
  </div>
</template>

<script>
app.component("login", {
  template: "#template",
  data() {
    return {
      epost: '',
      passord: '',
      loginMessage: '',
      userId: -1
    };
  },
  methods: {
    redirectToHome() {
      window.location = `/user/${this.userId}`;
    },
    async submitLogin() {
      try {
        const response = await fetch('/api/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ epost: this.epost, passord: this.passord })
        });
        const result = await response.json();
        console.log(result);
        if (result.isAuthenticated && result.userId) {
          this.loginMessage = 'Login successful! Redirecting...';
          this.userId = result.userId;
          window.setTimeout(this.redirectToHome, 3000);
        } else {
          this.loginMessage = 'Invalid email or password.';
        }
      } catch (error) {
        console.error('Login error:', error);
        this.loginMessage = 'An error occurred. Please try again.';
      }
    }
  }
});
</script>
<style>
.header {
  color: red;
  font-size: 50px;
}
</style>