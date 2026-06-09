import { apiFetch } from "./api.js";
const STATUSES = {
  'Активен': 'status--active',
  'Черновик': 'status--draft',
  'На согласовании': 'status--approving',
  'Подписан': 'status--signed',
  'Завершен': 'status--finished',
};

let allContracts = [];

const createContractRow = ({id, title, date, status}) => {
  const contract = document.createElement('tr');
      contract.classList.add('contract');

      const contractId = document.createElement('td');
      contractId.classList.add('contract-id');
      contractId.textContent = id;

      const contractTitle = document.createElement('td');
      contractTitle.classList.add('contract-title');
      contractTitle.textContent = title;

      const contractDate = document.createElement('td');
      contractDate.classList.add('contract-date');
      contractDate.textContent = date;

      const contractStatus = document.createElement('td');
      contractStatus.classList.add('contract-status');

      const statusBadge = document.createElement('span');
      statusBadge.textContent = status;
      statusBadge.classList.add('status-badge', STATUSES[status] || 'status--unknown');
      contractStatus.appendChild(statusBadge);

      contract.append(contractId, contractTitle, contractDate, contractStatus);
      return contract;
}

async function loadContracts() {
  const loadingIndicator = document.querySelector('#loading-indicator');

  try {
    loadingIndicator.classList.remove('hidden');
    const tableBody = document.querySelector('#contracts-table-body');
    tableBody.innerHTML = '';

    const data = await apiFetch('/contracts', {
      method: 'GET',
    });
    if (data.length === 0) {
      const noContractsMessage = document.createElement('tr');
      const noContractsMessageTd = document.createElement('td');
      noContractsMessageTd.colSpan = 4;
      noContractsMessageTd.textContent = 'Договоров нет.';
      noContractsMessageTd.classList.add('no-contracts-message');
      noContractsMessage.appendChild(noContractsMessageTd);
      tableBody.appendChild(noContractsMessage);
      return;
    }
    allContracts = data;
    renderContracts(allContracts);

  } catch (error) {
    console.error(error);
    const errorEl = document.querySelector('#contracts-error');
    errorEl.textContent = error.message;
    errorEl.classList.remove('hidden');
  } finally {
    loadingIndicator.classList.add('hidden');
  }

}

function renderContracts(contracts) {
  const tableBody = document.querySelector('#contracts-table-body');
  tableBody.innerHTML = '';
  if (contracts.length === 0) {
    tableBody.innerHTML = '<tr><td colspan="4">Ничего не найдено. </td> </tr>';
    return;
  }
  contracts.forEach((contract) => {
  tableBody.appendChild(createContractRow(contract));
  });
}

loadContracts();

document.querySelector('#search-input').addEventListener('input', (e) => {
  const query = e.target.value;
  const filtered = allContracts.filter(c =>
    c.title.toLowerCase().includes(query.toLowerCase())
  );
  renderContracts(filtered);
})
