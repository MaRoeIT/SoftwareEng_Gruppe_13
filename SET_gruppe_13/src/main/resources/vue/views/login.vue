<template id="template">
  <div class="container mt-5">
    <h2>Login</h2>
    <form @submit.prevent="submitLogin">
      <div class="mb-3">
        <label for="email" class="form-label">Email:</label>
        <input type="email" v-model="email" id="email" required class="form-control" />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password:</label>
        <input type="password" v-model="password" id="password" required class="form-control" />
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <div v-if="loginMessage" class="mt-3">{{ loginMessage }}</div>
  </div>
</template>

<script>
app.component("login", {
  template: "#template",
  data() {
    return {
      email: '',
      password: '',
      loginMessage: ''
    };
  },
  methods: {
    async submitLogin() {
      try {
        const response = await fetch('/api/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ email: this.email, password: this.password })
        });
        const result = await response.json();

        if (result) {
          this.loginMessage = 'Login successful!';
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