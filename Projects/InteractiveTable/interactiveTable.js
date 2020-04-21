/**
 * ajax Resoult will be html valid table and then number of total record inside div
 * Filtering is done using SQL select also sorting and paging
 */
let settings = {
  ajaxPage: "index.php",
  tableId: "myTable",
  slqTableInfoId: "info",
  pageSizeOptions: [10, 25, 50, 100, 250],
};
/**
 * Filters is array of filter that is object with param colomn and filter
 */
let urlParams = {
  filters: [],
  orderBy: [],
  orderByDesc: [],
  pageNumber: 0,
  pageSize: 0,
};
let htmlElements = {};
function createInteractiveTable(ajaxPage = "index.php", tableId = "myTable", slqTableInfoId = "info") {
  settings.ajaxPage = ajaxPage;
  settings.tableId = tableId;
  settings.slqTableInfoId = slqTableInfoId;
  loadTable(settings.ajaxPage + rulesToUrl(urlParams));
}

function loadTable(url) {
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      generateTable(this.response);
    }
  };
  xhttp.open("GET", url, true);
  xhttp.send();
}

function rulesToUrl(rule) {
  let rulesString = "?";
  for (let i = 0; i < rule.filters.length; i++) {
    rulesString += "filter[" + encodeURI(rule.filters[i].colomn) + "]=" + encodeURI(rule.filters[i].filter) + "&";
  }
  for (let i = 0; i < rule.orderBy.length; i++) {
    rulesString += "orderBy[]=" + encodeURI(rule.orderBy) + "&";
  }
  for (let i = 0; i < rule.orderByDesc.length; i++) {
    rulesString += "orderByDesc[]=" + encodeURI(rule.orderByDesc) + "&";
  }
  rulesString += "pageNumber=" + rule.pageNumber + "&";
  rulesString += "pageSize=" + rule.pageSize + "&";
  return rulesString.substring(0, rulesString.length - 1);
}

// ?filter[colomnName]=filterRule
// ?OrderBy[]=colomnName&OrderBy[]=colomnName2
// ?OrderByDesc[]=colomnName&OrderByDesc[]=colomnName2

function removeOldTable() {
  let oldTable = Array.from(document.getElementsByClassName("tableWrapper"));
  oldTable.forEach((elm) => elm.parentNode.removeChild(elm));
}
function generateTable(response) {
  removeOldTable();
  let tableWrapper = document.createElement("div");
  tableWrapper.className = "tableWrapper";
  let parser = new DOMParser();
  response = parser.parseFromString(response, "text/html");
  let table = response.getElementById(settings.tableId);
  let tableInfo = response.getElementById(settings.slqTableInfoId);
  tableInfo = JSON.parse(tableInfo.innerHTML);
  let numberOfRows = tableInfo.count;
  let tableHead = document.createElement("div");
  tableHead.className = "tableHead";
  let pageSizer = document.createElement("span");
  pageSizer.className = "pageSize";
  pageSizer.innerText = "Page size:";
  let pageSizerSelect = document.createElement("select");
  pageSizerSelect.name = "pageSize";
  pageSizerSelect.id = "pageSizer";
  settings.pageSizeOptions.forEach((element) => {
    let option = document.createElement("option");
    option.value = element;
    option.innerText = element;
    option.selected = element == urlParams.pageSize;
    pageSizerSelect.appendChild(option);
  });
  pageSizer.appendChild(pageSizerSelect);
  let filter = document.createElement("span");
  filter.className = "filter";
  filter.innerText = "Search:";
  let filterInput = document.createElement("input");
  filterInput.type = "text";
  filterInput.id = "filter";
  filter.appendChild(filterInput);
  tableHead.appendChild(pageSizer);
  tableHead.appendChild(filter);
  tableWrapper.appendChild(tableHead);
  let head = table.getElementsByTagName("thead")[0];
  Array.from(head.getElementsByTagName("th")).forEach((element) => {
    let arrows = document.createElement("span");
    arrows.className = "arrow";
    arrows.innerHTML = '<div class="up">▲</div>' + "\n" + '<div class="down">▼</div>' + "\n";
    element.innerHTML += arrows.outerHTML;
  });
  tableWrapper.appendChild(table);
  let tableFooter = document.createElement("div");
  tableFooter.className = "tableFooter";
  tableFooter.innerText =
    "Showing " + urlParams.pageSize * urlParams.pageNumber + " to " + urlParams.pageSize * (urlParams.pageNumber + 1) + " of " + numberOfRows + " entries";
  let pageSelect = document.createElement("span");
  pageSelect.className = "pageSelect";
  let buttonPrevious = document.createElement("a");
  buttonPrevious.id = "buttonPrevious";
  if (urlParams.pageNumber == 0) {
    buttonPrevious.className = "disabled";
  }
  buttonPrevious.innerText = "Previous";
  pageSelect.appendChild(buttonPrevious);
  let pages = document.createElement("span");
  pages.className = "pages";

  let page;
  if (urlParams.pageNumber < 5) {
    for (let i = 0; i < 5; i++) {
      page = document.createElement("a");
      page.innerText = i;
      if (i == urlParams.pageNumber) {
        page.className = "current";
      }
      pages.appendChild(page);
    }
  } else if (urlParams.pageNumber > numberOfRows - 5) {
    for (let i = 5; i > 0; i--) {
      page = document.createElement("a");
      page.innerText = numberOfRows - i;
      if (i == urlParams.pageNumber) {
        page.className = "current";
      }
      pages.appendChild(page);
    }
  } else {
    page = document.createElement("a");
    page.innerText = 1;
    pages.appendChild(page);
    page = document.createElement("a");
    page.className = "disabled";
    page.innerText = "...";
    pages.appendChild(page);

    page = document.createElement("a");
    page.innerText = urlParams.pageNumber;
    page.className = "current";
    pages.appendChild(page);

    page = document.createElement("a");
    page.className = "disabled";
    page.innerText = "...";
    pages.appendChild(page);
    page = document.createElement("a");
    page.innerText = numberOfRows;
    pages.appendChild(page);
  }
  pageSelect.appendChild(pages);
  let buttonNext = document.createElement("a");
  buttonNext.id = "buttonNext";
  if (urlParams.pageNumber >= Math.round(numberOfRows / urlParams.pageSize)) {
    buttonNext.className = "disabled";
  }
  buttonNext.innerText = "Next";
  pageSelect.appendChild(buttonNext);
  tableFooter.appendChild(pageSelect);
  tableWrapper.appendChild(tableFooter);
  document.body.appendChild(tableWrapper);
  htmlElements = {};
  pageSizerSelect.onchange = onPageSizeChange;
}
function onPageSizeChange() {
  if (urlParams.pageSize != pageSizer.value) {
    urlParams.pageSize = pageSizer.value;
    loadTable(settings.ajaxPage + rulesToUrl(urlParams));
  }
}
