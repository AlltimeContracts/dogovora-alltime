
async function loadContracts(){
const response = await fetch('http://localhost:3000/contracts', {
    method: 'GET',
    headers: {
      'Content-type': 'application/json'
    }
  });
  const data = await response.json();
  console.log('Получены договора:', data);
  const contractsList = document.querySelector('#contracts-list');
  data.forEach(({id, title, date, status}) => {
    const contract = document.createElement('li');
    contract.classList.add('contract');
    const contractId = document.createElement('span');
    contractId.classList.add('contract-id');
    contractId.textContent = id;
    const contractTitle = document.createElement('span');
    contractTitle.classList.add('contract-title');
    contractTitle.textContent = title;
    const contractDate = document.createElement('span');
    contractDate.classList.add('contract-date');
    contractDate.textContent = date;
    const contractStatus = document.createElement('span');
    contractStatus.classList.add('contract-status');
    contractStatus.textContent = status;
    contract.append(contractId, contractTitle, contractDate, contractStatus);
    contractsList.appendChild(contract)
  })
    
}
loadContracts();
