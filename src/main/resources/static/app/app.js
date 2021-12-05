const list = document.getElementById('showList')

document.getElementById("loadAll").addEventListener('click', () => {
    list.innerHTML = '';
    fetch(`http://localhost:8080/statistic/`)
        .then(res => res.json())
        .then(data => data.forEach(element => {
            let li = document.createElement('li');
            li.innerHTML = `<div id="cardForHobby" class="card col-sm-12 col-md-7 col-lg-7" >
               <img src = ${element.photo.url} width="200px" height="150px"></img> 
               <p class="card-text">${element.user.username}</p>
               <div class="card-body">
                       <h5 class="card-title">${element.title}</h5>
                        <p class="card-text">${element.description}</p>
                         <button id="deleteBtn" onclick="deleteFun(${element.id})" class="btn btn-outline-success btn-sm">Delete</button>
               </div>
            </div>`;
            list.appendChild(li);
        }))
});


document.getElementById('searchByUsernameBtn').addEventListener('click', () => {
    list.innerHTML = '';
    let user = document.getElementById('username');
    fetch(`http://localhost:8080/statistic/` + user.value)
        .then(res => res.json())
        .then(data => data.forEach(el => {
            let li = document.createElement('li');
            if (user.value === el.user.username) {
                li.innerHTML = `<div id="cardForHobby" class="card col-sm-12 col-md-7 col-lg-7">
                       <img src = ${el.photo.url} width="200px" height="150px"></img>
                     <p class="card-text">${el.user.username}</p>
                     <div class="card-body">
                       <h5 class="card-title">${el.title}</h5>
                        <p class="card-text">${el.description}</p>
                     <button id="deleteBtn" onclick="deleteFun(${el.id})" class="btn btn-outline-success btn-sm" name="delete">Delete</button>
                      </div>
                  </div>`;
                list.appendChild(li);
            }

        }))
});
document.getElementById('searchByUserBtn').addEventListener('click', () => {
    list.innerHTML = '';
    document.getElementById('div2').style.display='block';
    document.getElementById('h2').style.display='block';
    fetch(`http://localhost:8080/statistic/user`)
        .then(res => res.json())
        .then(data => data.forEach(user => {
            let body = document.getElementById('tBody');
            let tr = document.createElement('tr');
            tr.innerHTML = `
           <td>${user.id}</td>
           <td>${user.username}</td>
           <td>${user.firstName}</td>
           <td>${user.lastName}</td>
           <td>${user.email}</td>
           <td><button id="deleteBtn" onclick="deleteUser(${user.id})" class="btn btn-outline-success btn-sm" name="delete">Delete
           </button>
           </td>
           </tr>
       </tbody>
    </table>
   </div>`;
     body.appendChild(tr);
        }));
})

function deleteUser(id){
    fetch(`http://localhost:8080/statistic/user/` + id, {method: 'DELETE'}).then(err => alert(err));
}

function deleteFun(id) {
    fetch(`http://localhost:8080/statistic/` + id, {method: 'DELETE'}).then(err => alert(err));
}
