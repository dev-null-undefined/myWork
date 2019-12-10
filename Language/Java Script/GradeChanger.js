function changeGrades(toGrades="&#128512") {
  document.getElementsByTagName("main")[0].firstElementChild.style.backgroundImage="url(https://www.trzcacak.rs/myfile/detail/3-33517_15-smiley-face-png-for-free-on-mbtskoudsalg.png)";
  document.getElementsByClassName('score')[0].style.backgroundColor="rgba(255,255,255,0.01)";
  let elemtens=document.getElementsByTagName("td");
  for(let item of elemtens){
    item.style.backgroundColor="rgba(255,255,255,0.01)";
  }
  elemtens=document.getElementsByTagName("th");
  for(let item of elemtens){
    item.style.backgroundColor="rgba(255,255,255,0.01)";
  }
  for (let i = 1; i <= 5; i++) {
    if (i != toGrades) {
      console.log(i);
      console.log(document.getElementsByClassName(`scoreValue${i}`));
      while (document.getElementsByClassName(`scoreValue${i}`).length > 0) {
        document.getElementsByClassName(`scoreValue${i}`)[0].innerHTML = document
          .getElementsByClassName(`scoreValue${i}`)[0]
          .innerHTML.replace(`value\">${i}`, `value\">${toGrades}`);

        document.getElementsByClassName(`scoreValue${i}`)[0].outerHTML = document
          .getElementsByClassName(`scoreValue${i}`)[0]
          .outerHTML.replace(`scoreValue${i}`, `scoreValue${toGrades}`);
      }
    }
  }
}