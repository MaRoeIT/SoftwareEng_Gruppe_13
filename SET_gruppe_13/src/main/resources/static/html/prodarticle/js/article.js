document.addEventListener('DOMContentLoaded', function () {
    const params = new URLSearchParams(window.location.search);
    const productId = params.get('product_id');

    if (productId) {
        fetch(`getProductDetails.php?product_id=${productId}`)
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    console.error(data.error);
                    return;
                }

                // Update the title tag
                document.title = data.navn;

                // Update the product name
                document.querySelector('.sub-title-article').textContent = data.navn;

                // Insert the product image
                const imgContainer = document.querySelector('#bilde .flex-container figure');
                imgContainer.innerHTML = `<img src="${data.bucketlink}" alt="${data.navn}">`;

                // Insert the product description
                const aboutContainer = document.querySelector('#about .flex-container figure');
                aboutContainer.innerHTML = `<p>${data.artikkel}</p>`;

                // Insert product specifications
                const specsContainer = document.querySelector('#specifications .flex-container figure');
                specsContainer.innerHTML = `
                    <p>Produktserie: ${data.produktserie}</p>
                    <p>Modell: ${data.modell}</p>
                    <p>Garanti (måneder): ${data.garanti_måneder}</p>
                    <p>EAN: ${data.EAN}</p>
                `;

                // Insert product dependencies, if any
                if (data.avhengigav) {
                    fetch(`getProductDetails.php?product_id=${data.avhengigav}`)
                        .then(response => response.json())
                        .then(dependency => {
                            if (dependency.error) {
                                console.error('Dependency not found:', dependency.error);
                                return;
                            }
                            const dependenciesContainer = document.querySelector('#dependencies .flex-container figure');
                            dependenciesContainer.innerHTML = `
                                <a href="article.html?product_id=${dependency.produkt_id}">
                                    <img src="${dependency.bucketlink}" alt="${dependency.navn}">
                                    <h4>${dependency.navn}</h4>
                                </a>
                            `;
                        })
                        .catch(error => console.error('Error fetching dependency:', error));
                }
            })
            .catch(error => console.error('Error fetching product data:', error));
    } else {
        console.error('No product ID found in the URL');
    }
});
