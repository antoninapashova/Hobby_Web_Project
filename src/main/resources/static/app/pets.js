document.getElementById('petsBtn').addEventListener('click', () => {
    fetch(`http://localhost:8080/pets`)
        .then(res => res.json())
        .then(data => data.forEach(el => {
            let div = document.getElementById('div');
            let divCard = document.createElement('div');
            divCard.classList.add('card')
            divCard.innerHTML = `<div class="col-sm-12 col-md-7 col-lg-5">
               <img src=${el.photo.url}  class="card-img-top" alt="Card image cap">
                         <div class="card-body">
                          <h5 class="card-user">${el.user.username}</h5>
                          <h5  class="card-title">${el.title}</h5>
                          <h5  class="card-title">${el.category.category}</h5>
                          <p class="card-text">${el.description}</p>
                    </div>
                </div>`;

            div.appendChild(divCard);
        }))
})
