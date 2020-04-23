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
let currentFocus;
/**
 * Filters is array of filter that is object with param colomn and filter
 */
let urlParams = {
  filters: [],
  orderBy: [],
  orderByDesc: [],
  pageNumber: 0,
  pageSize: settings.pageSizeOptions[0],
  sqlTable: "PlayerData",
};
let htmlElements = {};
function createInteractiveTable(
  ajaxPage = "index.php",
  tableId = "myTable",
  slqTableInfoId = "info",
  sqlTable = "PlayerData"
) {
  settings.ajaxPage = ajaxPage;
  settings.tableId = tableId;
  settings.slqTableInfoId = slqTableInfoId;
  urlParams.sqlTable = sqlTable;
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
    if (rule.filters[i].filter.length != "") {
      rulesString +=
        "filter[" +
        encodeURI(rule.filters[i].colomn) +
        "]=" +
        encodeURI(rule.filters[i].filter) +
        "&";
    }
  }
  for (let i = 0; i < rule.orderBy.length; i++) {
    rulesString += "orderBy[]=" + encodeURI(rule.orderBy[i]) + "&";
  }
  for (let i = 0; i < rule.orderByDesc.length; i++) {
    rulesString += "orderByDesc[]=" + encodeURI(rule.orderByDesc[i]) + "&";
  }
  rulesString += "Table=" + encodeURI(rule.sqlTable) + "&";
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
  // Get data from async
  let parser = new DOMParser();
  response = parser.parseFromString(response, "text/html");
  let table = response.getElementById(settings.tableId);
  if (table) {
    removeOldTable();
  } else {
    return;
  }
  let tableInfo = response.getElementById(settings.slqTableInfoId);
  tableInfo = JSON.parse(tableInfo.innerHTML);
  let numberOfRows = tableInfo.count;
  let numberOfPages = Math.floor(numberOfRows / urlParams.pageSize) - 1;
  let tableWrapper = document.createElement("div");
  tableWrapper.className = "tableWrapper";
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
  if (currentFocus) {
    filterInput.value = currentFocus.filter;
  }
  filter.appendChild(filterInput);
  tableHead.appendChild(pageSizer);
  tableHead.appendChild(filter);
  tableWrapper.appendChild(tableHead);
  let head = table.getElementsByTagName("thead")[0];
  Array.from(head.getElementsByTagName("th")).forEach((element) => {
    let thSpan = document.createElement("span");
    thSpan.innerText = element.innerText;
    element.innerText = "";
    thSpan.onclick = () => focusHead(thSpan);
    if (currentFocus) {
      if (currentFocus.colomn == element.innerText) {
        thSpan.className = "active";
        currentFocus.element = thSpan;
      }
    }
    let arrows = document.createElement("span");
    arrows.className = "arrow";
    let arrowUp = document.createElement("div");
    if (urlParams.orderBy.find((element) => element == thSpan.innerText)) {
      arrowUp.className = "active";
    }
    arrowUp.innerText = "▲";
    arrows.appendChild(arrowUp);
    let arrowDown = document.createElement("div");
    if (urlParams.orderByDesc.find((element) => element == thSpan.innerText)) {
      arrowDown.className = "active";
    }
    arrowDown.innerText = "▼";
    arrows.appendChild(arrowDown);
    element.appendChild(thSpan);
    element.appendChild(arrows);
    arrows.onclick = () => togleOrderBy(element);
  });
  let tableHolder = document.createElement("div");
  tableHolder.className = "holder";
  tableHolder.appendChild(table);
  tableWrapper.appendChild(tableHolder);
  let tableFooter = document.createElement("div");
  tableFooter.className = "tableFooter";
  tableFooter.innerText =
    "Showing " +
    urlParams.pageSize * urlParams.pageNumber +
    " to " +
    Math.min(urlParams.pageSize * (urlParams.pageNumber + 1), numberOfRows) +
    " of " +
    numberOfRows +
    " entries";
  let pageSelect = document.createElement("span");
  pageSelect.className = "pageSelect";
  let buttonPrevious = document.createElement("a");
  buttonPrevious.id = "buttonPrevious";
  if (urlParams.pageNumber <= 0) {
    buttonPrevious.className = "disabled";
  } else {
    buttonPrevious.onclick = () => changePage(urlParams.pageNumber - 1);
  }
  buttonPrevious.innerText = "Previous";
  pageSelect.appendChild(buttonPrevious);
  let pages = document.createElement("span");
  pages.className = "pages";
  let page;
  if (urlParams.pageNumber < 4) {
    for (let i = 0; i < Math.min(numberOfPages, 5); i++) {
      page = document.createElement("a");
      page.innerText = i + 1;
      if (i == urlParams.pageNumber) {
        page.className = "current";
      } else {
        page.onclick = () => changePage(i);
      }
      pages.appendChild(page);
    }
  } else if (urlParams.pageNumber > numberOfPages - 5) {
    for (let i = 5; i > 0; i--) {
      page = document.createElement("a");
      page.innerText = numberOfPages - i + 1;
      if (numberOfPages - i == urlParams.pageNumber) {
        page.className = "current";
      } else {
        page.onclick = () => changePage(numberOfPages - i);
      }
      pages.appendChild(page);
    }
  } else {
    page = document.createElement("a");
    page.innerText = 1;
    page.onclick = () => changePage(1);
    pages.appendChild(page);
    page = document.createElement("a");
    page.className = "disabled";
    page.innerText = "...";
    pages.appendChild(page);
    page = document.createElement("a");
    page.innerText = urlParams.pageNumber + 1;
    page.className = "current";
    pages.appendChild(page);
    page = document.createElement("a");
    page.className = "disabled";
    page.innerText = "...";
    pages.appendChild(page);
    page = document.createElement("a");
    page.onclick = () => changePage(numberOfPages);
    page.innerText = numberOfPages;
    pages.appendChild(page);
  }
  pageSelect.appendChild(pages);
  let buttonNext = document.createElement("a");
  buttonNext.id = "buttonNext";
  if (urlParams.pageNumber >= numberOfPages) {
    buttonNext.className = "disabled";
  } else {
    buttonNext.onclick = () => changePage(urlParams.pageNumber + 1);
  }
  buttonNext.innerText = "Next";
  pageSelect.appendChild(buttonNext);
  tableFooter.appendChild(pageSelect);
  tableWrapper.appendChild(tableFooter);
  document.body.appendChild(tableWrapper);
  htmlElements = {};
  pageSizerSelect.onchange = onPageSizeChange;
  htmlElements.search = filterInput;
  filterInput.onchange = (event) => filterInputChange(event);
}
function filterInputChange(event) {
  if (currentFocus) {
    if (currentFocus.filter != event.target.value) {
      currentFocus.filter = event.target.value;
      loadTable(settings.ajaxPage + rulesToUrl(urlParams));
    }
  }
}
function onPageSizeChange() {
  if (urlParams.pageSize != pageSizer.value) {
    urlParams.pageSize = pageSizer.value;
    urlParams.pageNumber = 0;
    loadTable(settings.ajaxPage + rulesToUrl(urlParams));
  }
}
function changePage(newPageNumber) {
  if (newPageNumber != urlParams.pageNumber) {
    urlParams.pageNumber = newPageNumber;
    loadTable(settings.ajaxPage + rulesToUrl(urlParams));
  }
}
function focusHead(head) {
  headText = head.innerText;
  if (currentFocus) {
    if (currentFocus.colomn == headText) {
      return;
    }
  }
  head.className = "active";
  if (currentFocus) {
    currentFocus.element.className = "";
  }
  let filter = urlParams.filters.find((element) => element.colomn == headText);
  if (filter) {
    htmlElements.search.value = filter.filter;
  } else {
    htmlElements.search.value = "";
    filter = { colomn: headText, filter: "", element: head };
    urlParams.filters.push(filter);
  }
  currentFocus = filter;
}
function togleOrderBy(thead) {
  let text = thead.childNodes[0].innerText;
  let arrows = thead.childNodes[1];
  if (urlParams.orderBy.find((element) => element == text)) {
    arrows.childNodes[0].className = "active";
    arrows.childNodes[1].className = "";
    urlParams.orderByDesc.push(text);
    urlParams.orderBy = urlParams.orderBy.filter((element) => element != text);
    loadTable(settings.ajaxPage + rulesToUrl(urlParams));
  } else if (urlParams.orderByDesc.find((element) => element == text)) {
    arrows.childNodes[0].className = "";
    urlParams.orderByDesc = urlParams.orderByDesc.filter(
      (element) => element != text
    );
    loadTable(settings.ajaxPage + rulesToUrl(urlParams));
  } else {
    arrows.childNodes[0].className = "";
    arrows.childNodes[1].className = "active";
    urlParams.orderBy.push(text);
    urlParams.orderByDesc = urlParams.orderByDesc.filter(
      (element) => element != text
    );
    loadTable(settings.ajaxPage + rulesToUrl(urlParams));
  }
}
/**
 * Made in Czechia LULW
 * Martin Kos©
 */