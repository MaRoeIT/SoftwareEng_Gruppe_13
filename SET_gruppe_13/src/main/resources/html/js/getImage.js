document.addEventListener('DOMContentLoaded', function() {
    // Update the fetch call to point to the Java API endpoint
    fetch('/api/products/images')
        .then(response => response.json())
        .then(images => {
            const categoryContainers = {};

            // Initialize category containers by filtering distinct categories and mapping them to their containers
            images
                .map(image => image.kategori.toLowerCase())
                .filter((category, index, self) => self.indexOf(category) === index)
                .forEach(category => {
                    categoryContainers[category] = document.querySelector(`#${category} .flex-container`);
                    if (categoryContainers[category]) {
                        categoryContainers[category].innerHTML = ''; // Clear existing content
                    }
                });

            // Use .filter() and .map() to process and create elements dynamically
            images
                .filter(image => categoryContainers[image.kategori.toLowerCase()])
                .map(image => {
                    const category = image.kategori.toLowerCase();
                    const container = categoryContainers[category];

                    // Create anchor tag and set its href attribute
                    const link = document.createElement('a');
                    link.href = `./pages/article.html?product_id=${image.produktId}`;

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

                    return { link, container };
                })
                .forEach(({ link, container }) => container.appendChild(link));
        })
        .catch(error => console.error('Error fetching images:', error));
});
