document.addEventListener('DOMContentLoaded', function() {
    fetch('getImage.php')
        .then(response => response.json())
        .then(images => {
            const categoryContainers = {};

            images.forEach(image => {
                // Use lowercase kategori values to match HTML id attributes (e.g., `electronics`, `security`)
                const category = image.kategori.toLowerCase();
                if (!categoryContainers[category]) {
                    categoryContainers[category] = document.querySelector(`#${category} .flex-container`);
                    if (categoryContainers[category]) {
                        categoryContainers[category].innerHTML = ''; // Clear existing content
                    }
                }

                if (categoryContainers[category]) {
                    // Create anchor tag and set its href attribute to link to article.html with product ID as a query parameter
                    const link = document.createElement('a');
                    link.href = `pages/article.html?product_id=${image.produkt_id}`;

                    // Create figure and img elements
                    const figure = document.createElement('figure');
                    figure.className = 'box-one';

                    const img = document.createElement('img');
                    img.src = image.bucketlink;
                    img.alt = `${image.kategori} image`;

                    const nameElement = document.createElement('p');
                    nameElement.setAttribute('itemprop', 'name');
                    nameElement.textContent = image.navn;

                    // Append img and name elements to figure
                    figure.appendChild(img);
                    figure.appendChild(nameElement);

                    // Append figure to the link
                    link.appendChild(figure);

                    // Append link to the category container
                    categoryContainers[category].appendChild(link);
                }
            });
        })
        .catch(error => console.error('Error fetching images:', error));
});
