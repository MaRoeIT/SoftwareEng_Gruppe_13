document.addEventListener('DOMContentLoaded', function() {
    // Get the product ID from the URL parameters
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('product_id');

    if (productId) {
        // Fetch product details from the Java API
        fetch(`/api/products/details/${productId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(product => {

                document.querySelector('h2.sub-title-article').textContent = product.navn;
                // Populate the article sections with product data
                document.querySelector('#bilde .box-one').innerHTML = `
                    <img src="${product.bucketlink}" alt="Product Image">`;

                document.querySelector('#about .box-one').innerHTML = `
                    <p>${product.description}</p>
                `;

                document.querySelector('#specifications .box-one').innerHTML = `
                    <ul>
                        <li>Kategori: ${product.kategori}</li>
                        <li>Modell: ${product.modell}</li>
                        <li>Warranty: ${product.garantiMåneder} måneder</li>
                        <li>EAN: ${product.ean}</li>
                    </ul>
                `;

                if (product.avhengigAv) {
                    // Fetch details for the dependent product
                    fetch(`/api/products/details/${product.avhengigAv}`)
                        .then(response => {
                            if (!response.ok) {
                                throw new Error(`HTTP error! Status: ${response.status}`);
                            }
                            return response.json();
                        })
                        .then(dependentProduct => {
                            document.querySelector('#dependencies .box-two').innerHTML = `
                <a href="/pages/article.html?product_id=${dependentProduct.produktId}" class="dependency-link">
                    <img src="${dependentProduct.bucketlink}" alt="${dependentProduct.navn}" class="dependency-image" style="width: 25%;">
                    <figcaption>${dependentProduct.navn}</figcaption>
                </a>
            `;})
                .catch(error => {
                    console.error('Error fetching dependent product details:', error);
                    document.querySelector('#dependencies .box-two').innerHTML = `<p>Dependencies: None</p>`;
                });

                } else {
                    document.querySelector('#dependencies .box-two').innerHTML = `<p>Dependencies: None</p>`;
                }
            })
            .catch(error => console.error('Error fetching product details:', error));
    } else {
        console.error('No product ID found in the URL');
    }
});
