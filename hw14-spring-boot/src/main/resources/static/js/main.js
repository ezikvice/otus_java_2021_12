function getClientById() {
    const clientIdTextBox = document.getElementById('clientIdTextBox');
    const clientDataContainer = document.getElementById('clientDataContainer');
    const id = clientIdTextBox.value;
    fetch('api/clients/' + id)
        .then(response => response.json())
        .then(client => clientDataContainer.innerHTML = JSON.stringify(client));
}

function saveClient() {
    const clientName = document.getElementById('clientName').value;
    const clientAddress = document.getElementById('clientAddress').value;
    const clientPhones = document.getElementById('phoneInputs').children;
    let phones = [];
    for (const clientPhone of clientPhones) {
        phones.push({id: null, number: clientPhone.value})
    }
    const data = {
        id: null,
        name: clientName,
        address: {id: null, street: clientAddress},
        phones: phones
    }
    fetch('/api/clients', {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(client => showClientData(client, '#showClientModal', '#clientDataModal'));
}

function showClientData(clientJsonData, modalId, dataContainerId) {
    const clientName = clientJsonData.name;
    const clientAddress = clientJsonData.address.street;
    const savedClient = "ФИО: " + clientName + ", адрес: " + clientAddress;
    $(dataContainerId).html(savedClient);
    $(modalId).modal('show');
}

function addPhone() {
    let phonesContainer = document.getElementById('phoneInputs');
    let i = phonesContainer.children.length + 1;
    const phoneInput = document.createElement('input');
    phoneInput.className = 'phone ml-3';
    phoneInput.placeholder = "Телефон";
    phoneInput.id = 'clientPhone' + i;
    const deletePhoneButton = document.createElement('button');
    deletePhoneButton.className = 'btn btn-grey btn-sm';
    deletePhoneButton.id = 'deletePhoneBtn' + i;
    deletePhoneButton.type = 'button';
    deletePhoneButton.innerText = 'X';
    deletePhoneButton.addEventListener("click", function (e) {
        deletePhone(i);
    }, false);
    phonesContainer.appendChild(phoneInput);
    phonesContainer.appendChild(deletePhoneButton);
}

function deletePhone(phoneId) {
    document.getElementById('clientPhone' + phoneId).remove();
    document.getElementById('deletePhoneBtn' + phoneId).remove();
}