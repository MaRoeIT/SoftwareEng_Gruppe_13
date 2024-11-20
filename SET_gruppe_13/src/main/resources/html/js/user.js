
  let user = null;      // Holds the fetched user data
  var loading = true;  // Shows loading state
  var error = null;     // Shows error message if the fetch fails
  const queryString = document.location.search;
  const params = new URLSearchParams(queryString);
  const id = params.get("id");
  console.log(id);

  if (!id) document.location.href = "login.html";

  fetchUserData(id);
  fetchUserProducts(id);

  // Function to fetch user data based on ID
  async function fetchUserData(userId) {
    try {
      const response = await fetch(`../../api/users/id/${userId}`);
      console.log(userId);
      if (!response.ok) {
        throw new Error('Failed to fetch user data');
      }

      let user = await response.json(); // Parse and store the JSON response
      console.log(user);

      document.getElementById("name").innerHTML += `${ user.firstName}  ${ user.lastName}`;
      document.getElementById("email").innerHTML += `${ user.email} `;
      document.getElementById("mobile").innerHTML += `${ user.mobile} `;
      document.getElementById("adresse").innerHTML += `${  user.address.adresse } `;
      document.getElementById("postnummer").innerHTML += `${  user.address.postnummer } `;

    } catch (error) {
      this.error = error.message;
    } finally {
      this.loading = false;
    }
  }

  async function fetchUserProducts(userId) {
    try {
      const response = await fetch(`../../api/products/user_id/${userId}`);

      if(!response) {
        throw new Error('Failed to fetch products based on userId');
      }

      let userProducts = await response.json();
      console.log(userProducts);

      document.getElementById("myProducts").innerHTML =  userProducts.map(e => {
        return `<div class="row">
          <div class="col-md-6">Produkt: <span>${e.navn}</span></div>
          <div class="col-md-6">Kategori: <span>${e.kategori}</span></div>
          </div>`;
      }).join("");

    } catch (error) {
      this.error = error.message; // Set error message if something goes wrong
    } finally {
      this.loading = false; // Stop the loading spinner
    }
  }

 function logout() {
    fetch('/api/authenticate/logout', {
        method: 'POST',
        credentials: 'include'
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            localStorage.removeItem('userId');
            window.location.href = '/pages/login.html';
        } else {
            alert(data.message);
        }
    })
    .catch(error => {
        console.error('Logout error:', error);
    });
}