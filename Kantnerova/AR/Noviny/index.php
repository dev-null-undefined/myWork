<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Newspaper</title>
  <link href="https://fonts.googleapis.com/css2?family=Charm&display=swap" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css2?family=Cinzel+Decorative:wght@900&display=swap" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
  <script src="scripts/app.js"></script>
  <link rel="stylesheet" href="style/style.css" />
</head>

<body>
  <header>
    <img src="media/hogwartsLogo.png" alt="" />
    <h1>hogwarts - Daily Prophet</h1>
    <h2 style="float:right">4</h2>
  </header>
  <main>
    <div class="title">
      <i class="fa fa-newspaper-o"></i>
      <span>News</span>
    </div>
    <article>
      <h3>Lorem ipsum dolor sit amet</h3>
      <span>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Architecto, consequuntur?
        <br />
        Esse ipsa dolorum sunt reiciendis! Est, iure non. Inventore, eligendi!
        <br />
        Assumenda, corporis! Ratione fuga ex suscipit, voluptate expedita aspernatur explicabo?
        <br />
        Temporibus at aliquam repudiandae sint nostrum? Distinctio ipsam laudantium dolor?
        <br />
        Iste doloremque consequuntur exercitationem minus, amet rem at architecto delectus.
        <br />
        Error quia ea laborum eum. Ducimus odit sequi ipsum vitae!
        <br />
        Id libero assumenda eum soluta possimus itaque, quas ullam quos.
        <br />
        Doloremque inventore architecto itaque fugit aut labore aspernatur, eligendi soluta?
        <br />
      </span>
      <img src="media/SirusBlack.gif" alt="" />
      <div class="timeStamp">31.6.1993|Barnabas Cuffe|Daily Prophet</div>
    </article>
    <article>
      <h3>Lorem ipsum dolor sit.</h3>
      <span>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatem, debitis placeat.
        <br />
        Error expedita sint doloremque sit nesciunt architecto reprehenderit ex, neque fuga?
        <br />
        A culpa eum quis aliquid libero illo nobis voluptate eos laudantium.
        <br />
        Nostrum, repellat fugiat? Perspiciatis quae deserunt magni maiores nesciunt, voluptatem consequuntur.
        <br />
        Necessitatibus sapiente asperiores recusandae corrupti adipisci perferendis facere nam ea inventore?
        <br />
        Dicta temporibus exercitationem rem laborum et itaque unde, consequuntur natus alias!
        <br />
        Architecto mollitia doloribus laudantium repudiandae? Debitis voluptate facere expedita unde cupiditate.
        <br />
      </span>
      <img src="media/SirusBlack.gif" alt="" />
      <div class="timeStamp">31.6.1993|Barnabas Cuffe|Daily Prophet</div>
    </article>
    <article>
      <h3>Lorem ipsum dolor sit.</h3>
      <span>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptatem, debitis placeat.
        <br />
        Error expedita sint doloremque sit nesciunt architecto reprehenderit ex, neque fuga?
        <br />
        A culpa eum quis aliquid libero illo nobis voluptate eos laudantium.
        <br />
        Nostrum, repellat fugiat? Perspiciatis quae deserunt magni maiores nesciunt, voluptatem consequuntur.
        <br />
        Necessitatibus sapiente asperiores recusandae corrupti adipisci perferendis facere nam ea inventore?
        <br />
        Dicta temporibus exercitationem rem laborum et itaque unde, consequuntur natus alias!
        <br />
        Architecto mollitia doloribus laudantium repudiandae? Debitis voluptate facere expedita unde cupiditate.
        <br />
      </span>
      <img src="media/SirusBlack.gif" alt="" />
      <div class="timeStamp">31.6.1993|Barnabas Cuffe|Daily Prophet</div>
    </article>
  </main>
  <aside>
    Archive
    <form onsubmit="return openArchive(event)">
      <select name="ArchiveNumber" id="archiveSelector">
        <?php
        $files = scandir('./archive/');
        $index = 0;
        foreach ($files as $file) {
          if (is_file('./archive/' . $file)) {
            $index++;
            printf('<option value="%s">%s</option>', $file, $index);
          }
        }
        ?>
      </select>
      <button type="submit" onclick="">Zobraz</button>
    </form>
  </aside>
  <footer>

    Střední průmyslová škola elektrotechnická, Praha 2, Ječná 30, Ječná 30, 120 00, Praha 2
    <br>
    Zřizovatel školy: Hlavní město Praha
    <br>
    Tel/Fax: 224 941 469 | E-mail: spsejecna@spsejecna.cz | www.spsejecna.cz | facebook.com/spsejecna
  </footer>
</body>

</html>